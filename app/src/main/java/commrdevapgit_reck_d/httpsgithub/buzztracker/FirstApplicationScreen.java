package commrdevapgit_reck_d.httpsgithub.buzztracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class FirstApplicationScreen extends AppCompatActivity {

    private Button logout, locations;
    private FirebaseAuth mAuth;

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
                Intent goToWelcome =new Intent(FirstApplicationScreen.this, WelcomeScreen.class);
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
    }
}
