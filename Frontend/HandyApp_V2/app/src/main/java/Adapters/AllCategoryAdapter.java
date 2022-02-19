package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import Data.CategoryData;
import com.example.handyapp_v2.R;
import Fragments.SellerDetailsFragment;

import java.util.ArrayList;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.ViewHolder> {
    Context context;
    ArrayList<CategoryData> models;



    public AllCategoryAdapter(Context context, ArrayList<CategoryData> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public AllCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.item_all_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AllCategoryAdapter.ViewHolder holder, final int position) {
        CategoryData model = models.get(position);
        holder.imgBg.setImageResource(model.getImgCategory());
        holder.tvTitle.setText(model.getTvCategory());





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String casting;
                int holderPosition;

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment fr = new SellerDetailsFragment();
                activity.getSupportFragmentManager().beginTransaction().add(R.id.categID,fr).addToBackStack(null).commit();


            }
        });
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBg;
        TextView tvTitle;


        public ViewHolder(View itemView) {
            super(itemView);
            imgBg = itemView.findViewById(R.id.imgBg);
            tvTitle = itemView.findViewById(R.id.tvTitle);

        }
    }

    public void filterList(ArrayList<CategoryData> filteredList)
    {
        models = filteredList;
        notifyDataSetChanged();
    }
}
