package br.livro.android.cap19.media;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * @author ricardo
 *
 */
public class ExemploCamera extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);

		ImageButton b = (ImageButton) findViewById(R.id.btAbrirCamera);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
				startActivityForResult(i, 0);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (data != null) {
			Bundle bundle = data.getExtras();
			if (bundle != null) {
				// Recupera o Bitmap retornado pela câmera
				Bitmap bitmap = (Bitmap) bundle.get("data");
				// Atualiza a imagem na tela
				ImageView img = (ImageView) findViewById(R.id.imagem);
				img.setImageBitmap(bitmap);
			}
		}
	}
}