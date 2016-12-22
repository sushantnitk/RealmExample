package android.example.realmapp.Adapters;

import android.content.Context;
import android.example.realmapp.R;
import android.example.realmapp.Realm.Products;
import android.example.realmapp.Utils.ItemClickListener;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

/**
 * An activity representing a single Item detail screen. This activity is only
 * used on handset devices. On tablet-size devices, item details are presented
 * side-by-side with a list of items in a {@link .ItemListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing more than

 */
public class ItemlistAdapter extends RealmRecyclerViewAdapter<Products,ItemlistAdapter.MyViewHolder> {
       private final int rowlayout;
       private ItemClickListener clickListener;
       private Context mcontext;
       private RealmResults<Products> prodlist;

       public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
              ImageView imageView;
              TextView prodName,prodCompsnyname,productprice;
              public MyViewHolder(View itemView) {
                     super(itemView);
                     imageView=(ImageView)itemView.findViewById(R.id.list_image);
                     prodName=(TextView)itemView.findViewById(R.id.items_product_name);
                     prodCompsnyname=(TextView)itemView.findViewById(R.id.item_list_description);
                     productprice=(TextView)itemView.findViewById(R.id.item_price);
                     itemView.setOnClickListener(this);
              }


              public void onClick(View view) {
                     if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
              }
       }
       public ItemlistAdapter(Context mContext, int rowlayout, RealmResults<Products> prodList) {
              super(mContext,prodList,true);
              this.mcontext=mContext;
              this.prodlist=prodList;
              this.rowlayout=rowlayout;

       }

       @Override
       public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
              View itemView = LayoutInflater.from(parent.getContext())
                      .inflate(rowlayout, parent, false);

              return new MyViewHolder(itemView);
       }

       @Override
       public void onBindViewHolder(final MyViewHolder holder, int position) {
             Products productsData= prodlist.get(position);
              String url = productsData.getItemIamgeURL();
              float price = productsData.getItemPrice();
              String prodprice= "$"+ String.valueOf(price);
              Glide.with(mcontext)
                      .load(url)
                      .into(holder.imageView);
              holder.productprice.setText(prodprice);
              holder.prodName.setText(productsData.getItemName());
              holder.prodCompsnyname.setText(productsData.getItemDescription());

       }

       @Override
       public int getItemCount() {
              return prodlist.size();
       }
}
