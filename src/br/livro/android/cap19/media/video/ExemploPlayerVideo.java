package br.livro.android.cap19.media.video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
public class ExemploPlayerVideo extends Activity implements OnClickListener {
	private static final String CATEGORIA = "livro";
	private ImageButton btStart;
	private EditText text;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_video_simples);

		text = (EditText) findViewById(R.id.arquivo);

		btStart = (ImageButton) findViewById(R.id.start);
		btStart.setOnClickListener(this);
	}

	/**
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View view) {
		try {
			// Recupera o caminho do vídeo
			String video = text.getText().toString();
			
			// Abre a tela que tem o SurfaceView para exibir o vídeo
			Intent intent = new Intent(this,ExemploPlayerVideoSurface.class);
			intent.putExtra("video", video);
			startActivity(intent);
		} catch (Exception e) {
			Log.e(CATEGORIA, e.getMessage(), e);
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
}
