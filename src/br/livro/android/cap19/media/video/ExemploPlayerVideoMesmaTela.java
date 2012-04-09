package br.livro.android.cap19.media.video;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import br.livro.android.cap19.media.R;

/**
 * Simples Player de Vídeo
 * 
 * @author ricardo
 * 
 */
public class ExemploPlayerVideoMesmaTela extends Activity implements OnClickListener {
	private static final String CATEGORIA = "livro";
	// Classe que encapsula o MediaPlayer
	private PlayerVideo player;
	private ImageButton btStart;
	private ImageButton btPause;
	private ImageButton btStop;
	private EditText text;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_video);

		// Recupera o SurfaceView
		SurfaceView surface = (SurfaceView) findViewById(R.id.surfaceView);
		SurfaceHolder holder = surface.getHolder();

		text = (EditText) findViewById(R.id.arquivo);
		String video = text.getText().toString();
		
		player = new PlayerVideo(holder,video);

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
				
				player.start();
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
		// Libera recursos do MediaPlayer
		player.fechar();
	}
}
