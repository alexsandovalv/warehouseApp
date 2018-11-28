package pe.edu.upc.warehouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);



    }
}
