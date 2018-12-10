package pe.edu.upc.warehouse.storage.network;

import java.util.Map;

import pe.edu.upc.warehouse.model.Cliente;
import pe.edu.upc.warehouse.storage.network.entity.ClienteBL;
import pe.edu.upc.warehouse.storage.network.entity.Customers;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServicesApiInterface {

    @POST("/UPC/Android/clientes/agregar")
    Call<Cliente> addCustomer(@Body ClienteBL raw);

    @GET("/UPC/Android/clientes/listar")
    Call<Customers> allCustomer();

}
