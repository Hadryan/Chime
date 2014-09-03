package com.example.chime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Song;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArtistsFragment extends ListFragment{
	
	Map<String, ArrayList<String>> songsInArtistFormat = null;
	
	//artists to be added to the playlist
	Map<String, Song> songsInArtistsFormatForPlaylist = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		//hide action bar because we just want a list view in this fragment
		getActivity().getActionBar().hide();
		
		//Check if there is even music on the device.
		if (AddPlaylistView.getSongs() != null){
			//trying this new adapter method of things
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
