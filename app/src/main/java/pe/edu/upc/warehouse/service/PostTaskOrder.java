package pe.edu.upc.warehouse.service;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import pe.edu.upc.warehouse.R;

public class PostTaskOrder extends AsyncTask<String,Void,Boolean> {
    private ProgressDialog progressDialog;
    AlertDialog.Builder builder;
    private Context context;

    /**Constructor de clase */
    public PostTaskOrder(Context context) {
        this.context = context;
        builder = new AlertDialog.Builder(context);
    }

    /**
     * Antes de comenzar la tarea muestra el progressDialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Por favor espere", "Subiendo...");
    }

    /**
     * @param
     * */
    @Override
    protected Boolean doInBackground(String... params) {
        Boolean r = false;
        PostHttpOrder postHttpOrder = new PostHttpOrder(params[0]);
        try {
            r = postHttpOrder.postOrder(params[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    /**
     * Cuando se termina de ejecutar, cierra el progressDialog y avisa
     * **/
    @Override
    protected void onPostExecute(Boolean resul) {
        progressDialog.dismiss();
        if( resul )
        {
            builder.setMessage("El pedido ha sido guardado!")
                    .setTitle("Information")
                    .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            dialog.cancel();
                        }
                    }).create().show();
        }
        else
        {
            builder.setMessage("No se pudo guardar el pedido!")
                    .setTitle("Information")
                    .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            dialog.cancel();
                        }
                    }).create().show();
        }
    }
}
