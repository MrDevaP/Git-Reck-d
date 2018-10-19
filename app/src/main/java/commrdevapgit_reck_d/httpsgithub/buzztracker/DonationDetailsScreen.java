package commrdevapgit_reck_d.httpsgithub.buzztracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class DonationDetailsScreen extends AppCompatActivity {

    private EditText details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_details_screen);

        final String donationName = getIntent().getStringExtra("DonationName");
        final String locationName = getIntent().getStringExtra("LocationName");
        details = (EditText) findViewById(R.id.viewDetails);
        details.setText(((Location) Location.getLocations().get(locationName)).getDonations().get(donationName).toString());
    }
}
