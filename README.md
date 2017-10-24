# OnlineSqlite
This library aims at debugging sqlite database more easy and efficient, we don't have to export the dabasefile to PC and then analyze the original data any more, now you can use OnlineSqlite via browser directly!

# How to use
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

