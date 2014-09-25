package frontend;

import java.util.ArrayList;

import backend.Playlists;

import com.example.chime.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PlaylistListView extends Activity implements OnClickListener{
	
	//Variables associated with Popupwindow
	private PopupWindow newPlaylistNamePopup;
	private Button saveNameOfPlaylist;
	private TextView popupText;
	private EditText textField;
	private LinearLayout layoutOfPopup;
	
	//Static list of all playlists held on this device
	public static ArrayList<Playlists> listOfAllPlaylists; 
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playlist_view);
		popUpWindow();
		
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
			//show the popup window here so we can grab the string for the newplaylistname
			newPlaylistNamePopup.showAtLocation(layoutOfPopup, Gravity.CENTER, 50, 50);
			
			//newPlaylistNamePopup.update();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public void onClick(View v) {
		if(v == saveNameOfPlaylist){
			//get text from our textfield
			String newPlaylistName = textField.getText().toString();
			newPlaylistNamePopup.dismiss();
			//extra content is the string for the newplaylistname
			Intent intent = new Intent(this, AddPlaylistView.class);
			intent.putExtra("NAME_OF_NEW_PLAYLIST", newPlaylistName);
			startActivity(intent);
		}
		
	}
	
	public void popUpWindow(){
		//popupwindow asking for the name of the new playlist
		
		//items to show
		popupText = new TextView(this);
		textField = new EditText(this);
		saveNameOfPlaylist = new Button(this);
		
		//view
		layoutOfPopup = new LinearLayout(this);
		
		//function calls
		saveNameOfPlaylist.setText("Set name of playlist");
		popupText.setText("Name your playlist: ");
		textField.setHint("enter name here");
		
		//settings
		popupText.setPadding(0, 0, 0, 20);
		layoutOfPopup.setOrientation(1);
		layoutOfPopup.addView(popupText);
		layoutOfPopup.addView(textField);
		layoutOfPopup.addView(saveNameOfPlaylist);
		
		saveNameOfPlaylist.setOnClickListener(this);
		newPlaylistNamePopup = new PopupWindow(layoutOfPopup, LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		newPlaylistNamePopup.setFocusable(true);
		newPlaylistNamePopup.update();
		
		newPlaylistNamePopup.setContentView(layoutOfPopup);
	}

}
