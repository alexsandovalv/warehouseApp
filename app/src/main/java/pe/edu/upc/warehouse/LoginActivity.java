package pe.edu.upc.warehouse;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity:";

    @BindView(R.id.txtEmail)
    EditText txtEmail;
    @BindView(R.id.txtPassword)
    EditText txtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.login_progress)
    View mProgressView;
    @BindView(R.id.frm_login)
    LinearLayout mLoginFormView;


    private FirebaseAuth mAuth;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        final String email = txtEmail.getText().toString();
        final String password = txtPassword.getText().toString();

        if (!isUserValid(email, password)) {
            Toast.makeText(this, "Debes ingresar un Usuario o password", Toast.LENGTH_SHORT).show();
            return;
        }else{
            authLogin(email, password);
        }
    }

    @OnEditorAction(R.id.txtPassword)
    public boolean onViewClickedPassword(int actionId, KeyEvent event) {
        final String email = txtEmail.getText().toString();
        final String password = txtPassword.getText().toString();

        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) ||
                (actionId == EditorInfo.IME_ACTION_DONE)){
            hideSoftKeyboard();
            if (!isUserValid(email, password)) {
                Toast.makeText(this, "Debes ingresar un Usuario o password", Toast.LENGTH_SHORT).show();
                return false;
            }else{
                authLogin(email, password);
            }
            return true;
        }

        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent act = new Intent(this, InicioActivity.class);
            startActivity(act);
            finish();
        }
    }

    private boolean isUserValid(String userText, String password) {
        if (null == userText)
            return false;
        if (null == password)
            return false;
        if (userText.isEmpty())
            return false;
        if (password.isEmpty())
            return false;

        return true;
    }


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

    private void authLogin(String email, String password){
        showProgress(true);
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }
                        else {
                            showProgress(false);
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    public void hideSoftKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
