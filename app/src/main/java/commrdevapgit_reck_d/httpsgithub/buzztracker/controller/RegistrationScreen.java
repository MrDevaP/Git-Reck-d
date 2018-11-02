package commrdevapgit_reck_d.httpsgithub.buzztracker.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import commrdevapgit_reck_d.httpsgithub.buzztracker.R;
import commrdevapgit_reck_d.httpsgithub.buzztracker.model.User;
import commrdevapgit_reck_d.httpsgithub.buzztracker.model.UserType;

public class RegistrationScreen extends AppCompatActivity implements View.OnClickListener {

    private Button register;
    private Button cancel;
    private EditText email;
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
        email = (EditText) findViewById(R.id.email);
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
                registerUser();
                break;

            case R.id.btnCancel:
                Intent goToWelcome = new Intent(RegistrationScreen.this, WelcomeScreen.class);
                startActivity(goToWelcome);
                break;
        }
    }

    private void registerUser() {
        String newEmail = email.getText().toString().trim();
        String newPassword = password.getText().toString().trim();

        if (newEmail.isEmpty()) {
            email.setError("Email required");
            email.requestFocus();
            return;
        }

        if (newPassword.isEmpty()) {
            password.setError("Password required");
            password.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()) {
            email.setError("Please enter a valid email.");
            email.requestFocus();
            return;
        }

        if (newPassword.length() < 8 || !newPassword.matches(".*\\d+.*")) {
            password.setError("Password must be at least 8 characters and must contain at least one number.");
            password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(newEmail, newPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    sendUserDataToDatabase();
                    Toast.makeText(getApplicationContext(), "Registration Successful.", Toast.LENGTH_SHORT).show();
                    Intent goToApp = new Intent(RegistrationScreen.this, FirstApplicationScreen.class);
                    goToApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goToApp);
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "Email is already registered.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void sendUserDataToDatabase() {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mReference = mDatabase.getReference().child("User").child(mAuth.getUid());
        int selectedId = type.getCheckedRadioButtonId();
        User user = null;
        if (selectedId == R.id.radioGeneralUser) {
            user = new User(email.getText().toString().trim(), UserType.GENERAL);
        } else if (selectedId == R.id.radioLocationEmployee) {
            user = new User(email.getText().toString().trim(), UserType.EMPLOYEE);
        } else if (selectedId == R.id.radioLocationManager) {
            user = new User(email.getText().toString().trim(), UserType.MANAGER);
        } else if (selectedId == R.id.radioAdmin) {
            user = new User(email.getText().toString().trim(), UserType.ADMIN);
        }
        mReference.setValue(user);
    }
}
