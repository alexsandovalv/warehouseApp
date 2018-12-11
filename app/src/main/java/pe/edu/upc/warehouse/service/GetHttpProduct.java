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
import pe.edu.upc.warehouse.adapter.ProductAdapter;
import pe.edu.upc.warehouse.model.Product;

public class GetHttpProduct extends AsyncTask<Void, Void, String> {
    private List<Product> httpList;
    private RecyclerView httpRecycler;
    private RecyclerView.Adapter httpAdapter;
    private Context httpContext;
    ProgressDialog progressDialog;

    public GetHttpProduct(List<Product> httpList, RecyclerView httpRecycler, RecyclerView.Adapter httpAdapter, Context httpContext) {
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
            String wsURL = "http://tramitar.net/UPC/Android/productos/buscar?q=coca";
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
            JSONArray jsonArray = jsonObject.getJSONArray("productos");

            for (int i = 0; i < jsonArray.length(); i++) {
                int id = Integer.parseInt(jsonArray.getJSONObject(i).getString("id"));
                String imagen = jsonArray.getJSONObject(i).getString("imagen");
                String nombre = jsonArray.getJSONObject(i).getString("nombre");
                String descripcion = jsonArray.getJSONObject(i).getString("descripcion");
                int stock = Integer.parseInt(jsonArray.getJSONObject(i).getString("stock"));
                Double precio = Double.parseDouble(jsonArray.getJSONObject(i).getString("precio"));
                DateFormat dc = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date created_at = null;
                Date updated_at = null;
                try {
                    created_at = dc.parse(jsonArray.getJSONObject(i).getString("created_at"));
                    updated_at = dc.parse(jsonArray.getJSONObject(i).getString("updated_at"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int relevance = Integer.parseInt(jsonArray.getJSONObject(i).getString("relevance"));

                this.httpList.add(new Product(id,imagen,nombre,descripcion,stock,precio,created_at,updated_at,relevance));
                System.out.println(httpList.get(i).getDescripcion());
            }

            //Enviando lista al adaptador
            httpAdapter = new ProductAdapter(this.httpList);
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
