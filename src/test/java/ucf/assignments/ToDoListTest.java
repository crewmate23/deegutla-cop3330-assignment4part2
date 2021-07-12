package ucf.assignments;


import javafx.embed.swing.JFXPanel;
import javafx.scene.control.CheckBox;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {

    private ToDoList list;

    //create a panel for testing the list and item object
    //since used CheckBox (javafx) object by Item class
    private JFXPanel panel = new JFXPanel();

    @Test
    void addItem_Test() {
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

        assertArrayEquals(expectedItems.toArray(), list.getAllItems().toArray());
    }

    @Test
    void removeItem_Test(){
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

        list.removeItem(item3);

        expectedItems.remove(item3);

        assertArrayEquals(expectedItems.toArray(), list.getAllItems().toArray());
    }

    @Test
    void removeItem_Test_False(){
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

       list.removeItem(item1);

        expectedItems.remove(item3);

        Boolean check = (list.getAllItems()).equals(expectedItems);

        assertFalse(check);
    }

    @Test
    void clearAll_Test(){
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