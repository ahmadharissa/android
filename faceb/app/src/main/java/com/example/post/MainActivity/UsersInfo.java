package com.example.post.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import com.example.post.Adapter.UsersAdapter;
import com.example.post.R;
import com.example.post.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class UsersInfo extends AppCompatActivity {
    String url = "http://192.168.1.4";
    TextView tvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.users);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder ().permitAll ().build ();
        StrictMode.setThreadPolicy (policy);

        tvUsers = findViewById (R.id.tvUserName);

        OkHttpClient client = new OkHttpClient ();
        Request request = new Request.Builder ()
                .url (url + "/users")
                .build ();

        try {
            Response response = client.newCall (request).execute ();
            ResponseBody responseBody = response.body ();
            if (responseBody != null) {
                String stringUsers = responseBody.string ();
                ObjectMapper mapper = new ObjectMapper ();
                List<User> users = mapper.readValue (stringUsers, new TypeReference<List<User>> () {
                });
               fillUsers(users);
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    private void fillUsers(List<User> users) {
        RecyclerView rv = findViewById (R.id.rvUsers);
        UsersAdapter usersAdapter = new UsersAdapter (this,users);
        rv.setHasFixedSize (true);
        rv.setLayoutManager (new LinearLayoutManager (this));
        rv.setAdapter (usersAdapter);
    }
}
