package cn.ralken.android.onlinesqlite.sample;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class manages to copy the .db file from assets to external sd card, so
 * that we can access the database for further use.
 * 
 * @author liaoralken
 * @version V1.0
 * @date Apr 7, 2016
 */
public class AssertsUtil {

	public static void copyAssertToExternalStorage(Context context, String srcFileName, String destPath) {
		AssetManager assetManager = context.getAssets();
		InputStream in = null;
		OutputStream out = null;
		try {
			in = assetManager.open(srcFileName);
			File outFile = new File(destPath, srcFileName);
			out = new FileOutputStream(outFile);
			copyFile(in, out);
		} catch (IOException e) {
			Log.e("tag", "Failed to copy asset file: " + srcFileName, e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// NOOP
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// NOOP
				}
			}
		}
	}

	private static void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}

}
