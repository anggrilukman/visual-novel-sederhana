package com.example.dayak;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_IMAGE_INDEX = "image_index";
    private ImageView imageView;
    private ImageButton btnPrevious, btnNext;
    private int currentImageIndex = 1;
    private MediaPlayer mediaPlayer, backgroundMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        initializeBackgroundAudio();

        if (savedInstanceState != null) {
            currentImageIndex = savedInstanceState.getInt(KEY_IMAGE_INDEX);
        }

        setImage();

        btnPrevious.setOnClickListener(view -> showImage(-1));
        btnNext.setOnClickListener(view -> showImage(1));
    }

    private void initializeViews() {
        imageView = findViewById(R.id.imageView);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_IMAGE_INDEX, currentImageIndex);
    }

    private void setImage() {
        int imageResource = getResources().getIdentifier("image_" + currentImageIndex, "drawable", getPackageName());
        int audioResource = getResources().getIdentifier("audio_" + currentImageIndex, "raw", getPackageName());

        imageView.setImageResource(imageResource);
        playSound(audioResource);

        if (currentImageIndex == 6 || currentImageIndex == 16) {
            changeBackgroundSound("background_audio_image_" + currentImageIndex);
        }
    }

    private void changeBackgroundSound(String resourceName) {
        int resourceId = getResources().getIdentifier(resourceName, "raw", getPackageName());
        if (backgroundMediaPlayer != null) {
            backgroundMediaPlayer.release();
        }
        backgroundMediaPlayer = MediaPlayer.create(this, resourceId);
        startBackgroundMediaPlayer();
    }

    private void showImage(int direction) {
        int newIndex = currentImageIndex + direction;
        if (newIndex >= 1 && newIndex <= 1000) {
            currentImageIndex = newIndex;
            setImage();
        }
    }

    private void initializeBackgroundAudio() {
        backgroundMediaPlayer = MediaPlayer.create(this, R.raw.background_audio);
        startBackgroundMediaPlayer();
    }

    private void startBackgroundMediaPlayer() {
        if (backgroundMediaPlayer != null) {
            backgroundMediaPlayer.setLooping(true);
            backgroundMediaPlayer.setVolume(0.2f, 0.2f);
            backgroundMediaPlayer.start();
        }
    }

    private void playSound(int audioResource) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, audioResource);
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        if (backgroundMediaPlayer != null) {
            backgroundMediaPlayer.release();
        }
        super.onDestroy();
    }
}
