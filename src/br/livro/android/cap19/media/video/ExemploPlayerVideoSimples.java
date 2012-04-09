package br.livro.android.cap19.media.video;

import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;
import br.livro.android.cap19.media.R;

/**
 * Simples Player de Vídeo
 * 
 * @author ricardo
 * 
 */
public class ExemploPlayerVideoSimples extends Activity implements OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener, Callback {
	private static final String CATEGORIA = "livro";
	// Classe que encapsula o MediaPlayer
	private MediaPlayer player;
	private SurfaceHolder holder;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.video);

		// Recupera o SurfaceView
		SurfaceView surface = (SurfaceView) findViewById(R.id.surfaceView);
		holder = surface.getHolder();
		holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//		player = new PlayerVideo(holder);
	}
	
	private void play(String video) throws IllegalArgumentException, IllegalStateException, IOException{
		 // Create a new media player and set the listeners
        player = new MediaPlayer();
        player.setDataSource(video);
        player.setDisplay(holder);
        player.prepare();
        player.setOnBufferingUpdateListener(this);
        player.setOnCompletionListener(this);
        player.setOnPreparedListener(this);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Libera recursos do MediaPlayer
		player.release();
	}
	
	public void onBufferingUpdate(MediaPlayer mediaplayer, int i) {
		Log.i(CATEGORIA,"onBufferingUpdate");
	}
	public void onCompletion(MediaPlayer mediaplayer) {
		Log.i(CATEGORIA,"onCompletion");
	}
	public void onPrepared(MediaPlayer mediaplayer) {
		Log.i(CATEGORIA,"onPrepared");

        int videoWidth = player.getVideoWidth();
        int videoHeight = player.getVideoHeight();
        Log.d(CATEGORIA, "videoWidth: " + videoWidth);
        Log.d(CATEGORIA, "videoHeight: " + videoHeight);
        if (videoWidth != 0 && videoHeight != 0) {
            holder.setFixedSize(videoWidth, videoHeight);
            
            Log.d(CATEGORIA, "player.start()");
            mediaplayer.start();
        }
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Log.i(CATEGORIA,"surfaceChanged: " + format + " width: " + width + ", height: " + height);
		holder.setFixedSize(width, height);
		
		try {
			play("/sdcard/last_mohicans.3gp");
		} catch (Exception e) {
			Log.e(CATEGORIA, e.getMessage(),e);
		}

	}
	public void surfaceCreated(SurfaceHolder surfaceholder) {
		Log.i(CATEGORIA,"surfaceCreated");
	}
	public void surfaceDestroyed(SurfaceHolder surfaceholder) {
		Log.i(CATEGORIA,"surfaceDestroyed");		
	}
}
