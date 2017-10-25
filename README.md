# OnlineSqlite
This library aims at debugging sqlite database more easy and efficient, we don't have to export the dabasefile to PC and then analyze the original data any more, now you can use OnlineSqlite via browser directly!

## Usage
**Step 1.** Add it in your root build.gradle at the end of repositories:

```java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2. 

```
  final OnlineConfig config = new OnlineConfig.Builder()
                    .setDatabasePath(path)
                    .setAvailableTables(tables)
                    .setOutputIpAddressEnabled(true)
                    .setReadOnly(false)
                    .build();

    try {
        OnlineSqlite.init(getApplication()).setOnlineConfig(config)
        .start();
    } catch (Throwable throwable) {
        throwable.printStackTrace();
    }
```

# Project specification
This project started on Oct. 24 2017 and its now a pretty beta-version, it's working well at the moment but some experice can be further improved- especilly the Web/Javascript part. You will be very welcome if you're interested in this project and willing to contribute your effort to make it better! Please send a pull request, thanks!

## License

```
    Copyright 2015 Ralken.cn Ltd.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```
