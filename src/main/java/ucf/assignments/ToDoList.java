/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sathwika Deegutla
 */

package ucf.assignments;

import javafx.scene.control.CheckBox;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ToDoList {

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
        //use CheckBox isSelected() method, which returns a boolean
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
        //use CheckBox isSelected() method, which returns a boolean
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
        //use arraylist method clear to remove all items
        items.clear();
    }

    public void updateItem(Item item, String newDescription, LocalDate newDueDate){
        //get the item object, and its new description and date object
        //using item's setter methods, assign them respectively
        item.setDescription(newDescription);
        item.setDueDate(newDueDate);
    }


    public Boolean itemExists(String findItem){
        //check if the items exists already using description
        //run through the loop and find if the item exists
        for(Item i : items){
            if(i.getDescription().equals(findItem)){
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
            //use a bufferedwriter to write into the file
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileDirectory));

            //write the headings
            bw.write("Description");
            bw.write(",");
            bw.write("DueDate");
            bw.write(",");
            bw.write("Completed");
            bw.write("\n");

            //loop through every time and write into file
            for (Item item: items) {
                //console message
                System.out.print("Adding item to file: ");
                System.out.print(item.getDescription() + ", ");

                //write description
                bw.write(item.getDescription());
                bw.write(",");

                //first convert date object into string using toString()
                String date = item.getDueDate().toString();

                //console message
                System.out.print(date+"\n");

                //write date - string
                bw.write(date);
                bw.write(",");

                //check if checkbox is checked or unchecked
                if(item.getComplete().isSelected()){
                    bw.write("yes\n"); //if checked, write "yes" into file
                }else{
                    bw.write("no\n"); //else, write "no" into file
                }
            }

            //close writer
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Item> load(File fileDirectory){
        //creates and initializes new arraylist - fileItems
        //gets a file or file directory, ".csv" file
        //reads the file in csv format
        //assigns new item into the fileItems

        ArrayList<Item> fileItems = new ArrayList<>();

        try {
            //use a bufferedreader for reading the csv file
            BufferedReader br = new BufferedReader(new FileReader(fileDirectory));

            String line = "";
            line = br.readLine(); //ignores the headings
            while((line = br.readLine()) != null){
                String[] values = line.split(","); //to separate values

                System.out.println(values); //console message

                //convert from string to date
                Item item = new Item(values[0], convertToDate(values[1]));

                //create a temp checkbox to add to an item
                CheckBox complete = new CheckBox();

                //check if the text is "yes" or "no" and set checkbox accordingly
                if(values[2].equalsIgnoreCase("yes")){
                    complete.setSelected(true);
                }else{
                    complete.setSelected(false);
                }

                //add the checkbox to the item
                item.setComplete(complete);

                //add into fileItems
                fileItems.add(item);
            }

            //also add into this list items
            items = fileItems;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileItems; //return arraylist to display on screen
    }

    //helps convert string to formatted date object
    private LocalDate convertToDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //format

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);

        System.out.println(localDate); //console message

        return localDate;
    }

}


