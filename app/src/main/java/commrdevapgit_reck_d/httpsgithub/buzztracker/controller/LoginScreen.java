package commrdevapgit_reck_d.httpsgithub.buzztracker.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import commrdevapgit_reck_d.httpsgithub.buzztracker.R;

/**
 * The type Login screen.
 */
public class LoginScreen extends AppCompatActivity {

    private Button login;
    private Button cancel;
    private EditText email;
    private EditText password;
    private TextView failedLogin;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        login = (Button) findViewById(R.id.btnValidate);
        cancel = (Button) findViewById(R.id.btnCancel);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        failedLogin = (TextView) findViewById(R.id.failedLogin);

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
        String newEmail = email.getText().toString().trim();
        String newPassword = password.getText().toString().trim();

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

        mAuth.signInWithEmailAndPassword(newEmail, newPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent goToApp = new Intent(LoginScreen.this, FirstApplicationScreen.class);
                    goToApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goToApp);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
