package commrdevapgit_reck_d.httpsgithub.buzztracker;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Set;

public class LocationsScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_screen);
        Set<String> locations = Location.getLocations().keySet();
        for (String l: locations) {
            final Button newLocation = new Button(this);
            newLocation.setText(l);
            LinearLayout layoutLocation = (LinearLayout) findViewById(R.id.layoutLocation);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutLocation.addView(newLocation, params);

            newLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToDetails = new Intent(LocationsScreen.this, LocationDetailsScreen.class);
                    goToDetails.putExtra("LocationName", newLocation.getText().toString());
                    startActivity(goToDetails);
                }
            });
        }
    }

}
