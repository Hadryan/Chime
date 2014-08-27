package com.example.chime;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

import models.Song;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ArtistAdapter extends BaseAdapter{

	private ArrayList<Song> artists;
	private LayoutInflater songInf;
	
	public ArtistAdapter(Context c, ArrayList<Song> theArtists){
		artists=theArtists;
		songInf=LayoutInflater.from(c);
	}

	@Override
	public int getCount() {
		return artists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//map to song layout
	    LinearLayout songLay = (LinearLayout)songInf.inflate (R.layout.artist_list_item,
	    		parent, false);
	    //get artist view
	    TextView artistView = (TextView)songLay.findViewById(R.id.song_artist);
	    //get song using position
	    Song currSong = songs.get(position);
	    //set the artist text box
	    artistView.setText(currSong.getArtist());
	    //set position as tag
	    songLay.setTag(position);
	    return songLay;
	}
	
	

}
