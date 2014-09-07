package com.example.chime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.chime.MusicService.MusicBinder;

import models.Song;
import android.app.ActionBar;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.MenuItem;
import android.view.View;
import com.example.chime.MusicService.MusicBinder;





public class AddPlaylistView extends FragmentActivity implements ActionBar.TabListener{
	
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	private static final String TAG = "MusicGrabber";
	
	private MusicService musicSrv;
	private Intent playIntent;
	private boolean musicBound=false;
	
	//All of the songs on the device
	public static ArrayList<Song> music = new ArrayList<Song>();
	static ArrayList<String> allArtists = new ArrayList<String>();
	static Map<String, ArrayList<Song>> songsInArtistFormat = new HashMap<String, ArrayList<Song>>();
	static Map<String, ArrayList<Song>> songsInGenreFormat;
	
	/*
	 * Bring the music service with this activity. (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onStart()
	 */
	@Override
	protected void onStart() {
	  super.onStart();
	  if(playIntent==null){
	    playIntent = new Intent(this, MusicService.class);
	    bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
	    startService(playIntent);
	  }
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//implement tabs
		final ActionBar actionBar = getActionBar();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_playlist_view);
		
		Context context = getApplicationContext();
		CharSequence text = "Error!!";
		int duration = Toast.LENGTH_SHORT;
		
		// minSDKVersion is 11 or higher
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    //Prepare all music from the phone.
	    musicGrabber();
	
		//Make a list of all artists
	    
	    if (music != null){
	    	for (int i = 0;i < music.size();i++){
		        //adds a track to its artist key
	    		Song tempSong = music.get(i);
	    		ArrayList<Song> emptyList = new ArrayList<Song>();
	    		if(songsInArtistFormat.get(tempSong.getArtist()) == null){
		    		songsInArtistFormat.put(tempSong.getArtist(), emptyList);
	    		}
	    		songsInArtistFormat.get(tempSong.getArtist()).add(tempSong);
		    }
	    }
	    
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		
		//Add 2 tabs 
	    actionBar.addTab(actionBar.newTab().setText("Songs").setTabListener(this));
	    actionBar.addTab(actionBar.newTab().setText("Artists").setTabListener(this));
	    
	    
	}
	
	//connect to the service
	private ServiceConnection musicConnection = new ServiceConnection(){
	 
	  @Override
	  public void onServiceConnected(ComponentName name, IBinder service) {
	    MusicBinder binder = (MusicBinder)service;
	    //get service
	    musicSrv = binder.getService();
	    //pass list
	    musicSrv.setList(music);
	    musicBound = true;
	  }
	 
	  @Override
	  public void onServiceDisconnected(ComponentName name) {
	    musicBound = false;
	  }
	};
	
	public void songPicked(View view){
	  musicSrv.setSong(Integer.parseInt(view.getTag().toString()));
	  musicSrv.playSong();
	}
	
	@Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar().getSelectedNavigationIndex());
    }
	
	@Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }
	
	@Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	    if (tab.getPosition() == 0) {
	      SongsFragment songFrag = new SongsFragment();
	      getSupportFragmentManager().beginTransaction().replace(R.id.new_playlist_view, songFrag).commit();
	     } 
	    else if (tab.getPosition() == 1) {
	      ArtistsFragment artistFrag = new ArtistsFragment();
	      getSupportFragmentManager().beginTransaction().replace(R.id.new_playlist_view, artistFrag).commit();
		}
	}
	
	@Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
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
//			Intent intent1 = new Intent(this, Settings.class);
//			startActivity(intent1);
//			return true;
			stopService(playIntent);
			musicSrv=null;
			System.exit(0);
			break;
		}
		return super.onOptionsItemSelected(item);
		
		
	}
	
	@Override
	protected void onDestroy() {
	  stopService(playIntent);
	  musicSrv=null;
	  super.onDestroy();
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_playlist_menu, menu);
		return super.onCreateOptionsMenu (menu);
	}

	
	public static ArrayList<Song> getSongs(){
		return music;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> getArtists(){
		if(songsInArtistFormat != null){
			for(String v : songsInArtistFormat.keySet()){
				allArtists.add(v);
			}
		}
		return allArtists;
	}
	
	public static Map<String, ArrayList<Song>> getSongsInArtistFormat(){
		return songsInArtistFormat;
	}
	
	public static Map<String, ArrayList<Song>> getSongsInGenreFormat(){
		return songsInGenreFormat;
	}
	
	
	
	public void musicGrabber(){
		//Technically (for instance on my nexus 5) the music is stored on the 
		//internal storage but for some reason it is recognized as an 
		//external storage, more research has to be done here
		
		//the content resolver
		ContentResolver mContentResolver = getContentResolver();
		
		Log.i(TAG, "Querying media...");
		
		Uri uriExternal = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		Log.i(TAG, "URIEXTERNAL: " + uriExternal.toString());
		
		//Perform a query on the content resolver. The URI we're passing specifies that
		//we want to query for all audio media on internal storage.
		Cursor curExternal = mContentResolver.query(uriExternal, null, 
				MediaStore.Audio.Media.IS_MUSIC + " = 1", null, null);
		
		
		//If our queries failed...
		if (curExternal == null){
			Log.e(TAG, "Failed to move cursor to first row (no query results");
			return;
		}
		
		if(!curExternal.moveToFirst()){
			Log.e(TAG, "failed to move cursor to first row (no music on device");
			return;
		}
		
		Log.i(TAG, "Listing...");
		
		//Retrieve columns indices for where song info is
		int artistColumn = curExternal.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int titleColumn = curExternal.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int albumColumn = curExternal.getColumnIndex(MediaStore.Audio.Media.ALBUM);
        int durationColumn = curExternal.getColumnIndex(MediaStore.Audio.Media.DURATION);
        int idColumn = curExternal.getColumnIndex(MediaStore.Audio.Media._ID);
        
        //Do the same with external here...
        
        
        Log.i(TAG, "Title column index: " + String.valueOf(titleColumn));
        Log.i(TAG, "ID column index: " + String.valueOf(idColumn));

        while (curExternal.moveToNext()){
        	Log.i(TAG, "ID: " + curExternal.getString(idColumn) + " Title: " + curExternal.getString(titleColumn));
            music.add(new Song(
                    curExternal.getLong(idColumn),
                    curExternal.getString(artistColumn),
                    curExternal.getString(titleColumn),
                    curExternal.getString(albumColumn),
                    curExternal.getLong(durationColumn)));
        }
        
        Log.i(TAG, "Done querying media. MusicGrabber is ready.");
	}

	
	
	
	
	

}
