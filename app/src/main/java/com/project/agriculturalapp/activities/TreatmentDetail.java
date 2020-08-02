package com.project.agriculturalapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.agriculturalapp.R;


public class TreatmentDetail extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.treatment_coordinator);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        int image=(Integer) getIntent().getIntExtra("image",R.drawable.t1);
        int text=(Integer)getIntent().getIntExtra("info",0);

        Log.v("###",text+"");

        imageView=(ImageView)findViewById(R.id.imageView);
        textView=(TextView)findViewById(R.id.detail);

        imageView.setImageResource(image);
        textView.setText(text);
    }
}
