package com.example.chime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.chime.AddPlaylistView;
import models.MusicGrabber.Item;
import android.app.ActionBar;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SongsFragment extends ListFragment{
	
	Map<String, Item> songsInTitleFormat = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		//hide action bar because we just want a list view in this fragment
		getActivity().getActionBar().hide();
		
		//get songs in title format
		songsInTitleFormat = AddPlaylistView.getSongsInTitleFormat();
		
		//implementation of songsInTitleFormat for reference:
		//key = song.title, text2 = song.artist
		
		//convert keyset of songsInTitleFormat to a string key set because android is dumb and wont recognize is directly
		//IMPORTANT: WILL ONLY START WITH SONG TITLES AND NOT THE ACTUAL ITEMS
		if (songsInTitleFormat == null){
			//return an error toast and don't continue on
			
		}
		List<String> list = new ArrayList<String>(songsInTitleFormat.keySet());
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
				list);
		
		setListAdapter(adapter);
		
		//TODO: Going to have to switch to expandableListView class as I want to group items
		
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long ld){
		//TODO: make grouping selections when clicking on items, to be saved for a playlist later
		//for instance
	}
	
	//Creates the fragment
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		//Inflate the layout for this fragment.
		return inflater.inflate(R.id.listView1, container, false);
	}
	
	
	

}
