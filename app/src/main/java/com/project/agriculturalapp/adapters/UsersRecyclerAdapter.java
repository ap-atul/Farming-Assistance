package com.project.agriculturalapp.adapters;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.agriculturalapp.R;
import com.project.agriculturalapp.modals.Goods;

import java.util.List;


public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {

    private List<Goods> listGoods;

    public UsersRecyclerAdapter(List<Goods> listUsers) {
        this.listGoods = listUsers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewName.setText(listGoods.get(position).getName());
        holder.textViewEmail.setText(listGoods.get(position).getEmail());
        holder.textViewMobile.setText(listGoods.get(position).getMobile());
        holder.textViewCrop.setText(listGoods.get(position).getCrop());
        holder.textViewQuantity.setText(listGoods.get(position).getQuantity());
        holder.textViewPrice.setText(listGoods.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+ listGoods.size());
        return listGoods.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewMobile;
        public AppCompatTextView textViewCrop;
        public AppCompatTextView textViewQuantity;
        public AppCompatTextView textViewPrice;


        public UserViewHolder(View view) {
            super(view);
            textViewName = view.findViewById(R.id.textViewNameS);
            textViewEmail = view.findViewById(R.id.textViewEmailS);
            textViewMobile = view.findViewById(R.id.textViewMobileS);
            textViewCrop = view.findViewById(R.id.textViewCropS);
            textViewQuantity = view.findViewById(R.id.textViewQuantityS);
            textViewPrice = view.findViewById(R.id.textViewPriceS);
        }
    }


}