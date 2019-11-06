package com.example.downloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.update_tv);
        Button downloadBtn = findViewById(R.id.download_Btn);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SetDownloadTask().execute();
            }
        });
    }


    class SetDownloadTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url = new URL("https://images.ctfassets.net/37l920h5or7f/5veFGObZjqmQY8qKu6auAW/abe271ddb25ae87d1212a4da798d3229/asset-030.jpg");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {

            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            statusText.setText("Download Started");
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            statusText.setText("Download Completed");
            ImageView imageView = findViewById(R.id.image_view);
            imageView.setImageBitmap(bitmap);
        }
    }

}
