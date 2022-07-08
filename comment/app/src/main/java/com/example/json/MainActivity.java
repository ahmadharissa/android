package com.example.json;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.json.models.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    String url = "http://192.168.1.4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder ().permitAll ().build ();
        StrictMode.setThreadPolicy (policy);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("click Posts")
                .setNegativeButton("Retry", null)
                .create()
                .show();

        start ();

    }

    public void start(){

        OkHttpClient client = new OkHttpClient ();

        Request request = new Request.Builder ()
                .url (url + "/posts")
                .build ();
        try {
            Response response = client.newCall (request).execute ();
            ResponseBody responseBody = response.body ();
            if (responseBody != null) {
                String stringPosts = responseBody.string ();
                ObjectMapper mapper = new ObjectMapper ();

                List<Post> posts = mapper.readValue (stringPosts, new TypeReference<List<Post>> () {
                });
                fillPostList (posts);
            }
        } catch (Exception e) {
            Log.e ("connection", "this is a message", e);
        }
        refrech(1000);
    }

    private void refrech(int millisecond) {
        final Handler handler = new Handler ();
        final Runnable runnabler = new Runnable () {
            @Override
            public void run() {
                start ();
            }
        };
        handler.postDelayed (runnabler,millisecond);
    }

    private void fillPostList(List<Post> posts) {
        ListView lvpost = findViewById(R.id.lv);
        PostsAdapter adapter = new PostsAdapter(this, posts);

        lvpost.setAdapter(adapter);
        lvpost.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
               Toast.makeText(getApplicationContext(),"Position : " +position , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
