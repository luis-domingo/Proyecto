package com.example.loginregister.ui.forum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginregister.APIInterface;
import com.example.loginregister.R;
import com.example.loginregister.models.ForumPublication;

import java.util.LinkedList;
import java.util.List;

public class ForumPublicationsFragment extends Fragment {

    private ForumPublicationsViewModel forumPublicationsViewModel;
    APIInterface apiIface;
    List<ForumPublication> publicationList = new LinkedList<ForumPublication>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        forumPublicationsViewModel =
                new ViewModelProvider(this).get(ForumPublicationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forum_topics, container, false);



        return root;
    }

    public void setPublicationList(List<ForumPublication> publicationList){
        this.publicationList = publicationList;
    }
}