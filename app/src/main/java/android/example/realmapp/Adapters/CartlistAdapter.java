package android.example.realmapp.Adapters;

import android.content.Context;
import android.example.realmapp.R;
import android.example.realmapp.Realm.Favourites;
import android.example.realmapp.UI.CartItems;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

/**
 * Created by Pandey on 21-11-2016.
 */
public class CartlistAdapter extends RealmRecyclerViewAdapter<Favourites,RecyclerView.ViewHolder> {
    RealmResults<Favourites> cartItems;
    Context context;
    String coupon;
    public CartlistAdapter(Context context, RealmResults<Favourites> cart, String coupon) {
        super(context,cart,true);
        this.context=context;
        this.cartItems=cart;
        this.coupon = coupon;
    }




    @Override
    public int getItemViewType(int position) {
        if(cartItems.get(position) instanceof Favourites ){
            return 1;
        }else {
            return 2;
        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case 1:
                View viewONE = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_list, parent, false);
                viewHolder= new CartListViewHolder(viewONE,context);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()){
            case 1:
                CartListViewHolder vh1 = (CartListViewHolder)holder;
                configureViewHoldercart(vh1,position);
                break;
        }
    }

    private void configureViewHoldercart(CartListViewHolder vh1, int position) {
        Favourites productsData=  cartItems.get(position);
        String url = productsData.getItemIamgeURL();
        float price = productsData.getItemPrice();
        String prodprice= "$"+ String.valueOf(price);
        Glide.with(context)
                .load(url)
                .into(vh1.getImageView());
        vh1.getQuantity().setText(String.valueOf(productsData.getItemQuantity()));
        vh1.getProductprice().setText(prodprice);
        vh1.getProdName().setText(productsData.getItemName());
        vh1.getProdCompsnyname().setText(productsData.getItemDescription());

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public interface OnSharingOptionSelected {
        void onOptionSelected(int id, int position);
    }
}
