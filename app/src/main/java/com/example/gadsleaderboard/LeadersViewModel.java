package com.example.gadsleaderboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.gadsleaderboard.ApiUtil.Endpoint.LEARNING_HOURS;
import static com.example.gadsleaderboard.ApiUtil.Endpoint.SKILL_IQ;

public class LeadersViewModel extends ViewModel {

    private MutableLiveData<List<Leader>> mutableLiveData;
    private LeadersRepository leadersRepository;

    public LeadersViewModel() {
        leadersRepository = LeadersRepository.getInstance();
    }

    public LiveData<List<Leader>> getLearningHoursLeaders() {
        mutableLiveData = leadersRepository.getLeaders(LEARNING_HOURS);
        return mutableLiveData;
    }

    public LiveData<List<Leader>> getSkillIQLeaders() {
        mutableLiveData = leadersRepository.getLeaders(SKILL_IQ);
        return mutableLiveData;
    }

    public LiveData<List<Leader>> getHoursLeaders() {
        ApiUtil apiUtil = ApiUtil.getInstance();
        ArrayList<Leader> learningHoursLeaders = apiUtil.getLeadersFromJson(LEARNING_HOURS);
        MutableLiveData<List<Leader>> mutableLeadersListLiveData = new MutableLiveData<>();
        mutableLeadersListLiveData.setValue(learningHoursLeaders);
        return mutableLeadersListLiveData;
    }

    public LiveData<List<Leader>> getIQLeaders() {
        ApiUtil apiUtil = ApiUtil.getInstance();
        ArrayList<Leader> learningHoursLeaders = apiUtil.getLeadersFromJson(SKILL_IQ);
        MutableLiveData<List<Leader>> mutableLeadersListLiveData = new MutableLiveData<>();
        mutableLeadersListLiveData.setValue(learningHoursLeaders);
        return mutableLeadersListLiveData;
    }

}