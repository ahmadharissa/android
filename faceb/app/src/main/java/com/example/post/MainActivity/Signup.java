package com.example.post.MainActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.post.R;
import com.example.post.SQLite.DataBase;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Signup extends Activity {
    String url = "http://192.168.1.4";
    EditText etName, etUserName, etEmail, etPassword, etConfirmPassword, etPhone;
    Button btnSave;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.signup);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder ().permitAll ().build ();
        StrictMode.setThreadPolicy (policy);

        etEmail = findViewById (R.id.etSignupEmail);
        etName = findViewById (R.id.etSignupName);
        etUserName = findViewById (R.id.etSignupUsername);
        etPassword = findViewById (R.id.etSignupPassword);
        etConfirmPassword = findViewById (R.id.etSignupCPassword);
        etPhone = findViewById (R.id.etSignupPhone);
        btnSave = findViewById (R.id.btnSignupSave);
        btnSave.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String etname, etuserName, etemail, etpassword, etconfirmpassword, etphone;
                etname = etName.getText ().toString ();
                etemail = etEmail.getText ().toString ();
                etuserName = etUserName.getText ().toString ();
                etpassword = etPassword.getText ().toString ();
                etconfirmpassword = etConfirmPassword.getText ().toString ();
                etphone = etPhone.getText ().toString ();
                if (etname.length () >= 3 && etuserName.length () >= 4) {
                    if (etemail.contains ("@") && etemail.contains (".")) {
                        if (etphone.trim ().length () == 8) {
                            if (etpassword.trim ().length () > 6) {
                                if (etpassword.trim ().equals (etconfirmpassword.trim ())) {
                                    MediaType mediaType = MediaType.parse ("application/json");
                                    OkHttpClient client = new OkHttpClient ();
                                    JSONObject dataJson = new JSONObject ();

                                    try {
                                        dataJson.put ("name", etname);
                                        dataJson.put ("username", etuserName);
                                        dataJson.put ("email", etemail);
                                        dataJson.put ("password", etpassword);
                                        dataJson.put ("phone", etphone);
                                        dataJson.put ("photo", "https://cdn.psychologytoday.com/sites/default/files/styles/image-article_inline_full/public/field_blog_entry_images/2018-09/shutterstock_648907024.jpg?itok=ji6Xj8tv");
                                    } catch (JSONException e) {
                                        e.printStackTrace ();
                                    }
                                    RequestBody requestBody = RequestBody.create (mediaType, dataJson.toString ());
                                    Request request = new Request.Builder ()
                                            .url (url + "/users")
                                            .post (requestBody)
                                            .build ();

                                    client.newCall (request).enqueue (new Callback () {
                                        @Override
                                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                            String mMessage = e.getMessage ().toString ();
                                            Log.w ("failure Response", mMessage);
                                        }

                                        @Override
                                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                            String mMessage = response.body ().string ();
                                            Log.e ("lolooooooo", mMessage);
                                        }
                                    });
                                    db = new DataBase (getApplicationContext ());
                                    if (!db.exist ()) {
                                        db.insert (etemail, etpassword);
                                    }
                                    Intent intent = new Intent (getApplicationContext (), Menu.class);
                                    startActivity (intent);
                                    finish ();
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder (Signup.this);
                                    builder.setTitle ("ERRORE")
                                            .setMessage ("Password must be equal Confirm Password")
                                            .setNegativeButton ("OK", null)
                                            .create ()
                                            .show ();
                                }
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder (Signup.this);
                                builder.setTitle ("ERRORE")
                                        .setMessage ("Password must be greater than 6")
                                        .setNegativeButton ("OK", null)
                                        .create ()
                                        .show ();
                            }
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder (Signup.this);
                            builder.setTitle ("ERRORE")
                                    .setMessage ("\nNumber phone must be 8 number")
                                    .setNegativeButton ("OK", null)
                                    .create ()
                                    .show ();
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder (Signup.this);
                        builder.setTitle ("ERRORE")
                                .setMessage ("\nEmail not register")
                                .setNegativeButton ("OK", null)
                                .create ()
                                .show ();
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder (Signup.this);
                    builder.setTitle ("ERRORE")
                            .setMessage ("\nName or user Name is too short")
                            .setNegativeButton ("OK", null)
                            .create ()
                            .show ();
                }
            }
        });
    }
}
