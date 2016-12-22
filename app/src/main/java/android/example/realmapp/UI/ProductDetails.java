package android.example.realmapp.UI;

import android.example.realmapp.R;
import android.example.realmapp.Realm.Favourites;
import android.example.realmapp.Realm.Products;
import android.example.realmapp.Utils.RealmManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Pandey on 22-12-2016.
 */
public class ProductDetails extends AppCompatActivity {
    private ImageView itemImage,addItem,removeItem;
    private TextView itemname,itemPrice,itemDescription,address,countItem;
    private Button addtocart;

    private Realm realm;
    private final Handler handler = new Handler();
    private Favourites fav = new Favourites();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);
        final String articleId = getIntent().getStringExtra("Id");
        RealmManager.initializeRealmConfig(getApplicationContext());
        realm=RealmManager.getRealm();
        final Products result = realm.where(Products.class).equalTo("itemArticleID",articleId).findFirst();
        addItem=(ImageView)findViewById(R.id.addItem);
        removeItem=(ImageView)findViewById(R.id.removeItem);
        itemImage=(ImageView)findViewById(R.id.item_imageView);
        addItem=(ImageView)findViewById(R.id.addItem);
        removeItem=(ImageView)findViewById(R.id.removeItem);
        countItem=(TextView)findViewById(R.id.countItem);
        itemname=(TextView)findViewById(R.id.product_name);
        address=(TextView)findViewById(R.id.prod_address);
        itemDescription=(TextView)findViewById(R.id.item_name);
        itemPrice=(TextView)findViewById(R.id.prod_price);
        addtocart=(Button)findViewById(R.id.add_to_cart);
        Glide.with(this).load(result.getItemIamgeURL()).into(itemImage);
        itemname.setText(result.getItemName());
        address.setText(result.getItemLocation());
        itemDescription.setText(result.getItemDescription());
        itemPrice.setText(String.valueOf(result.getItemPrice()));
        String price= "$"+" " + result.getItemPrice();
        itemPrice.setText(price);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        int newcount= Integer.valueOf(countItem.getText().toString()) +1;
                        countItem.setText(String.valueOf(newcount));
                    }
                });
            }
        });


        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(new Runnable() {
                    int local= Integer.valueOf(countItem.getText().toString());
                    @Override
                    public void run() {
                        if(local>1){
                            int newcount=local-1;
                            countItem.setText(String.valueOf(newcount));
                        }else{
                            countItem.setText("1");
                        }
                    }
                });

            }
        });

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fav.setBarCodeNumber(result.getBarCodeNumber());
                fav.setItemPrice(result.getItemPrice());
                fav.setItemName(result.getItemName());
                fav.setItemDescription(result.getItemDescription());
                fav.setItemArticleID(articleId);
                fav.setItemPrice(result.getItemPrice());
                fav.setItemIamgeURL(result.getItemIamgeURL());
                fav.setItemID(result.getItemID());
                fav.setItemLocation(result.getItemLocation());
                fav.setCount(Integer.valueOf(countItem.getText().toString()));
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.insertOrUpdate(fav);
                        fav.addChangeListener(callback);
                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        fav.removeChangeListener(callback);

    }

    private RealmChangeListener callback = new RealmChangeListener() {

        @Override
        public void onChange(Object element) {

        }

        public void onChange(RealmResults<Favourites> results) {
            // called once the query complete and on every update
            Toast.makeText(ProductDetails.this,"Added Successfully",Toast.LENGTH_LONG).show();
        }
    };
}
