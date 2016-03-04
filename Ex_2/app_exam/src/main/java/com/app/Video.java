package com.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.MediaController;
import java.io.File;
import java.text.DateFormat;
import java.util.Date;

public class Video extends AppCompatActivity {

    // VIEW
    Button btnCamera;
    VideoView vvVideo;
    Spinner spVideo;
    TextView tvPathVideo;
    // ACTIVITIES
    Intent intent;
    // UTIL
    File path;
    File file;
    String[] videos;
    ArrayAdapter<String> videoAdapter;
    MediaController mediaController;
    String timeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // FOLDERS, SD
        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        if(!path.exists()) path.mkdirs();
        videos = path.list();
        videoAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, videos);

        // VIEW
        tvPathVideo = (TextView) findViewById(R.id.tvPathVideo);
        tvPathVideo.setText(path.toString());

        vvVideo = (VideoView) findViewById(R.id.vvVideo);
        mediaController = new MediaController(this);
        vvVideo.setMediaController(mediaController);

        // EVENTS

        // Camera
        btnCamera = (Button) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeStamp = DateFormat.getDateTimeInstance().format(new Date()).replaceAll(":", "").replaceAll("/", "_") .replaceAll(" ", "_");
                file = new File(path,timeStamp + ".mp4");
                intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, 1);
                vvVideo.setVideoURI(Uri.fromFile(file));
            }
        });

        // Spinner
        spVideo = (Spinner) findViewById(R.id.spVideo);
        spVideo.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        setVideoAdapter();
        spVideo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vvVideo.setVideoURI(Uri.parse(path.getPath() + "/"+ videos[i]));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    @Override
    protected void onResume() {
        setVideoAdapter();
        super.onResume();
    }

    private void setVideoAdapter(){
        videos = path.list();
        videoAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, videos);
        spVideo.setAdapter(videoAdapter);
    }

}
