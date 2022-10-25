package com.example.logbook2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.logbook2.databinding.ActivityMainBinding;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Handler mainHandler = new Handler();
    ProgressDialog progressDialog;

    ImageView imageView;
    Button backward, forward;
    int i = 0;


    private int[] changeImage = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());

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
                    imageView.setImageResource(changeImage[i]);
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i < 4) {
                    i++;
                    imageView.setImageResource(changeImage[i]);
                }
            }
        });

        class AddImage extends Thread{

            String URL;
            Bitmap bitmap;

            AddImage(String URL){
                this.URL = URL;
            }

            @Override
            public void run() {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(MainActivity.this);
                        progressDialog.setMessage("Getting your pic...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }
                });

                InputStream inputStream = null;
                try {
                    inputStream = new java.net.URL(URL).openStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        binding.imageView.setImageBitmap(bitmap);
                    }
                });

            }
        }

        binding.addlinkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = binding.etURL.getText().toString();
                new AddImage(url).start();
            }
        });
    }
}