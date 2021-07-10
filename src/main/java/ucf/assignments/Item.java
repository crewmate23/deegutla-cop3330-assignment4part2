/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sathwika Deegutla
 */

package ucf.assignments;

//package for Date object
import javafx.beans.property.SimpleStringProperty;

import java.awt.*;
import java.time.LocalDate;

public class Item {

    //Private String varibale for description
    //SimpleStringProperty - easier to output for javafx
    private SimpleStringProperty description;

    //Private String variable for date
    private LocalDate dueDate;

    //Private CheckBox varibale for whether the item is finished
    //instead of a boolean use checkbox for each item, easier for javafx
    private Checkbox complete;

    //constructor
    public Item(String description, LocalDate dueDate){
        //takes in parameters for description and date
        //assigns them accordingly
        this.description = new SimpleStringProperty(description);
        this.dueDate = dueDate;

        //also initializes a new checkbox for an item
        this.complete = new Checkbox();
    }

    //getters methods
    public LocalDate getDate(){
        //convert to Date object and return it
        //returns the Date object from convertDate()
        return dueDate;
    }

    public String getDescription(){
        //return the description of an Item object
        //.get() returns String, converting from SimpleStringProperty
        return description.get();
    }

    public Checkbox getComplete(){
        //return the checkbox complete
        return complete;
    }

    //setters methods
    public void editDescription(String description){
        //updates this description to the new description from parameter
        this.description = new SimpleStringProperty(description);
    }

    public void editDate(LocalDate dueDate){
        //updates this date to the new date from parameter
        this.dueDate = dueDate;
    }

    public void setComplete(Checkbox complete){
        //set the complete to the checkbox parameter
        this.complete = complete;
    }


    /*
    NOT NEEDED METHODS ANYMORE

    private Date convertDate(){
        //initialize new Date object
        //converts the String data of date into that Date object (for comparison)

        //returns converted Date object
        return null; //right now null literal
    }

    public void editFinished(Boolean finish){
        //sets whether the item is finished or not

    }

    public Boolean checkFinished() {
        //returns the boolean value of finished (for display options)
        return false; //right now false
    }*/

}

