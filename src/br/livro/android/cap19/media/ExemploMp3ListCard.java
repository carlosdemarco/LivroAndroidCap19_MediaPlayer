package br.livro.android.cap19.media;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Simples Player MP3 utilizando a classe MediaPlayer do Android
 * 
 * Monta um ListView com todas as mp3 disponíveis no cartão SD em /sdcard
 * 
 * @author ricardo
 *
 */
public class ExemploMp3ListCard extends Activity implements OnItemClickListener {
	private static final String CATEGORIA = "livro";
	private String nomes[] = null;
	private PlayerMp3 player = new PlayerMp3();

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.list_sdcard);

		File sdcardDir = new File("/sdcard");
		Log.i(CATEGORIA,"/sdcard: " + sdcardDir.getAbsolutePath() + " - " + sdcardDir.exists());

		//abre a pasta /sdcard
		if (sdcardDir.exists()){
			File[] files = sdcardDir.listFiles();
			nomes = new String[files.length];
			for (int i = 0; i < files.length; i++) {
				nomes[i] = files[i].getName();
			}
		}

		//recupera o ListView
		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setOnItemClickListener(this);

		//monta a lista com os nomes das mp3
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, nomes);
		listView.setAdapter(arrayAdapter);
	}
	/**
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(this, nomes[position], Toast.LENGTH_SHORT).show();
		player.start("/sdcard/"+nomes[position]);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		player.fechar();
	}
}
