package br.livro.android.cap19.media;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import br.livro.android.cap19.media.service.InterfaceMp3;
import br.livro.android.cap19.media.service.ServicoMp3;
import br.livro.android.cap19.media.service.ServicoMp3.ConexaoInterfaceMp3;

/**
 * Simples Player MP3 utilizando a classe MediaPlayer do Android
 * 
 * @author ricardo
 *
 */
public class ExemploPlayerMp3ComServico extends Activity implements OnClickListener {
	private static final String CATEGORIA = "livro";

	//Interface para se comunicar com o serviço em segundo plano
	private InterfaceMp3 interfaceMp3;

	private ImageButton btStart;
	private ImageButton btPause;
	private ImageButton btStop;
	private Button btStopService;
	private EditText text;

	private ServiceConnection conexao = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			Log.i(CATEGORIA,"onServiceConnected, serviço conectado");
			//Recupera a interface para interagir com o serviço
			ConexaoInterfaceMp3 conexao = (ConexaoInterfaceMp3) service;
			Log.i(CATEGORIA,"Recuperada a interface para controlar o mp3.");
			interfaceMp3 = conexao.getService();
		}
		public void onServiceDisconnected(ComponentName className) {
			Log.i(CATEGORIA,"onServiceDisconnected, liberando recursos.");
			interfaceMp3 = null;
		}
	};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.form_mp3_service);

		text = (EditText) findViewById(R.id.arquivo);

		btStart = (ImageButton) findViewById(R.id.start);
		btStart.setOnClickListener(this);

		btPause = (ImageButton) findViewById(R.id.pause);
		btPause.setOnClickListener(this);

		btStop = (ImageButton) findViewById(R.id.stop);
		btStop.setOnClickListener(this);

		btStopService = (Button) findViewById(R.id.stopService);
		btStopService.setOnClickListener(this);

		Log.i(CATEGORIA,"Chamando startService()...");

		//inicia o service
		startService(new Intent(this, ServicoMp3.class));

		Log.i(CATEGORIA,"Chamando bindService()...");

		boolean b = bindService(new Intent(this, ServicoMp3.class), conexao, Context.BIND_AUTO_CREATE);

		Log.i(CATEGORIA,"bindService retorno: " + b);
	}

	/**
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View view) {
		try {
			if (view == btStart) {
				String mp3 = text.getText().toString();
				interfaceMp3.start(mp3);
			} else if (view == btPause) {
				interfaceMp3.pause();
			} else if (view == btStop) {
				interfaceMp3.stop();
			}else if (view == btStopService) {
				Log.i(CATEGORIA, "Parando o mp3 e o servi�o.");
				//para o player
				interfaceMp3.stop();
				//para o Service, com a mesma Intent que o criou
				stopService(new Intent(this, ServicoMp3.class));
			}
		} catch (Exception e) {
			Log.e(CATEGORIA, e.getMessage(), e);
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(CATEGORIA, "Activity destruida! Mas a musica continua...");
		unbindService(conexao);
	}
}
