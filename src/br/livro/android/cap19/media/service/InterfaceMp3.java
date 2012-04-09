package br.livro.android.cap19.media.service;

/**
 * Interface para fazer o bind do Service MediaPlayerService que a implementa
 * 
 * @author ricardo
 *
 */
public interface InterfaceMp3 {
	public void start(String mp3);
	public void pause();
	public void stop();
}
