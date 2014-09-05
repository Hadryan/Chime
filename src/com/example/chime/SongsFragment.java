package com.example.chime;

import java.util.Map;

import models.Song;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

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
		//hide action bar because we just want a list view in this fragment
		//getActivity().getActionBar().hide();

		//Check if there is even music on the device.
		if (AddPlaylistView.getSongs() != null){
			SongAdapter songAdt = new SongAdapter(getActivity(), AddPlaylistView.getSongs());
			setListAdapter(songAdt);
		}
		
		//TODO: Going to have to switch to expandableListView class as I want to group Songs
		
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long ld){
		//TODO: make grouping selections when clicking on items, to be saved for a play list later
		//for instance
	}
	
	//save the current user selections here
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
	
	@Override
	public void onResume(){
		SongAdapter songAdt = new SongAdapter(getActivity(), AddPlaylistView.getSongs());
		setListAdapter(songAdt);
	    if(index!=-1){
	       this.getListView().setSelectionFromTop(index, top);
	    }
	}
	
	public void getSongList() {
		  //retrieve song info
		}
	
	
	

}
