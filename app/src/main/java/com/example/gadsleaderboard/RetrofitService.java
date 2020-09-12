package com.example.gadsleaderboard;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static Retrofit retrofitLeaderboard = new Retrofit.Builder()
            .baseUrl(ApiUtil.getInstance().BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static Retrofit retrofitSubmission = new Retrofit.Builder()
            .baseUrl(ApiUtil.getInstance().BASE_SUBMISSION_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createLeaderboardService(Class<S> serviceClass) {
        return retrofitLeaderboard.create(serviceClass);
    }

    public static <S> S createSubmissionService(Class<S> serviceClass) {
        return retrofitSubmission.create(serviceClass);
    }

}