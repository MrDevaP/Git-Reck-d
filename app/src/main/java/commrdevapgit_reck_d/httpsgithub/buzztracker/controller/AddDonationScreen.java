package commrdevapgit_reck_d.httpsgithub.buzztracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import commrdevapgit_reck_d.httpsgithub.buzztracker.R;
import commrdevapgit_reck_d.httpsgithub.buzztracker.model.Donation;
import commrdevapgit_reck_d.httpsgithub.buzztracker.model.DonationCategory;

public class AddDonationScreen extends AppCompatActivity {

    private EditText time;
    private EditText shortDesc;
    private EditText desc;
    private EditText value;
    private Spinner category;
    private Button newDonation;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        time = (EditText) findViewById(R.id.time);
        shortDesc = (EditText) findViewById(R.id.shortDesc);
        desc = (EditText) findViewById(R.id.description);
        value = (EditText) findViewById(R.id.value);
        category = (Spinner) findViewById(R.id.category);

        final String locationAddress = getIntent().getStringExtra("LocationAddress");

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, DonationCategory.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        newDonation = (Button) findViewById(R.id.btnAddDonation);
        cancel = (Button) findViewById(R.id.btnCancel);

        newDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Donation donation = new Donation(time.getText().toString(), shortDesc.getText().toString(), desc.getText().toString(),
                        (DonationCategory) category.getSelectedItem(), value.getText().toString());

                addDonationToLocation(donation);

                Intent goToAddDonation = new Intent(AddDonationScreen.this, DonationsScreen.class);
                goToAddDonation.putExtra("LocationAddress", locationAddress);
                startActivity(goToAddDonation);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddDonation = new Intent(AddDonationScreen.this, DonationsScreen.class);
                goToAddDonation.putExtra("LocationAddress", locationAddress);
                startActivity(goToAddDonation);
            }
        });
    }

    private void addDonationToLocation(Donation donation) {
        final String locationAddress = getIntent().getStringExtra("LocationAddress");

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mReference = mDatabase.getReference().child("Location").child(locationAddress).child("Donations").child(donation.getFullDescription());
        mReference.setValue(donation);
    }
}
