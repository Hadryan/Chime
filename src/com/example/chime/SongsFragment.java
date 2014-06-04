package com.example.chime;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SongsFragment {
	
	public static class songsFragment extends ListFragment{

		@Override
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			setListAdapter(new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, songsFromDevice);
			
		}
		
		@Override
		public void onListItemClick(ListView l, View v, int position, long ld){
			
		}
	}

}
