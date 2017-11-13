package cn.ralken.android.onlinesqlite;

import java.util.concurrent.TimeUnit;

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

public class OnlineConfig implements InternalAction {
    private static final int DEFAULT_SERVICE_PORT = 8083;

    private String databasePath;
    private boolean outputIpAddressEnabled;
    private String[] availableTables;
    private boolean readOnly = false;
    private int port;
    private long shutDownDelay;

    public String getDatabasePath() {
        return databasePath;
    }

    public boolean isOutputIpAddressEnabled() {
        return outputIpAddressEnabled;
    }

    public String[] getAvailableTables() {
        return availableTables;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public int getPort() {
        return port;
    }

    public long getShutDownDelay() {
        return shutDownDelay;
    }

    private OnlineConfig(Builder builder) {
        databasePath = builder.databasePath;
        outputIpAddressEnabled = builder.outputIpAddressEnabled;
        availableTables = builder.availableTables;
        readOnly = builder.readOnly;
        port = builder.port;
        shutDownDelay = builder.shutDownDelay;
    }

    public static final class Builder {
        private String databasePath;
        private boolean outputIpAddressEnabled;
        private String[] availableTables;
        private boolean readOnly;
        private int port = DEFAULT_SERVICE_PORT;
        private long shutDownDelay = TimeUnit.MINUTES.toMillis(30);

        public Builder() {
        }

        public Builder setDatabasePath(String val) {
            databasePath = val;
            return this;
        }

        public Builder setOutputIpAddressEnabled(boolean val) {
            outputIpAddressEnabled = val;
            return this;
        }

        public Builder setAvailableTables(String[] val) {
            availableTables = val;
            return this;
        }

        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public Builder setShutDownDelay(long delay) {
            this.shutDownDelay = delay;
            return this;
        }

        public Builder setReadOnly(boolean val) {
            readOnly = val;
            return this;
        }

        public OnlineConfig build() {
            return new OnlineConfig(this);
        }
    }
}
