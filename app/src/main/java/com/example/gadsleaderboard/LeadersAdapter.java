package com.example.gadsleaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LeadersAdapter extends RecyclerView.Adapter<LeadersAdapter.LeaderViewHolder> {

    private Context context;
    private List<Leader> leaders;
    private int pageIndex;

    class LeaderViewHolder extends RecyclerView.ViewHolder {
        TextView leaderName = itemView.findViewById(R.id.leader_name);
        TextView leaderInformation = itemView.findViewById(R.id.leader_information);

        LeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    LeadersAdapter(Context context, List<Leader> leaders, int pageIndex) {
        this.context = context;
        this.leaders = leaders;
        this.pageIndex = pageIndex;
    }

    @NonNull
    @Override
    public LeadersAdapter.LeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView;
        switch (pageIndex) {
            case 0:
                itemView = LayoutInflater.from(context).inflate(R.layout.hours_leader_item, parent, false);
                break;
            case 1:
                itemView = LayoutInflater.from(context).inflate(R.layout.skilliq_leader_item, parent, false);
                break;
            default:
                itemView = LayoutInflater.from(context).inflate(R.layout.hours_leader_item, parent, false);
        }
        return new LeaderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeadersAdapter.LeaderViewHolder holder, int position) {
        Leader leader = leaders.get(position);
        String leader_info = "";
        switch (pageIndex) {
            case 0:
                leader_info = context.getString(R.string.learning_leader_info, leader.getHours(), leader.getCountry());
                break;
            case 1:
                leader_info = context.getString(R.string.skill_iq_info, leader.getScore(), leader.getCountry());
                break;
        }
        holder.leaderName.setText(leader.getName());
        holder.leaderInformation.setText(leader_info);
    }

    @Override
    public int getItemCount() {
        if (leaders != null)
            return leaders.size();
        else return 0;
    }

}