package com.example.post.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.post.MainActivity.MyPosts;
import com.example.post.R;
import com.example.post.SQLite.DataBase;
import com.example.post.models.Post;
import com.example.post.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class PostAdapterForMe extends RecyclerView.Adapter<PostAdapterForMe.ViewHolder> {
    private Dialog thisDialog;
    private EditText etTitle, etBody;
    private ImageView ivPost;
    private DataBase db;
    private Context context;
    private List<Post> posts;
    private String stringUsers, title, body, url = "http://192.168.1.4";
    private User user;
    private int userid;

    public PostAdapterForMe(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.my_post, parent, false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Post post = posts.get (position);
        holder.tvTitle.setText (post.title);
        holder.tvBody.setText (post.body);
        final int postid = post.id;
        holder.cv.setOnLongClickListener (new View.OnLongClickListener () {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder (context);
                builder.setTitle ("DELETE")
                        .setMessage ("Are you sure you want to delete this post")
                        .setNegativeButton ("NO", null)
                        .setPositiveButton ("YES", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                OkHttpClient client = new OkHttpClient ();
                                Request request = new Request.Builder ()
                                        .url (url + "/posts/" + String.valueOf (postid))
                                        .delete ()
                                        .build ();
                                try {
                                    Response response = client.newCall (request).execute ();
                                    response.body ().string ();
                                } catch (IOException e) {
                                    e.printStackTrace ();
                                }
                            }
                        })
                        .create ()
                        .show ();
                return true;
            }
        });
        holder.iv.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                thisDialog = new Dialog (context);
                thisDialog.setContentView (R.layout.post_update);
                thisDialog.show ();

                etTitle = thisDialog.findViewById (R.id.etUpdateTitle);
                etBody = thisDialog.findViewById (R.id.etUpdateBody);
                ivPost = thisDialog.findViewById (R.id.ivUpdateSend);

                etTitle.setText (post.title);
                etBody.setText (post.body);

                db = new DataBase (context);
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
                                dataJson.put ("title", title);
                                dataJson.put ("body", body);
                            } catch (JSONException e) {
                                e.printStackTrace ();
                            }

                            RequestBody requestBody = RequestBody.create (mediaType, dataJson.toString ());
                            Request request = new Request.Builder ()
                                    .url (url + "/posts/" + String.valueOf (postid))
                                    .patch (requestBody)
                                    .build ();

                            try {
                                Response response = client.newCall (request).execute ();
                                response.body ().string ();
                            } catch (IOException e) {
                                e.printStackTrace ();
                            }
                            etTitle.setText ("");
                            etBody.setText ("");
                            Toast.makeText (context, "Update", Toast.LENGTH_SHORT).show ();
                        } else {
                            Toast.makeText (context, "Fill Title and Body", Toast.LENGTH_SHORT).show ();
                        }
                    }
                });
            }
        });
    }

    public void sharedPrefesSave(String Title, String Body) {
        SharedPreferences preferences = context.getSharedPreferences ("Name", 0);
        SharedPreferences.Editor pref = preferences.edit ();
        pref.putString ("Title", Title);
        pref.putString ("Body", Body);
        pref.commit ();
    }

    @Override
    public int getItemCount() {
        return posts.size ();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvBody;
        CardView cv;
        ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);
            tvTitle = itemView.findViewById (R.id.tvMyTitle);
            tvBody = itemView.findViewById (R.id.tvMyBody);
            cv = itemView.findViewById (R.id.cvPost);
            iv = itemView.findViewById (R.id.ivMyEdit);
        }
    }
}
