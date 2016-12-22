package android.example.realmapp.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.example.realmapp.API.ApiClient;
import android.example.realmapp.API.MshopApi;
import android.example.realmapp.R;
import android.example.realmapp.Realm.Products;
import android.example.realmapp.Utils.RealmManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Button realmButton;
    private MshopApi mshopApi;
    private Realm realm;
    private ProgressDialog dialog;
    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mshopApi= ApiClient.getClient().create(MshopApi.class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RealmManager.initializeRealmConfig(getApplicationContext());
        realm = RealmManager.getRealm();
        realmButton = (Button)findViewById(R.id.realm_click);
        dialog = new ProgressDialog(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        realmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setMessage("Loading...");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setIndeterminate(true);
                dialog.show();
                Call<Items> call = mshopApi.getAllProduct();
                call.enqueue(new Callback<Items>() {
                    @Override
                    public void onResponse(Call<Items> call, Response<Items> response) {
                        List<Items.ProductsData> itemsList= response.body().getProductsData();
                        int size=itemsList.size()-1;

                        while(size>0){
                            final Products products = new Products();
                            products.setItemIamgeURL(itemsList.get(size).getItemIamgeURL());
                            products.setItemQuantity(itemsList.get(size).getItemQuantity());
                            products.setItemID(itemsList.get(size).getItemID());
                            products.setItemLocation(itemsList.get(size).getItemLocation());
                            products.setItemDescription(itemsList.get(size).getItemDescription());
                            products.setItemArticleID(itemsList.get(size).getItemArticleID());
                            products.setItemName(itemsList.get(size).getItemName());
                            products.setItemPrice(itemsList.get(size).getItemPrice());
                            products.setBarCodeNumber(itemsList.get(size).getBarCodeNumber());
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    realm.insertOrUpdate(products);
                                }
                            });
                            size--;
                        }
                        Intent intent = new Intent(MainActivity.this,ProductList.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<Items> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.FavCart) {


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
