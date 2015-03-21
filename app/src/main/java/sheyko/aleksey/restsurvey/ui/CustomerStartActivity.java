package sheyko.aleksey.restsurvey.ui;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseAnalytics;

import sheyko.aleksey.restsurvey.BaseActivityFullscreen;
import sheyko.aleksey.restsurvey.R;


public class CustomerStartActivity extends BaseActivityFullscreen {

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_start);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        mPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        Button b = (Button) findViewById(R.id.startButton);
        b.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View view) {
                startActivity(new Intent(CustomerStartActivity.this,
                        CustomerSurveyActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        if (!mPreferences.getBoolean("dark_theme", false)) {
            b.setBackground(getResources().getDrawable(
                    R.drawable.button_start_dark));
        }
    }

    private boolean doubleBackToExitPressedOnce;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            if (mPreferences.getBoolean("lock_app", false)) {
                DialogFragment f = new ConfirmExitFragment();
                f.show(getFragmentManager(), "dialog");
            } else {
                super.onBackPressed();
            }
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void confirmExit() {
        Intent i = new Intent(CustomerStartActivity.this,
                AdminPanelActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}