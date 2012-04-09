package br.livro.android.cap19.media.video;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Simples teste do VideoView
 * 
 * @author ricardo
 * 
 */
public class ExemploVideoView extends Activity {
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);

		VideoView v = new VideoView(this);
		setContentView(v);

		v.setVideoPath("/data/local/tmp/last_mohicans.3gp");
		v.setMediaController(new MediaController(this));
		v.requestFocus();
	}
}
