package pe.edu.upc.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout inicioMenu ;
    private NavigationView inicioNavigation;

    protected void enabledBack(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    protected void load() {

        inicioMenu = (DrawerLayout)findViewById(R.id.inicioMenu);
        inicioNavigation = (NavigationView)findViewById(R.id.inicioNavigation);
        toggle = new ActionBarDrawerToggle(this, inicioMenu, R.string.open, R.string.close);

        inicioMenu.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inicioNavigation.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Intent i = null;

        switch (menuItem.getItemId()){
            case R.id.addCustomer:
                i = new Intent(this, CreateCustomer.class);
                break;
            case R.id.searchCustomer:
                i = new Intent(this, SearchCustomer.class);
                break;
            case R.id.getQuote:
                i = new Intent(this, GetQuote.class);
                break;
            case R.id.searchQuoteCustomer:
                i = new Intent(this, GetQuote.class);
                break;
        }
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.inicioMenu);
        inicioMenu.closeDrawer(GravityCompat.START);

        if(i != null){
            startActivity(i);
            finish();
        }
        return true;
    }
}
