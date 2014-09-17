package frontend;

import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

import com.example.chime.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import backend.Song;


public class SongAdapter extends BaseAdapter {
	
	private ArrayList<Song> songs;
	private LayoutInflater songInf;
	static SparseBooleanArray mCheckStates; 
	
	public SongAdapter(Context c, ArrayList<Song> theSongs){
		songs=theSongs;
		songInf=LayoutInflater.from(c);
		mCheckStates = new SparseBooleanArray(songs.size());
	}

	@Override
	public int getCount() {
		return songs.size();
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
	    LinearLayout songLay = (LinearLayout)songInf.inflate (R.layout.song_list_item,
	    		parent, false);
	    //get title and artist views
	    TextView songView = (TextView)songLay.findViewById(R.id.song_title);
	    TextView artistView = (TextView)songLay.findViewById(R.id.song_artist);
	    //get song using position
	    Song currSong = songs.get(position);
	    mCheckStates.get(position, false);
	    //get title and artist strings
	    songView.setText(currSong.getTitle());
	    artistView.setText(currSong.getArtist());
	    //set position as tag
	    songLay.setTag(position);
	    return songLay;
	}
	
	/*
	 * Turns a list item to the unchecked postion.
	 */
	 public boolean isChecked(int position) {
        return mCheckStates.get(position, false);
    }
	
	/*
	 * Will be used to set list items to the checked position.
	 */
	public void setChecked(int position, boolean isChecked) {
        mCheckStates.put(position, isChecked);
    }
	
	/*
	 * Will toggle a list items check box.
	 */
	public void toggle(int position) {
        setChecked(position, !isChecked(position));
    }

}
