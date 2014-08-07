package models;

import java.util.ArrayList;
import java.util.Map;

import models.Song;

public class Playlists {
	
	//Initial data to be held by all play lists
	String name;
	Map<String, Song> songs;
	Map<String, ArrayList<Song>> artists;
	Map<String, ArrayList<Song>> genres;
	
	//Map to store playlists
	public static Map<String, ArrayList<String>> playlists;
	
	
	public Playlists(String name, Map<String, Song> songs, Map<String, ArrayList<Song>> artists,
			Map<String, ArrayList<Song>> genres){
		this.name = name;
		this.songs = songs;
		this.artists = artists;
		this.genres = genres;
	}
	
	
	public boolean newPlaylist(){
		return false;
		
	}
	
	public void populateSongListView(){
		
	}
	
	public void populateArtistView(){
		
	}
	
	public void populateGenreView(){
		
	}
	
	
	
	public boolean saveToDevice(){
		return false;
		
	}
	
	public boolean loadFromDevice(){
		return false;
		
	}
	
	

}
