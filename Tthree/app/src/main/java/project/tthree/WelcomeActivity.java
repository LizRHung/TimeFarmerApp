package project.tthree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class WelcomeActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final String PREFS_NAME = "MyPrefsFile";
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if(settings.getBoolean("my_first_time", true)){
            Log.d("Comments", "First time");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run(){
                    Intent TutorialIntent = new Intent(WelcomeActivity.this, TutorialActivity.class);
                    startActivity(TutorialIntent);
                    finish();
                }
            },SPLASH_TIME_OUT);

            settings.edit().putBoolean("my_first_time", false).commit();
        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run(){
                    Intent loginIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            },SPLASH_TIME_OUT);
        }


    }
}