/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sathwika Deegutla
 */

package ucf.assignments;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class ToDoList {

    //NOT NEEDED - Private String variable for title
    //Private List<Item> for items in the list
    private ArrayList<Item> items;

    //default constructor
    public ToDoList(){
        //initializes arraylist when created object
        items = new ArrayList<>();
    }

    //getters
    public ArrayList<Item> getAllItems(){
        //return all the items in the arraylist
        return items;
    }

    public ArrayList<Item> inCompleteItems(){
        //initialize a new arraylist for incomplete items
        ArrayList<Item> incompleteItems = new ArrayList<>();

        //loop through items and find if incomplete
        //use by getComplete() in Item.java- which returns an item's CheckBox
        //use CheckBox getState() method, which returns a boolean
        for(Item i: items){
            Checkbox temp = i.getComplete();
            if(!temp.getState()){ //if the check box is off
                incompleteItems.add(i); //then add into the incomplete items arraylist
            }
        }

        return incompleteItems;
    }

    public ArrayList<Item> completeItems(){
        //initialize a new arraylist for complete items
        ArrayList<Item> completeItems = new ArrayList<>();

        //loop through items and find if complete
        //use by getComplete() in Item.java- which returns an item's CheckBox
        //use CheckBox getState() method, which returns a boolean
        for(Item i: items){
            Checkbox temp = i.getComplete();
            if(temp.getState()){ //if the check box is on
                completeItems.add(i); //then add into the incomplete items arraylist
            }
        }

        return completeItems;
    }


    //setters
    public void addItem(Item item){
        //get a new created Item object
        //add that Item object (parameter) to list of items
        items.add(item);
    }

    public void removeItem(Item item){
        //removes it from the list
        items.remove(item);
    }

    public void clearAll(){
        //removes every single item in the list
        //using enhanced for loop
        /*for(Item i: items){
            items.remove(i);
        }*/
        items.clear();
    }


    public Boolean itemExists(Item findItem){
        //run through the loop and find if the item exists
        for(Item i : items){
            if(i.equals(findItem)){
                return true; //item found return true
            }
        }

        return false; //else return false if item not found
    }


    public void save(File fileDirectory){
        //gets a file or file directory
        //saves this arraylist of items into file as tabular format
        //EXAMPLE OUTPUT
        //Description       |Due Date       |Completed
        //------------------|---------------|-----------
    }

    public ArrayList<Item> load(File fileDirectory){
        //creates and initializes new arraylist - loadItems
        //gets a file or file directory, ".txt" file
        //reads the file in tabular format
        //assigns new item into the loadItems

        return null;
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
     */
}


