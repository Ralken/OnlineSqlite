package cn.ralken.android.onlinesqlite;

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

public class LightLogger {
    private static final String LOGGER_TAG = "OnlineSqlite";

    private LightLogger() {
    }

    public enum LogLevel {
        VERBOSE, DEBUG, WARNING, NONE
    }

    public static void setLogLevel(LogLevel level) {
        // android.util.Log.d(LOGGER_TAG, )
    }

    public static void d(String log) {
        android.util.Log.d(LOGGER_TAG, log);
    }

}
