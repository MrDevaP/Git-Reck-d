package commrdevapgit_reck_d.httpsgithub.buzztracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import commrdevapgit_reck_d.httpsgithub.buzztracker.R;
import commrdevapgit_reck_d.httpsgithub.buzztracker.model.Location;

/**
 * The type Location details screen.
 */
public class LocationDetailsScreen extends Activity {

    private EditText details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details_screen);
        //Intent intentTemp = getIntent();
        //final String locationAddress = intentTemp.getStringExtra("LocationAddress");
        Intent intent = getIntent();
        final String locationAddress = intent.getStringExtra("LocationAddress");

        details = findViewById(R.id.viewDetails);
        Button donations = findViewById(R.id.btnDonations);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference referenceTemp = mDatabase.getReference();
        DatabaseReference refChild = referenceTemp.child("Location");
        DatabaseReference mReference = refChild.child(locationAddress);

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Location loc = dataSnapshot.getValue(Location.class);
                if (loc != null) {
                    details.setText(loc.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        donations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDonationsScreen = new Intent(LocationDetailsScreen.this,
                        DonationsScreen.class);
                goToDonationsScreen.putExtra("LocationAddress", locationAddress);
                startActivity(goToDonationsScreen);
            }
        });
    }

}
