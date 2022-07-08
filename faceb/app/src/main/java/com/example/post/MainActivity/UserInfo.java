package com.example.post.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.post.R;
import com.example.post.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class UserInfo extends AppCompatActivity {
    String url = "http://192.168.1.4";
    TextView tvName, tvEmail, tvPhone;
    ImageView ivPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.user);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder ().permitAll ().build ();
        StrictMode.setThreadPolicy (policy);

        tvName = findViewById (R.id.tvUserName);
        tvEmail = findViewById (R.id.tvUserEmail);
        tvPhone = findViewById (R.id.tvUserPhone);
        ivPicture = findViewById (R.id.ivUser);

        Bundle userId = getIntent ().getExtras ();
        int userid = userId.getInt ("userid");

        OkHttpClient client = new OkHttpClient ();
        Request request = new Request.Builder ()
                .url (url + "/users/" + String.valueOf (userid))
                .build ();

        try {
            Response response = client.newCall (request).execute ();
            ResponseBody responseBody = response.body ();
            if (responseBody != null) {
                String responseUser = responseBody.string ();
                ObjectMapper mapper = new ObjectMapper ();
                User user = mapper.readValue (responseUser, new TypeReference<User> () {
                });
                tvName.setText (user.username);
                tvEmail.setText (user.email);
                tvPhone.setText (user.phone);
                loadImage (user.photo);
            }
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
