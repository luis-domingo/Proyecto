package com.example.loginregister.ui.forum;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.APIClient;
import com.example.loginregister.APIInterface;
import com.example.loginregister.R;
import com.example.loginregister.models.ForumPublication;
import com.example.loginregister.models.ForumTopic;
import com.example.loginregister.utils.MyRecyclerViewForumPublicationsAdapter;
import com.example.loginregister.utils.MyRecyclerViewForumTopicsAdapter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ForumPublicationsFragment extends Fragment {

    private ForumPublicationsViewModel forumPublicationsViewModel;
    APIInterface apiIface;
    List<ForumPublication> publicationList = new LinkedList<ForumPublication>();
    SharedPreferences mySharedPreferences;
    EditText editTextTextMultiLine;
    String id;
    String title;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        apiIface = APIClient.getClient().create(APIInterface.class);
        mySharedPreferences = getActivity().getSharedPreferences("mySharedPreferences", MODE_PRIVATE);
        forumPublicationsViewModel = new ViewModelProvider(this).get(ForumPublicationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forum_publications, container, false);
        TextView txtTitle = (TextView)root.findViewById(R.id.txtTitleTopic);
        editTextTextMultiLine = (EditText)root.findViewById(R.id.editTextTextMultiLine);
        title = getActivity().getIntent().getExtras().get("title").toString();
        txtTitle.setText("Topic: " + title);
        id = getActivity().getIntent().getExtras().get("ID").toString();
        ForumTopic topic = new ForumTopic(title, id);
        Call<List<ForumPublication>> call = apiIface.getPublications(topic);
        call.enqueue(new Callback<List<ForumPublication>>() {
            @Override
            public void onResponse(Call<List<ForumPublication>> call, Response<List<ForumPublication>> response) {
                if (response.code() == 200){
                    Log.d("INFO", "onResponse: " + response.body());
                    publicationList = response.body();
                    RecyclerView myRecyclerView = (RecyclerView)root.findViewById(R.id.publicationsTable);
                    myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    MyRecyclerViewForumPublicationsAdapter adapter;
                    adapter = new MyRecyclerViewForumPublicationsAdapter(getContext(), publicationList);
                    myRecyclerView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(getContext(), "Error when connecting to the database.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<ForumPublication>> call, Throwable throwable) {
                call.cancel();
            }
        });
        Button button = (Button)root.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                ForumPublication newForumPublication = new ForumPublication(mySharedPreferences.getAll().get("Username").toString(), LocalDateTime.now().toString(), editTextTextMultiLine.getText().toString(), title);
                Call<Void> call = apiIface.addPublication(newForumPublication);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        publicationList.add(newForumPublication);
                        onCreateView(inflater, container, savedInstanceState);
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable throwable) {
                        Toast.makeText(getContext(), "Error when connecting to the database.", Toast.LENGTH_SHORT).show();
                        call.cancel();
                    }
                });
            }
        });
        return root;
    }
}