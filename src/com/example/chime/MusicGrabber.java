package com.example.chime;

import java.util.ArrayList;
import java.util.List;

import android.content.ClipData.Item;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

/*
 * Notes: might want to use a SimpleCursorAdapter to grab a specific song/artist/genre using 
 * a search function in the near future
 */
public class MusicGrabber {
	
	ContentResolver mContentResolver;
	
	private static final String TAG = "MusicGrabber";
	
	//All of the songs on the device
	List<Item> mItems = new ArrayList<Item>();
	
	public MusicGrabber(ContentResolver cr){
		mContentResolver = cr;
	}
	
	//Checks to see if external storage is available
	public boolean isExternalStorageWritable(){
		String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	/*
	 * Prepares the storage for queries.
	 * TODO: Currently everything is repeated for external storage as well, bad form, 
	 * have to combine steps later on so tasks dont get repeated. 
	 */
	public void prepare(){
		
		Boolean isExternalAvailable = isExternalStorageWritable();
		
		Log.i(TAG, "Querying media...");
		
		Uri uriInternal = android.provider.MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
		Log.i(TAG, "URIINTERNAL: " + uriInternal.toString());
		
		//Perform a query on the content resolver. The URI we're passing specifies that
		//we want to query for all audio media on internal storage 
		//and external storage (eg. SD card).
		Cursor curInternal = mContentResolver.query(uriInternal, null, 
				MediaStore.Audio.Media.IS_MUSIC + " = 1", null, null);
		
		
		//If our queries failed...
		if (curInternal == null){
			Log.e(TAG, "Failed to move cursor to first row (no query results");
			return;
		}
		
		if(!curInternal.moveToFirst()){
			Log.e(TAG, "failed to move cursor to first row (no music on device");
			return;
		}
		
		//Lets see if external storage is available. This method does everything on
		//the external what we just did with the internal.
		if (isExternalAvailable){
			Uri uriExternal = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
			Log.i(TAG, "URIEXTERNAL: " + uriExternal.toString());
			Cursor curExternal = mContentResolver.query(uriExternal, null, 
					MediaStore.Audio.Media.IS_MUSIC + " = 1", null, null);
			if (curExternal == null){
				Log.e(TAG, "Failed to move cursor to first row (no query results");
				return;
			}
			
			if(!curExternal.moveToFirst()){
				Log.e(TAG, "failed to move cursor to first row (no music on device");
				return;
			}
		}
		
		Log.i(TAG, "Listing...");
		
		//Retrieve columns indices for where song info is
		int artistColumn = curInternal.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int titleColumn = curInternal.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int albumColumn = curInternal.getColumnIndex(MediaStore.Audio.Media.ALBUM);
        int durationColumn = curInternal.getColumnIndex(MediaStore.Audio.Media.DURATION);
        int idColumn = curInternal.getColumnIndex(MediaStore.Audio.Media._ID);
        
        //Do the same with external here...
        
        
        
	}
	
	

}
