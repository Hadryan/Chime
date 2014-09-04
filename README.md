Chime
=====

Location-based Music Player Android App!

Currently not fully functional yet. 

Notes I have been taking on the project:

A descripton of functions being used when a user wants to create a new play list and attach it to a
location.
Once the user presses the application icon (Chime), activity_main.xml(the main activity) is shown.

The user can now go the list view of play lists currently created. These will be stored on the users device. This is the playlist_view.xml file.
The play list view is shown. I want the list view here to take objects from the Playlists class
and list them in the adapter. Information in each list item will be: User-made title, (general) location in 
which this play list is for. Possible design choice in the future is when you press the list item once it expands to show more info maybe such as genres of music it entitles, or last time it was played. Then when it is pressed again it can be played.
 
The user can then press an icon on the top right of the screen to be brought to the create new playlist screen. This takes the user to new_playlist_view.xml. Music is the variable in which all of the song items will be held. They are automatically sorted while querying for them initially.

The layout for this activity has a tab view, showing either songs, albums or artists. A check box is shown beside every song item through each tab view. The user can tap on the songs he/she wants for the playlist. When the user is done here they can save the play list and it will be saved to the phone. This class also puts all of the music in the right sorted format and will then send it to the corresponding fragments. The fragments for the tab views are all created in the xml code for new_playlist_view.xml.

The first default fragment that will be created is the general list of songs that is on the users device. 
The artist and genre frags are only created when the user swipes to the other tabs corresponding
to these tabs.

More explanation: Little empty check boxes are shown beside every song in the song frag, artist in the artist frag
and genre in the genre frag. These are clicked by the user to be included in the play list. When the 
user swipes between the tabs, the onPause() function is called by the frags. In this function the 
songs/artists/genres that the user has already clicked will be saved. 

Then the user can click the top right button to save this play list. All of the songs from the frags
will be collected and sent to a new object (Playlist(name, songs, artists, genres)).

The user will then be prompted to define a location (or more so a fence) that this play list will
be played in. 


List of things to do:
-list songs in artist/genre format
-checkboxes beside song/artist/genre view to group items
-saving them to a playlist
-geo tag the playlist
-basic music playback
-music playback based on location

List of notes:
-need to consider when same subset of songs are to be included in the same play list, for instance when 
a user clicks some songs on the songs frag and then clicks on an artist in the artists frag that
includes the song which is already clicked.
In this instance both can be clicked but a checker must run after the playlist is
saved to make sure only one reference to the song (item) is included in the playlist and not two


credits:
-http://code.tutsplus.com/tutorials/create-a-music-player-on-android-project-setup--mobile-22764
