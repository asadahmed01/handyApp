package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handyapp_v2.R;
import Data.SellerData;

import java.util.ArrayList;

import Activities.ProfileActivity;

public class SellerDataAdapter extends RecyclerView.Adapter<SellerDataAdapter.ViewHolder>{

    Context context;
    ArrayList<SellerData> models;

    public SellerDataAdapter(Context context, ArrayList<SellerData> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public SellerDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seller_list,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SellerDataAdapter.ViewHolder holder, int position) {
        SellerData model = models.get(position);
        holder.imgBg.setImageResource(model.getImgBg());
        holder.tvRating.setText(model.getTvRating());
        holder.tvTitle.setText(model.getTvTitle());
        holder.tvUser.setText(model.getTvUser());
        holder.tvPrice.setText(model.getTvPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context.getApplicationContext(), ProfileActivity.class);
                context.startActivity(intent);

                //go to the created fragment for specific sellers
            }
        });



    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBg;
        TextView tvRating,tvTitle,tvUser,tvTiming,tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBg = itemView.findViewById(R.id.imgBg);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
