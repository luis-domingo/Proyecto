package com.example.loginregister.ui.forum;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.APIClient;
import com.example.loginregister.APIInterface;
import com.example.loginregister.R;
import com.example.loginregister.models.ForumPublication;
import com.example.loginregister.models.ForumTopic;
import com.example.loginregister.utils.MyRecyclerViewForumTopicsAdapter;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForumPublicationsFragment extends Fragment {

    private ForumPublicationsViewModel forumPublicationsViewModel;
    APIInterface apiIface;
    List<ForumPublication> publicationList = new LinkedList<ForumPublication>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        apiIface = APIClient.getClient().create(APIInterface.class);
        forumPublicationsViewModel = new ViewModelProvider(this).get(ForumPublicationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forum_publications, container, false);
        String name = getActivity().getIntent().getExtras().get("")
        Call<List<ForumPublication>> call = apiIface.getPublications();
        call.enqueue(new Callback<List<ForumTopic>>() {
            @Override
            public void onResponse(Call<List<ForumTopic>> call, Response<List<ForumTopic>> response) {
                if (response.code() == 200){
                    Log.d("INFO", "onResponse: " + response.body());
                    forumTopics = response.body();
                    RecyclerView myRecyclerView = (RecyclerView)root.findViewById(R.id.topicsTable);
                    myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    MyRecyclerViewForumTopicsAdapter adapter;
                    adapter = new MyRecyclerViewForumTopicsAdapter(getContext(), forumTopics);
                    adapter.setOnItemClickListener(new MyRecyclerViewForumTopicsAdapter.ClickListener() {
                        @Override
                        public void onItemClick(int position, View v) {
                            Log.d("grup3", "onItemClickPosition: " + position);
                            Intent openTopic = new Intent(getContext(), ForumPublication.class);
                            openTopic.putExtra("title", forumTopics.get(position).getTitle());
                            openTopic.putExtra("ID", forumTopics.get(position).getId());
                            startActivity(openTopic);
                        }
                    });
                    myRecyclerView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(getContext(), "Error when connecting to the database.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<ForumTopic>> call, Throwable throwable) {
                call.cancel();
            }
        });
        return root;
    }
}