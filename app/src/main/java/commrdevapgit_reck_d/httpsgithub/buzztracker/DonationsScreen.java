package commrdevapgit_reck_d.httpsgithub.buzztracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Set;

public class DonationsScreen extends AppCompatActivity {

    private Button addDonation;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations_screen);
        final String locationAddress = getIntent().getStringExtra("LocationAddress");

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference userInfo = mDatabase.getReference().child("User").child(mAuth.getCurrentUser().getUid());

        DatabaseReference mReference = mDatabase.getReference().child("Location").child(locationAddress).child("Donations");

        addDonation = (Button) findViewById(R.id.btnAddDonation);
        addDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddDonation = new Intent(DonationsScreen.this, AddDonation.class);
                goToAddDonation.putExtra("LocationAddress", locationAddress);
                startActivity(goToAddDonation);
            }
        });
        userInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String type = dataSnapshot.child("type").getValue().toString();
                if (!type.equals("EMPLOYEE")) {
                    addDonation.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    final Button donation = new Button(DonationsScreen.this);
                    final Donation don = d.getValue(Donation.class);
                    donation.setText(don.getShortDescription());
                    LinearLayout layoutLocation = (LinearLayout) findViewById(R.id.layoutLocation);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutLocation.addView(donation, params);

                    donation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent goToDetails = new Intent(DonationsScreen.this, DonationDetailsScreen.class);
                            goToDetails.putExtra("DonationDesc", don.getFullDescription());
                            goToDetails.putExtra("LocationAddress", locationAddress);
                            startActivity(goToDetails);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
