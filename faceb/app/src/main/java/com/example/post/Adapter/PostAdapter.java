package com.example.post.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.post.MainActivity.PostInfo;
import com.example.post.R;
import com.example.post.models.Post;
import com.example.post.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    String url = "http://192.168.1.4";
    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvBody;
        CardView cv;

        ViewHolder(@NonNull View itemView) {
            super (itemView);
            tvName = itemView.findViewById (R.id.tvPostUsername);
            tvBody = itemView.findViewById (R.id.tvPostBody);
            cv = itemView.findViewById (R.id.cvPost);
        }
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.post, parent, false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, final int position) {
        Post post = posts.get (position);
        holder.tvName.setText (post.title);
        holder.tvBody.setText (post.body);
        holder.cv.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent (context, PostInfo.class);
                Bundle postPosition = new Bundle ();
                postPosition.putInt ("id", position);
                myintent.putExtras (postPosition);
                context.startActivity (myintent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size ();
    }
}
