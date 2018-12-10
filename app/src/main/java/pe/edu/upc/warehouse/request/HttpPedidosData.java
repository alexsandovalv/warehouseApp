package pe.edu.upc.warehouse.request;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

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

import pe.edu.upc.warehouse.model.Pedido;

public class HttpPedidosData extends AsyncTask<Void, Void, String> {

    public AsyncResponsePedidos delegate = null;
    private List<Pedido> httpList;
    private Context httpContext;
    ProgressDialog progressDialog;
    private Integer clienteID;

    public HttpPedidosData(List<Pedido> httpList, Context httpContext, Integer clienteID) {
        this.httpList = httpList;
        this.httpContext = httpContext;
        this.clienteID = clienteID;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        progressDialog = ProgressDialog.show(httpContext, "Descargando información de pedidos", "Por favor, espere...");
    }

    @Override
    protected String doInBackground(Void... voids) {

        String result = null;

        try{

            String wsURL = "http://tramitar.net/UPC/Android/clientes/"+ clienteID +"/pedidos";
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
            JSONObject jsonCliente = jsonObject.getJSONObject("cliente");
            JSONArray jsonArray = jsonCliente.getJSONArray("pedidos");

            for (int i = 0; i < jsonArray.length(); i++) {

                int id = jsonArray.getJSONObject(i).getInt("id");
                int clienteid = jsonArray.getJSONObject(i).getInt("cliente_id");

                String estado = jsonArray.getJSONObject(i).getString("estado");

                String notaextra = null;
                if(!jsonArray.getJSONObject(i).isNull("nota_extra")){
                    notaextra = jsonArray.getJSONObject(i).getString("nota_extra");
                }


                String fechaentrega = null;
                if(!jsonArray.getJSONObject(i).isNull("fecha_entrega")){
                    fechaentrega = jsonArray.getJSONObject(i).getString("fecha_entrega");
                }

                String creacion = null;
                if(!jsonArray.getJSONObject(i).isNull("created_at")){
                    creacion = jsonArray.getJSONObject(i).getString("created_at");
                }


                this.httpList.add(new Pedido(id, clienteid, estado, notaextra, creacion, fechaentrega));
            }

            delegate.processFinish(httpList);

        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
