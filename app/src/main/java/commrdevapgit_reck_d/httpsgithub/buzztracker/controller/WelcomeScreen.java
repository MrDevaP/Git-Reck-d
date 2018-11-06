package commrdevapgit_reck_d.httpsgithub.buzztracker.controller;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import commrdevapgit_reck_d.httpsgithub.buzztracker.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;

public class WelcomeScreen extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private Button register;
    private FirebaseAuth mAuth;

    private static final int ERROR = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
//        readLocationData();

        login = (Button) findViewById(R.id.btnValidate);
        register = (Button) findViewById(R.id.btnRegister);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            finish();
            startActivity(new Intent(WelcomeScreen.this, FirstApplicationScreen.class));
        }

        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnValidate:
                finish();
                Intent goToLogin =new Intent(WelcomeScreen.this, LoginScreen.class);
                goToLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goToLogin);
                break;

            case R.id.btnRegister:
                Intent goToRegistration =new Intent(WelcomeScreen.this, RegistrationScreen.class);
                goToRegistration.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goToRegistration);
                break;
        }
    }

//    private void readLocationData() {
//        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
//        InputStream locationCSV = getResources().openRawResource(R.raw.locationdata);
//        BufferedReader reader = new BufferedReader(
//                new InputStreamReader(locationCSV, Charset.forName("UTF-8"))
//        );
//
//        try {
//            reader.readLine();
//            String line = reader.readLine();
//            while (line != null) {
//                String[] items = line.split(",");
//
//                String name = items[1];
//                String type = items[8];
//                String address = items[4] + ", " + items[5] + ", " + items[6] + " " + items[7];
//                String phoneNumber = items[9];
//                float latitude = Float.parseFloat(items[2]);
//                float longitude = Float.parseFloat(items[3]);
//
//                Location newLocation = new Location(name, type, address, phoneNumber, latitude, longitude);
//                DatabaseReference mReference = mDatabase.getReference().child("Location").child(newLocation.getAddress());
//                mReference.setValue(newLocation);
//
//                line = reader.readLine();
//            }
//        } catch (IOException e) {
//            return;
//        }
//
//
//    }
}
