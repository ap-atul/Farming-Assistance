package com.project.agriculturalapp.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.project.agriculturalapp.R;
import com.project.agriculturalapp.adapters.MainAdapter;
import com.project.agriculturalapp.modals.MainListItem;

import java.util.ArrayList;


public class CropProductionActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ArrayList<MainListItem> list;
    MainAdapter adapter;

    Integer[] imageUrls={R.drawable.ic_wheat,R.drawable.ic_rice,R.drawable.ic_bean};

    Integer[] hindiTexts={R.string.crop1_hi,R.string.crop2_hi,
            R.string.crop3_hi};

    Integer[] englishTexts={R.string.crop1_en,R.string.crop2_en,
            R.string.crop3_en};

    String[] backgroundColors={"#86c5f9","#86c5f9","#86c5f9"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_production);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        list=new ArrayList<>();

        Intent[] links=getIntents();

        for (int i=0;i<imageUrls.length;i++)
        {
            MainListItem item=new MainListItem();
            item.setEnglishText(englishTexts[i]);
            item.setHindiText(hindiTexts[i]);
            item.setBackgroundColor(backgroundColors[i]);
            item.setImageUrl(imageUrls[i]);
            item.setIntent(links[i]);
            list.add(item);
        }

        adapter=new MainAdapter(this,list,R.layout.item_horticulture);

        mRecyclerView=(RecyclerView)findViewById(R.id.recycler);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        findViewById(R.id.progress).setVisibility(View.GONE);
    }


    public Intent[] getIntents(){
        Intent i1=new Intent(this, CropDetailActivity.class);
        Intent i2=new Intent(this, CropDetailActivity.class);
        Intent i3=new Intent(this, CropDetailActivity.class);
        i1.putExtra("number",1);
        i2.putExtra("number",2);
        i3.putExtra("number",3);

        Intent[] links={ i1, i2, i3 };

        return links;
    }

}

