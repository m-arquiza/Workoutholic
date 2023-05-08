package com.example.workoutholicapp.backend.ViewWorkout;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ViewWorkout {

    public static void main(String[] args) {

            List<Exercise> list = JSONmapper(View("chest"));
            Iterator<Exercise> itr = list.iterator();
            while(itr.hasNext()){
                System.out.println(itr.next().getName());
            }


    }


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
}

