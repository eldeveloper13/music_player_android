package com.google.eldeveloper13.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOpenFile = (Button) findViewById(R.id.btn_open_file);
        btnOpenFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openDirectory = new Intent(MainActivity.this, FileNavigationActivity.class);
                startActivity(openDirectory);
            }
        });

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent musicServiceIntent = new Intent(MainActivity.this, MusicPlayerService.class);
                musicServiceIntent.setAction(MusicPlayerService.ACTION_PLAY);
                musicServiceIntent.putExtra(MusicPlayerService.SONG_URI, "android.resource://com.google.eldeveloper13.musicplayer/raw/clare_de_lune");
                startService(musicServiceIntent);
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent musicServiceIntent = new Intent(MainActivity.this, MusicPlayerService.class);
                musicServiceIntent.setAction(MusicPlayerService.ACTION_PLAY);
                musicServiceIntent.putExtra(MusicPlayerService.SONG_URI, "android.resource://com.google.eldeveloper13.musicplayer/raw/vivaldi_spring");
                startService(musicServiceIntent);
            }
        });

        Button btnStop = (Button) findViewById(R.id.btn_stop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent musicServiceIntent = new Intent(MainActivity.this, MusicPlayerService.class);
                stopService(musicServiceIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
