package com.example.tess;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final ImageView[] image = new ImageView[10];
    private final TextView[] text = new TextView[10];
    Thread secThread;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < image.length; i++) {
            int imageId = getResources().getIdentifier("imageView" + i, "id", getPackageName());
            int textId = getResources().getIdentifier("textView" + i, "id", getPackageName());
            image[i] = findViewById(imageId);
            text[i] = findViewById(textId);
        }
        init();

//        Picasso.with(this).load(tempUrl).into(image);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        image[0].setEnabled(true);
//    }

    private void init(){
        MyRunnable runnable = new MyRunnable(image, text);
        secThread = new Thread(runnable);
        secThread.start();
    }

    @Override
    public void onClick(View view) {
//        System.out.println("CHECK     " + view.getId());
//        System.out.println("CHECK     " + getResources().getIdentifier("textImage0", "id", getPackageName()));
//        Intent intent = new Intent((MainActivity.this), Post.class);
        }

}

