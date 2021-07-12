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

        ArrayList<Item> expectedItems = new ArrayList<>();

        Item item1 = new Item("Cook", LocalDate.of(2021, Month.JULY, 12));
        Item item2 = new Item("Take Dog to walk", LocalDate.of(2021, Month.JULY, 13));
        Item item3 = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        list.addItem(item1);
        list.addItem(item2);
        list.addItem(item3);

        expectedItems.add(item1);
        expectedItems.add(item2);
        expectedItems.add(item3);

        list.clearAll();

        expectedItems.clear();

        assertArrayEquals(expectedItems.toArray(), list.getAllItems().toArray());
    }

    @Test
    void updateItem_Description_Test(){
        list = new ToDoList();

        Item item = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        list.addItem(item);

        list.updateItem(item, "OOP GUI Assignment", LocalDate.of(2021, Month.JULY, 11));

        assertEquals(item.getDescription(), "OOP GUI Assignment");
    }

    @Test
    void updateItem_DueDate_Test(){
        list = new ToDoList();

        Item item = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        list.addItem(item);

        list.updateItem(item, "OOP GUI Assignment", LocalDate.of(2021, Month.JULY, 11));

        assertEquals(item.getDueDate(), LocalDate.of(2021, Month.JULY, 11));

    }

    @Test
    void markComplete_true_Test(){
        list = new ToDoList();

        Item item = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        CheckBox testComplete = new CheckBox();
        testComplete.setSelected(true);

        item.setComplete(testComplete);

        assertTrue(item.getComplete().isSelected());
    }

    @Test
    void markComplete_false_Test(){
        list = new ToDoList();

        Item item = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        CheckBox testComplete = new CheckBox();
        testComplete.setSelected(false);

        item.setComplete(testComplete);

        assertFalse(item.getComplete().isSelected());
    }

    @Test
    void getAllItems_Test(){
        list = new ToDoList();

        ArrayList<Item> expectedItems = new ArrayList<>();

        Item item1 = new Item("Cook", LocalDate.of(2021, Month.JULY, 12));
        Item item2 = new Item("Take Dog to walk", LocalDate.of(2021, Month.JULY, 13));
        Item item3 = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        list.addItem(item1);
        list.addItem(item2);
        list.addItem(item3);

        expectedItems.add(item1);
        expectedItems.add(item2);
        expectedItems.add(item3);

        ArrayList<Item> actualItems = list.getAllItems();

        assertArrayEquals(expectedItems.toArray(), actualItems.toArray());
    }

    @Test
    void completeItems_Test(){
        list = new ToDoList();

        ArrayList<Item> expectedItems = new ArrayList<>();

        Item item1 = new Item("Cook", LocalDate.of(2021, Month.JULY, 12));
        Item item2 = new Item("Take Dog to walk", LocalDate.of(2021, Month.JULY, 13));
        Item item3 = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));
        Item item4 = new Item("CS Assignment", LocalDate.of(2021, Month.JULY, 23));

        list.addItem(item1);
        list.addItem(item2);
        list.addItem(item3);
        list.addItem(item4);

        CheckBox temp = new CheckBox();
        temp.setSelected(true);
        item2.setComplete(temp);
        item4.setComplete(temp);

        expectedItems.add(item2);
        expectedItems.add(item4);

        ArrayList<Item> actualItems = list.completeItems();

        assertArrayEquals(expectedItems.toArray(), actualItems.toArray());
    }

    @Test
    void inCompleteItems_Test(){
        list = new ToDoList();

        ArrayList<Item> expectedItems = new ArrayList<>();

        Item item1 = new Item("Cook", LocalDate.of(2021, Month.JULY, 12));
        Item item2 = new Item("Take Dog to walk", LocalDate.of(2021, Month.JULY, 13));
        Item item3 = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));
        Item item4 = new Item("CS Assignment", LocalDate.of(2021, Month.JULY, 23));

        list.addItem(item1);
        list.addItem(item2);
        list.addItem(item3);
        list.addItem(item4);

        CheckBox temp = new CheckBox();
        temp.setSelected(true);
        item2.setComplete(temp);
        item4.setComplete(temp);

        expectedItems.add(item1);
        expectedItems.add(item3);

        ArrayList<Item> actualItems = list.inCompleteItems();

        assertArrayEquals(expectedItems.toArray(), actualItems.toArray());
    }

    @Test
    void save_Test(){
        list = new ToDoList();

        Item item1 = new Item("Cook", LocalDate.of(2021, Month.JULY, 12));
        Item item2 = new Item("Take Dog to walk", LocalDate.of(2021, Month.JULY, 13));
        Item item3 = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        list.addItem(item1);
        list.addItem(item2);
        list.addItem(item3);

        CheckBox temp = new CheckBox();
        temp.setSelected(true);
        item2.setComplete(temp);

        File file = new File("/Users/sathwika/Desktop/ToDo_Deegutla/Testing.csv");
        list.save(file);

        String actual ="";

        try{
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line = "";
            while((line = br.readLine()) != null){
                actual += line + "\n";
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String expected = """
                Description,DueDate,Completed
                Cook,2021-07-12,no
                Take Dog to walk,2021-07-13,yes
                OOP Assignment,2021-07-11,no
                """;

        assertEquals(expected, actual);
    }

    @Test
    void load_Test(){

        list = new ToDoList();

        Item item1 = new Item("Cook", LocalDate.of(2021, Month.JULY, 12));
        Item item2 = new Item("Take Dog to walk", LocalDate.of(2021, Month.JULY, 13));
        Item item3 = new Item("OOP Assignment", LocalDate.of(2021, Month.JULY, 11));

        CheckBox temp = new CheckBox();
        temp.setSelected(true);
        item2.setComplete(temp);

        list.addItem(item1);
        list.addItem(item2);
        list.addItem(item3);

        File file = new File("/Users/sathwika/Desktop/ToDo_Deegutla/Testing.csv");
        list.save(file);

        ArrayList<Item> actual = list.load(file);

        String actualString = convertToString(actual);

        String expectedString = """
                Description,DueDate,Completed
                Cook,2021-07-12,no
                Take Dog to walk,2021-07-13,yes
                OOP Assignment,2021-07-11,no
                """;

        assertEquals(expectedString, actualString);

    }

    public String convertToString(ArrayList<Item> list){
        String ret = "Description,DueDate,Completed\n";

        for(Item item: list){
            ret += item.getDescription() + ",";
            ret += item.getDueDate().toString() + ",";
            if(item.getComplete().isSelected()){
                ret += "yes\n";
            }else{
                ret += "no\n";
            }
        }

        return ret;
    }
}