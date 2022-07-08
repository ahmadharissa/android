package com.example.post.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.post.Adapter.CommentAdapter;
import com.example.post.R;
import com.example.post.models.Post;
import com.example.post.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class PostInfo extends AppCompatActivity {
    String url = "http://192.168.1.4";
    TextView tvName, tvBody, tvComment,tvTitle;
    CardView cvPost;
    ImageView ivPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.post_info);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder ().permitAll ().build ();
        StrictMode.setThreadPolicy (policy);

        tvName = findViewById (R.id.tvInfoName);
        tvTitle = findViewById (R.id.tvInfoTitle);
        tvBody = findViewById (R.id.tvInfoBody);
        ivPicture = findViewById (R.id.ivInfo);
        cvPost = findViewById (R.id.cvInfoPost);

        Bundle postPosition = getIntent ().getExtras ();
        int id = postPosition.getInt ("id");

        OkHttpClient client = new OkHttpClient ();
        Request request = new Request.Builder ()
                .url (url + "/posts/" + String.valueOf (id + 1) + "?_embed=comments")
                .build ();

        try {
            Response response = client.newCall (request).execute ();
            ResponseBody responseBody = response.body ();
            String stringPostInfo = responseBody.string ();
            ObjectMapper mapper = new ObjectMapper ();

            Post post = mapper.readValue (stringPostInfo, new TypeReference<Post> () {
            });

            final int userid = post.userId;

            Request request2 = new Request.Builder ()
                    .url (url + "/users/" + String.valueOf (userid))
                    .build ();
            Response response2 = client.newCall (request2).execute ();
            ResponseBody responseBody2 = response2.body ();
            String stringPostUser = responseBody2.string ();

            User user = mapper.readValue (stringPostUser, new TypeReference<com.example.post.models.User> () {
            });

            loadImage (user.photo);
            tvName.setText (user.username);
            tvTitle.setText (post.title);
            tvBody.setText (post.body);

            if (post.comments.size () > 0) {
                RecyclerView recyclerView = findViewById (R.id.rvInfo);
                CommentAdapter commentAdapter = new CommentAdapter (this, post);
                recyclerView.setHasFixedSize (true);
                recyclerView.setLayoutManager (new LinearLayoutManager (this));
                recyclerView.setAdapter (commentAdapter);
            } else {
                tvComment = findViewById (R.id.tvInfoComment);
                tvComment.setText ("No Comment");
            }


            final Intent myintent = new Intent (this, UserInfo.class);
            cvPost.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Bundle userId = new Bundle ();
                    userId.putInt ("userid", userid);
                    myintent.putExtras (userId);
                    startActivity (myintent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    private void loadImage(String url) {
        Picasso.get ()
                .load (url)
                .error (R.drawable.ic_launcher_foreground)
                .into (ivPicture);
    }
}
