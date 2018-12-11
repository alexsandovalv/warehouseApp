package pe.edu.upc.warehouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.warehouse.model.Customer;
import pe.edu.upc.warehouse.service.GetHttpCustomer;

public class ListCustomersActivity extends BaseActivity {
    private RecyclerView reciclador;
    private List<Customer> items = new ArrayList();
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customers);

        reciclador = (RecyclerView)findViewById(R.id.reciclador);
        reciclador.setHasFixedSize(true);

        //Agregar un layout
        lManager = new LinearLayoutManager(this);
        reciclador.setLayoutManager(lManager);

        enabledBack();

        FillCustomer();
    }

    private void FillCustomer() {
        GetHttpCustomer ws = new GetHttpCustomer(items, reciclador, adapter,this);
        ws.execute();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
