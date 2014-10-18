package frontend;

import java.util.Map;

import backend.Song;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.widget.CheckBox;

public class SongsFragment extends ListFragment{
	
	Map<String, Song> songs = null;
	
	// the songs to be added to the playlist
	Map<String, Song> songsForPlaylist = null;
	
	//position in the list to be saved 
	private int index = -1;
	private int top = 0;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//Check if there is even music on the device.
		if (AddPlaylistView.getSongs() != null){
			SongAdapter songAdt = new SongAdapter(getActivity(), AddPlaylistView.getSongs());
			setListAdapter(songAdt);
		}
		
	}
	
	//save the current user selections here (currently not working :( )
	@Override
	public void onPause(){
		super.onPause();
	    try{
	       index = this.getListView().getFirstVisiblePosition();
	       View v = this.getListView().getChildAt(0);
	       top = (v == null) ? 0 : v.getTop();
	    }
	    catch(Throwable t){
	       t.printStackTrace();
	    }
	}
	
	/*
	 * Want it to save the index position we are at but currently not working
	 */
	@Override
	public void onResume(){
		super.onResume();
		SongAdapter songAdt = new SongAdapter(getActivity(), AddPlaylistView.getSongs());
		setListAdapter(songAdt);
	    if(index!=-1){
	       this.getListView().setSelectionFromTop(index, top);
	    }
	}
	
	
	
	
	

}
