package backend;

import java.util.ArrayList;
import java.util.Map;

import backend.Song;



public class Playlist {
	
	static public ArrayList<Playlist> allPlaylists = new ArrayList<Playlist>();
	
	//Variables for each playlist class
	String name;
	ArrayList<Song> songList;

	public Playlist(String name, ArrayList<Song> songList){
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
