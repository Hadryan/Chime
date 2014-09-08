package com.example.chime;

import java.util.ArrayList;
import java.util.Map;

import models.Song;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ArtistsFragment extends ListFragment{
	
	Map<String, ArrayList<String>> songsInArtistFormat = null;
	
	//artists to be added to the playlist
	Map<String, Song> songsInArtistsFormatForPlaylist = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		
		if (AddPlaylistView.getArtists() != null){
			ArtistAdapter songAdt = new ArtistAdapter(getActivity(),
					AddPlaylistView.getArtists());
			setListAdapter(songAdt);
		}
		
		
		//TODO: Going to have to switch to expandableListView class as I want to group items
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long ld){
		
	}
	

	

}
