package com.google.eldeveloper13.musicplayer;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FileNavigationActivity extends ActionBarActivity {

    ListView fileListView;
    FileListAdapater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_navigation);

        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES);

        setTitle(directory.getAbsolutePath());
        File[] files = directory.listFiles();
        List<File> fileList;
        if (files == null) {
            fileList = new ArrayList<>();
        } else {
            fileList = Arrays.asList(files);
        }
        adapter = new FileListAdapater(this, fileList);

        fileListView = (ListView) findViewById(R.id.fileList);
        fileListView.setAdapter(adapter);

        fileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File file = adapter.getItem(position);
                Intent musicServiceIntent = new Intent(FileNavigationActivity.this, MusicPlayerService.class);
                musicServiceIntent.setAction(MusicPlayerService.ACTION_PLAY);
                musicServiceIntent.putExtra(MusicPlayerService.SONG_URI, file.getAbsolutePath());
                startService(musicServiceIntent);

            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_file_navigation, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
