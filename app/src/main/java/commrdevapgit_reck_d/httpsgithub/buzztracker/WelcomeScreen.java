package commrdevapgit_reck_d.httpsgithub.buzztracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

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
}
