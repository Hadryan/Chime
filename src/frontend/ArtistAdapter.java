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


public class ArtistAdapter extends BaseAdapter{

	private ArrayList<String> artists;
	private LayoutInflater songInf;
	static SparseBooleanArray mCheckStates; 
	
	public ArtistAdapter(Context c, ArrayList<String> theArtists){
		artists=theArtists;
		songInf=LayoutInflater.from(c);
		mCheckStates = new SparseBooleanArray(artists.size());
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
	    LinearLayout artistLay = (LinearLayout)songInf.inflate (R.layout.artist_list_item,
	    		parent, false);
	    //get artist view
	    TextView artistView = (TextView)artistLay.findViewById(R.id.Artist);
	    //get artist using position
	    String currArtist = artists.get(position);
	    //get the checked boxes
	    mCheckStates.get(position, false);
	    //set the artist text box
	    artistView.setText(currArtist);
	    //set position as tag
	    artistLay.setTag(position);
	    return artistLay;
	}
	
	/*
	 * Will be used to set list items to the checked position.
	 */
	public void setChecked(int position, boolean isChecked) {
        mCheckStates.put(position, isChecked);
    }
	
	/*
	 * Turns a list item to the unchecked postion.
	 */
	 public boolean isChecked(int position) {
        return mCheckStates.get(position, false);
    }
	
	/*
	 * Will toggle a list items check box.
	 */
	public void toggle(int position) {
        setChecked(position, !isChecked(position));
    }
	
}
