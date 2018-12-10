package pe.edu.upc.warehouse.request;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import pe.edu.upc.warehouse.model.Cliente;


public class HttpClienteData extends AsyncTask<Void, Void, String> {

    public AsyncResponse delegate = null;
    private Cliente cliente;
    private Context httpContext;
    private Integer clienteID;
    ProgressDialog progressDialog;


    public HttpClienteData(Cliente cliente, Context httpContext, Integer clienteID) {
        this.cliente = cliente;
        this.httpContext = httpContext;
        this.clienteID = clienteID;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();

        progressDialog = ProgressDialog.show(httpContext, "Descargando información del cliente", "Por favor, espere...");

    }

    @Override
    protected String doInBackground(Void... voids) {

        String result = null;

        try{

            String wsURL = "http://tramitar.net/UPC/Android/clientes/"+ clienteID +"/detalle";
            URL url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = inputStreamToString(in);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private String inputStreamToString(InputStream in) {

        String rLine = "";
        StringBuilder answer = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader rd = new BufferedReader(inputStreamReader);

        try{
            while ((rLine = rd.readLine()) != null){
                answer.append(rLine);
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        return answer.toString();
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        progressDialog.dismiss();

        try{

            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8"));

                if(jsonObject.getInt("code") == 200){
                    JSONObject jsonCliente = jsonObject.getJSONObject("cliente");
                    int id = jsonCliente.getInt("id");
                    String imagen = null;
                    if(!jsonCliente.isNull("imagen")){
                        imagen = jsonCliente.getString("imagen");
                    }

                    String tipo = jsonCliente.getString("tipo");

                    String nombre = null;
                    if(!jsonCliente.isNull("nombre")){
                        nombre = jsonCliente.getString("nombre");
                    }

                    String razon_social = null;
                    if(!jsonCliente.isNull("razon_social")){
                        razon_social = jsonCliente.getString("razon_social");
                    }

                    String tipo_documento = jsonCliente.getString("tipo_documento");

                    Long n_documento = jsonCliente.getLong("n_documento");


                    String email = null;
                    if(!jsonCliente.isNull("email")){
                        email = jsonCliente.getString("email");
                    }

                    String distrito = jsonCliente.getString("distrito");
                    String telefono = jsonCliente.getString("telefono");
                    String direccion = jsonCliente.getString("direccion");

                    String referencia = null;
                    if(!jsonCliente.isNull("referencia")){
                        referencia = jsonCliente.getString("referencia");
                    }

                    String latitud = null;
                    if(!jsonCliente.isNull("latitud")){
                        latitud = jsonCliente.getString("latitud");
                    }

                    String longitud = null;
                    if(!jsonCliente.isNull("longitud")){
                        longitud = jsonCliente.getString("longitud");
                    }

                    cliente.setId(id);
                    cliente.setImagen(imagen);
                    cliente.setTipo(tipo);
                    cliente.setNombre(nombre);
                    cliente.setRazon_social(razon_social);
                    cliente.setTipo_dni(tipo_documento);
                    cliente.setN_documento(n_documento);
                    cliente.setEmail(email);
                    cliente.setDistrito(distrito);
                    cliente.setTelefono(telefono);
                    cliente.setDireccion(direccion);
                    cliente.setReferencia(referencia);
                    cliente.setLatitud(latitud);
                    cliente.setLongitud(longitud);

                    delegate.processFinish(cliente);
                }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
