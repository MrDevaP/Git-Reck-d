package commrdevapgit_reck_d.httpsgithub.buzztracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class WelcomeScreen extends AppCompatActivity {

    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        readLocationData();

        login = (Button) findViewById(R.id.btnValidate);
        register = (Button) findViewById(R.id.btnRegister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLogin =new Intent(WelcomeScreen.this, LoginScreen.class);
                startActivity(goToLogin);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegistration =new Intent(WelcomeScreen.this, RegistrationScreen.class);
                startActivity(goToRegistration);
            }
        });
    }

    private void readLocationData() {
        InputStream locationCSV = getResources().openRawResource(R.raw.locationdata);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(locationCSV, Charset.forName("UTF-8"))
        );

        try {
            reader.readLine();
            String line = reader.readLine();
            while (line != null) {
                String[] items = line.split(",");

                String name = items[1];
                String type = items[8];
                String address = items[4] + ", " + items[5] + ", " + items[6] + " " + items[7];
                String phoneNumber = items[9];
                float latitude = Float.parseFloat(items[2]);
                float longitude = Float.parseFloat(items[3]);

                Location newLocation = new Location(name, type, address, phoneNumber, latitude, longitude);

                line = reader.readLine();
            }
        } catch (IOException e) {
            return;
        }


    }
}
