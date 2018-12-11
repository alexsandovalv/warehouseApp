package pe.edu.upc.warehouse.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pe.edu.upc.warehouse.NewProductActivity;
import pe.edu.upc.warehouse.R;
import pe.edu.upc.warehouse.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> items;

    public ProductAdapter(List<Product> items){
        this.items = items;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public CardView productCardView;
        public ImageView imgProducto;
        public TextView lblListProductsNombre;
        public TextView lblListProductsPrecio;

        public ProductViewHolder(@NonNull View itemView){
            super(itemView);
            productCardView = (CardView) itemView.findViewById(R.id.productCardView);
            imgProducto = (ImageView) itemView.findViewById(R.id.imgProducto);
            lblListProductsNombre = (TextView) itemView.findViewById(R.id.lblListProductsNombre);
            lblListProductsPrecio = (TextView) itemView.findViewById(R.id.lblListProductsPrecio);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);

        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, final int i){
        productViewHolder.lblListProductsNombre.setText(items.get(i).getNombre());
        productViewHolder.lblListProductsPrecio.setText("S/. " + String.valueOf(items.get(i).getPrecio()));
        Picasso.with(productViewHolder.imgProducto.getContext()).load(items.get(i).getImagen()).into(productViewHolder.imgProducto);

        productViewHolder.productCardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("curId", items.get(i).getId());
                bundle.putString("curNombre", items.get(i).getNombre());
                bundle.putString("curPrecio", ("S/. " + String.valueOf(items.get(i).getPrecio())));

                Intent iconIntent = new Intent(view.getContext(), NewProductActivity.class);
                iconIntent.putExtras(bundle);
                view.getContext().startActivity(iconIntent);
            }
        });
    }
}
