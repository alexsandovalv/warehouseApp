package pe.edu.upc.warehouse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.warehouse.adapter.PedidoAdapter;
import pe.edu.upc.warehouse.model.Pedido;
import pe.edu.upc.warehouse.request.AsyncResponsePedidos;
import pe.edu.upc.warehouse.request.HttpPedidosData;

public class PedidosActivity extends AppCompatActivity implements AsyncResponsePedidos {

    private Integer clientID;
    private RecyclerView reciclador;
    private Button btnNuevoPedido;
    private LinearLayout linarSinPedidos;
    private List<Pedido> items = new ArrayList();
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            clientID = extras.getInt("ID");
            RellenarPedidos();
        }

        linarSinPedidos = findViewById(R.id.linearSinPedidos);
        btnNuevoPedido = findViewById(R.id.btnNuevoPedido);

        linarSinPedidos.setVisibility(View.INVISIBLE);

        reciclador = (RecyclerView) findViewById(R.id.rvReciclador);
        reciclador.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        reciclador.setLayoutManager(lManager);

        btnNuevoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PedidosActivity.this, "Agregar pedido a: " + clientID, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void RellenarPedidos() {

        HttpPedidosData wsPedidos = new HttpPedidosData(items, PedidosActivity.this, clientID);
        wsPedidos.delegate = this;
        wsPedidos.execute();
    }

    @Override
    public void processFinish(List<Pedido> pedidos) {
        this.items = pedidos;

        if(items.size() > 0){
            adapter = new PedidoAdapter(items);
            reciclador.setAdapter(adapter);
        } else {
            linarSinPedidos.setVisibility(View.VISIBLE);
            reciclador.setVisibility(View.INVISIBLE);
        }

    }

}
