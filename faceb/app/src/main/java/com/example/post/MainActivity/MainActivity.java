package com.example.post.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;

import com.example.post.Adapter.PostAdapter;
import com.example.post.R;
import com.example.post.models.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String url = "http://192.168.1.4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder ().permitAll ().build ();
        StrictMode.setThreadPolicy (policy);

        start ();

        final SwipeRefreshLayout swipeLayout = findViewById (R.id.swMain);
        swipeLayout.setOnRefreshListener (new SwipeRefreshLayout.OnRefreshListener () {
            @Override
            public void onRefresh() {
                start ();
            }
        });
        final  Handler handler = new Handler ();
        handler.postDelayed (new Runnable () {
            @Override
            public void run() {
                swipeLayout.setRefreshing (false);
            }
        }, 3000);

        swipeLayout.setColorSchemeColors (
                getResources ().getColor (android.R.color.holo_blue_bright),
                getResources ().getColor (android.R.color.holo_blue_light)
        );
    }

    public void start() {
        OkHttpClient client = new OkHttpClient ();
        Request request = new Request.Builder ()
                .url (url + "/posts")
                .build ();

        try {
            Response response = client.newCall (request).execute ();
            ResponseBody responseBody = response.body ();
            if (responseBody != null) {
                String responsePosts = responseBody.string ();
                ObjectMapper mapper = new ObjectMapper ();

                List<Post> posts = mapper.readValue (responsePosts, new TypeReference<List<Post>> () {
                });
                fillListPost (posts);
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    private void fillListPost(List<Post> posts) {
        RecyclerView rv = findViewById (R.id.rvMain);
        PostAdapter postAdapter = new PostAdapter (this, posts);
        rv.setHasFixedSize (true);
        rv.setLayoutManager (new LinearLayoutManager (this));
        rv.setAdapter (postAdapter);
    }
}
