package commrdevapgit_reck_d.httpsgithub.buzztracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddDonation extends AppCompatActivity {

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

        final String locationName = getIntent().getStringExtra("LocationName");
        final Location location = (Location) Location.getLocations().get(locationName);

        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, DonationCategory.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter2);

        newDonation = (Button) findViewById(R.id.btnAddDonation);
        cancel = (Button) findViewById(R.id.btnCancel);

        newDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Donation donation = new Donation(time.getText().toString(), location, shortDesc.getText().toString(), desc.getText().toString(),
                        (DonationCategory) category.getSelectedItem(), value.getText().toString());

                location.addDonation(donation);

                Intent goToAddDonation = new Intent(AddDonation.this, DonationsScreen.class);
                goToAddDonation.putExtra("LocationName", locationName);
                startActivity(goToAddDonation);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddDonation = new Intent(AddDonation.this, DonationsScreen.class);
                goToAddDonation.putExtra("LocationName", locationName);
                startActivity(goToAddDonation);
            }
        });
    }
}
