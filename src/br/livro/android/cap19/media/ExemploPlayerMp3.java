package br.livro.android.cap19.media;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Simples Player MP3 utilizando a classe MediaPlayer do Android
 * 
 * @author ricardo
 *
 */
public class ExemploPlayerMp3 extends Activity implements OnClickListener {
	private static final String CATEGORIA = "livro";
	//Classe que encapsula o MediaPlayer
	private PlayerMp3 player = new PlayerMp3();
	private ImageButton btStart;
	private ImageButton btPause;
	private ImageButton btStop;
	private EditText text;
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_mp3);

		text = (EditText) findViewById(R.id.arquivo);

		btStart = (ImageButton) findViewById(R.id.start);
		btStart.setOnClickListener(this);

		btPause = (ImageButton) findViewById(R.id.pause);
		btPause.setOnClickListener(this);

		btStop = (ImageButton) findViewById(R.id.stop);
		btStop.setOnClickListener(this);
	}
	/**
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View view) {
		try {
			if (view == btStart) {
				String mp3 = text.getText().toString();
				player.start(mp3);
			} else if (view == btPause) {
				player.pause();
			} else if (view == btStop) {
				player.stop();
			}
		} catch (Exception e) {
			Log.e(CATEGORIA, e.getMessage(), e);
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//libera recursos do MediaPlayer
		player.fechar();
	}
}
