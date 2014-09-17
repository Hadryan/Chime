package backend;

import java.util.ArrayList;
import java.util.Map;

import backend.Song;


public class Playlists {
	
	//Initial data to be held by all playlists
	String name;
	ArrayList<Song> songList;

	public Playlists(String name, ArrayList<Song> songList){
		this.name = name;
		this.songList = songList;
	}
	
	public boolean saveToDevice(){
		return false;
		
	}
	
	public boolean loadFromDevice(){
		return false;
		
	}
	
	

}
