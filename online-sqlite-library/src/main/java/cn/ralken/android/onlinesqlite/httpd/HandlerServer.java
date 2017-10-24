package cn.ralken.android.onlinesqlite.httpd;

import android.content.Context;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.ralken.android.onlinesqlite.LightLogger;
import cn.ralken.android.onlinesqlite.OnlineSqlite;
import cn.ralken.android.onlinesqlite.browser.MIME;

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
 */

public class HandlerServer extends NanoHTTPD {

    public HandlerServer(int port) {
        super(port);
    }

    /***
     * https://stackoverflow.com/questions/17102954/why-images-and-style-files-couldnt-found-on-nanohttpd
     * @param session The HTTP session
     * @return
     */
    @Override
    public Response serve(IHTTPSession session) {
        String body = null;

        final String uri = session.getUri();

        Response.IStatus HTTP_OK = Response.Status.OK;
        Context mContext = OnlineSqlite.getInstance().getContext();

        try {
            InputStream allocateBuffer;

            final String targetDatabasePath = OnlineSqlite.getInstance().getOnlineConfig().getDatabasePath();

            LightLogger.d("Serve requested uri: " + uri);

            if (uri.contains(".js")) {
                allocateBuffer = mContext.getAssets().open(uri.substring(1));
                return new NanoHTTPD.Response(HTTP_OK, MIME.MIME_JS, allocateBuffer);
            } else if (uri.contains(".css")) {
                allocateBuffer = mContext.getAssets().open(uri.substring(1));
                return new NanoHTTPD.Response(HTTP_OK, MIME.MIME_CSS, allocateBuffer);
            } else if (uri.contains(".png")) {
                allocateBuffer = mContext.getAssets().open(uri.substring(1));
                return new NanoHTTPD.Response(HTTP_OK, MIME.MIME_PNG, allocateBuffer);
            } else if (uri.contains(".db")) {   // all .db request from client would be intercepted.
                // allocateBuffer = mContext.getAssets().open(uri.substring(1));
                allocateBuffer = new FileInputStream(targetDatabasePath);
                LightLogger.d("db file request...");
                return new NanoHTTPD.Response(HTTP_OK, MIME.MIME_DATABASE, allocateBuffer);
            } else {
                allocateBuffer = mContext.getAssets().open("SQLiteViewer.html");
                return new NanoHTTPD.Response(HTTP_OK, MIME_HTML, allocateBuffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Response(body);
    }

}
