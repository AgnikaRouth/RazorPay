package com.example.paymentrp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    //Initialize the variables
    Button btPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable
        btPay = findViewById(R.id.bt_pay);

        //Initialize amount
        String sAmount = "100";
        //Round off the amount
        final int amount = Math.round(Float.parseFloat(sAmount) *100);

        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize the razorpay checkout

                Checkout checkout = new Checkout();
                // Set key id
                checkout.setKeyID("rzp_test_AP5Zcg92rQYLsb");
                //set Image
                checkout.setImage(R.drawable.ic_razorpay_glyph);
                //initialize JSON object
                JSONObject object = new JSONObject();

                //PUT name
                try {
                    object.put("name", "Agnika");
                    //PUT description
                    object.put("description", "Test Payment");
                    //PUT theme color
                    object.put("theme.color", "#3399cc");
                    //PUT currency unit
                    object.put("currency", "INR");
                    //PUT amount
                    object.put("amount", amount);//pass amount in currency subunits
                    //PUT mobile number
                    object.put("prefill.contact", "0768191003");

                    //Open RazorPay Checkout Activity
                    checkout.open(MainActivity.this, object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    @Override
    public void onPaymentSuccess(String s) {

        //Initialize alert dialog
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        //Set title
        builder.setTitle("Payment ID");
        //Set message
        builder.setMessage(s);
        //Show alert dialog
        builder.show();

    }

    @Override
    public void onPaymentError(int i, String s) {

        //Display Toast
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

    }
}
