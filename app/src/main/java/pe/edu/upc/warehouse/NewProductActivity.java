package pe.edu.upc.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewProductActivity extends AppCompatActivity {
    @BindView(R.id.toolbarNewProduct)
    Toolbar toolbarNewProduct;
    @BindView(R.id.lblNewProductNombre)
    TextView lblNewProductNombre;
    @BindView(R.id.lblNewProductPrecio)
    TextView lblNewProductPrecio;
    @BindView(R.id.txtNewProductCantidad)
    EditText txtNewProductCantidad;
    @BindView(R.id.btnNewProductAgregar)
    Button btnNewProductAgregar;
    @BindView(R.id.btnNewProductCancelar)
    Button btnNewProductCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarNewProduct);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        lblNewProductNombre.setText(getIntent().getExtras().getString("curNombre"));
        lblNewProductPrecio.setText(getIntent().getExtras().getString("curPrecio"));
    }


    @OnClick({R.id.btnNewProductAgregar, R.id.btnNewProductCancelar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnNewProductAgregar:

                break;
            case R.id.btnNewProductCancelar:

                break;
        }
    }

    private void redireccionar(){
        Intent intent = new Intent(this, NewOrderActivity.class);
        startActivity(intent);
    }
}
