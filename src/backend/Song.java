package backend;

import java.util.ArrayList;

import android.content.ContentUris;
import android.net.Uri;

public class Song{
    long id;
    String artist;
    String title;
    String album;
    long duration;
    
    //List of playlists this song belongs to
	private ArrayList<Playlist> listOfPlaylists = new ArrayList<Playlist>();
    
    public Song(long id, String artist, String title, String album, long duration) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.album = album;
        this.duration = duration;
    }
    public long getId() {
        return id;
    }
    public String getArtist() {
        return artist;
    }
    public String getTitle() {
        return title;
    }
    public String getAlbum() {
        return album;
    }
    public long getDuration() {
        return duration;
    }
    public Uri getURI() {
        return ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.INTERNAL_CONTENT_URI, id);
    }
    
    //This will add the song to the playlist (when the user clicks on the checkbox) 
    public void addToPlaylist(Playlist current){
    	listOfPlaylists.add(current);
    }
    
    //Will delete 
    public void deleteFromPlaylist(Playlist current){
    	listOfPlaylists.remove(current);
    }
    
    
}
