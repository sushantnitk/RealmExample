package android.example.realmapp.API;


import android.example.realmapp.UI.Items;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Pandey on 16-11-2016.
 */
public interface MshopApi {


//    @GET("/getProduct")
//    Call<Items> getProduct(@Query("barCodeNumber") String barcode);

//    @FormUrlEncoded
//    @POST("/registerUser")
//    Call<RegisteredUser>regUser(@Field("userName") String username, @Field("memberID") String memberId, @Field("mobileNumber") String mobilenumber, @Field("password") String password, @Field("emailID") String emailId);

//    @FormUrlEncoded
//    @POST("/login")
//    Call<UserDetails>logInUser(@Field("mobileNumber") String mobile, @Field("password") String password);

//    @FormUrlEncoded
//    @POST("/updateUser")
////    Call<UserUpdated>updateUser(@Field("userName") String name, @Field("memberID") String memberId, @Field("mobileNumber") String mobile, @Field("emailID") String email);
////
    @GET("/getAllProduct")
    Call<Items> getAllProduct();

    @GET("/productImage?pathname=images/{id}")
    Call<String>getImage(@Path("id") String id);
}
