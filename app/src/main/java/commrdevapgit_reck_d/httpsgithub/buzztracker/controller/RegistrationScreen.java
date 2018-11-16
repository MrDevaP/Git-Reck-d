package commrdevapgit_reck_d.httpsgithub.buzztracker.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Matcher;

import commrdevapgit_reck_d.httpsgithub.buzztracker.R;
import commrdevapgit_reck_d.httpsgithub.buzztracker.model.User;
import commrdevapgit_reck_d.httpsgithub.buzztracker.model.UserType;

/**
 * The type Registration screen.
 */
public class RegistrationScreen extends AppCompatActivity implements View.OnClickListener {

    private EditText email;
    private EditText password;
    private Spinner type;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        Button register = findViewById(R.id.btnRegister);
        Button cancel = findViewById(R.id.btnCancel);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        type = findViewById(R.id.type);
        mAuth = FirebaseAuth.getInstance();


        //noinspection unchecked
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout
                .simple_spinner_item, UserType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);

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
        Editable emailText = email.getText();
        String emailString = emailText.toString();
        String newEmail = emailString.trim();
        //String newEmail = email.getText().toString().trim();
        Editable passwordText = password.getText();
        String passwordString = passwordText.toString();
        String newPassword = passwordString.trim();
        //String newPassword = password.getText().toString().trim();

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

        Matcher emailMatch = Patterns.EMAIL_ADDRESS.matcher(newEmail);
        if (!emailMatch.matches()) {
            email.setError("Please enter a valid email.");
            email.requestFocus();
            return;
        }

        if ((newPassword.length() < 8) || !newPassword.matches(".*\\d+.*")) {
            password.setError("Password must be at least 8 characters and must contain at " +
                    "least one number.");
            password.requestFocus();
            return;
        }
        Task mAuthCreate = mAuth.createUserWithEmailAndPassword(newEmail, newPassword);
        mAuthCreate.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    sendUserDataToDatabase();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Registration Successful.", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent goToApp = new Intent(RegistrationScreen.this,
                            FirstApplicationScreen.class);
                    goToApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goToApp);
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast toast2 = Toast.makeText(getApplicationContext(),
                                "Email is already registered.", Toast.LENGTH_SHORT);
                        toast2.show();
                    } else {
                        Context appContext = getApplicationContext();
                        Exception e = task.getException();
                        Object obj = Objects.requireNonNull(e);
                        String appMessage = ((Exception) obj).getMessage();
                        Toast toastText = Toast.makeText(appContext, appMessage,
                                Toast.LENGTH_SHORT);
                        toastText.show();
                    }
                }
            }
        });
    }

    private void sendUserDataToDatabase() {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDataReference = mDatabase.getReference();
        DatabaseReference mChild1 = mDataReference.child("User");
        String mUid = mAuth.getUid();
        String o = Objects.requireNonNull(mUid);
        DatabaseReference mReference = mChild1.child(o);
        Editable emailText = email.getText();
        String emailString = emailText.toString();
        String emailTrim = emailString.trim();
        UserType userType = (UserType) type.getSelectedItem();
        User newUser = new User(emailTrim, userType);
        mReference.setValue(newUser);
    }
}
