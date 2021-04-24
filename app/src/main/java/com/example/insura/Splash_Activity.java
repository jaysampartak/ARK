package com.example.insura;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash_Activity extends AppCompatActivity {
    Handler handler;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        logo = findViewById(R.id.logo_id);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.splash_anim);
        logo.startAnimation(animation);

    }
}
