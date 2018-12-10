package pe.edu.upc.warehouse.request;

import java.util.List;

import pe.edu.upc.warehouse.model.Pedido;

public interface AsyncResponsePedidos {
    void processFinish(List<Pedido> output);
}
