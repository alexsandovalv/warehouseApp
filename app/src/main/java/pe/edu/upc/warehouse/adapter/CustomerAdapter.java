package pe.edu.upc.warehouse.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.edu.upc.warehouse.NewOrderActivity;
import pe.edu.upc.warehouse.R;
import pe.edu.upc.warehouse.model.Customer;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    private List<Customer> items;

    public CustomerAdapter(List<Customer> items){
        this.items = items;
    }

    public static class CustomerViewHolder extends RecyclerView.ViewHolder {
        public CardView customerCardView;
        public TextView lblListCustomersNombre;
        public TextView lblListCustomersTipo;
        public TextView lblListCustomersNumDocumento;

        public CustomerViewHolder(@NonNull View itemView){
            super(itemView);
            customerCardView = (CardView) itemView.findViewById(R.id.customerCardView);
            lblListCustomersNombre = (TextView) itemView.findViewById(R.id.lblListCustomersNombre);
            lblListCustomersNumDocumento = (TextView) itemView.findViewById(R.id.lblListCustomersNumDocumento);
            lblListCustomersTipo = (TextView) itemView.findViewById(R.id.lblListCustomersTipo);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_card, parent, false);

        return new CustomerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder customerViewHolder, final int i){
        customerViewHolder.lblListCustomersNombre.setText(items.get(i).getNombre() + " - " + items.get(i).getRazon_social());
        customerViewHolder.lblListCustomersNumDocumento.setText(items.get(i).getTipo_documento().toUpperCase() + ": " + items.get(i).getN_documento());
        customerViewHolder.lblListCustomersTipo.setText("Persona: " + items.get(i).getTipo());

        customerViewHolder.customerCardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("curId", items.get(i).getId());
                bundle.putString("curNombre", (items.get(i).getNombre() + " - " + items.get(i).getRazon_social()));
                bundle.putString("curTipoDocumento", items.get(i).getTipo_documento());
                bundle.putString("curNumDocumento", items.get(i).getN_documento());
                bundle.putString("curAgregarPedidoUrl", items.get(i).getAgregar_pedido_url());

                Intent iconIntent = new Intent(view.getContext(), NewOrderActivity.class);
                iconIntent.putExtras(bundle);
                view.getContext().startActivity(iconIntent);
            }
        });
    }
}