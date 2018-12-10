package pe.edu.upc.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pe.edu.upc.warehouse.model.Cliente;
import pe.edu.upc.warehouse.request.AsyncResponse;
import pe.edu.upc.warehouse.request.HttpClienteData;

public class ClientActivity extends BaseActivity implements AsyncResponse {

    private Integer clientID;
    private Cliente cliente;
    private TextView txtNombre;
    private TextView txtTipo;
    private TextView txtDNI;
    private TextView txtEmail;
    private TextView txtTelefono;
    private TextView txtDistrito;
    private TextView txtDireccion;
    private TextView txtReferencia;
    private Button btnPedidos;
    private Button btnAgregarPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        cliente = new Cliente();

        txtNombre = findViewById(R.id.txtNombre);
        txtTipo = findViewById(R.id.txtTipo);
        txtDNI = findViewById(R.id.txtDNI);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtDistrito = findViewById(R.id.txtDistrito);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtReferencia = findViewById(R.id.txtReferencia);
        btnPedidos = findViewById(R.id.btnPedidos);
        btnAgregarPedido = findViewById(R.id.btnAgregarPedido);

        enabledBack();
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            clientID = extras.getInt("ID");
            RellenarFichaCliente();
        }

        btnPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer clientID = (cliente.getId());
                Intent i = new Intent(getBaseContext(), PedidosActivity.class);
                i.putExtra("ID", clientID);
                startActivity(i);
            }
        });

    }

    private void RellenarFichaCliente() {

        HttpClienteData wsCliente = new HttpClienteData(cliente, ClientActivity.this, clientID);
        wsCliente.delegate = this;
        wsCliente.execute();
    }

    @Override
    public void processFinish(Cliente client) {

        this.cliente = client;

        String nombre = "Sin nombre";

        if(client.getTipo().equals("natural")){
            txtTipo.setText("Tipo: Persona natural");
            if(client.getNombre() != null){
                nombre = client.getNombre();
            }
        } else if(client.getTipo().equals("juridica")){
            txtTipo.setText("Tipo: Persona jurídica");
            if(client.getRazon_social() != null){
                nombre = client.getRazon_social();
            }
        }
        txtNombre.setText(" " + nombre);

        if(client.getTipo_dni().equals("dni")){
            txtDNI.setText("DNI: " + client.getN_documento());
        } else if(client.getTipo_dni().equals("ruc")){
            txtDNI.setText("RUC: " + client.getN_documento());
        }

        if(client.getEmail() != null){
            txtEmail.setText("E-mail: " + client.getEmail());
        } else {
            txtEmail.setText("E-mail: No definido");
        }

        txtTelefono.setText("Teléfono: " + client.getTelefono());
        txtDistrito.setText("Distrito: " + client.getDistrito());
        txtDireccion.setText("Dirección: " + client.getDireccion());

        if(client.getReferencia() != null){
            txtReferencia.setText("Referencia: " + client.getReferencia());
        } else {
            txtReferencia.setText("Referencia: No definido");
        }
    }
}
