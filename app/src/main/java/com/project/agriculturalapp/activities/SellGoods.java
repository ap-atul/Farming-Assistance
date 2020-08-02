package com.project.agriculturalapp.activities;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import android.view.View;

import com.project.agriculturalapp.R;
import com.project.agriculturalapp.modals.Goods;
import com.project.agriculturalapp.utilities.GoodsHelper;
import com.project.agriculturalapp.utilities.InputValidation;

public class SellGoods extends AppCompatActivity {

    private final AppCompatActivity activity = SellGoods.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutname;
    private TextInputLayout textInputLayoutemail;
    private TextInputLayout textInputLayoutmobile;
    private TextInputLayout textInputLayoutcrop;
    private TextInputLayout textInputLayoutquantity;
    private TextInputLayout textInputLayoutprice;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextMobile;
    private TextInputEditText textInputEditTextCrop;
    private TextInputEditText textInputEditTextQuantity;
    private TextInputEditText textInputEditTextPrice;

    private AppCompatButton appCompatButtonSell;

    private InputValidation inputValidation;
    private Goods goods;
    private GoodsHelper goodsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_goods);

        initViews();
        initListeners();
        intiObjects();
    }

    private void intiObjects() {
        inputValidation = new InputValidation(activity);
        goodsHelper = new GoodsHelper(activity);
        goods = new Goods();
    }

    private void initListeners() {
        appCompatButtonSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataToSQLite();
            }
        });
    }

    public void initViews(){
        nestedScrollView = findViewById(R.id.nestedScrollViewG);

        textInputLayoutname = findViewById(R.id.textInputLayoutNameG);
        textInputLayoutemail = findViewById(R.id.textInputLayoutEmailG);
        textInputLayoutmobile = findViewById(R.id.textInputLayoutMobileG);
        textInputLayoutcrop = findViewById(R.id.textInputLayoutCropG);
        textInputLayoutquantity = findViewById(R.id.textInputLayoutQuantityG);
        textInputLayoutprice = findViewById(R.id.textInputLayoutPriceG);

        textInputEditTextName = findViewById(R.id.textInputEditTextNameG);
        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmailG);
        textInputEditTextMobile = findViewById(R.id.textInputEditTextMobileG);
        textInputEditTextCrop = findViewById(R.id.textInputEditTextCropG);
        textInputEditTextQuantity = findViewById(R.id.textInputEditTextQuantityG);
        textInputEditTextPrice = findViewById(R.id.textInputEditTextPriceG);

        appCompatButtonSell = findViewById(R.id.appCompatButtonSell);
    }


    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutname, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutemail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutemail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextMobile, textInputLayoutmobile, "Enter Mobile")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextCrop, textInputLayoutcrop, "Enter Crop Name")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextQuantity, textInputLayoutquantity, "Enter Crop Quantity")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPrice, textInputLayoutprice, "Enter Crop Price")) {
            return;
        }

        goods.setName(textInputEditTextName.getText().toString().trim());
        goods.setEmail(textInputEditTextEmail.getText().toString().trim());
        goods.setMobile(textInputEditTextMobile.getText().toString().trim());
        goods.setCrop(textInputEditTextCrop.getText().toString().trim());
        goods.setQuantity(textInputEditTextQuantity.getText().toString().trim());
        goods.setPrice(textInputEditTextPrice.getText().toString().trim());

        goodsHelper.addUser(goods);

        // Snack Bar to show success message that record saved successfully
        Snackbar.make(nestedScrollView, "Seller has been notified", Snackbar.LENGTH_LONG).show();
        emptyInputEditText();
    }

    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextMobile.setText(null);
        textInputEditTextCrop.setText(null);
        textInputEditTextQuantity.setText(null);
        textInputEditTextPrice.setText(null);
    }
}
