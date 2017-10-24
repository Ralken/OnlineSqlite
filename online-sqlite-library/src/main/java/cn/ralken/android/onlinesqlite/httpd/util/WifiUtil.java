package cn.ralken.android.onlinesqlite.httpd.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import java.io.IOException;
import java.net.InetAddress;

/** Copyright Ralken Liao

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 *   http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Ralken Liao
 * @date
 */
public class WifiUtil {

	/**
	 * Calculate the broadcast IP we need to send the packet along. If we send
	 * it to 255.255.255.255, it never gets sent. I guess this has something to
	 * do with the mobile network not wanting to do broadcast.
	 */
	public static InetAddress getBroadcastAddress(Context context) throws IOException {
		WifiManager wm = (WifiManager) context.getSystemService(Activity.WIFI_SERVICE);
		DhcpInfo dhcp = wm.getDhcpInfo();
		if (dhcp == null) {
			return null;
		}

		int broadcast = (dhcp.ipAddress & dhcp.netmask) | ~dhcp.netmask;
		byte[] quads = new byte[4];
		for (int k = 0; k < 4; k++)
			quads[k] = (byte) ((broadcast >> k * 8) & 0xFF);
		return InetAddress.getByAddress(quads);
	}

	public static String getIpAddress(Context context) {
		WifiManager wm = (WifiManager) context.getSystemService(Activity.WIFI_SERVICE);
		String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
		return ip;
	}

	/**
	 * Returns the service set identifier (SSID) of the current 802.11 network.
	 * If the SSID is an ASCII string, it will be returned surrounded by double
	 * quotation marks.Otherwise, it is returned as a string of hex digits. The
	 * SSID may be null if there is no network currently connected.
	 * 
	 * @param context
	 * @return the SSID
	 */
	public static String readWifiConnectionName(Context context) {
		if (isWifiConnected(context)) {
			final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
			if (connectionInfo != null && !(connectionInfo.getSSID().equals(""))) {
				// if (connectionInfo != null &&
				// !StringUtil.isBlank(connectionInfo.getSSID())) {
				return connectionInfo.getSSID();
			}
		}
		return null;
	}

	public static boolean isWifiConnected(Context context) {
		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return networkInfo != null && networkInfo.isConnected();
	}
}
