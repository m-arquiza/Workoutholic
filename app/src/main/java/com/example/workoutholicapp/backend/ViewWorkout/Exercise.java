package com.example.workoutholicapp.backend.ViewWorkout;

public class Exercise {
    private String name;
    private String type;
    private String muscle;
    private String equipment;
    private String difficulty;
    private String instructions;
    private String weight;
    private String NumberOfSets;
    private String NumberOfReps;
    private String RestTimePerSet;

    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }
    public String getMuscle(){
        return muscle;
    }
    public String getEquipment(){
        return equipment;
    }
    public String getDifficulty(){
        return difficulty;
    }
    public String getInstructions(){
        return instructions;
    }
    public String getWeight(){
        return weight;
    }
    public void setWeight(String input){
        weight = input;
    }
    public String getNumberOfSets(){
        return NumberOfSets;
    }
    public void setNumberOfSets(String input){
        NumberOfSets = input;
    }
    public String getNumberOfReps(){
        return NumberOfReps;
    }
    public void setNumberOfReps(String input){
        NumberOfReps = input;
    }
    public String getRestTimePerSet(){
        return RestTimePerSet;
    }
    public void setRestTimePerSet(String input){
        RestTimePerSet = input;
    }
}
