package commrdevapgit_reck_d.httpsgithub.buzztracker;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LocationDetailsScreen extends Activity {

    private EditText details;
    private Button donations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details_screen);

        final String locationName = getIntent().getStringExtra("LocationName");
        details = (EditText) findViewById(R.id.viewDetails);
        details.setText(Location.getLocations().get(locationName).toString());
        details.setText(Location.getLocations().get(locationName).toString());
        donations = (Button) findViewById(R.id.btnDonations);

        donations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDonationsScreen = new Intent(LocationDetailsScreen.this, DonationsScreen.class);
                goToDonationsScreen.putExtra("LocationName", locationName);
                startActivity(goToDonationsScreen);
            }
        });
    }

}
