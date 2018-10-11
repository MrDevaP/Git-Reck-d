package commrdevapgit_reck_d.httpsgithub.buzztracker;

import android.os.Bundle;
import android.app.Activity;
import android.widget.EditText;

public class LocationDetailsScreen extends Activity {

    private EditText details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details_screen);
        String locationName = getIntent().getStringExtra("LocationName");
        details = (EditText) findViewById(R.id.viewDetails);
        details.setText(Location.getLocations().get(locationName).toString());
    }

}
