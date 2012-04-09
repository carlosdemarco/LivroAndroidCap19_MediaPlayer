package br.livro.android.cap19.media.video;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

/**
 * Classe para encapsular o acesso ao MediaPlayer para reproduzir um vídeo
 * 
 * @author ricardo
 *
 */
public class PlayerVideo implements OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener, Callback  {
	private static final String CATEGORIA = "livro";
	private static final int NOVO 		= 0;
	private static final int INICIADO 	= 1;
	private static final int PAUSADO 	= 2;
	private static final int PARADO 	= 3;
	// Começa o status zerado
	private int status = NOVO;
	private MediaPlayer player;
	private final String video;

	public PlayerVideo(SurfaceHolder holder, String video) {
		this.video = video;
		// Isto parece redimensionar o video para ocupar a tela inteira
		holder.setFixedSize(10, 10);
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		Log.i(CATEGORIA,"Construtor PlayerVideo ok: " + this.video);
	}
	// Faz pause
	public void pause() {
		player.pause();
		status = PAUSADO;
	}
	// Para a reprodução
	public void stop() {
		player.stop();
		status = PARADO;
	}
	// Para a reprodução, e libera recursos
	public void fechar() {
		stop();
		player.release();
		player = null;
	}
	// Inicia a reprodução do vídeo
	public void start() {
		try {
			switch (status) {
				case INICIADO:
					player.stop();
				case PARADO:
					player.reset();
				case NOVO:
					player.setDataSource(video);
					// Ao chamar o prepare() o listener "onPrepared(mediaPlayer)" será chamado
					player.prepare();
				case PAUSADO:
					player.start();
					break;
			}

			status = INICIADO;
		} catch (Exception e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		}catch (Throwable e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		}
	}
	//Cria o MediaPlayer e inicia o vídeo somente quando o SurfaceView/SurfaceHolder estiverem ok
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Log.i(CATEGORIA,"surfaceChanged: " + format + " width: " + width + ", height: " + height);
		Log.i(CATEGORIA,"Video: " + video);

		player = new MediaPlayer();
		player.setDisplay(holder);
		player.setOnBufferingUpdateListener(this);
		player.setOnCompletionListener(this);
		player.setOnPreparedListener(this);
		player.setAudioStreamType(AudioManager.STREAM_MUSIC);

		//Inicia a reprodução automaticamente
		start();
	}
	public void surfaceCreated(SurfaceHolder surfaceholder) {
		Log.i(CATEGORIA,"surfaceCreated");
	}
	public void surfaceDestroyed(SurfaceHolder surfaceholder) {
		Log.i(CATEGORIA,"surfaceDestroyed");		
	}
	public void onBufferingUpdate(MediaPlayer mediaplayer, int i) {
		Log.i(CATEGORIA,"onBufferingUpdate");
	}
	public void onCompletion(MediaPlayer mediaplayer) {
		Log.i(CATEGORIA,"onCompletion - Fim do video");
	}
	//Inicia o MediaPlayer.start() somente quando o vídeo estiver pronto para reproduzir
	public void onPrepared(MediaPlayer mediaplayer) {
		Log.i(CATEGORIA,"onPrepared");

        int videoWidth = player.getVideoWidth();
        int videoHeight = player.getVideoHeight();
        Log.d(CATEGORIA, "videoWidth: " + videoWidth);
        Log.d(CATEGORIA, "videoHeight: " + videoHeight);
	}
}
