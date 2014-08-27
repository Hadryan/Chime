package com.example.chime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Song;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class AddPlaylistView extends Activity{
	
	private static final String TAG = "MusicGrabber";
	
	//All of the songs on the device
	public static ArrayList<Song> music = new ArrayList<Song>();
	
	static ArrayList<Song> songsInTitleFormat;
	static ArrayList<String> allArtists;
	static Map<String, ArrayList<Song>> songsInArtistFormat;
	static Map<String, Song> songsInAlbumFormat;
	
	
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
	    musicGrabber();
	
	    //put all this music in list format and send it to the corresponding fragments
	    
	    if (music != null){
	    	//CORRECTION: Music is already stored nicely and will give me
	    	//a nice list of all of the songs when queried :)
	    	
	    	//<Artist, list of all of the artist songs>
		    for (int i = 0;i < music.size();i++){
		    	ArrayList emptyList = new ArrayList();
		    	//create null arraylist to put in an artists value if nothing is there
		    	if (songsInArtistFormat.get(music.get(i).getArtist()) == null){
		    		songsInArtistFormat.put(music.get(i).getArtist(), 
		    				emptyList);
		    	}
		    	//add song to correct artist position in map
		    	songsInArtistFormat.get(music.get(i).getArtist()).add(music.get(i));
		    }
		    
		  
	    } 
	}
	
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
	
//	public static Map<String, Song> getSongsInTitleFormat(){
//		return songsInTitleFormat;
//	}
	
	public static ArrayList<Song> getSongs(){
		return music;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> getArtists(){
		allArtists = (ArrayList<String>) songsInArtistFormat.keySet();
		return allArtists;
	}
	
	public static Map<String, ArrayList<Song>> getSongsInArtistFormat(){
		return songsInArtistFormat;
	}
	
	public static Map<String, Song> getSongsInAlbumFormat(){
		return songsInAlbumFormat;
	}
	
	
	
	public void musicGrabber(){
		//Technically (for instance on my nexus 5) the music is stored on the 
		//internal storage but for some reason it is recognized as an 
		//external storage, more research has to be done here
		boolean isExternalAvailable = false;
		
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
		
		//Lets see if external storage is available. This method does everything on
		//the external what we just did with the internal.
//		if (isExternalAvailable){
//			Uri uriExternal = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//			Log.i(TAG, "URIEXTERNAL: " + uriExternal.toString());
//			Cursor curExternal = mContentResolver.query(uriExternal, null, 
//					MediaStore.Audio.Media.IS_MUSIC + " = 1", null, null);
//			if (curExternal == null){
//				Log.e(TAG, "Failed to move cursor to first row (no query results");
//				return;
//			}
//			
//			if(!curExternal.moveToFirst()){
//				Log.e(TAG, "failed to move cursor to first row (no music on device");
//				return;
//			}
//		}
		
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
	
	public boolean isExternalStorageWritable(){
		String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

}
