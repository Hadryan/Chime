package frontend;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.ArrayList;

import android.content.ContentUris;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.PowerManager;
import android.util.Log;
import backend.Song;

public class MusicService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
MediaPlayer.OnCompletionListener {
	
	//media player
	private MediaPlayer player;
	//song list
	private ArrayList<Song> songs;
	//current position
	private int songPosn;
	
	private final IBinder musicBind = new MusicBinder();
	
	public void onCreate(){
			//create the service
			super.onCreate();
			//initialize position
			songPosn=0;
			//create player
			player = new MediaPlayer();
			initMusicPlayer();
		}
	
	public void initMusicPlayer(){
		  //set player properties
		  player.setWakeMode(getApplicationContext(),
		  	PowerManager.PARTIAL_WAKE_LOCK);
		  player.setAudioStreamType(AudioManager.STREAM_MUSIC);
		  player.setOnPreparedListener(this);
		  player.setOnCompletionListener(this);
		  player.setOnErrorListener(this);
		}
	

	@Override
	public IBinder onBind(Intent intent) {
	  return musicBind;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
	  //start playback
	  mp.start();
	}
	
	public void setList(ArrayList<Song> theSongs){
		  songs=theSongs;
	}
	
	public class MusicBinder extends Binder {
	  MusicService getService() {
	    return MusicService.this;
	  }
	}
	
	@Override
	public boolean onUnbind(Intent intent){
	  player.stop();
	  player.release();
	  return false;
	}
	
	public void playSong(){
	  player.reset();
	  //get song
	  Song playSong = songs.get(songPosn);
	  //get id
	  long currSong = playSong.getId();
	  //set uri
	  Uri trackUri = ContentUris.withAppendedId(
	  android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
	  currSong);
	  //play the song
	  try{
		  player.setDataSource(getApplicationContext(), trackUri);
		}
		catch(Exception e){
		  Log.e("MUSIC SERVICE", "Error setting data source", e);
		}
	  player.prepareAsync();
	}
	
	public void setSong(int songIndex){
	  songPosn=songIndex;
	}

}
