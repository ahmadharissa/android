package com.example.post.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.post.R;
import com.example.post.models.Post;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context context;
    private Post post;

    public CommentAdapter(Context context, Post post) {
        this.context = context;
        this.post = post;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvBody;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);
            tvName = itemView.findViewById (R.id.tvCommentName);
            tvBody = itemView.findViewById (R.id.tvCommentBody);
        }
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.comment, parent, false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        holder.tvName.setText (post.comments.get (position).name);
        holder.tvBody.setText (post.comments.get (position).body);
    }

    @Override
    public int getItemCount() {
        return post.comments.size ();
    }
}
