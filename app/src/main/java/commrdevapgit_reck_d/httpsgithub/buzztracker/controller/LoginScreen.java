package commrdevapgit_reck_d.httpsgithub.buzztracker.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import java.util.regex.Matcher;

import commrdevapgit_reck_d.httpsgithub.buzztracker.R;

/**
 * The type Login screen.
 */
public class LoginScreen extends AppCompatActivity {

    private EditText email;
    private EditText password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Button login = findViewById(R.id.btnValidate);
        Button cancel = findViewById(R.id.btnCancel);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        TextView failedLogin = findViewById(R.id.failedLogin);

        mAuth = FirebaseAuth.getInstance();

        failedLogin.setText("");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToWelcome =new Intent(LoginScreen.this, WelcomeScreen.class);
                startActivity(goToWelcome);
            }
        });
    }

    private void validate() {
        Editable emailText = email.getText();
        String emailString = emailText.toString();
        String newEmail = emailString.trim();
        //String newEmail = email.getText().toString().trim();
        Editable passwordText = password.getText();
        String passwordString = passwordText.toString();
        String newPassword = passwordString.trim();
        //String newPassword = password.getText().toString().trim();

        if (newEmail.isEmpty()) {
            email.setError("email required");
            email.requestFocus();
            return;
        }

        if (newPassword.isEmpty()) {
            password.setError("password required");
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

        mAuth.signInWithEmailAndPassword(newEmail, newPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent goToApp = new Intent(LoginScreen.this, FirstApplicationScreen.class);
                    goToApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goToApp);
                } else {
                    Context appContext = getApplicationContext();
                    Exception taskException = task.getException();
                    String taskMessage = taskException.getMessage();
                    String s = Objects.requireNonNull(taskMessage);
                    Toast toast = Toast.makeText(appContext, s, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}
