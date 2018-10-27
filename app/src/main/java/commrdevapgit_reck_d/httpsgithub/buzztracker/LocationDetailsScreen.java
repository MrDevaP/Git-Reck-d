package commrdevapgit_reck_d.httpsgithub.buzztracker;

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

public class LocationDetailsScreen extends Activity {

    private EditText details;
    private Button donations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details_screen);
        final String locationAddress = getIntent().getStringExtra("LocationAddress");

        details = (EditText) findViewById(R.id.viewDetails);
        donations = (Button) findViewById(R.id.btnDonations);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mReference = mDatabase.getReference().child("Location").child(locationAddress);

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Location loc = dataSnapshot.getValue(Location.class);
                details.setText(loc.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        donations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDonationsScreen = new Intent(LocationDetailsScreen.this, DonationsScreen.class);
                goToDonationsScreen.putExtra("LocationAddress", locationAddress);
                startActivity(goToDonationsScreen);
            }
        });
    }

}
