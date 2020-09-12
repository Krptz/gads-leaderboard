package com.example.gadsleaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LeadersFragment extends Fragment {

    private final static String ARG_PAGE_INDEX = "page_index";
    private int pageIndex;
    private ArrayList<Leader> leadersList = new ArrayList<>();
    private RecyclerView leadersRecyclerView;
    private LeadersViewModel leadersViewModel;
    private LeadersAdapter leadersAdapter;
    private ProgressBar progressBar;

    static LeadersFragment newInstance(int pageIndex) {
        LeadersFragment fragment = new LeadersFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE_INDEX, pageIndex);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leadersViewModel = ViewModelProviders.of(this).get(LeadersViewModel.class);
        pageIndex = 0;
        if (getArguments() != null)
            pageIndex = getArguments().getInt(ARG_PAGE_INDEX);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.leaders_fragment, container, false);
        leadersRecyclerView = root.findViewById(R.id.hours_leaders_rview);
        progressBar = root.findViewById(R.id.loading);
        switch (pageIndex) {
            case 0:
                leadersViewModel.getLearningHoursLeaders().observe(this, new Observer<List<Leader>>() {
                    @Override
                    public void onChanged(List<Leader> leaders) {
                        if (leaders != null && !leaders.isEmpty()) {
                            progressBar.setVisibility(View.GONE);
                            leadersList.addAll(leaders);
                            leadersAdapter.notifyDataSetChanged();
                        } else {
                            progressBar.setVisibility(View.VISIBLE);
                        }
                    }
                });
                break;
            case 1:
                leadersViewModel.getSkillIQLeaders().observe(this, new Observer<List<Leader>>() {
                    @Override
                    public void onChanged(List<Leader> leaders) {
                        if (leaders != null && !leaders.isEmpty()) {
                            progressBar.setVisibility(View.GONE);
                            leadersList.addAll(leaders);
                            leadersAdapter.notifyDataSetChanged();
                        } else {
                            progressBar.setVisibility(View.VISIBLE);
                        }
                    }
                });
                break;
        }
        setupRecyclerView();
        return root;
    }

    private void setupRecyclerView() {
        if (leadersAdapter == null) {
            leadersAdapter = new LeadersAdapter(getActivity(), leadersList, pageIndex);
            leadersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            leadersRecyclerView.setAdapter(leadersAdapter);
            leadersRecyclerView.setItemAnimator(new DefaultItemAnimator());
            leadersRecyclerView.setNestedScrollingEnabled(true);
        } else {
            leadersAdapter.notifyDataSetChanged();
        }
    }

}