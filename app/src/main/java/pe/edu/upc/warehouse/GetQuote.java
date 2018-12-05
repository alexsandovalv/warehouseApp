package pe.edu.upc.warehouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GetQuote extends BaseActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_quote);

        load();
    }
}
