package br.livro.android.cap19.media;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Hello World que toca uma mp3 localizada na pasta /res/raw ou /data/local/tmp/
 * 
 * @author ricardo
 * 
 */
public class ExemploMediaPlayer extends Activity {
	private static final String CATEGORIA = "livro";
	private MediaPlayer player;

	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
/*
		player = MediaPlayer.create(this, R.raw.once);
		player.start();
*/
		try {
//			player = new MediaPlayer();
            player = MediaPlayer.create(this, R.raw.once);
//			player.setDataSource("/data/local/tmp/once.mp3");
//			player.setDataSource("/sdcard/linkin_park1.mp3");
			//player.prepare();
			player.start();
		} catch (Exception e) {
			Log.e(CATEGORIA,e.getMessage(),e);
		}

		TextView text = new TextView(this);
		text.setText("Exemplo mp3.");
		setContentView(text);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (player != null) {
			player.stop();
			player.release();
		}
	}
}
