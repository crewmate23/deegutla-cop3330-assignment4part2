/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sathwika Deegutla
 */
package ucf.assignments;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.CheckBox;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {

    //global declaraiton of todolist object
    private ToDoList list;

    //create a panel for testing the list and item object
    //since used CheckBox (javafx) object by Item class
    private JFXPanel panel = new JFXPanel();

    @Test
    void addItem_Test() {
        //test addItem()
        //initialize ToDoList object
        list = new ToDoList();

        //create a expected arraylist for comparing
        ArrayList<Item> expectedItems = new ArrayList<>();

        //create dummy data of item's objects
        Item item1 = new Item("Cook", LocalDate.of(2021, Month.JULY, 12));
        Item item2 = new Item("Take Dog to walk", LocalDate.of(2021, Month.JULY, 13));
        Item item3 = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        //add the dummy date into ToDoList object using addItem() method
        list.addItem(item1);
        list.addItem(item2);
        list.addItem(item3);

        //add dummy date into expected arraylist
        expectedItems.add(item1);
        expectedItems.add(item2);
        expectedItems.add(item3);

        //compare both by converting to arrays and check if they are equal
        assertArrayEquals(expectedItems.toArray(), list.getAllItems().toArray());
    }

    @Test
    void removeItem_Test(){
        //test removeItem()
        //initialize ToDoList object
        list = new ToDoList();

        //also create an expected arraylist for comparing
        ArrayList<Item> expectedItems = new ArrayList<>();

        //create dummy data of item's objects
        Item item1 = new Item("Cook", LocalDate.of(2021, Month.JULY, 12));
        Item item2 = new Item("Take Dog to walk", LocalDate.of(2021, Month.JULY, 13));
        Item item3 = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        //add the dummy data into ToDoList object using addItem() method
        list.addItem(item1);
        list.addItem(item2);
        list.addItem(item3);

        //also add into the expeceted arraylist
        expectedItems.add(item1);
        expectedItems.add(item2);
        expectedItems.add(item3);

        //call removeItem() on dummy item
        list.removeItem(item3);

        //call remove on expeceted arraylist on the same dummy item
        expectedItems.remove(item3);

        //check if both arraylist are equal by converting to arrays
        assertArrayEquals(expectedItems.toArray(), list.getAllItems().toArray());
    }

    @Test
    void removeItem_Test_False(){
        //check if removeItem() works

        //initialize ToDoList object
        list = new ToDoList();

        //also create a expected arraylist for comparing
        ArrayList<Item> expectedItems = new ArrayList<>();

        //create dummy data of item's objects
        Item item1 = new Item("Cook", LocalDate.of(2021, Month.JULY, 12));
        Item item2 = new Item("Take Dog to walk", LocalDate.of(2021, Month.JULY, 13));
        Item item3 = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        //add the dummy items into ToDoList object
        list.addItem(item1);
        list.addItem(item2);
        list.addItem(item3);

        //also add into expected arraylist
        expectedItems.add(item1);
        expectedItems.add(item2);
        expectedItems.add(item3);

        //remove a random item from dummy data
        list.removeItem(item1);

        //remove a different item
        expectedItems.remove(item3);

        //should be false, since they both removed a different item
        Boolean check = (list.getAllItems()).equals(expectedItems);

        //check if it is false
        assertFalse(check);
    }

    @Test
    void clearAll_Test(){
        //clearAll()
        //initialize ToDoList object
        list = new ToDoList();

        //also create an expected arraylist for comparing
        ArrayList<Item> expectedItems = new ArrayList<>();

        //create dummy data of items' objects
        Item item1 = new Item("Cook", LocalDate.of(2021, Month.JULY, 12));
        Item item2 = new Item("Take Dog to walk", LocalDate.of(2021, Month.JULY, 13));
        Item item3 = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        //add the dummy items to list object and expected arraylist
        list.addItem(item1);
        list.addItem(item2);
        list.addItem(item3);

        expectedItems.add(item1);
        expectedItems.add(item2);
        expectedItems.add(item3);

        //call list's clearAll() method
        list.clearAll();

        //also clear the expected arraylist
        expectedItems.clear();

        //since both are cleared, they are suppose to be equal
        //compare by converting to arrays
        assertArrayEquals(expectedItems.toArray(), list.getAllItems().toArray());
    }

    @Test
    void updateItem_Description_Test(){
        //updateItem() - description
        //initialize ToDoList object
        list = new ToDoList();

        //create dummy data of item object
        Item item = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        //add the dummy item object into ToDoList object
        list.addItem(item);

        //update the item's description
        list.updateItem(item, "OOP GUI Assignment", LocalDate.of(2021, Month.JULY, 11));

        //check if the item's description has been updated
        assertEquals(item.getDescription(), "OOP GUI Assignment");
    }

    @Test
    void updateItem_DueDate_Test(){
        //updateItem() - duedate
        //initialize ToDoList object
        list = new ToDoList();

        //create dummy data of item object
        Item item = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        //add the dummy item object into ToDoList object
        list.addItem(item);

        //update the item's due date
        list.updateItem(item, "OOP GUI Assignment", LocalDate.of(2021, Month.JULY, 11));

        //check if the item's due date has been updated
        assertEquals(item.getDueDate(), LocalDate.of(2021, Month.JULY, 11));

    }

    @Test
    void markComplete_true_Test(){
        //setComplete() - item's setter
        //check if complete is set to checked
        //initialize ToDoList object
        list = new ToDoList();

        //create dummy data for item object
        Item item = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        //create a temp checkbox and assign it true
        CheckBox testComplete = new CheckBox();
        testComplete.setSelected(true);

        //add the temp checkbox to the dummy item
        item.setComplete(testComplete);

        //check if the item is set to true
        assertTrue(item.getComplete().isSelected());
    }

    @Test
    void markComplete_false_Test(){
        //setComplete() - item's setter
        //check if complete is set to unchecked
        //initialize ToDoList object
        list = new ToDoList();

        //create dummy data for item object
        Item item = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        //create a temp checkbox and assign it false
        CheckBox testComplete = new CheckBox();
        testComplete.setSelected(false);

        //add the temp checkbox to the dummy item
        item.setComplete(testComplete);

        //check if the item is set to false
        assertFalse(item.getComplete().isSelected());
    }

    @Test
    void getAllItems_Test(){
        //getAllItems()
        //initialize ToDoList object
        list = new ToDoList();

        //also create expected arraylist for comparing
        ArrayList<Item> expectedItems = new ArrayList<>();

        //create dummy data of item's object
        Item item1 = new Item("Cook", LocalDate.of(2021, Month.JULY, 12));
        Item item2 = new Item("Take Dog to walk", LocalDate.of(2021, Month.JULY, 13));
        Item item3 = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        //add the dummy data into ToDoList object and expected arraylist
        list.addItem(item1);
        list.addItem(item2);
        list.addItem(item3);

        expectedItems.add(item1);
        expectedItems.add(item2);
        expectedItems.add(item3);

        //call getItems() and set to new actualItems arraylist
        ArrayList<Item> actualItems = list.getAllItems();

        //compare both arraylists if they are same, convert to array
        assertArrayEquals(expectedItems.toArray(), actualItems.toArray());
    }

    @Test
    void completeItems_Test(){
        //completeItems()
        //initialize ToDoList object
        list = new ToDoList();

        //also create expected arraylist for comparing
        ArrayList<Item> expectedItems = new ArrayList<>();

        //create dummy data of item's object
        Item item1 = new Item("Cook", LocalDate.of(2021, Month.JULY, 12));
        Item item2 = new Item("Take Dog to walk", LocalDate.of(2021, Month.JULY, 13));
        Item item3 = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));
        Item item4 = new Item("CS Assignment", LocalDate.of(2021, Month.JULY, 23));

        //add the dummy data into ToDoList object and expected arraylist
        list.addItem(item1);
        list.addItem(item2);
        list.addItem(item3);
        list.addItem(item4);

        //create temp checkbox and set it to true
        CheckBox temp = new CheckBox();
        temp.setSelected(true);

        //set the true checkbox to two of four dummy item's using setComplete()
        item2.setComplete(temp);
        item4.setComplete(temp);

        //add only the true items into the expected arraylist
        expectedItems.add(item2);
        expectedItems.add(item4);

        //call completeItems() and set to new actualItems arraylist
        ArrayList<Item> actualItems = list.completeItems();

        //compare both arraylists if they are same, convert to array
        assertArrayEquals(expectedItems.toArray(), actualItems.toArray());
    }

    @Test
    void inCompleteItems_Test(){
        //inCompleteItems()
        //initialize ToDoList object
        list = new ToDoList();

        //also create expected arraylist for comparing
        ArrayList<Item> expectedItems = new ArrayList<>();

        //create dummy data of item's object
        Item item1 = new Item("Cook", LocalDate.of(2021, Month.JULY, 12));
        Item item2 = new Item("Take Dog to walk", LocalDate.of(2021, Month.JULY, 13));
        Item item3 = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));
        Item item4 = new Item("CS Assignment", LocalDate.of(2021, Month.JULY, 23));

        //add the dummy data into ToDoList object and expected arraylist
        list.addItem(item1);
        list.addItem(item2);
        list.addItem(item3);
        list.addItem(item4);

        //create temp checkbox and set it to true
        CheckBox temp = new CheckBox();
        temp.setSelected(true);

        //set the true checkbox to two of four dummy item's using setComplete()
        item2.setComplete(temp);
        item4.setComplete(temp);

        //add only the false items into the expected arraylist
        expectedItems.add(item1);
        expectedItems.add(item3);

        //call inCompleteItems() and set to new actualItems arraylist
        ArrayList<Item> actualItems = list.inCompleteItems();

        //compare both arraylists if they are same, convert to array
        assertArrayEquals(expectedItems.toArray(), actualItems.toArray());
    }

    @Test
    void save_Test(){
        //save()
        //initialize ToDoList object
        list = new ToDoList();

        //create dummy data of Item's object
        Item item1 = new Item("Cook", LocalDate.of(2021, Month.JULY, 12));
        Item item2 = new Item("Take Dog to walk", LocalDate.of(2021, Month.JULY, 13));
        Item item3 = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        //add the dummy items into ToDoList object
        list.addItem(item1);
        list.addItem(item2);
        list.addItem(item3);

        //create a temp checkbox and set one item to true
        CheckBox temp = new CheckBox();
        temp.setSelected(true);
        item2.setComplete(temp);

        //create a File with a directory
        File file = new File("/Users/sathwika/Desktop/ToDo_Deegutla/Testing.csv");
        list.save(file); //call list's save() method with File

        //for comparing create a string and read the file
        String actual ="";

        try{
            //read the file
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line = "";
            while((line = br.readLine()) != null){
                actual += line + "\n"; //add the file values into the actual string
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //create an expected string with dummy item's value
        String expected = """
                Description,DueDate,Completed
                Cook,2021-07-12,no
                Take Dog to walk,2021-07-13,yes
                OOP Assignment,2021-07-11,no
                """;

        //compare expected string with actual string from reading the saved file
        assertEquals(expected, actual);
    }

    @Test
    void load_Test(){
        //load()
        //initialize ToDoList object
        list = new ToDoList();

        //create a dummy data of item's objects
        Item item1 = new Item("Cook", LocalDate.of(2021, Month.JULY, 12));
        Item item2 = new Item("Take Dog to walk", LocalDate.of(2021, Month.JULY, 13));
        Item item3 = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        //create a temp checkbox and set it to true
        //add to the item
        CheckBox temp = new CheckBox();
        temp.setSelected(true);
        item2.setComplete(temp);

        //add the dummy item to ToDoList object
        list.addItem(item1);
        list.addItem(item2);
        list.addItem(item3);

        //create a new File and save it
        File file = new File("/Users/sathwika/Desktop/ToDo_Deegutla/Testing.csv");
        list.save(file);

        //call load() method and assign the return value to actual arraylist
        ArrayList<Item> actual = list.load(file);

        //convert the arraylist of items to string same format as expected
        //convertToString()
        String actualString = convertToString(actual);

        //create an expectedString from dummy item's values
        String expectedString = """
                Description,DueDate,Completed
                Cook,2021-07-12,no
                Take Dog to walk,2021-07-13,yes
                OOP Assignment,2021-07-11,no
                """;

        //compare expected string with actual string from loading the saved file
        assertEquals(expectedString, actualString);

    }

    public String convertToString(ArrayList<Item> list){
        //converts the arraylist of items to string data
        //same format as expected string
        String ret = "Description,DueDate,Completed\n";

        for(Item item: list){
            //get description and due date
            //add the to return string
            ret += item.getDescription() + ",";
            ret += item.getDueDate().toString() + ",";
            if(item.getComplete().isSelected()){
                ret += "yes\n";
            }else{
                ret += "no\n";
            }
        }

        //return the built string from arraylist
        return ret;
    }
}