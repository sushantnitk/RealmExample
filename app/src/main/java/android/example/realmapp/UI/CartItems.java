package android.example.realmapp.UI;

import android.content.Intent;
import android.example.realmapp.Adapters.CartlistAdapter;
import android.example.realmapp.R;
import android.example.realmapp.Realm.Favourites;
import android.example.realmapp.Utils.RealmManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.SecureRandom;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Pandey on 21-11-2016.
 */
public class CartItems extends AppCompatActivity implements CartlistAdapter.OnSharingOptionSelected {
    private RecyclerView recyclerView;
    private CartlistAdapter cartlistAdapter;
    private Realm realm;
    private SecureRandom random;
    private RealmResults<Favourites> itemsCart;
    private String coupon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        random = new SecureRandom();
        RealmManager.initializeRealmConfig(getApplicationContext());
        realm = RealmManager.getRealm();

        itemsCart = realm.where(Favourites.class).findAll();
        setContentView(R.layout.activity_items);
        recyclerView = (RecyclerView) findViewById(R.id.itemListView);
        coupon=nextSessionId();
        if(!itemsCart.isEmpty()){
            final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            cartlistAdapter = new CartlistAdapter(this, itemsCart,coupon);
            recyclerView.setAdapter(cartlistAdapter);
        } else{
            Intent intent = new Intent(this,EmptyCart.class);
            startActivity(intent);
            Toast.makeText(this,"Your Cart is empty!Please order.", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onOptionSelected(int id, int position) {
        if(id== R.id.checkout){

        }else if(id== R.id.deleteItem){
            final Favourites items = itemsCart.get(position);
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    items.deleteFromRealm();
                    items.addChangeListener(callback);

                }
            });

            } else{
                Intent intent = new Intent(this,EmptyCart.class);
                startActivity(intent);
                Toast.makeText(this,"Your Cart is empty!Please order.", Toast.LENGTH_LONG).show();
            }


            cartlistAdapter.notifyDataSetChanged();
        }

    private RealmChangeListener callback = new RealmChangeListener() {
        @Override
        public void onChange(Object element) {
            itemsCart = realm.where(Favourites.class).findAll();
            if (!itemsCart.isEmpty()) {
                cartlistAdapter = new CartlistAdapter(CartItems.this, itemsCart, coupon);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(CartItems.this, 1);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(cartlistAdapter);
            }

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if(itemsCart.size()==0){
            Intent intent = new Intent(this,EmptyCart.class);
            startActivity(intent);
            Toast.makeText(this,"Your Cart is empty!Please order.", Toast.LENGTH_LONG).show();
        }
    }


        public String nextSessionId() {
            return new BigInteger(130, random).toString(32).substring(0,16);
        }

    public class CartCheckOut {
        private int count;
        private float totalBill;

        public CartCheckOut(int count,float totalBill){
            this.count=count;
            this.totalBill=totalBill;
        }

        public int getCount() {
            return count;
        }


        public float getTotalBill() {
            return totalBill;
        }


    }
}
