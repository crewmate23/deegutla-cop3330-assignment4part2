/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sathwika Deegutla
 */

package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //create a list object
    ToDoList list = new ToDoList();

    //configure the table and table columns
    @FXML
    private TableView<Item> tableView;

    @FXML
    private TableColumn<Item, String> descriptionColumn;
    @FXML
    private TableColumn<Item, LocalDate> dueDateColumn;
    @FXML
    //the checkbox used is awt (not javafx)
    private TableColumn<Item, Checkbox> completedColumn;


    //instance variables for text field and date picker
    @FXML private TextField descriptionTextField;
    @FXML private DatePicker dueDatePicker;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<Item, LocalDate>("dueDate"));

        //update the table to allow the fields to be editable
        // tableView.setEditable(true);

    }

    //declare and initialize AllLists arraylist by calling it
    //utilize and interpret AllLists methods according to eventhandlers

    //event handlers code
    //item options
    public void addItemClicked(ActionEvent actionEvent) {
        //get information from text fields and date picker

        //create new Item object
        //add to list
    }

    public void editItemClicked(ActionEvent actionEvent) {
        //get the ToDoList list and Item item objects from which button clicked
        //call AllLists's listExists(), if true continue, else addToDoList() to AllLists arraylist
        //if description edited, call the Item object's editDescription() method
        //if date edited, call the Item's object's editDate() method
    }

    public void deleteItemClicked(ActionEvent actionEvent) {
        //get the ToDoList list and Item item objects from which button clicked
        //call AllLists's listExists(), if true continue, else addToDoList() to AllLists arraylist
        //remove the item from that ToDoList object's items list
    }

    public void itemFinishedSelected(ActionEvent actionEvent) {
        //get the Item and ToDoList object from which the finished is selected
        //call AllLists's listExists(), if true continue, else addToDoList() to AllLists arraylist
        //check whether it is checked or not
        //if checked mark the boolean "check" as true, else as false
        //update that item finished by calling editFinished() with passing in the boolean "check"
    }

    //save and load options
    public void saveListClicked(ActionEvent actionEvent) {
        //get the list that was clicked - saveList
        //call AllLists's listExists(), if true continue, else addToDoList() to AllLists arraylist
        //call AllLists save(saveList) method by passing the saveList parameter
    }

    //display options selection
    public void allDisplaySelected(ActionEvent actionEvent) {
        //get the ToDoList title from 'displayToDoList' textfield for display options
        //find the ToDoList by using getTitle() and listExists() and store it in new ToDoList obj - displayList
        //check whether allDisplay is marked or not
        //if it is,
        //call the displayItems() method with the parameter of the
        // return value from ToDoLists's getAllItems() method by passing displayList
    }

    public void completeDisplaySelected(ActionEvent actionEvent) {
        //get the ToDoList title from 'displayToDoList' textfield for display options
        //find the ToDoList by using getTitle() and listExists() and store it in new ToDoList obj - displayList
        //check whether completeDisplay is marked or not
        //if it is,
        //call the displayItems() method with the parameter of the
        //return value from ToDoLists's completeItems() method by passing displayList
    }

    public void incompleteDisplaySelected(ActionEvent actionEvent) {
        //get the ToDoList title from 'displayToDoList' textfield for display options
        //find the ToDoList by using getTitle() and listExists() and store it in new ToDoList obj - displayList
        //check whether inCompleteDisplay is marked or not
        //if it is,
        //call the displayItems() method with the parameter of the
        //return value from ToDoLists's inCompleteItems() method by passing displayList
    }

    public void displayItems(ArrayList<Item> items, ToDoList list){
        //find the 'list' and clear its display
        //loop through 'items'
        //display them on screen withing the 'list'
    }
    public void displayLists(ArrayList<ToDoList> lists){
        //firstly, call the AllLists's sort() method and then
        //loop through the parameter of all lists
        //display them on screen
    }

    /*
    NOT NEEDED METHODS ANYMORE

    public void saveAllClicked(ActionEvent actionEvent) {
        //call AllLists saveAll() method
    }

    public void loadRecentClicked(ActionEvent actionEvent) {
        //get the ToDoList object LoadList from calling AllLists loadRecent() method
        //display() that ToDoList object LoadList
    }

    public void loadAllClicked(ActionEvent actionEvent) {
        //declare new arraylist LoadAll
        //initialize that arrayList LoadAll with return value from calling AllLists loadAll() method
        //pass the new arrayList into display() method
    }

    public void saveItemClicked(ActionEvent actionEvent) {
        //get the list the of the saved item
        //call AllLists's listExists(), if true continue, else addToDoList() to AllLists arraylist
        //get the description "item_des" and date "item_date" textfields
        //create new Item object by calling the constructor
        //add the new Item object into the existing list
        //call ToDoList addItem() method
    }


    list options if included multiple lists
    public void editListClicked(ActionEvent actionEvent) {
        //get the ToDoList list object from which the edit is clicked
        //call AllLists's listExists(), if true continue, else addToDoList() to AllLists arraylist
        //find the list is in (using the title) from AllLists arraylist
        //if title is edited, call the ToDoList object's editTitle() by passing new title
    }

    public void deleteListClicked(ActionEvent actionEvent) {
        //get the ToDoList list object from which the edit is clicked
        //call AllLists's listExists(), if true continue, else addToDoList() to AllLists arraylist
        //find the list is in (using the title) from AllLists arraylist
        //remove that list from the AllLists arraylist
    }

    public void addListClicked(ActionEvent actionEvent) {
        //show up the anchor pane "todoList_Pane"
    }*/
}
