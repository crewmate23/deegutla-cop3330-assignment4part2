/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sathwika Deegutla
 */

package ucf.assignments;

import java.util.ArrayList;

public class ToDoList {

    //NOT NEEDED - Private String variable for title
    //Private List<Item> for items in the list
    private ArrayList<Item> items;


    public void addItem(Item item){
        //get a new created Item object
        //add that Item object (parameter) to list of items
    }

    public void removeItem(Item item){
        //finds the item in list
        //removes it from the list
    }


    public ArrayList<Item> getAllItems(ToDoList list){
        //declare a new arraylist of Item objects - allItems
        //loop through ToDoList 'list' parameter and inside that loop through 'items'
        //add each item to the allItems arraylist
        //return the ArrayList<Item> 'allItems'
        return null; //right now null
    }

    public ArrayList<Item> inCompleteItems(ToDoList list){
        //declare a new arraylist of Item objects - inCompleteItems
        //loop through ToDoList 'list' parameter and inside that loop through 'items'
        //check whether that item is finished by calling Item's checkFinished() method
        //if they are not, add that item to inComplete items
        //return the inCompleteItems
        return null; //right now null
    }

    public ArrayList<Item> completeItems(ToDoList list){
        //declare a new arraylist of Item objects - completeItems
        //loop through arraylist 'lists' parameter and inside that loop through 'items'
        //check whether that item is finished by calling Item's checkFinished() method
        //if they are, add that item to completeItems
        //return the completeItems
        return null; //right now null
    }

    /* NOT NEEDED METHODS ANYMORE
    //constructor
    public ToDoList(String title){
        //sets this title to the given parameter
    }

    public void editTitle(String title){
        //updates this title to the new title from the parameter
    }

    public String getTitle(){
        //return the title value of the list
        return null; //right now null
    }
     */
}


