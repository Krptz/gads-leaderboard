package com.example.gadsleaderboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LeadersApi {

    @GET("/api/hours")
    Call<List<Leader>> getLearningHoursLeaders();

    @GET("/api/skilliq")
    Call<List<Leader>> getSkillIqLeaders();

}


