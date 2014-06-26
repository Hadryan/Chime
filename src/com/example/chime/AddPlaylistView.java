package com.example.chime;

import java.util.Map;

import models.MusicGrabber;
import models.MusicGrabber.Item;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class AddPlaylistView extends Activity{
	
	static Map<String, Item> songsInTitleFormat = null;
	static Map<String, Item> songsInArtistFormat = null;
	static Map<String, Item> songsInAlbumFormat = null;
	final MusicGrabber music = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_playlist_view);
		
		Context context = getApplicationContext();
		CharSequence text = "Error!!";
		int duration = Toast.LENGTH_SHORT;
		
		// minSDKVersion is 11 or higher
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    //Prepare all music from the phone.
	    music.prepare();
	
	    //all music is in music.mItems()
	    //put all this music in list format and send it to the corresponding fragments
	    //<Song title-Artist title, actual song>
	    
	    for (int i = 0;i < music.mItems.size();i++){
	    	songsInTitleFormat.put(music.mItems.get(i).getTitle() + ", " + music.mItems.get(i).getArtist(), 
	    			music.mItems.get(i));
	    }
	    
	    //<Artist, sub list of all of the artist songs>
	    for (int i = 0;i < music.mItems.size();i++){
	    	songsInArtistFormat.put(music.mItems.get(i).getArtist(), 
	    			music.mItems.get(i));
	    }
	    
	    //<Album, sub list of songs in that album>
	    for (int i = 0;i < music.mItems.size();i++){
	    	songsInAlbumFormat.put(music.mItems.get(i).getAlbum(), 
	    			music.mItems.get(i));
	    }
	    
//	    SongsFragment songFrag = (SongsFragment) getFragmentManager().findFragmentById(R.id.songslist);
//	    if (songFrag != null){
//	    	
//	    }
	}
	
	//Creates the fragment
//	@Override
//	public void onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//		
//		
//	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_playlist_menu, menu);
		return super.onCreateOptionsMenu (menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		//Handle presses on the action bar items
		switch(item.getItemId()) {
		case R.id.add_playlist:
			Intent intent = new Intent(this, PlaylistListView.class);
			startActivity(intent);
			return true;
		case R.id.new_playlist_settings:
			Intent intent1 = new Intent(this, Settings.class);
			startActivity(intent1);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public static Map<String, Item> getSongsInTitleFormat(){
		return songsInTitleFormat;
	}
	
	public static Map<String, Item> getSongsInArtistFormat(){
		return songsInArtistFormat;
	}
	
	public static Map<String, Item> getSongsInAlbumFormat(){
		return songsInAlbumFormat;
	}

}
