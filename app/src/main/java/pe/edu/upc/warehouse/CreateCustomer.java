package pe.edu.upc.warehouse;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
        String email = etEmail.getText().toString();
        String telefono = etTelefono.getText().toString();
        String fullName = nombres.concat(" ").concat(apellidos);

        ClienteBL cliente = new ClienteBL(tipoCliente,fullName, dni, email, telefono);

        Call<Cliente> call = ApiClient.getMyApiClient().addCustomer(cliente);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                hideLoading();
                Log.i(TAG, "respuesta del servidor "+ response.isSuccessful());
                if(response!=null && response.isSuccessful()){
                    exitActivity();
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
}
