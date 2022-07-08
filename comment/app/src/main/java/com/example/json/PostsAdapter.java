package com.example.json;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.json.models.Post;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PostsAdapter extends ArrayAdapter<Post> {
    private Activity context;
    private List<Post> posts;

    public PostsAdapter(Activity context, List<Post> posts) {
        super (context, R.layout.post_list_item, posts);

        this.context = context;
        this.posts = posts;
    }


    @NotNull
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater ();
        View rowView = inflater.inflate (R.layout.post_list_item, null, true);

        TextView postTitle = rowView.findViewById (R.id.txtPostTitle);
        TextView postBody = rowView.findViewById (R.id.txtPostBody);

        Post post = posts.get (position);
        postTitle.setText (post.title);
        postBody.setText (post.body);

        return rowView;
    }
}
