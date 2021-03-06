# OnlineSqlite
This library aims at debugging sqlite database more easy and efficient, we don't have to export the dabasefile to PC and then analyze the original data any more, now you can use OnlineSqlite via browser directly!

![Online Sqlite](./page_screenshot.jpg)

## Usage
**Step 1.** Add the JitPack repository to your build file: 

```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

**Step 2.** Add library dependency in your gradle:

```groovy
	dependencies {
	    compile 'com.github.Ralken:OnlineSqlite:b641bfa88a'
	}
```

**Step 3.** Config the OnlineSqlite settings: 

```java
  final OnlineConfig config = new OnlineConfig.Builder()
                    .setDatabasePath(path)
                    .setAvailableTables(tables)
                    .setOutputIpAddressEnabled(true)
                    .setReadOnly(false)
		    .setAutoShutDown(false)
                    .build();

    try {
        OnlineSqlite.init(getApplication()).setOnlineConfig(config)
        .start();
    } catch (Throwable throwable) {
        throwable.printStackTrace();
    }
```

## Required
1. make sure that the brower which you access from must be the **same LAN network** with your android mobile device which is running OnlineSqlite service.

##  Project specification
This project started on Oct. 24 2017 and its now a pretty alpha-version, it's working well at the moment but some experice can be further improved- especilly the Web/Javascript part. You will be very welcome if you're interested in this project and willing to contribute your effort to make it better! Please send a pull request, thanks!

## Reference
 - [NanoHttpd](https://github.com/NanoHttpd/nanohttpd) - Core embeddable HTTP server component.
 - [sqlite-viewer](https://github.com/inloop/sqlite-viewer)

## Note
Due to OnlineSqlite is working based on a long lived HTTP service in the background, it should work only under non-product environment. It's strongly recommend that you turn on the service in **debug/test** case only.

## License

```
    Copyright 2015 Ralken Ltd.

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
