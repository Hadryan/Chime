package frontend;

import com.example.chime.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

//This class will encompass the actual mediacontroller service and deal with changes
//in user location.
public class GeoMusicPlayer extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.geo_music_player);
		
	}

}
