package pe.edu.upc.warehouse;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pe.edu.upc.warehouse.storage.preferences.PreferencesHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btnIngresar;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.txtEmail);
        etPassword = findViewById(R.id.txtPassword);
        btnIngresar = findViewById(R.id.btnLogin);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etEmail.length()==0 || etPassword.length()==0)
                {
                    Toast.makeText(LoginActivity.this, "DEBE INGRESAR LAS CREDENCIALES", Toast.LENGTH_SHORT).show();
                }else
                {
                    validarUsuario(etEmail.getText().toString(), etPassword.getText().toString());
                }
            }
        });

        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if(validateForm()) {
                        Toast.makeText(LoginActivity.this, "DEBE INGRESAR LAS CREDENCIALES", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });


    }

    public void validarUsuario(String email, String password){
        Intent intent = new Intent(this, InicioActivity.class);
        startActivityForResult(intent, 0);
        finish();
    }

    /*
    private void showLoading(){
        flayLoading.setVisibility(View.VISIBLE);
    }

    private void hideLoading(){
        flayLoading.setVisibility(View.GONE);
    }


    private void saveSession(LogInBLResponse logInResponse) {
        PreferencesHelper.saveBLSession(this,logInResponse.getEmail(),logInResponse.getToken());
    }
    */

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseas salir de la aplicación");
        builder.setTitle("Mensaje...");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                return;
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean validateForm() {
        username= etEmail.getText().toString();
        password = etPassword.getText().toString();

        if(username.isEmpty())
        {
            etEmail.setError("Error campo User");
            return false;
        }
        if(password.isEmpty())
        {
            etPassword.setError("Error campo Password");
            return false;
        }
        return true;
    }
}
