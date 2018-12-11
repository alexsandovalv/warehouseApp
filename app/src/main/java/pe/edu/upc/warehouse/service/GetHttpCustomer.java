package pe.edu.upc.warehouse.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pe.edu.upc.warehouse.R;
import pe.edu.upc.warehouse.adapter.CustomerAdapter;
import pe.edu.upc.warehouse.model.Customer;

public class GetHttpCustomer extends AsyncTask<Void, Void, String> {
    private List<Customer> httpList;
    private RecyclerView httpRecycler;
    private RecyclerView.Adapter httpAdapter;
    private Context httpContext;
    ProgressDialog progressDialog;

    public GetHttpCustomer(List<Customer> httpList, RecyclerView httpRecycler, RecyclerView.Adapter httpAdapter, Context httpContext) {
        this.httpList = httpList;
        this.httpRecycler = httpRecycler;
        this.httpAdapter = httpAdapter;
        this.httpContext = httpContext;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        progressDialog = ProgressDialog.show(httpContext, httpContext.getString(R.string.popMsgTitulo),httpContext.getString(R.string.popMsgMensaje));
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        try {
            String wsURL = "http://tramitar.net/UPC/Android/clientes/listar";
            URL url = new URL(wsURL);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConn.getInputStream());
            result = inputStreamToString(in);

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private String inputStreamToString(InputStream in) {
        String rLine = "";
        StringBuilder answer = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader rd = new BufferedReader(isr);
        try {
            while ((rLine = rd.readLine()) != null) {
                answer.append(rLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer.toString();
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        progressDialog.dismiss();

        try {
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s,"UTF-8"));
            JSONArray jsonArray = jsonObject.getJSONArray("clientes");

            for (int i = 0; i < jsonArray.length(); i++) {
                int id = Integer.parseInt(jsonArray.getJSONObject(i).getString("id"));
                String tipo = jsonArray.getJSONObject(i).getString("tipo");
                String nombre = jsonArray.getJSONObject(i).getString("nombre");
                String razon_social = jsonArray.getJSONObject(i).getString("razon_social");
                String tipo_documento = jsonArray.getJSONObject(i).getString("tipo_documento");
                String n_documento = jsonArray.getJSONObject(i).getString("n_documento");
                String email = jsonArray.getJSONObject(i).getString("email");
                String distrito = jsonArray.getJSONObject(i).getString("distrito");
                String telefono = jsonArray.getJSONObject(i).getString("telefono");
                String referencia = jsonArray.getJSONObject(i).getString("referencia");
                String estado = jsonArray.getJSONObject(i).getString("estado");
                Double longitud;
                if ((jsonArray.getJSONObject(i).getString("longitud")).isEmpty() || (jsonArray.getJSONObject(i).getString("longitud")) == "null"){
                    longitud = 0.00;
                } else {
                    longitud =  Double.parseDouble(jsonArray.getJSONObject(i).getString("longitud"));
                };
                Double latitud;
                if ((jsonArray.getJSONObject(i).getString("latitud")).isEmpty() || (jsonArray.getJSONObject(i).getString("latitud")) == "null"){
                    latitud = 0.00;
                } else {
                    latitud =  Double.parseDouble(jsonArray.getJSONObject(i).getString("latitud"));
                };

                DateFormat dc = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date created_at = null;
                Date updated_at = null;
                try {
                    created_at = dc.parse(jsonArray.getJSONObject(i).getString("created_at"));
                    updated_at = dc.parse(jsonArray.getJSONObject(i).getString("updated_at"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String agregar_pedido_url = jsonArray.getJSONObject(i).getString("agregar_pedido_url");
                String agregar_nota_url = jsonArray.getJSONObject(i).getString("agregar_nota_url");

                this.httpList.add(new Customer(id,tipo,nombre,razon_social,tipo_documento,n_documento,email,distrito,telefono,referencia,estado,longitud,latitud,created_at,updated_at,agregar_pedido_url,agregar_nota_url));
            }

            //Enviando lista al adaptador
            httpAdapter = new CustomerAdapter(this.httpList);
            httpRecycler.setAdapter(httpAdapter);

            String msg = String.valueOf(httpAdapter.getItemCount()) + " registros";
            Toast.makeText(httpContext, msg, Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
