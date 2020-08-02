package com.project.agriculturalapp.adapters;

import android.content.Context;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.agriculturalapp.R;
import com.project.agriculturalapp.modals.ItemSurvey;

import java.util.ArrayList;

public class SurveyAdapter  extends RecyclerView.Adapter<SurveyAdapter.viewHolder>{
    private ArrayList<ItemSurvey> items;
    private Context context;

    public SurveyAdapter(Context context,ArrayList<ItemSurvey> items){
        this.context=context;
        this.items=items;
    }

    @Override
    public SurveyAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crop_recycler,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        holder.year_view.setText(items.get(position).getYear());
        holder.area_view.setText(items.get(position).getArea());
        holder.production_view.setText(items.get(position).getProduction());

        if(position%2==0){
            holder.cardView.setBackgroundColor(Color.parseColor("#80cbc4"));
        }else{
            holder.cardView.setBackgroundColor(Color.parseColor("#ef9a9a"));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        private TextView year_view,area_view,production_view;
        private LinearLayout cardView;

        public viewHolder(View itemView) {
            super(itemView);
            year_view=(TextView)itemView.findViewById(R.id.year);
            area_view=(TextView)itemView.findViewById(R.id.area);
            production_view=(TextView)itemView.findViewById(R.id.production);
            cardView=(LinearLayout)itemView.findViewById(R.id.card);
        }
    }
}
