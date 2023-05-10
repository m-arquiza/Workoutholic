package com.example.workoutholicapp.backend.ViewWorkout;

import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ViewWorkout {
    public List<String> MuscleList = new ArrayList<String>(Arrays.asList(
            "abdominals",
            "abductors",
            "adductors",
            "biceps",
            "calves",
            "chest",
            "forearms",
            "glutes",
            "hamstrings",
            "lats",
            "lower_back",
            "middle_back",
            "neck",
            "quadriceps",
            "traps",
            "triceps"));
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final OkHttpClient client = new OkHttpClient();

    public static List<String> muscleList() {
        return new ArrayList<String>(Arrays.asList(
                "abdominals",
                "abductors",
                "adductors",
                "biceps",
                "calves",
                "chest",
                "forearms",
                "glutes",
                "hamstrings",
                "lats",
                "lower_back",
                "middle_back",
                "neck",
                "quadriceps",
                "traps",
                "triceps"));
    }

    /**
     * Gives a Json string of 10 workouts for a specified
     * muscle group
     *
     * @param muscle The muscle group will be looked up
     * @return Json String containing the exercises
     */
    public static String View(String muscle){
        OkHttpClient client = new OkHttpClient();
        String url ="https://api.api-ninjas.com/v1/exercises?muscle="+muscle;
        Request request = new Request.Builder()
                .url(url)
                .header("X-Api-Key", "pu0sVRYfKhVbegE9yYRpLA==pBKp94cR9RE5WLzK")
                .build();
        String responseBody ="";
        try(Response response = client.newCall(request).execute()){
            responseBody = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

    /**
     * Maps a Json string to a list of exercises
     *
     * @param  input A json string that woul be obtained in the View method
     * @return       A List of exercise objects
     */
    public static List<Exercise> JSONmapper(String input){
        ObjectMapper mapper = new ObjectMapper();
        List<Exercise> exerciseList = null;
        try{
            exerciseList = mapper.readValue(input, new TypeReference<List<Exercise>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exerciseList;
    }

    public static Future<Response> getRequest(String muscle) {
        String url = "https://api.api-ninjas.com/v1/exercises?muscle="+muscle;
        Log.d("muscle", "url is: " + url);
        return executor.submit(() -> {
            Request request = new Request.Builder()
                    .url(url)
                    .header("X-Api-Key", "pu0sVRYfKhVbegE9yYRpLA==pBKp94cR9RE5WLzK")
                    .build();
            return client.newCall(request).execute();
        });
    }
}

