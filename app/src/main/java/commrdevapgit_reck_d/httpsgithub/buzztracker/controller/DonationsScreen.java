package commrdevapgit_reck_d.httpsgithub.buzztracker.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import commrdevapgit_reck_d.httpsgithub.buzztracker.R;
import commrdevapgit_reck_d.httpsgithub.buzztracker.model.Donation;
import commrdevapgit_reck_d.httpsgithub.buzztracker.model.DonationCategory;

public class DonationsScreen extends AppCompatActivity {

    private Button addDonation;
    private Spinner filterDonations;
    private EditText searchBar;

    private FirebaseAuth mAuth;

    private String locationAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations_screen);

        filterDonations = (Spinner) findViewById(R.id.spnFilter);
        searchBar = (EditText) findViewById(R.id.search);

        locationAddress = getIntent().getStringExtra("LocationAddress");

        mAuth = FirebaseAuth.getInstance();

        ArrayList<String> filterOptions = new ArrayList<>();
        filterOptions.add("All");
        DonationCategory[] options = DonationCategory.values();
        ArrayList<String> stringOps = new ArrayList<String>();
        for (DonationCategory dc: options) {
            stringOps.add(dc.toString());
        }
        filterOptions.addAll(stringOps);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, filterOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterDonations.setAdapter(adapter);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference userInfo = mDatabase.getReference().child("User").child(mAuth.getCurrentUser().getUid());

        final DatabaseReference mReference = mDatabase.getReference().child("Location").child(locationAddress).child("Donations");

        addDonation = (Button) findViewById(R.id.btnAddDonation);
        addDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddDonation = new Intent(DonationsScreen.this, AddDonationScreen.class);
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

        final ValueEventListener filterEvent = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                addFilteredDonations(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        mReference.addValueEventListener(filterEvent);

        filterDonations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mReference.addValueEventListener(filterEvent);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mReference.addValueEventListener(filterEvent);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void addFilteredDonations(DataSnapshot dataSnapshot) {
        LinearLayout layoutLocation = (LinearLayout) findViewById(R.id.layoutLocation);
        layoutLocation.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (DataSnapshot d : dataSnapshot.getChildren()) {
            Donation don = d.getValue(Donation.class);
            if ((filterDonations.getSelectedItem().toString().compareTo("All") == 0
                    || filterDonations.getSelectedItem().toString().compareTo(don.getCategory().toString()) == 0)
                    && (searchBar.getText().toString().length() == 0
                    || don.getShortDescription().toLowerCase().contains(searchBar.getText().toString().toLowerCase()))) {

                final String desc = don.getFullDescription();
                final Button donation = new Button(DonationsScreen.this);

                donation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goToDetails = new Intent(DonationsScreen.this, DonationDetailsScreen.class);
                        goToDetails.putExtra("DonationDesc", desc);
                        goToDetails.putExtra("LocationAddress", locationAddress);
                        startActivity(goToDetails);
                    }
                });

                donation.setText(don.getShortDescription());
                layoutLocation.addView(donation, params);
            }
        }
    }

}
