package pe.edu.upc.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import pe.edu.upc.warehouse.model.Cliente;
import pe.edu.upc.warehouse.storage.network.ApiClient;
import pe.edu.upc.warehouse.storage.network.entity.ClienteBL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCustomer extends BaseActivity {

    public static final String TAG = CreateCustomer.class.getSimpleName();
    @BindView(R.id.cboDistrito)
    Spinner cboDistrito;
    @BindView(R.id.cboTipo)
    Spinner cboTipo;

    @BindView(R.id.et_direccion)
    EditText etDireccion;
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
    @BindView(R.id.frm_natural)
    LinearLayout frmNatural;
    @BindView(R.id.et_razonSocial)
    EditText etRazonSocial;
    @BindView(R.id.et_ruc)
    EditText etRuc;
    @BindView(R.id.frm_juridica)
    LinearLayout frmJuridica;
    @BindView(R.id.scroll_input)
    ScrollView scrollInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_customer);
        ButterKnife.bind(this);

        setTitle(R.string.title_create_customer);
        frmJuridica.setVisibility(View.GONE);
        enabledBack();
    }

    @OnClick({R.id.btnSave, R.id.btnCancel})
    public void onViewClicked(View view) {
        showLoading();
        hideSoftKeyboard();
        switch (view.getId()) {
            case R.id.btnSave:
                saveCustomer();
                break;
            case R.id.btnCancel:
                Intent intent = new Intent(this, InicioActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void saveCustomer() {
        String tipoCliente = cboTipo.getSelectedItem().toString();
        String nombres = "";
        String apellidos = "";
        String fullName = "";
        Long n_document = 0L;
        String razonsocial = "";
        String tipo_doc = "dni";
        if(frmNatural.getVisibility() == View.VISIBLE){
            nombres = etNombres.getText().toString();
            apellidos = etApellido.getText().toString();
            fullName = nombres.concat(" ").concat(apellidos);
            n_document = Long.parseLong(etDni.getText().toString());
            tipo_doc = "dni";
        }
        if(frmJuridica.getVisibility() == View.VISIBLE){
            n_document = Long.parseLong(etRuc.getText().toString());
            razonsocial = etRazonSocial.getText().toString();
            fullName = razonsocial;
            tipo_doc = "ruc";
        }

        String email = etEmail.getText().toString();
        String telefono = etTelefono.getText().toString();

        String distrito = cboDistrito.getSelectedItem().toString();
        String direccion = etDireccion.getText().toString();


        ClienteBL cliente = new ClienteBL(tipoCliente, fullName, tipo_doc, n_document, email, telefono);
        cliente.setDireccion(direccion);
        cliente.setDistrito(distrito);
        cliente.setRazon_social(razonsocial);

        if(!validateForm(fullName, n_document, email, telefono, direccion)){
            showMessage("Los datos ingresados tienen errores, verifique y vuelva a intentarlos.");
            return;
        }

        Call<Cliente> call = ApiClient.getMyApiClient().addCustomer(cliente);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                hideLoading();
                Log.i(TAG, "respuesta del servidor " + response.isSuccessful());
                if (response != null && response.isSuccessful()) {
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

    private void showMessage(String message) {
        Toast.makeText(this,
                message, Toast.LENGTH_LONG).show();
    }

    public void showLoading() {
        newUserProgressbar.setVisibility(View.VISIBLE);

    }

    public void hideLoading() {
        newUserProgressbar.setVisibility(View.GONE);
    }

    public boolean validateForm(String nombres, Long dniruc, String email, String telefono, String direccion){

        if(TextUtils.isEmpty(nombres))
            return false;
        if(TextUtils.isEmpty(email) && isEmailValid(email))
            return false;
        if(TextUtils.isEmpty(dniruc.toString()))
            return false;
        if(TextUtils.isEmpty(telefono))
            return false;
        if(TextUtils.isEmpty(direccion))
            return false;

        return true;
    }

    private void exitActivity() {
        this.finish();
    }

    private void cleanForm() {
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

    @OnItemSelected(R.id.cboTipo)
    public void onViewClicked() {
        cboTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String valueSelected = adapterView.getItemAtPosition(i).toString();
                Log.i(TAG, "OnItemSelectedListener : " + valueSelected);
                if(valueSelected.equalsIgnoreCase("Natural")){
                    etRazonSocial.setText("");
                    etRuc.setText("");
                    frmNatural.setVisibility(View.VISIBLE);
                    frmJuridica.setVisibility(View.GONE);
                }else if(valueSelected.equalsIgnoreCase("Juridica")){
                    etNombres.setText("");
                    etApellido.setText("");
                    etDni.setText("");
                    frmNatural.setVisibility(View.GONE);
                    frmJuridica.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.i(TAG, "do nothing");
            }
        });
    }
}
