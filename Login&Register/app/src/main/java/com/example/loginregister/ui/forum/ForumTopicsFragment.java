package com.example.loginregister.ui.forum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginregister.R;

public class ForumTopicsFragment extends Fragment {

    private ForumTopicsViewModel forumTopicsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        forumTopicsViewModel =
                new ViewModelProvider(this).get(ForumTopicsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forum_topics, container, false);
        return root;
    }
}