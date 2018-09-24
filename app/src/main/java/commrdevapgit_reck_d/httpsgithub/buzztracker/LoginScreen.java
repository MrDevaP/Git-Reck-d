package commrdevapgit_reck_d.httpsgithub.buzztracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity {

    private Button login;
    private Button cancel;
    private EditText username;
    private EditText password;
    private TextView failedLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        login = (Button) findViewById(R.id.btnValidate);
        cancel = (Button) findViewById(R.id.btnCancel);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        failedLogin = (TextView) findViewById(R.id.failedLogin);

        failedLogin.setText("");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(), password.getText().toString());
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

    private void validate(String userName, String Password) {
        if (userName == "user" && Password == "pass") {
            Intent goToApp = new Intent(LoginScreen.this, FirstApplicationScreen.class);
            startActivity(goToApp);
        } else {
            failedLogin.setText("Incorrect Login. Please try again.");
        }
    }
}
