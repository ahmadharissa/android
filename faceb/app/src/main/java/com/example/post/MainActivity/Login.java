package com.example.post.MainActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.post.R;
import com.example.post.SQLite.DataBase;
import com.example.post.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Login extends Activity {
    Button btnLogin;
    EditText etEmail, etPassword;
    DataBase db;
    public static int counter = 3;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.login);

        db = new DataBase (getApplicationContext ());
        if (!db.exist ()) {
            btnLogin = findViewById (R.id.btnLoginLogin);
            etEmail = findViewById (R.id.etLoginEmail);
            etPassword = findViewById (R.id.etLoginPassword);
            btnLogin.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    String email = etEmail.getText ().toString ().trim ();
                    String password = etPassword.getText ().toString ().trim ();
                    String url = "http://192.168.1.4/users?email=" + email + "&password=" + password;

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder ().permitAll ().build ();
                    StrictMode.setThreadPolicy (policy);

                    OkHttpClient client = new OkHttpClient ();
                    Request request = new Request.Builder ()
                            .url (url)
                            .build ();
                    try {
                        Response response = client.newCall (request).execute ();
                        ResponseBody responseBody = response.body ();
                        if (responseBody != null) {
                            String stringUser = responseBody.string ();
                            ObjectMapper mapper = new ObjectMapper ();
                            List<User> users = mapper.readValue (stringUser, new TypeReference<List<User>> () {
                            });
                            if (users.isEmpty ()) {
                                AlertDialog.Builder builder = new AlertDialog.Builder (Login.this);
                                builder.setMessage (("\nEmail or Password is incorrecte \nNumber of remaining attempts:" + counter))
                                        .setTitle ("ERROR")
                                        .setNegativeButton ("Retry", null)
                                        .create ()
                                        .show ();
                                counter--;
                                if (counter == -1) {
                                    btnLogin.setVisibility (View.GONE);
                                    counter = 3;
                                }
                            } else {
                                User user = users.get (0);
                                db.insert (user.email, user.password);
                                Intent intent = new Intent (getApplicationContext (), Menu.class);
                                startActivity (intent);
                                finish ();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace ();
                    }
                }
            });
        } else {
            String email = db.email ();
            String url = "http://192.168.1.4/users?email=" + email;

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder ().permitAll ().build ();
            StrictMode.setThreadPolicy (policy);

            OkHttpClient client = new OkHttpClient ();
            Request request = new Request.Builder ()
                    .url (url)
                    .build ();

            try {
                Response response = client.newCall (request).execute ();
                ResponseBody responseBody = response.body ();
                if (responseBody != null) {
                    String stringUser = responseBody.string ();
                    ObjectMapper mapper = new ObjectMapper ();
                    List<User> users = mapper.readValue (stringUser, new TypeReference<List<User>> () {
                    });
                    if (!users.isEmpty ()) {
                        Intent intent = new Intent (getApplicationContext (), Menu.class);
                        startActivity (intent);
                    }
                    finish ();
                }
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }

    public void signUp(View view) {
        Intent intent = new Intent (getApplicationContext (), Signup.class);
        startActivity (intent);
    }
}
