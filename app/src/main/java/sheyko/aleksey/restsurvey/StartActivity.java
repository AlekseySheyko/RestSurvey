package sheyko.aleksey.restsurvey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseAnalytics;


public class StartActivity extends Activity {
    private static final String TAG = StartActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        try {
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "cvwSNlSuCvUWvOP9RYXtPhWZR3Bm69xgT979VZk3",
                    "S72yDeO7sVS96p9IRjZzmeE9sy6WwxVhZsdn2sFQ");
            ParseAnalytics.trackAppOpenedInBackground(getIntent());
        } catch (IllegalStateException ise) {
            Log.d(TAG, "Parse already initialized");
        }

        Button b = (Button) findViewById(R.id.startButton);
        b.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,
                        SurveyActivity.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_switch_theme) {
            // TODO: Switch between light and black themes
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}