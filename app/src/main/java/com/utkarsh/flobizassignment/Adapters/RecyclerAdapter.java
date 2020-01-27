package com.utkarsh.flobizassignment.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.utkarsh.flobizassignment.R;

import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private List<String> citiesNames;
    private OnCardClickListener onCardClickListener;
    private Context context;


    public RecyclerAdapter(Context context,List<String> citiesNames,OnCardClickListener onCardClickListener) {
        this.context = context;
        this.onCardClickListener = onCardClickListener;
        this.citiesNames = citiesNames;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = Objects.requireNonNull(layoutInflater).inflate(R.layout.recycler_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(v,onCardClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        String cityName = citiesNames.get(position);
        holder.cityNameText.setText(cityName);
        switch(cityName){
            case "London":
                Glide.with(context)
                        .load(R.drawable.london)
                        .into(holder.cityImage);
                break;

            case "Delhi":
                Glide.with(context)
                        .load(R.drawable.delhi)
                        .into(holder.cityImage);
                break;
            case "Mumbai":
                Glide.with(context)
                        .load(R.drawable.mumbai)
                        .into(holder.cityImage);
                break;
            case "Pune":
                Glide.with(context)
                        .load(R.drawable.india)
                        .into(holder.cityImage);
                break;
            case "Rajkot":
                Glide.with(context)
                        .load(R.drawable.india)
                        .into(holder.cityImage);
                break;
            case "Jaipur":
                Glide.with(context)
                        .load(R.drawable.india)
                        .into(holder.cityImage);
                break;
            case "Paris":
                Glide.with(context)
                        .load(R.drawable.paris)
                        .into(holder.cityImage);
                break;
            case "Singapore":
                Glide.with(context)
                        .load(R.drawable.singapore)
                        .into(holder.cityImage);
                break;
            case "Sydney":
                Glide.with(context)
                        .load(R.drawable.sydney)
                        .into(holder.cityImage);
                break;
            case "Tokyo":
                Glide.with(context)
                        .load(R.drawable.tokyo)
                        .into(holder.cityImage);
                break;
            case "Zurich":
                Glide.with(context)
                        .load(R.drawable.zurich)
                        .into(holder.cityImage);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return citiesNames.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        OnCardClickListener onCardClickListener;
        TextView cityNameText;
        CircleImageView cityImage;

        ViewHolder(@NonNull View itemView, OnCardClickListener onCardClickListener) {
            super(itemView);
            cityNameText = itemView.findViewById(R.id.city_name);
            cityImage = itemView.findViewById(R.id.city_pic);
            this.onCardClickListener = onCardClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCardClickListener.onCardClick(getAdapterPosition());
        }
    }
   public interface OnCardClickListener {
        void onCardClick(int position);
   }
}
