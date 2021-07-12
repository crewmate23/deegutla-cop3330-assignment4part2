/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sathwika Deegutla
 */

package ucf.assignments;

//package for Date object
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

import java.time.LocalDate;

public class Item {

    //Private String varibale for description
    //SimpleStringProperty - easier to output for javafx
    private SimpleStringProperty description;

    //Private String variable for date
    private LocalDate dueDate;

    //Private JavaFX CheckBox varibale for whether the item is finished
    //instead of a boolean use checkbox for each item, easier for javafx
    private CheckBox complete;

    //constructor
    public Item(String description, LocalDate dueDate){
        //takes in parameters for description and date
        //assigns them accordingly
        this.description = new SimpleStringProperty(description);
        this.dueDate = dueDate;

        //also initializes a new checkbox for an item
        this.complete = new CheckBox();
    }

    //getters methods
    public LocalDate getDueDate(){
        //returns the Date object
        return dueDate;
    }

    public String getDescription(){
        //return the description of an Item object
        //.get() returns String, converting from SimpleStringProperty
        return description.get();
    }

    public CheckBox getComplete(){
        //return the checkbox complete
        return complete;
    }

    //setters methods
    public void setDescription(String description){
        //updates this description to the new description from parameter
        //convert from string to simplestringproperty
        this.description = new SimpleStringProperty(description);
    }

    public void setDueDate(LocalDate dueDate){
        //updates this date to the new date from parameter
        this.dueDate = dueDate;
    }

    public void setComplete(CheckBox complete){
        //set the complete to the checkbox parameter
        this.complete = complete;
    }

}

