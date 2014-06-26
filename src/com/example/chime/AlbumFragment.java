package com.example.chime;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AlbumFragment extends ListFragment{
	
	//TODO: songsFromDevice is going to be the content that will derive from songs list 
	//given from the phone
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		String[] songsFromDevice = null;
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, songsFromDevice));
		getActivity().getActionBar().hide();
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long ld){
		
	}
	
	//Creates the fragment
	//TODO: change placeholder *container* to be the parent of the inflated layout
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		//Inflate the layout for this fragment.
		return inflater.inflate(R.layout.album_frag, container, false);
	}
	

}
