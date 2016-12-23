package android.example.realmapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ecom.com.mshop.R;
import ecom.com.mshop.UI.CartItems;

/**
 * Created by Pandey on 25-11-2016.
 */
public class CartListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    Context context;
    private ImageView imageView,deleteItem;
    private TextView prodName,prodCompsnyname,productprice,quantity;
    public CartListViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        quantity=(TextView)itemView.findViewById(R.id.quantity_text);
        imageView=(ImageView)itemView.findViewById(R.id.list_image);
        deleteItem=(ImageView)itemView.findViewById(R.id.deleteItem);
        prodName=(TextView)itemView.findViewById(R.id.items_product_name);
        prodCompsnyname=(TextView)itemView.findViewById(R.id.item_list_description);
        productprice=(TextView)itemView.findViewById(R.id.item_price);

        if(deleteItem != null){
            deleteItem.setOnClickListener(this);
        }
    }

    public TextView getProdName() {
        return prodName;
    }

    public void setProdName(TextView prodName) {
        this.prodName = prodName;
    }

    public TextView getProdCompsnyname() {
        return prodCompsnyname;
    }

    public void setProdCompsnyname(TextView prodCompsnyname) {
        this.prodCompsnyname = prodCompsnyname;
    }

    public TextView getProductprice() {
        return productprice;
    }

    public void setProductprice(TextView productprice) {
        this.productprice = productprice;
    }

    public TextView getQuantity() {
        return quantity;
    }

    public void setQuantity(TextView quantity) {
        this.quantity = quantity;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageView getDeleteItem() {
        return deleteItem;
    }

    public void setDeleteItem(ImageView deleteItem) {
        this.deleteItem = deleteItem;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.deleteItem){
            ((CartItems)context).onOptionSelected(v.getId(),getAdapterPosition());
        }
    }
}
