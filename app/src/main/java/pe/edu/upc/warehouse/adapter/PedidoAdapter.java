package pe.edu.upc.warehouse.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import pe.edu.upc.warehouse.R;
import pe.edu.upc.warehouse.model.Pedido;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>{


    private List<Pedido> items;

    public PedidoAdapter(List<Pedido> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PedidoAdapter.PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.pedido_card, viewGroup, false);
        return new PedidoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoAdapter.PedidoViewHolder pedidoViewHolder, int i) {


        pedidoViewHolder.txtPedidoID.setText("Pedido ID: " + items.get(i).getId());

        if(items.get(i).getEstado().equals("pendiente")){
            pedidoViewHolder.txtPedidoEstado.setText("Pendiente de entrega");
            pedidoViewHolder.txtPedidoEntrega.setText("Pendiente de entrega");
        } else {
            pedidoViewHolder.txtPedidoEstado.setText("Entregado");
            if(items.get(i).getEntrega() != null){
                pedidoViewHolder.txtPedidoEntrega.setText(items.get(i).getEntrega());
            }
        }
        pedidoViewHolder.txtPedidoFecha.setText(items.get(i).getCreacion());

        if(items.get(i).getNota() != null){
            pedidoViewHolder.txtNota.setText(items.get(i).getNota());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder{

        public CardView cvPedidoCard;
        public TextView txtPedidoID;
        public TextView txtPedidoEstado;
        public TextView txtPedidoEntrega;
        public TextView txtPedidoFecha;
        public TextView txtNota;

        public PedidoViewHolder(View itemView) {
            super(itemView);
            cvPedidoCard = (CardView)itemView.findViewById(R.id.cvPedidoCard);
            txtPedidoID = (TextView)itemView.findViewById(R.id.txtPedidoID);
            txtPedidoEstado = (TextView)itemView.findViewById(R.id.txtPedidoEstado);
            txtPedidoEntrega = (TextView)itemView.findViewById(R.id.txtPedidoEntrega);
            txtPedidoFecha = (TextView)itemView.findViewById(R.id.txtPedidoFecha);
            txtNota = (TextView)itemView.findViewById(R.id.txtNota);
        }
    }


}
