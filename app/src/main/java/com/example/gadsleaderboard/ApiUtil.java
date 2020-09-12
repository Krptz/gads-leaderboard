package com.example.gadsleaderboard;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ApiUtil {

    final String BASE_API_URL = "https://gadsapi.herokuapp.com";
    final String BASE_SUBMISSION_URL = "https://docs.google.com/forms/d/e/";
    private static ApiUtil INSTANCE;

    enum Endpoint {

        LEARNING_HOURS("/api/hours"),
        SKILL_IQ("/api/skilliq");

        private String route;

        Endpoint(String route) {
            this.route = route;
        }

        public String getRoute() {
            return route;
        }

    }

    private ApiUtil() {
    }

    static ApiUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApiUtil();
        }
        return INSTANCE;
    }

    URL buildUrl(Endpoint endpoint) {
        URL url = null;
        try {
            url = new URL(BASE_API_URL + endpoint.getRoute());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return url;
    }

    String getJson(URL url) throws IOException {
        String json = "";
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasData = scanner.hasNext();
            if (hasData) {
                json = scanner.next();
            } else {
                json = null;
            }
        } catch (Exception exception) {
            Log.d(ApiUtil.getInstance().toString(), exception.toString());
        } finally {
            connection.disconnect();
        }
        return json;
    }

    public ArrayList<Leader> getLeadersFromJson(Endpoint endpoint) {
        ArrayList<Leader> leaders = new ArrayList<>();
        URL url = buildUrl(endpoint);
        try {
            String jsonString = getJson(url);
            Gson gson = new Gson();
            leaders = gson.fromJson(jsonString, new TypeToken<List<Leader>>(){}.getType());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return leaders;
    }

}