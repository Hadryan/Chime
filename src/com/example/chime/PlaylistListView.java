package com.example.chime;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PlaylistListView extends Activity{
	
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
		getMenuInflater().inflate(R.menu.main, menu);
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
