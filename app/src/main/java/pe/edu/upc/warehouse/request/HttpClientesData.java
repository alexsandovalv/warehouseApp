package pe.edu.upc.warehouse.request;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

import pe.edu.upc.warehouse.R;
import pe.edu.upc.warehouse.model.Cliente;

public class HttpClientesData extends AsyncTask<Void, Void, String> {

    private List<Cliente> httpList;
    private Context httpContext;
    ProgressDialog progressDialog;
    MarkerOptions marker;
    GoogleMap mMap;
    private Marker marca;


    public HttpClientesData(List<Cliente> httpList, Context httpContext, GoogleMap mMap) {
        this.httpList = httpList;
        this.httpContext = httpContext;
        this.mMap = mMap;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();

        progressDialog = ProgressDialog.show(httpContext, "Descargando información de clientes", "Por favor, espere...");

    }

    @Override
    protected String doInBackground(Void... voids) {

        String result = null;

        try{

            String wsURL = "http://tramitar.net/UPC/Android/clientes/listar";
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
            JSONArray jsonArray = jsonObject.getJSONArray("clientes");

            for (int i = 0; i < jsonArray.length(); i++) {


                String imagen = null;
                int id = jsonArray.getJSONObject(i).getInt("id");
                if(!jsonArray.getJSONObject(i).isNull("imagen")){
                    imagen = jsonArray.getJSONObject(i).getString("imagen");
                }

                String tipo = jsonArray.getJSONObject(i).getString("tipo");

                String nombre = null;
                if(!jsonArray.getJSONObject(i).isNull("nombre")){
                    nombre = jsonArray.getJSONObject(i).getString("nombre");
                }

                String razon_social = null;
                if(!jsonArray.getJSONObject(i).isNull("razon_social")){
                    razon_social = jsonArray.getJSONObject(i).getString("razon_social");
                }

                String tipo_documento = jsonArray.getJSONObject(i).getString("tipo_documento");

                Long n_documento = jsonArray.getJSONObject(i).getLong("n_documento");


                String email = null;
                if(!jsonArray.getJSONObject(i).isNull("email")){
                    email = jsonArray.getJSONObject(i).getString("email");
                }

                String distrito = jsonArray.getJSONObject(i).getString("distrito");
                String telefono = jsonArray.getJSONObject(i).getString("telefono");
                String direccion = jsonArray.getJSONObject(i).getString("direccion");

                String referencia = null;
                if(!jsonArray.getJSONObject(i).isNull("referencia")){
                    referencia = jsonArray.getJSONObject(i).getString("referencia");
                }

                String latitud = null;
                if(!jsonArray.getJSONObject(i).isNull("latitud")){
                    latitud = jsonArray.getJSONObject(i).getString("latitud");
                }

                String longitud = null;
                if(!jsonArray.getJSONObject(i).isNull("longitud")){
                    longitud = jsonArray.getJSONObject(i).getString("longitud");
                }

                this.httpList.add(new Cliente(id, imagen, tipo, nombre, razon_social, tipo_documento, n_documento, email, distrito, telefono, direccion, referencia, latitud, longitud));


                for(Cliente item : httpList){
                    if(item.getLatitud() != null && item.getLongitud() != null){

                        String client_nombre = "Sin nombre";

                        if(item.getTipo().equals("natural")){
                            if(item.getNombre() != null){
                                client_nombre = item.getNombre();
                            }
                        } else if(item.getTipo().equals("juridica")){
                            if(item.getRazon_social() != null){
                                client_nombre = item.getRazon_social();
                            }
                        }

                        marker = new MarkerOptions();
                        marker.position(new LatLng(Double.valueOf(item.getLatitud()), Double.valueOf(item.getLongitud())));
                        marker.title(client_nombre);
                        marker.snippet("Dirección: " + item.getDireccion());
                        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.shop));
                        marca = mMap.addMarker(marker);
                        marca.setTag(item.getId());
                    }
                }

            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
