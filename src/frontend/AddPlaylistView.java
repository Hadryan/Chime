package frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import backend.Playlists;
import backend.Song;

import com.example.chime.R;

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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.MenuItem;
import android.view.View;
import frontend.MusicService.MusicBinder;
import android.widget.PopupWindow;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;


public class AddPlaylistView extends FragmentActivity implements ActionBar.TabListener, OnClickListener{
	
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	private static final String TAG = "MusicGrabber";
	
	//Service to play music on an Android device
	private MusicService musicSrv;
	private Intent playIntent;
	private boolean musicBound=false;
	
	//All of the songs on the device
	public static ArrayList<Song> music = new ArrayList<Song>();
	
	//All Artists on the device
	static ArrayList<String> allArtists = new ArrayList<String>();
	
	//A nice map to store all of each artists songs
	static Map<String, ArrayList<Song>> songsInArtistFormat = new HashMap<String, ArrayList<Song>>();
	
	//new Playlist the user is currently editing
	Playlists newPlaylist;
	
	//Variables associated with Popupwindow
	PopupWindow newPlaylistNamePopup;
	Button saveNameOfPlaylist;
	TextView popupText;
	LinearLayout layoutOfPopup;
	
	/*
	 * Start the music service when this activity starts
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
		//popupwindow asking for the name of the new playlist
		saveNameOfPlaylist = new Button(this);
		popupText = new TextView(this);
		layoutOfPopup = new LinearLayout(this);
		saveNameOfPlaylist.setText("OK");
		popupText.setText("This is Popup Window.press OK to dismiss         it.");
		popupText.setPadding(0, 0, 0, 20);
		layoutOfPopup.setOrientation(1);
		layoutOfPopup.addView(popupText);
		layoutOfPopup.addView(saveNameOfPlaylist);
		saveNameOfPlaylist.setOnClickListener(this);
		newPlaylistNamePopup = new PopupWindow(layoutOfPopup, LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		newPlaylistNamePopup.setContentView(layoutOfPopup);
		newPlaylistNamePopup.showAsDropDown(layoutOfPopup, 0, 0);
		


		//implement tabs
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_playlist_view);
		
		Context context = getApplicationContext();
		CharSequence text = "Error!!";
		int duration = Toast.LENGTH_SHORT;
		
		// minSDKVersion is 11 or higher
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    //Prepare all music from the phone
	    musicGrabber();
	
		//Make a list of all artists if music is available
	    if (music != null){
	    	//Loop through the entire music library
	    	for (int i = 0;i < music.size();i++){
		        //Adds the track to its artist key in our map songsInArtistFormat
	    		Song tempSong = music.get(i);
	    		ArrayList<Song> emptyList = new ArrayList<Song>();
	    		if(songsInArtistFormat.get(tempSong.getArtist()) == null){
		    		songsInArtistFormat.put(tempSong.getArtist(), emptyList);
	    		}
	    		songsInArtistFormat.get(tempSong.getArtist()).add(tempSong);
		    }
	    }
	   
		//Add 2 tabs after we have set up our song list and artist list
	    actionBar.addTab(actionBar.newTab().setText("Songs").setTabListener(this));
	    actionBar.addTab(actionBar.newTab().setText("Artists").setTabListener(this));
	}
	
	/*
	 * Connect our activity to the music service
	 */
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
	
	/*
	 * Send a notification to the service to play the song we have picked.
	 */
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
	
	/*
	 * Switch the view to the artist view from the song view or vice versa.
	 */
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
	
	/*
	 * Eventually we will have *edit name of playlist*, *pick location*, and *cancel*
	 * as icons here in this menu
	 */
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
			//using this to stop playback of music currently
			stopService(playIntent);
			musicSrv=null;
			System.exit(0);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/*
	 * Used to stop music currently.
	 */
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

	/*
	 * Returns the list of all songs on the device. 
	 */
	public static ArrayList<Song> getSongs(){
		return music;
	}
	
	/*
	 * Returns all of the artists in an arraylist.
	 */
	public static ArrayList<String> getArtists(){
		//Loop through all of the keys because java doesnt like casting 
		//a keyset to an arraylist apparently
		if(songsInArtistFormat != null){
			for(String v : songsInArtistFormat.keySet()){
				allArtists.add(v);
			}
		}
		return allArtists;
	}
	
	/*
	 * Returns a map of all of the songs in <artist, list of artists songs format>
	 */
	public static Map<String, ArrayList<Song>> getSongsInArtistFormat(){
		return songsInArtistFormat;
	}
	

	/*
	 * Queries the devices external storage for music. Puts it into the list: music()
	 */
	public void musicGrabber(){
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


	@Override
	public void onClick(View v) {
		newPlaylistNamePopup.dismiss();
	}
	
	




	
	
	
	
	

}
