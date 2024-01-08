package com.example.dayak;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // Get the ImageView
        ImageView logoImageView = findViewById(R.id.logoImageView);

        // Optionally, you can add a fade-in animation to the logo
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(2000); // Adjust the duration as needed
        logoImageView.startAnimation(fadeIn);

        // Play a splash sound (assuming splash_sound.mp3 is in the res/raw folder)
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.splash_sound);
        mediaPlayer.start();

        // Set a delay for the splash screen (e.g., 3000 milliseconds or 3 seconds)
        int splashScreenDuration = 3000;

        // Start your main activity after the splash screen duration
        logoImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start your main activity (replace MainActivity.class with your actual main activity)
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));

                // Finish the splash screen activity
                finish();
            }
        }, splashScreenDuration);
    }
}
