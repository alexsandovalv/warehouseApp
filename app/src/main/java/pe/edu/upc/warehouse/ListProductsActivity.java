package pe.edu.upc.warehouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.warehouse.model.Product;
import pe.edu.upc.warehouse.service.GetHttpProduct;

public class ListProductsActivity extends BaseActivity {
    private RecyclerView recicladorProductos;
    private List<Product> items = new ArrayList();
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);

        recicladorProductos = (RecyclerView)findViewById(R.id.recicladorProductos);
        recicladorProductos.setHasFixedSize(true);

        //Agregar un layout
        lManager = new LinearLayoutManager(this);
        recicladorProductos.setLayoutManager(lManager);
        enabledBack();

        FillProducts();
    }

    private void FillProducts() {
        GetHttpProduct ws = new GetHttpProduct(items, recicladorProductos, adapter,this);
        ws.execute();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
