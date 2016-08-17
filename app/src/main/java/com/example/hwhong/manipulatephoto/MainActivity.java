package com.example.hwhong.manipulatephoto;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap map;
    private Bitmap resetMap;

    private Bitmap operation;

    private Button flip, grey, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flip = (Button) findViewById(R.id.flip);
        grey = (Button) findViewById(R.id.grey);
        reset = (Button) findViewById(R.id.reset);

        imageView = (ImageView) findViewById(R.id.imageView);
        //BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        imageView.setBackgroundResource(R.drawable.android);

        map = ((BitmapDrawable)imageView.getDrawable()).getBitmap();


        resetMap = map;

        flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                operation = Bitmap.createBitmap(map.getWidth(),map.getHeight(), map.getConfig());

                Color tempColor = null;
                int leftPixel = 0;
                int rightPixel = 0;

                int limit1 = operation.getWidth();
                int limit2 = operation.getHeight()/2;

                for(int y = 0; y < limit1; y++)
                {
                    for(int x = 0; x < limit2; x++)
                    {
                        //left pixel operations
                        leftPixel = operation.getPixel(x,y);
                        int r = Color.red(leftPixel);
                        int g = Color.green(leftPixel);
                        int b = Color.blue(leftPixel);

                        //right pixel operations
                        rightPixel = operation.getPixel(x,operation.getHeight()-1-y);
                        int r1 = Color.red(rightPixel);
                        int g1 = Color.green(rightPixel);
                        int b1 = Color.blue(rightPixel);

                        operation.setPixel(x, y, Color.argb(Color.alpha(leftPixel), r1, g1, b1));

                        operation.setPixel(x, operation.getHeight()-1-y, Color.argb(Color.alpha(rightPixel), r, g, b));
                    }
                }

                imageView.setImageBitmap(operation);

            }
        });

        grey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operation = Bitmap.createBitmap(map.getWidth(),map.getHeight(), map.getConfig());
                int pixel = 0;
                int intensity = 0;
                for( int y =0; y < map.getHeight(); y++)
                {
                    for (int x = 0; x < map.getWidth(); x++)
                    {
                        pixel = map.getPixel(x, y);
                        intensity = (int)(Color.red(pixel)+(Color.green(pixel))+(Color.blue(pixel))/3);
                        operation.setPixel(x, y, Color.argb(Color.alpha(pixel), intensity, intensity, intensity));
                    }
                }
                imageView.setImageBitmap(operation);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageBitmap(resetMap);
            }
        });
    }
}
