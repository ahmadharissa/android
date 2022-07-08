package com.example.post.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import android.os.Bundle;
import android.os.Handler;

import com.example.post.Adapter.PostAdapterForMe;
import com.example.post.R;
import com.example.post.SQLite.DataBase;
import com.example.post.models.Post;
import com.example.post.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class MyPosts extends AppCompatActivity {
    String url = "http://192.168.1.4";
    DataBase db;
    int userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.my_posts);
        start ();
    }

    public void start(){
        db = new DataBase (this);
        String email = db.email ();

        OkHttpClient client = new OkHttpClient ();
        Request request = new Request.Builder ()
                .url (url + "/users?email=" + email)
                .build ();
        try {
            Response response = client.newCall (request).execute ();
            ResponseBody responseBody = response.body ();
            if (responseBody != null) {
                String strigUsers = responseBody.string ();
                ObjectMapper mapper = new ObjectMapper ();
                List<User> users = mapper.readValue (strigUsers, new TypeReference<List<User>> () {
                });
                if (!users.isEmpty ()) {
                    User user = users.get (0);
                    userid = user.id;
                }
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }

        Request request1 = new Request.Builder ()
                .url (url + "/posts?userId=" + String.valueOf (userid))
                .build ();
        try {
            Response response1 = client.newCall (request1).execute ();
            ResponseBody responseBody1 =response1.body ();
            if (responseBody1 != null){
                String string = responseBody1.string ();
                ObjectMapper mapper = new ObjectMapper ();
                List<Post> posts = mapper.readValue (string, new TypeReference<List<Post>> () {
                });
                listPostForMe(posts);
            }
        }catch (IOException e){
            e.printStackTrace ();
        }
    }

    private void listPostForMe(List<Post> posts) {
        RecyclerView rv = findViewById (R.id.rvMy);
        PostAdapterForMe postAdapter = new PostAdapterForMe (this,posts);
        rv.setHasFixedSize (true);
        rv.setLayoutManager (new LinearLayoutManager (this));
        rv.setAdapter (postAdapter);
    }
}
