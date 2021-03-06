package commrdevapgit_reck_d.httpsgithub.buzztracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import commrdevapgit_reck_d.httpsgithub.buzztracker.R;

public class LocationsScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_screen);
        getLocations();
    }
    private void getLocations() {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mReference = mDatabase.getReference().child("Location");

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                LinearLayout layoutLocation = (LinearLayout) findViewById(R.id.layoutLocation);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                for (DataSnapshot l: dataSnapshot.getChildren()) {
                    String name = l.child("name").getValue().toString();
                    final String address = l.child("address").getValue().toString();
                    final Button location = new Button(LocationsScreen.this);
                    location.setText(name);

                    location.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent goToDetails = new Intent(LocationsScreen.this, LocationDetailsScreen.class);
                            goToDetails.putExtra("LocationAddress", address);
                            startActivity(goToDetails);
                        }
                    });

                    layoutLocation.addView(location, params);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
