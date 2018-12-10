package pe.edu.upc.warehouse.request;


import pe.edu.upc.warehouse.model.Cliente;

public interface AsyncResponse {
    void processFinish(Cliente output);
}