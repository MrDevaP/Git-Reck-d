package commrdevapgit_reck_d.httpsgithub.buzztracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class RegistrationScreen extends AppCompatActivity implements View.OnClickListener {

    private Button register;
    private Button cancel;
    private EditText username;
    private EditText password;
    private RadioGroup type;
    private TextView failedRegistration;
    private FirebaseAuth mAuth;

    public static boolean isLocationEmployee = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        register = (Button) findViewById(R.id.btnRegister);
        cancel = (Button) findViewById(R.id.btnCancel);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        type = (RadioGroup) findViewById(R.id.radioTypes);
        failedRegistration = (TextView) findViewById(R.id.failedRegistration);
        mAuth = FirebaseAuth.getInstance();

        failedRegistration.setText("");

        register.setOnClickListener(this);

        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                finish();
                registerUser(username.getText().toString(), password.getText().toString());
                break;

            case R.id.btnCancel:
                Intent goToWelcome = new Intent(RegistrationScreen.this, WelcomeScreen.class);
                startActivity(goToWelcome);
                break;
        }
    }

    private void registerUser(String Username, String Password) {
        try {
            int selectedID = type.getCheckedRadioButtonId();
            if (selectedID == R.id.radioGeneralUser) {
                GeneralUser newUser = new GeneralUser(Username, Password);
            } else if (selectedID == R.id.radioLocationEmployee) {
                LocationEmployee newUser = new LocationEmployee(Username, Password);
                isLocationEmployee = true;
            } else if (selectedID == R.id.radioLocationManager) {
                LocationManager newUser = new LocationManager(Username, Password);
            } else if (selectedID == R.id.radioAdmin) {
                Admin newUser = new Admin(Username, Password);
            }
            Intent goToApp = new Intent(RegistrationScreen.this, FirstApplicationScreen.class);
            startActivity(goToApp);
        } catch (IllegalArgumentException i) {
            failedRegistration.setText(i.getLocalizedMessage());
        }
    }
}
