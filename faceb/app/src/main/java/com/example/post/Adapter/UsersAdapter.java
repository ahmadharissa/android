package com.example.post.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.post.R;
import com.example.post.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    List<User> users;
    Context context;
    public UsersAdapter(Context context,List<User> users){
        this.context = context;
        this.users = users;
    }

    class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tvName,tvEmail,tvPhone;
        public ViewHolder(@NonNull View itemView) {
            super (itemView);
            tvName = itemView.findViewById (R.id.tvUserName);
            tvEmail = itemView.findViewById (R.id.tvUserEmail);
            tvPhone = itemView.findViewById (R.id.tvUserPhone);
            iv = itemView.findViewById (R.id.ivUser);
        }
    }
    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.user,parent,false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {
        User user = users.get (position);
        holder.tvName.setText (user.name);
        holder.tvEmail.setText (user.email);
        holder.tvPhone.setText (user.phone);
        loadImage (user.photo);
    }

    private void loadImage(String url) {
        Picasso.get ()
                .load (url)
                .error (R.drawable.ic_launcher_foreground);
    }

    @Override
    public int getItemCount() {
        return users.size ();
    }
}
