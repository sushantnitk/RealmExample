package android.example.realmapp.UI;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by Pandey on 21-12-2016.
 */
public class Items{

    private String message;
    private boolean status;
    @SerializedName("productsData")
    private List<ProductsData> productsData;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ProductsData> getProductsData() {
        return productsData;
    }

    public void setProductsData(List<ProductsData> productsData) {
        this.productsData = productsData;
    }

    public class ProductsData implements Serializable {
        private String barCodeNumber;
        private String itemArticleID;
        private String itemName;
        private float itemPrice;
        private int itemQuantity;
        private String itemDescription;
        private int itemID;
        private String itemLocation;
        private String itemIamgeURL;


        public String getBarCodeNumber() {
            return barCodeNumber;
        }

        public void setBarCodeNumber(String barCodeNumber) {
            this.barCodeNumber = barCodeNumber;
        }

        public String getItemArticleID() {
            return itemArticleID;
        }

        public void setItemArticleID(String itemArticleID) {
            this.itemArticleID = itemArticleID;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public float getItemPrice() {
            return itemPrice;
        }

        public void setItemPrice(float itemPrice) {
            this.itemPrice = itemPrice;
        }

        public int getItemQuantity() {
            return itemQuantity;
        }

        public void setItemQuantity(int itemQuantity) {
            this.itemQuantity = itemQuantity;
        }

        public String getItemDescription() {
            return itemDescription;
        }

        public void setItemDescription(String itemDescription) {
            this.itemDescription = itemDescription;
        }

        public int getItemID() {
            return itemID;
        }

        public void setItemID(int itemID) {
            this.itemID = itemID;
        }

        public String getItemLocation() {
            return itemLocation;
        }

        public void setItemLocation(String itemLocation) {
            this.itemLocation = itemLocation;
        }


        public String getItemIamgeURL() {
            return itemIamgeURL;
        }

        public void setItemIamgeURL(String itemIamgeURL) {
            this.itemIamgeURL = itemIamgeURL;
        }
    }
}
