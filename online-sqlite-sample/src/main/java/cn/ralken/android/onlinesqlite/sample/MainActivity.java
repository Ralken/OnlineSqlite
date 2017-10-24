package cn.ralken.android.onlinesqlite.sample;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.File;

import cn.ralken.android.onlinesqlite.LightLogger;
import cn.ralken.android.onlinesqlite.OnlineConfig;
import cn.ralken.android.onlinesqlite.OnlineSqlite;

public class MainActivity extends AppCompatActivity {
    private TextView mCenterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCenterTextView = (TextView) findViewById(R.id.mCenterTextView);

        if (BuildConfig.DEBUG) {
            final String path = ensureTestDBExist();
            final String[] tables = new String[]{};

            LightLogger.d("db file path: " + path);

            final OnlineConfig config = new OnlineConfig.Builder()
                    .setDatabasePath(path)
                    .setAvailableTables(tables)
                    .setOutputIpAddressEnabled(true)
                    .setReadOnly(false)
                    .build();

            try {
                OnlineSqlite.init(getApplication()).setOnlineConfig(config)
                .start();

                mCenterTextView.setText("Please visit your database via browser:\n"
                    + OnlineSqlite.getInstance().getAccessUrl());
            } catch (Throwable throwable) {
                throwable.printStackTrace();

                mCenterTextView.setText("OnlineSqlite's not working, please check logcat output for more details:\n "
                    + throwable.getClass().getSimpleName());
            }
        }
    }

    private String ensureTestDBExist() {
        final String dbName = "ndb_test.db";
        final String destDbPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final String absPath = destDbPath + File.separator + dbName;

        if (!new File(absPath).exists()) {
            AssertsUtil.copyAssertToExternalStorage(this, dbName, destDbPath);
        }
        return absPath;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
