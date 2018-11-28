package pe.edu.upc.warehouse;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class InicioActivity extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout inicioMenu ;
    private NavigationView inicioNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        inicioMenu = (DrawerLayout)findViewById(R.id.inicioMenu);
        inicioNavigation = (NavigationView)findViewById(R.id.inicioNavigation);
        toggle = new ActionBarDrawerToggle(this, inicioMenu, R.string.open, R.string.close);

        inicioMenu.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //inicioNavigation.setNavigationItemSelectedListener();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
