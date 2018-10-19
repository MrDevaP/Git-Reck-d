package commrdevapgit_reck_d.httpsgithub.buzztracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Set;

public class DonationsScreen extends AppCompatActivity {

    private Button addDonation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations_screen);

        final String locationName = getIntent().getStringExtra("LocationName");
        Set<String> donations = ((Location) Location.getLocations().get(locationName)).getDonations().keySet();

        addDonation = (Button) findViewById(R.id.btnAddDonation);
        addDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddDonation = new Intent(DonationsScreen.this, AddDonation.class);
                goToAddDonation.putExtra("LocationName", locationName);
                startActivity(goToAddDonation);
            }
        });

        if (!RegistrationScreen.isLocationEmployee) {
            addDonation.setEnabled(false);
        }

        for (String d: donations) {
            final Button donation = new Button(this);
            donation.setText(d);
            LinearLayout layoutLocation = (LinearLayout) findViewById(R.id.layoutLocation);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutLocation.addView(donation, params);

            donation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToDetails = new Intent(DonationsScreen.this, DonationDetailsScreen.class);
                    goToDetails.putExtra("DonationName", donation.getText().toString());
                    goToDetails.putExtra("LocationName", locationName);
                    startActivity(goToDetails);
                }
            });
        }
    }

}
