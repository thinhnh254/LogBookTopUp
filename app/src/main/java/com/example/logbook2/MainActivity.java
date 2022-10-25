package com.example.logbook2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button backward, forward;
    int i = 0;
    private int[] textureArrayWin = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        backward = (Button) findViewById(R.id.backward);
        forward = (Button) findViewById(R.id.forward);


        if(i == 0){
            backward.setVisibility(View.VISIBLE);
        }
        if (i == 5){
            forward.setVisibility(View.VISIBLE);
        }

        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i > 0) {
                    i--;
                    imageView.setImageResource(textureArrayWin[i]);
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i < 4) {
                    i++;
                    imageView.setImageResource(textureArrayWin[i]);
                }
            }
        });
    }
}