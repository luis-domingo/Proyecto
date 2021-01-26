package com.example.loginregister;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.models.ForumPublication;
import com.example.loginregister.models.ForumTopic;
import com.example.loginregister.utils.MyRecyclerViewForumPublicationsAdapter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForumPublicationsActivity extends AppCompatActivity {

    APIInterface apiIface;
    List<ForumPublication> publicationList = new LinkedList<ForumPublication>();
    SharedPreferences mySharedPreferences;
    EditText editTextTextMultiLine;
    String id;
    String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_forum_publications);
        apiIface = APIClient.getClient().create(APIInterface.class);
        mySharedPreferences = getSharedPreferences("mySharedPreferences", MODE_PRIVATE);
        TextView txtTitle = (TextView)findViewById(R.id.txtTitleTopic);
        editTextTextMultiLine = (EditText)findViewById(R.id.editTextTextMultiLine);
        title = getIntent().getExtras().get("title").toString();
        txtTitle.setText("Topic: " + title);
        id = getIntent().getExtras().get("ID").toString();
        ForumTopic topic = new ForumTopic(title, id);
        Call<List<ForumPublication>> call = apiIface.getPublications(topic);
        call.enqueue(new Callback<List<ForumPublication>>() {
            @Override
            public void onResponse(Call<List<ForumPublication>> call, Response<List<ForumPublication>> response) {
                if (response.code() == 200){
                    Log.d("INFO", "onResponse: " + response.body());
                    publicationList = response.body();
                    RecyclerView myRecyclerView = (RecyclerView)findViewById(R.id.publicationsTable);
                    myRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    MyRecyclerViewForumPublicationsAdapter adapter;
                    adapter = new MyRecyclerViewForumPublicationsAdapter(getApplicationContext(), publicationList);
                    myRecyclerView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error when connecting to the database.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<ForumPublication>> call, Throwable throwable) {
                call.cancel();
            }
        });
        Button button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                ForumPublication newForumPublication = new ForumPublication(mySharedPreferences.getAll().get("Username").toString(), LocalDateTime.now().toString(), editTextTextMultiLine.getText().toString(), id);
                Call<Void> call = apiIface.addPublication(newForumPublication);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        publicationList.add(newForumPublication);
                        ForumTopic topic = new ForumTopic(title, id);
                        Call<List<ForumPublication>> call1 = apiIface.getPublications(topic);
                        call1.enqueue(new Callback<List<ForumPublication>>() {
                            @Override
                            public void onResponse(Call<List<ForumPublication>> call1, Response<List<ForumPublication>> response) {
                                if (response.code() == 200){
                                    Log.d("INFO", "onResponse: " + response.body());
                                    publicationList = response.body();
                                    RecyclerView myRecyclerView = (RecyclerView)findViewById(R.id.publicationsTable);
                                    myRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    MyRecyclerViewForumPublicationsAdapter adapter;
                                    adapter = new MyRecyclerViewForumPublicationsAdapter(getApplicationContext(), publicationList);
                                    myRecyclerView.setAdapter(adapter);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Error when connecting to the database.", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<List<ForumPublication>> call, Throwable throwable) {
                                call1.cancel();
                            }
                        });
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable throwable) {
                        Toast.makeText(getApplicationContext(), "Error when connecting to the database.", Toast.LENGTH_SHORT).show();
                        call.cancel();
                    }
                });
            }
        });
    }
}