package com.example.chime;

import android.app.ActionBar;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SongsFragment {
	
	public static class songsFragment extends ListFragment{

		@Override
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			setListAdapter(new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, songsFromDevice);
			getActivity().getActionBar().hide();
		}
		
		@Override
		public void onListItemClick(ListView l, View v, int position, long ld){
			
		}
		
		//Creates the fragment
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
			//Inflate the layout for this fragment.
			return inflater.inflate(R.layout.songs_frag, container, false);
		}
	}

}
