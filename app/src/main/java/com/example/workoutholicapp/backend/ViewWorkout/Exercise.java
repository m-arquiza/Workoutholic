package com.example.workoutholicapp.backend.ViewWorkout;

/**
 * This is the Exercise Object
 * An Exercise object contain all the information
 * about the exercise, but also additional info
 * such as the number of reps you have to do for that
 * exercise
 *
 * All getters and setters provided for each param
 */
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
    public void setName(String name) { this.name = name; }
    public String getType(){
        return type;
    }

    public void setMuscle(String muscle) { this.muscle = muscle; }
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
