/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sathwika Deegutla
 */

package ucf.assignments;

import javafx.scene.control.CheckBox;

import javafx.scene.control.CheckBox;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            CheckBox temp = i.getComplete();
            if(!temp.isSelected()){ //if the check box is off
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
            CheckBox temp = i.getComplete();
            if(temp.isSelected()){ //if the check box is on
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

    public void updateItem(Item item, String newDescription, LocalDate newDueDate){
        item.setDescription(newDescription);
        item.setDueDate(newDueDate);
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
        //saves this arraylist of items into file as csv format
        //EXAMPLE OUTPUT
        //Description,Due Date,Completed


        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileDirectory));

            bw.write("Description");
            bw.write(",");
            bw.write("DueDate");
            bw.write(",");
            bw.write("Completed");
            bw.write("\n");

            for (Item item: items) {

                System.out.print("Adding item to file: ");
                System.out.print(item.getDescription());
                bw.write(item.getDescription());
                bw.write(",");

                String date = item.getDueDate().toString();
                System.out.print(date+"\n");
                bw.write(date);
                bw.write(",");


                if(item.getComplete().isSelected()){
                    bw.write("yes\n");
                }else{
                    bw.write("no\n");
                }
            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Item> load(File fileDirectory){
        //creates and initializes new arraylist - loadItems
        //gets a file or file directory, ".txt" file
        //reads the file in tabular format
        //assigns new item into the loadItems

        ArrayList<Item> fileItems = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileDirectory));

            String line = "";
            line = br.readLine(); //ignores the titles
            while((line = br.readLine()) != null){
                String[] values = line.split(",");

                System.out.println(values);
                //convert from string to date
                Item item = new Item(values[0], convertToDate(values[1]));
                CheckBox complete = new CheckBox();

                if(values[2].equalsIgnoreCase("yes")){
                    complete.setSelected(true);
                }else{
                    complete.setSelected(false);
                }

                item.setComplete(complete);

                fileItems.add(item);
            }

            items = fileItems;
            //br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileItems;
    }

    private LocalDate convertToDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);

        System.out.println(localDate);

        return localDate;
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


