package android.example.realmapp.UI;

import android.content.Intent;
import android.example.realmapp.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



/**
 * Created by Pandey on 22-11-2016.
 */
public class EmptyCart extends AppCompatActivity {
    private ImageView imageView;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_cart);
        imageView=(ImageView)findViewById(R.id.empty_cart_image);
        button=(Button)findViewById(R.id.continue_shop);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmptyCart.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
