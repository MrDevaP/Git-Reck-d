package commrdevapgit_reck_d.httpsgithub.buzztracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
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

/**
 * The type Add donation screen.
 */
public class AddDonationScreen extends AppCompatActivity {

    private EditText time;
    private EditText shortDesc;
    private EditText desc;
    private EditText value;
    private Spinner category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        time = findViewById(R.id.time);
        shortDesc = findViewById(R.id.shortDesc);
        desc = findViewById(R.id.description);
        value = findViewById(R.id.value);
        category = findViewById(R.id.category);

        final Intent intentTemp = getIntent();
        final String locationAddress = intentTemp.getStringExtra("LocationAddress");
        //final String locationAddress = getIntent().getStringExtra("LocationAddress");

        ArrayAdapter<String> adapter =
                new ArrayAdapter(this,android.R.layout.simple_spinner_item,
                        DonationCategory.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        Button newDonation = findViewById(R.id.btnAddDonation);
        Button cancel = findViewById(R.id.btnCancel);

        newDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable timeText = time.getText();
                Editable shortDescText = shortDesc.getText();
                Editable descText = desc.getText();
                Editable valueText = value.getText();
                Donation donation = new Donation(timeText.toString(),
                        shortDescText.toString(), descText.toString(),
                        (DonationCategory) category.getSelectedItem(), valueText.toString());

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
        Intent intentTemp = getIntent();
        final String locationAddress = intentTemp.getStringExtra("LocationAddress");
        //final String locationAddress = getIntent().getStringExtra("LocationAddress");

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDataReference1 = mDatabase.getReference();
        DatabaseReference mDataReference2 = mDataReference1.child("Location");
        DatabaseReference mDataReference3 = mDataReference2.child(locationAddress);
        DatabaseReference mDataReference4 = mDataReference3.child("Donations");
        String donationDescription = donation.getFullDescription();
        DatabaseReference mReference = mDataReference4.child(donationDescription);
        //DatabaseReference mReference = mDatabase.getReference().child("Location")
        //        .child(locationAddress).child("Donations").child(donation.getFullDescription());
        mReference.setValue(donation);
    }
}
