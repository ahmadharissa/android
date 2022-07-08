package com.example.post.MainActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.post.R;
import com.example.post.SQLite.DataBase;
import com.example.post.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Menu extends AppCompatActivity {
    CardView cardView;
    EditText etTitle, etBody;
    ImageView ivPost;
    Dialog thisDialog;
    String stringUsers, title, body, url = "http://192.168.1.4";
    User user;
    DataBase db;
    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.menu);

        cardView = findViewById (R.id.cvMenuSend);
        cardView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                thisDialog = new Dialog (Menu.this);
                thisDialog.setContentView (R.layout.post_send);
                thisDialog.show ();

                etTitle = thisDialog.findViewById (R.id.etSendTitle);
                etBody = thisDialog.findViewById (R.id.etSendBody);
                ivPost = thisDialog.findViewById (R.id.ivSendPost);

                db = new DataBase (getApplicationContext ());
                String email = db.email ();

                OkHttpClient client;
                client = new OkHttpClient ();
                Request request = new Request.Builder ()
                        .url (url + "/users?email=" + email)
                        .build ();
                try {
                    Response response = client.newCall (request).execute ();
                    ResponseBody responseBody = response.body ();
                    if (responseBody != null) {
                        stringUsers = responseBody.string ();
                        ObjectMapper mapper = new ObjectMapper ();
                        List<User> users = mapper.readValue (stringUsers, new TypeReference<List<User>> () {
                        });
                        if (!users.isEmpty ()) {
                            user = users.get (0);
                            userid = user.id;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace ();
                }

                ivPost.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        title = etTitle.getText ().toString ();
                        body = etBody.getText ().toString ();

                        sharedPrefesSave (title, body);
                        if (!title.equals ("") && !body.equals ("")) {
                            MediaType mediaType = MediaType.parse ("application/json");

                            OkHttpClient client = new OkHttpClient ();
                            JSONObject dataJson = new JSONObject ();

                            try {
                                dataJson.put ("userId", userid);
                                dataJson.put ("title", title);
                                dataJson.put ("body", body);
                            } catch (JSONException e) {
                                e.printStackTrace ();
                            }

                            RequestBody requestBody = RequestBody.create (mediaType, dataJson.toString ());
                            Request request = new Request.Builder ()
                                    .url (url + "/posts")
                                    .post (requestBody)
                                    .build ();

                            try {
                                Response response = client.newCall (request).execute ();
                                response.body ().string ();
                            } catch (IOException e) {
                                e.printStackTrace ();
                            }
                            etTitle.setText ("");
                            etBody.setText ("");
                            Toast.makeText (getApplicationContext (), "Send", Toast.LENGTH_SHORT).show ();
                        } else {
                            Toast.makeText (getApplicationContext (), "Fill Title and Body", Toast.LENGTH_SHORT).show ();
                        }
                    }
                });
            }
        });
    }

    public void post(View view) {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity (intent);
    }

    public void users(View view) {
        Intent intent = new Intent (this, UsersInfo.class);
        startActivity (intent);
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inf = getMenuInflater ();
        inf.inflate (R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu (menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId ();

        if (id == R.id.logout) {
            logOut ();
        }

        return super.onOptionsItemSelected (item);
    }

    private void logOut() {
        DataBase db = new DataBase (getApplicationContext ());
        db.deletAllData ();
        Intent intent = new Intent (getApplicationContext (), Login.class);
        startActivity (intent);
        finish ();
    }

    public void postForMe(View view) {
        Intent intent = new Intent (this, MyPosts.class);
        startActivity (intent);
    }

    public void sharedPrefesSave(String Title, String Body) {
        SharedPreferences preferences = getApplicationContext ().getSharedPreferences ("Name", 0);
        SharedPreferences.Editor pref = preferences.edit ();
        pref.putString ("Title", Title);
        pref.putString ("Body", Body);
        pref.commit ();
    }
    /*
               LayoutInflater inflater = (LayoutInflater) context.getSystemService (LAYOUT_INFLATER_SERVICE);
                final View popupView = inflater.inflate (R.layout.post_update, null);

                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;

                final PopupWindow popup = new PopupWindow (popupView, width, height, true);
                popup.showAtLocation (popupView, Gravity.CENTER, 0, -300);
                popupView.setOnTouchListener (new View.OnTouchListener () {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popup.dismiss ();
                        return true;
                    }
                });
                Button btnSave = popupView.findViewById (R.id.btnUpdateSave);
                btnSave.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {

                    }
                });
    */
}

