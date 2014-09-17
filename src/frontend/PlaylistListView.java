package frontend;

import java.util.ArrayList;

import backend.Playlists;

import com.example.chime.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PlaylistListView extends Activity{
	
	//Static list of all playlists held on this device
	public static ArrayList<Playlists> listOfAllPlaylists; 
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playlist_view);
		
	    // minSDKVersion is 11 or higher
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	} 
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.playlist_view_menu, menu);
		return super.onCreateOptionsMenu (menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		//Handle presses on the action bar items
		switch(item.getItemId()) {
		case R.id.add_playlist:
			Intent intent = new Intent(this, AddPlaylistView.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	

}
