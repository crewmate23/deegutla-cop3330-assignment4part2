/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sathwika Deegutla
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    //create a list object
    ToDoList list = new ToDoList();

    ObservableList<Item> obList = FXCollections.observableArrayList();

    @FXML private Label fileLabel;
    @FXML private VBox fileBox;

    FileChooser fileChooser = new FileChooser();

    @FXML private ComboBox<String> displayOptionsBox;

    //configure the table and table columns
    @FXML
    private TableView<Item> tableView;

    @FXML
    private TableColumn<Item, String> descriptionColumn;
    @FXML
    private TableColumn<Item, LocalDate> dueDateColumn;
    @FXML
    //the checkbox used is awt (not javafx)
    private TableColumn<Item, CheckBox> completedColumn;


    //instance variables for text field and date picker
    @FXML private TextField descriptionTextField;
    @FXML private DatePicker dueDatePicker;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<Item, LocalDate>("dueDate"));
        completedColumn.setCellValueFactory(new PropertyValueFactory<Item, CheckBox>("complete"));

        displayOptionsBox.getItems().addAll(
            "All Items",
            "Incomplete Items",
            "Completed Items"
        );

        //create a folder on desktop for to do lists
        File newDirectory = new File("/Users/sathwika/Desktop/ToDo_Deegutla");
        newDirectory.mkdirs();

        //initialize the directory for file chooser
        fileChooser.setInitialDirectory(newDirectory);

        dueDatePicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                dueDatePicker.setPromptText(pattern.toLowerCase());
            }

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        fileLabel.setText("");

        obList = FXCollections.observableArrayList(list.getAllItems());
        sortByDate();
        tableView.setItems(obList);

        //allows to select one row at a time from the table for editing or deleting purposes
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //update the table to allow the fields to be editable
        //tableView.setEditable(true);
        //descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //dueDateColumn.setCellFactory();


    }

    public void saveBtnClicked(ActionEvent actionEvent){

        Window stage = fileBox.getScene().getWindow();

        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("ToDoList");

        //only accept csv files
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("csv file", "*.csv"));

        try {
            File file = fileChooser.showSaveDialog(stage);
            //sets a directory for future reference
            fileChooser.setInitialDirectory(file.getParentFile());

            if(file != null){
               list.save(file);
                /*PrintWriter printWriter = new PrintWriter(file);
                printWriter.write("HELLOOOOO");
                printWriter.close();*/
            }

            //list.save(file);


        }catch (Exception ex){
            System.out.println("An error occurred.");
        }
    }

    public void loadBtnClicked(ActionEvent actionEvent){

        Window stage = fileBox.getScene().getWindow();

        fileChooser.setTitle("Load Dialog");

        //only accept csv files
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("csv file", "*.csv"));

        try {
            File file = fileChooser.showOpenDialog(stage);
            //sets a directory for future reference
            fileChooser.setInitialDirectory(file.getParentFile());

            if(file != null){
                obList = FXCollections.observableArrayList(list.load(file));
                sortByDate();
                tableView.setItems(obList);
            }


        }catch (Exception ex){
            System.out.println("An error occurred.");
        }
    }

    public void displayOptionsAction(ActionEvent actionEvent){
        String selectedOption = displayOptionsBox.getValue();

        if(selectedOption.equalsIgnoreCase("all items")){
            System.out.println("All items displaying...");
            displayAllItems();
        }else if(selectedOption.equalsIgnoreCase("incomplete items")){
            System.out.println("Incomplete items displaying...");
            displayIncompleteItems();
        }else if(selectedOption.equalsIgnoreCase("completed items")){
            System.out.println("Completed items displaying...");
            displayCompletedItems();
        }

    }

    public void displayAllItems(){
        obList = FXCollections.observableArrayList(list.getAllItems());
        sortByDate();
        tableView.setItems(obList);
        //tableView.refresh();
    }

    public void displayIncompleteItems(){
        System.out.print(list.inCompleteItems());

        obList = FXCollections.observableArrayList(list.inCompleteItems());
        sortByDate();
        tableView.setItems(obList);
        //tableView.refresh();
    }

    public void displayCompletedItems(){
        System.out.print(list.completeItems());

        obList = FXCollections.observableArrayList(list.completeItems());
        sortByDate();
        tableView.setItems(obList);
        //tableView.refresh();
    }

    public void sortByDate(){

        FXCollections.sort(obList, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getDueDate().compareTo(o2.getDueDate());
            }
        });
    }

    //declare and initialize AllLists arraylist by calling it
    //utilize and interpret AllLists methods according to eventhandlers

    //event handlers code
    //item options
    public void addItemClicked(ActionEvent actionEvent) {
        //create new Item object from information in text fields and date picker
        System.out.print("Adding item: ");
        System.out.print(descriptionTextField.getText() + " ");
        System.out.print(dueDatePicker.getValue() + "\n");

        String description = descriptionTextField.getText();
        LocalDate dueDate = dueDatePicker.getValue();
        //Item another = new Item("Cook", LocalDate.of(2021, Month.JULY, 25));
        //list.addItem(another);

        if(descriptionTextField.getText().equals("")) {
            description = "";
        }
        if(dueDatePicker.getValue().equals(null)){
            dueDate = null;
        }

        Item newItem = new Item(description, dueDate);
        //add to list
        list.addItem(newItem);

        //ObservableList<Item> items = FXCollections.observableArrayList(list.getAllItems());
        //tableView.setItems(items);
        tableView.getItems().add(newItem);
        sortByDate();
        tableView.refresh();
        clearField();
    }

    public void removeItemClicked(ActionEvent actionEvent){

        Item selectedItem = tableView.getSelectionModel().getSelectedItem();

        System.out.print("Removing item: ");
        System.out.println(selectedItem.getDescription() + " ");
        System.out.println(selectedItem.getDueDate() + "\n");

        displayItem(selectedItem);

        list.removeItem(selectedItem);
        tableView.getItems().remove(selectedItem);

        sortByDate();
        tableView.refresh();

        clearField();
    }

    public void clearAllClicked(ActionEvent actionEvent){

        System.out.println("Clearing all");

        list.clearAll();

        //obList = FXCollections.observableArrayList(list.getAllItems());
        //tableView.setItems(obList);
        tableView.getItems().clear();
    }


    public void editItemClicked(ActionEvent actionEvent){
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();

        System.out.print("Editing item: ");
        System.out.println(selectedItem.getDescription() + " ");
        System.out.println(selectedItem.getDueDate() + "\n");

        String newDescription = descriptionTextField.getText();
        LocalDate newDueDate = dueDatePicker.getValue();

        list.updateItem(selectedItem, newDescription, newDueDate);
        sortByDate();
        tableView.refresh();

        clearField();
    }

    public void helpBtnClicked(ActionEvent actionEvent) throws IOException {
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HelpScreen.fxml"));

        Parent helpScreenParent = loader.load();
        Scene helpScreenScene = new Scene(helpScreenParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(helpScreenScene);
        window.show();*/

        //open a new window
        Parent helpScreenParent = FXMLLoader.load(getClass().getResource("HelpScreen.fxml"));

        ScrollPane sp = new ScrollPane();
        sp.setContent(helpScreenParent);


        /*BorderPane bp = new BorderPane();
        bp.setLeft(sp);*/

        Stage helpStage = new Stage();
        helpStage.setTitle("Help");
        helpStage.setScene(new Scene(sp, 720, 500));
        helpStage.show();

        //don't hide existing screen
        //((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }


    /*Parent root;
        try {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("path/to/other/view.fxml"), resources);
        Stage stage = new Stage();
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 450, 450));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
        catch (IOException e) {
        e.printStackTrace();
    }
}*/
    public void selectedRow(){
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        displayItem(selectedItem);
    }

    public void displayItem(Item item){
        descriptionTextField.setText(item.getDescription());
        dueDatePicker.setValue(item.getDueDate());
    }

    public void clearField(){
        //descriptionTextField.setPromptText("Description");
        descriptionTextField.setText("");
        dueDatePicker.setValue(null);
    }

    /*public void fieldsClicked(){
        displayAllItems();
    }*/
    /*public void editItemClicked(ActionEvent actionEvent) {
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
    }*/

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
