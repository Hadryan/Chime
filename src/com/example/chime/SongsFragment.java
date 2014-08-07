package com.example.chime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Song;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Collections;
import java.util.Comparator;
import android.net.Uri;
import android.content.ContentResolver;
import android.database.Cursor;

public class SongsFragment extends ListFragment{
	
	Map<String, Song> songs = null;
	
	// the songs to be added to the playlist
	Map<String, Song> songsForPlaylist = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		//hide action bar because we just want a list view in this fragment
		getActivity().getActionBar().hide();
		
		//get songs in title format
		//songs = AddPlaylistView.getSongsInTitleFormat();
		
		
		
		//implementation of songsInTitleFormat for reference:
		//key = song.title, text2 = song.artist
		
		if (AddPlaylistView.getMusic() != null){
			//trying this new adapter method of things
			SongAdapter songAdt = new SongAdapter(getActivity(), AddPlaylistView.getMusic());
			
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
		
	}
	
	public void getSongList() {
		  //retrieve song info
		}
	
	
	

}
