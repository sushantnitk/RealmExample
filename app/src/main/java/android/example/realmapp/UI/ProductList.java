package android.example.realmapp.UI;

import android.content.Intent;
import android.example.realmapp.Adapters.ItemlistAdapter;
import android.example.realmapp.Adapters.RecyclerItemClickListener;
import android.example.realmapp.R;
import android.example.realmapp.Realm.Products;
import android.example.realmapp.Utils.RealmManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Pandey on 21-12-2016.
 */
public class ProductList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemlistAdapter itemlistAdapter;
    private RealmResults<Products> productsDatas;
    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RealmManager.initializeRealmConfig(getApplicationContext());
        realm =RealmManager.getRealm();
        setContentView(R.layout.activity_items);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView=(RecyclerView)findViewById(R.id.itemListView);
        recyclerView.setLayoutManager(layoutManager);
        productsDatas= realm.where(Products.class).findAll();
        itemlistAdapter= new ItemlistAdapter(this,R.layout.content_product_list,productsDatas);
        recyclerView.setAdapter(itemlistAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Products products = productsDatas.get(position);
                String articleId = products.getItemArticleID();
                Intent intent = new Intent(ProductList.this,ProductDetails.class);
                intent.putExtra("Id",articleId);
                startActivity(intent);


            }
        }));
    }
}
