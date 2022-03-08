package Adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.handyapp_v2.R;
import com.makeramen.roundedimageview.RoundedImageView;

import org.w3c.dom.Text;

import java.util.List;

import Activities.models.usermodel.Model;

public class UserAdapter extends RecyclerView .Adapter<UserAdapter.ViewHolder>{
    private Context context;
    private List<Model> mUsers;
    private String thelastMessage;

    boolean ischat = false;


    public UserAdapter(Context context, List<Model> mUsers, boolean ischat)
    {
        this.context = context;
        this.mUsers = mUsers;
        this.ischat = ischat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView username;
        TextView last_msg;
        public RoundedImageView profile_image;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            username = itemView.findViewById(R.id.username);
            last_msg = itemView.findViewById(R.id.last_msg);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }
}
