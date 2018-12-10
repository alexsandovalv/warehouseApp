package pe.edu.upc.warehouse;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.edu.upc.warehouse.model.Cliente;
import pe.edu.upc.warehouse.storage.network.ApiClient;
import pe.edu.upc.warehouse.storage.network.entity.ClienteBL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCustomer extends BaseActivity {

    public static final String TAG = CreateCustomer.class.getSimpleName();
    @BindView(R.id.cboTipo)
    Spinner cboTipo;
    @BindView(R.id.et_nombres)
    EditText etNombres;
    @BindView(R.id.et_apellido)
    EditText etApellido;
    @BindView(R.id.et_dni)
    EditText etDni;
    @BindView(R.id.et_telefono)
    EditText etTelefono;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.frm_input)
    LinearLayout frmInput;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.fmr_buttons)
    LinearLayout fmrButtons;
    @BindView(R.id.new_customer_progressbar)
    ProgressBar newUserProgressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_customer);
        ButterKnife.bind(this);

        setTitle(R.string.title_create_customer);

        enabledBack();
    }

    @OnClick({R.id.btnSave, R.id.btnCancel})
    public void onViewClicked(View view) {
        showLoading();
        switch (view.getId()) {
            case R.id.btnSave:
                saveCustomer();
                break;
            case R.id.btnCancel:
                break;
        }
    }

    private void saveCustomer() {
        String tipoCliente = cboTipo.getSelectedItem().toString();
        String nombres = etNombres.getText().toString();
        String apellidos = etApellido.getText().toString();
        Long dni = Long.parseLong(etDni.getText().toString());
        String tipo_doc = "dni";

        if(String.valueOf(dni.intValue()).length() > 9)
            tipo_doc = "ruc";

        String email = etEmail.getText().toString();
        String telefono = etTelefono.getText().toString();
        String fullName = nombres.concat(" ").concat(apellidos);

        String distrito = "Surco";
        String direccion = "Av melgarejo 123";

        ClienteBL cliente = new ClienteBL(tipoCliente,fullName, tipo_doc, dni, email, telefono);
        cliente.setDireccion(direccion);
        cliente.setDistrito(distrito);

        Call<Cliente> call = ApiClient.getMyApiClient().addCustomer(cliente);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                hideLoading();
                Log.i(TAG, "respuesta del servidor "+ response.isSuccessful());
                if(response!=null && response.isSuccessful()){
                    cleanForm();
                    showMessage("Registro exitoso..");
                    //exitActivity();
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Log.i(TAG, "respuesta del servidor con error");
                hideLoading();
                showMessage(t.getMessage());
            }
        });

    }

    private void showMessage(String message){
        Toast.makeText(this,
                "error "+message,Toast.LENGTH_LONG).show();
    }

    public void showLoading() {
        newUserProgressbar.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        newUserProgressbar.setVisibility(View.GONE);
    }


    private void exitActivity(){
        this.finish();
    }

    private void cleanForm(){
        etTelefono.setText("");
        etDni.setText("");
        etApellido.setText("");
        etNombres.setText("");
        etEmail.setText("");
        cboTipo.setSelection(0);
    }

    private boolean isEmailValid(@NonNull String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
