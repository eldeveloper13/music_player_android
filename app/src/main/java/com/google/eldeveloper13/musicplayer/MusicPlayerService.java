package com.google.eldeveloper13.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class MusicPlayerService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    public static final String ACTION_PLAY = "com.example.action.PLAY";
    public static final String SONG_URI = "SONGURI";
    MediaPlayer mediaPlayer = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction().equals(ACTION_PLAY)) {
            try {
                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.reset();
                String song = intent.getStringExtra(SONG_URI);
                mediaPlayer.setOnPreparedListener(this);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                Uri songUri = Uri.parse(song);
                mediaPlayer.setDataSource(this, songUri);
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            return Service.START_STICKY;
        } else {
            return super.onStartCommand(intent, flags, startId);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        mediaPlayer.release();
        mediaPlayer = null;
        super.onDestroy();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d("MusicPlayerService", "Media Player completed");
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.e("MusicPlayerService", "Media Player error");
        return false;
    }
}
