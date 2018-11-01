package commrdevapgit_reck_d.httpsgithub.buzztracker;

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class FirstApplicationScreen extends AppCompatActivity {

    private Button logout, locations, locationMap;
    private FirebaseAuth mAuth;

    private int ERROR = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_application_screen);

        logout = (Button) findViewById(R.id.btnLogout);
        locations = (Button) findViewById(R.id.btnLocations);

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
                Intent goToLocationsScreen = new Intent(FirstApplicationScreen.this, LocationsScreen.class);
                goToLocationsScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goToLocationsScreen);
            }
        });

        if (isServicesOk()) {
            locationMap = (Button) findViewById(R.id.btnLocationMap);
            locationMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToLocationMap = new Intent(FirstApplicationScreen.this, LocationMapScreen.class);
                    goToLocationMap.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goToLocationMap);
                }
            });
        }
    }
    public boolean isServicesOk() {
        int avail = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(FirstApplicationScreen.this);

        if (avail == ConnectionResult.SUCCESS) {
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(avail)) {
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(FirstApplicationScreen.this, avail, ERROR);
            dialog.show();
        } else {
            Toast.makeText(FirstApplicationScreen.this, "Error", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
