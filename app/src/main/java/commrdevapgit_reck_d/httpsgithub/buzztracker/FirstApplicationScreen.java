package commrdevapgit_reck_d.httpsgithub.buzztracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstApplicationScreen extends AppCompatActivity {

    private Button logout, locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_application_screen);

        logout = (Button) findViewById(R.id.btnLogout);
        locations = (Button) findViewById(R.id.btnLocations);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToWelcome =new Intent(FirstApplicationScreen.this, WelcomeScreen.class);
                startActivity(goToWelcome);
            }
        });

        locations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLocationsScreen = new Intent(FirstApplicationScreen.this, LocationsScreen.class);
                startActivity(goToLocationsScreen);
            }
        });
    }
}
