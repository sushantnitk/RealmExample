package android.example.realmapp.Adapters;

import android.content.Context;
import android.example.realmapp.R;
import android.example.realmapp.UI.CartItems;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Pandey on 25-11-2016.
 */
public class PriceListViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
    private TextView quantity,price,couponcode;
    private Button checkOut;
    Context context;
    public PriceListViewHolder(View itemView, Context context) {
        super(itemView);
        quantity = (TextView)itemView.findViewById(R.id.total_items);
        price= (TextView)itemView.findViewById(R.id.price_button);
        checkOut=(Button)itemView.findViewById(R.id.checkout);
        couponcode=(TextView)itemView.findViewById(R.id.coupon_code);
        this.context= context;
        if(checkOut!=null){
            checkOut.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.checkout){
            ((CartItems)context).onOptionSelected(v.getId(),getAdapterPosition());
        }
    }

    public TextView getQuantity() {
        return quantity;
    }

    public void setQuantity(TextView quantity) {
        this.quantity = quantity;
    }

    public TextView getPrice() {
        return price;
    }

    public void setPrice(TextView price) {
        this.price = price;
    }

    public TextView getCouponcode() {
        return couponcode;
    }

    public void setCouponcode(TextView couponcode) {
        this.couponcode = couponcode;
    }
}
