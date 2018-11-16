package commrdevapgit_reck_d.httpsgithub.buzztracker.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import commrdevapgit_reck_d.httpsgithub.buzztracker.R;
import commrdevapgit_reck_d.httpsgithub.buzztracker.model.Donation;

/**
 * The type Donation details screen.
 */
public class DonationDetailsScreen extends AppCompatActivity {

    private EditText details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_details_screen);

        details = findViewById(R.id.viewDetails);

        Intent intentTemp1 = getIntent();
        final String donationDesc = intentTemp1.getStringExtra("DonationDesc");
        //final String donationDesc = getIntent().getStringExtra("DonationDesc");
        Intent intentTemp2 = getIntent();
        final String locationAddress = intentTemp2.getStringExtra("LocationAddress");
        //final String locationAddress = getIntent().getStringExtra("LocationAddress");

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mReference1 = mDatabase.getReference();
        DatabaseReference mReference2 = mReference1.child("Location");
        DatabaseReference mReference3 = mReference2.child(locationAddress);
        DatabaseReference mReference4 = mReference3.child("Donations");
        DatabaseReference mReference = mReference4.child(donationDesc);
        //DatabaseReference mReference = mDatabase.getReference().child("Location")
        //        .child(locationAddress).child("Donations").child(donationDesc);

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Donation d = dataSnapshot.getValue(Donation.class);
                Object o = Objects.requireNonNull(d);
                details.setText(o.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
