package pe.edu.upc.warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import pe.edu.upc.warehouse.service.PostHttpOrder;
import pe.edu.upc.warehouse.service.PostTaskOrder;

public class NewOrderActivity extends AppCompatActivity {
    private EditText txtNewOrderNombre;
    private TextView lblNewOrderTipo;
    private EditText txtNewOrderNumDocumento;
    private Button btnNewOrderAgregarProducto;
    private Button btnNewOrderFacturar;
    private String agregar_pedido_url;
    private EditText txtNewOrderNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarNewOrder);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtNewOrderNombre = (EditText)findViewById(R.id.txtNewOrderNombre);
        lblNewOrderTipo = (TextView) findViewById(R.id.lblNewOrderTipo);
        txtNewOrderNumDocumento = (EditText) findViewById(R.id.txtNewOrderNumDocumento);
        btnNewOrderAgregarProducto = (Button) findViewById(R.id.btnNewOrderAgregarProducto);
        btnNewOrderFacturar = (Button) findViewById(R.id.btnNewOrderFacturar);
        txtNewOrderNotas = (EditText) findViewById(R.id.txtNewOrderNotas);

        txtNewOrderNombre.setText(getIntent().getExtras().getString("curNombre"));
        lblNewOrderTipo.setText(getIntent().getExtras().getString("curTipoDocumento").toUpperCase() + ":");
        txtNewOrderNumDocumento.setText(getIntent().getExtras().getString("curNumDocumento"));

        agregar_pedido_url = getIntent().getExtras().getString("curAgregarPedidoUrl");

        btnNewOrderFacturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarPedido();
            }
        });

        btnNewOrderAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarProducto();
            }
        });
    }

    private void GuardarPedido() {
        if(txtNewOrderNombre.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese su nombre o Razón Social", Toast.LENGTH_SHORT).show();
            return;
        }
        if(txtNewOrderNumDocumento.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese su número de documento", Toast.LENGTH_SHORT).show();
            return;
        }
        if(txtNewOrderNotas.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese notas", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject postData = new JSONObject();
        try {
            String nota_extra = txtNewOrderNotas.getText().toString();

            JSONArray productos = new JSONArray();
            productos.put(getProductDetail("3","10"));
            productos.put(getProductDetail("1","4"));
            productos.put(getProductDetail("2","5"));

            postData.put("nota_extra", nota_extra);
            postData.put("productos", productos);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        new PostTaskOrder(NewOrderActivity.this).execute(agregar_pedido_url, postData.toString());
    }

    JSONObject getProductDetail(String producto_id, String cantidad){
        JSONObject product = new JSONObject();
        try {
            product.put("producto_id", producto_id);
            product.put("cantidad", cantidad);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return product ;
    }

    private void AgregarProducto() {
        Intent act = new Intent(this, ListProductsActivity.class);
        startActivity(act);
    }
}
