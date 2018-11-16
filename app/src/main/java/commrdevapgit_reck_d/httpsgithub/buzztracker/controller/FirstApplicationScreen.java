package commrdevapgit_reck_d.httpsgithub.buzztracker.controller;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;

import commrdevapgit_reck_d.httpsgithub.buzztracker.R;

/**
 * The type First application screen.
 */
public class FirstApplicationScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_application_screen);

        Button logout = findViewById(R.id.btnLogout);
        Button locations = findViewById(R.id.btnLocations);

        mAuth = FirebaseAuth.getInstance();


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                Intent goToWelcome = new Intent(FirstApplicationScreen.this, WelcomeScreen.class);
                goToWelcome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goToWelcome);
            }
        });

        locations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLocationsScreen = new Intent(FirstApplicationScreen.this,
                        LocationsScreen.class);
                goToLocationsScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goToLocationsScreen);
            }
        });

        if (isServicesOk()) {
            Button locationMap = findViewById(R.id.btnLocationMap);
            locationMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToLocationMap = new Intent(FirstApplicationScreen.this,
                            LocationMapScreen.class);
                    goToLocationMap.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goToLocationMap);
                }
            });
        }
    }

    /**
     * Method to determine if services are running as expected
     *
     * @return boolean of whether or not services are running
     */
    private boolean isServicesOk() {
        GoogleApiAvailability googleAvail = GoogleApiAvailability.getInstance();
        int avail = googleAvail
                .isGooglePlayServicesAvailable(FirstApplicationScreen.this);

        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        if (avail == ConnectionResult.SUCCESS) {
            return true;
        } else if (instance.isUserResolvableError(avail)) {
            int ERROR = 9001;
            Dialog dialog = instance
                    .getErrorDialog(FirstApplicationScreen.this, avail, ERROR);
            dialog.show();
        } else {
            Toast toastText = Toast.makeText(FirstApplicationScreen.this, "Error",
                    Toast.LENGTH_SHORT);
            toastText.show();
        }
        return false;
    }
}
