package commrdevapgit_reck_d.httpsgithub.buzztracker;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonationDetailsScreen extends AppCompatActivity {

    private EditText details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_details_screen);

        details = (EditText) findViewById(R.id.viewDetails);

        final String donationDesc = getIntent().getStringExtra("DonationDesc");
        final String locationAddress = getIntent().getStringExtra("LocationAddress");

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mReference = mDatabase.getReference().child("Location").child(locationAddress).child("Donations").child(donationDesc);

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Donation d = dataSnapshot.getValue(Donation.class);
                details.setText(d.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
