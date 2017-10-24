package cn.ralken.android.onlinesqlite;

import android.app.Application;
import android.content.Context;

import cn.ralken.android.onlinesqlite.exception.AssetIOException;
import cn.ralken.android.onlinesqlite.exception.ConfigUnsetException;
import cn.ralken.android.onlinesqlite.exception.IllegalDatabasePathException;
import cn.ralken.android.onlinesqlite.exception.UnSupportNetworkException;
import cn.ralken.android.onlinesqlite.httpd.HandlerServer;
import cn.ralken.android.onlinesqlite.httpd.util.WifiUtil;

/**
 * Copyright Ralken Liao
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Ralken Liao
 * @date
 */

public class OnlineSqlite implements InternalAction {
    private final Context context;
    private OnlineConfig onlineConfig;
    private HandlerServer mHandlerServer;

    private static OnlineSqlite sInstance;

    public OnlineSqlite(Context context) {
        this.context = context;
    }

    public static OnlineSqlite init(Application application) {
        if (sInstance == null) {
            sInstance = new OnlineSqlite(application);
        }
        return sInstance;
    }

    public static OnlineSqlite getInstance() {
        return sInstance;
    }

    public OnlineConfig getOnlineConfig() {
        return onlineConfig;
    }

    public OnlineSqlite setOnlineConfig(OnlineConfig onlineConfig) {
        this.onlineConfig = onlineConfig;
        this.mHandlerServer = new HandlerServer(onlineConfig.getPort());
        return this;
    }

    public Context getContext() {
        return context;
    }

    public void start() throws Throwable {
        assertConfigEnvironment();
        mHandlerServer.start();

        LightLogger.d("Server started at site: " + getAccessUrl());
    }

    public String getAccessUrl(){
        return WifiUtil.getIpAddress(context) + ":" + onlineConfig.getPort();
    }

    public void stop() {
        try {
            mHandlerServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void assertConfigEnvironment() throws ConfigUnsetException, IllegalDatabasePathException,
            UnSupportNetworkException, AssetIOException {
        if (null == onlineConfig) {
            throw new ConfigUnsetException();
        }

        if (onlineConfig.getDatabasePath() == null || onlineConfig.getDatabasePath().trim().length() == 0) {
            throw new IllegalDatabasePathException();
        }

        if (!WifiUtil.isWifiConnected(context)) {
            throw new UnSupportNetworkException();
        }
    }

}