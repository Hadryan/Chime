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

public class GenresFragment extends ListFragment{
	
	//TODO: songsFromDevice is going to be the content that will derive from songs list 
	//given from the phone
	
	Map<String, ArrayList<Song>> songsInGenreFormat = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//hide action bar because we just want a list view in this fragment
		getActivity().getActionBar().hide();
		
		//get songs in title format
		songsInGenreFormat = AddPlaylistView.getSongsInArtistFormat();
		
		//implementation of songsInTitleFormat for reference:
		//key = song.title, text2 = song.artist
		
		//convert key set of songsInTitleFormat to a string key set because android is dumb and wont recognize is directly
		//IMPORTANT: WILL ONLY START WITH SONG TITLES AND NOT THE ACTUAL ITEMS
		if (songsInGenreFormat != null){
			//return an error toast and don't continue on
			List<String> list = new ArrayList<String>(songsInGenreFormat.keySet());
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
					list);
			
			setListAdapter(adapter);
		}
		
		
		//TODO: Going to have to switch to expandableListView class as I want to group items
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long ld){
		
	}
	
	
	

}
