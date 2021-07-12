/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sathwika Deegutla
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;


import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //create a list object - a model
    ToDoList list = new ToDoList();

    //global observable list, easier when displaying in the table
    ObservableList<Item> obList = FXCollections.observableArrayList();

    //label that show file name, when loaded
    @FXML private Label fileLabel;

    //vertical box includes "save" and "load" buttons
    @FXML private VBox fileBox;

    //global fileChooser for "save" and "load"
    FileChooser fileChooser = new FileChooser();

    //dropdown combobox for display options
    @FXML private ComboBox<String> displayOptionsBox;

    //configure the table and table columns
    @FXML
    private TableView<Item> tableView;
    @FXML
    private TableColumn<Item, String> descriptionColumn;
    @FXML
    private TableColumn<Item, LocalDate> dueDateColumn;
    @FXML
    private TableColumn<Item, CheckBox> completedColumn;


    //instance variables for text field and date picker
    @FXML private TextField descriptionTextField;
    @FXML private DatePicker dueDatePicker;


    //runs when the stage is launched
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //sets cell value factory to Item's values respectively
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<Item, LocalDate>("dueDate"));
        completedColumn.setCellValueFactory(new PropertyValueFactory<Item, CheckBox>("complete"));

        //adds options to display options
        displayOptionsBox.getItems().addAll(
            "All Items",
            "Incomplete Items",
            "Completed Items"
        );

        //create a folder on desktop for to do lists
        String currentUser = System.getProperty("user.home");
        String directory = currentUser + File.separator + "Desktop";
        String newFolder = directory + File.separator + "ToDo_Deegutla";
        File newDirectory = new File(newFolder);
        newDirectory.mkdirs();

        //initialize the directory for file chooser
        fileChooser.setInitialDirectory(newDirectory);

        //converts date picker format to YYYY-MM-DD
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

        //initially set file label to null, not opened anything yet
        fileLabel.setText("");

        //initializes and converts Arraylist to Observable list for display
        obList = FXCollections.observableArrayList(list.getAllItems());
        sortByDate(); //sort method
        tableView.setItems(obList); //displays

        //allows to select one row at a time from the table for editing or deleting purposes
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    //save
    public void saveBtnClicked(ActionEvent actionEvent){
        //shows a new window for saving the file
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
               list.save(file); //calls list's save method to write into that file
            }
        }catch (Exception ex){
            System.out.println("An error occurred.");
        }
    }

    //load
    public void loadBtnClicked(ActionEvent actionEvent){
        //shows a new window for opening an existing todo
        Window stage = fileBox.getScene().getWindow();

        fileChooser.setTitle("Load Dialog");

        //only accept csv files
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("csv file", "*.csv"));

        try {
            File file = fileChooser.showOpenDialog(stage);
            //sets a directory for future reference
            fileChooser.setInitialDirectory(file.getParentFile());

            //gets file name and shows in the todo application
            fileLabel.setText(file.getName());

            if(file != null){
                //call list's load to get arraylist of items
                //convert to observable list and sort by date and display in the table
                obList = FXCollections.observableArrayList(list.load(file));
                sortByDate();
                tableView.setItems(obList);
            }
        }catch (Exception ex){
            System.out.println("An error occurred.");
        }
    }


    //display options
    public void displayOptionsAction(ActionEvent actionEvent){
        //get selected option from combobox
        String selectedOption = displayOptionsBox.getValue();

        //check which option selected and call that method respectively
        if(selectedOption.equalsIgnoreCase("all items")){
            System.out.println("All items displaying..."); //console message
            displayAllItems();
        }else if(selectedOption.equalsIgnoreCase("incomplete items")){
            System.out.println("Incomplete items displaying..."); //console message
            displayIncompleteItems();
        }else if(selectedOption.equalsIgnoreCase("completed items")){
            System.out.println("Completed items displaying..."); //console message
            displayCompletedItems();
        }

    }

    public void displayAllItems(){
        //call list's getAllItems()
        //convert to observable list
        //sort by date
        //display on the table
        obList = FXCollections.observableArrayList(list.getAllItems());
        sortByDate();
        tableView.setItems(obList);
    }

    public void displayIncompleteItems(){
        System.out.print(list.inCompleteItems()); //console message

        //call list's inCompleteItems()
        //convert to observable list
        //sort by date
        //display on the table
        obList = FXCollections.observableArrayList(list.inCompleteItems());
        sortByDate();
        tableView.setItems(obList);
    }

    public void displayCompletedItems(){
        System.out.print(list.completeItems()); //console message

        //call list's completeItems()
        //convert to observable list
        //sort by date
        //display on the table
        obList = FXCollections.observableArrayList(list.completeItems());
        sortByDate();
        tableView.setItems(obList);
    }

    public void sortByDate(){

        //sort the Observable list using a comparator
        //compares date objects and sorts

        FXCollections.sort(obList, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getDueDate().compareTo(o2.getDueDate());
            }
        });
    }

    //item options
    public void addItemClicked(ActionEvent actionEvent) {
        //console message
        System.out.print("Adding item: ");
        System.out.print(descriptionTextField.getText() + " ");
        System.out.print(dueDatePicker.getValue() + "\n");

        //check if the text field or date picker are left blank, if so show error
        if(descriptionTextField.getText().equals("") || dueDatePicker.getValue().equals(null)) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("The fields or date picker cannot be left blank.");
            errorAlert.setContentText("Please enter valid description between 1 and 256 characters and valid date.");
            errorAlert.showAndWait();
        }else if(list.itemExists(descriptionTextField.getText())){
            //check if the item already exists, if so show error
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Items exists already");
            errorAlert.setContentText("Please edit the existing item. Duplicate items are not supported.");
            errorAlert.showAndWait();
        } else {
            //if text field and date picker are not left blank nor item does not exist
            //create new Item object from information in text fields and date picker
            Item newItem = new Item(descriptionTextField.getText(), dueDatePicker.getValue());
            //add to list
            list.addItem(newItem);

            //add to the display
            tableView.getItems().add(newItem);
            //sort by date
            sortByDate();
            //refresh table
            tableView.refresh();

            //clear the fields for adding next item
            clearField();
        }
    }

    public void removeItemClicked(ActionEvent actionEvent){
        //get selected row from the table
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();

        //console message
        System.out.print("Removing item: ");
        System.out.println(selectedItem.getDescription() + " ");
        System.out.println(selectedItem.getDueDate() + "\n");

        //display that item in the text field and date picker
        displayItem(selectedItem);

        list.removeItem(selectedItem); //call list's removeItem() method
        tableView.getItems().remove(selectedItem); //remove it from the table

        //sort by date again
        sortByDate();
        //refresh the table
        tableView.refresh();

        //clear the text field and date picker
        clearField();
    }

    public void clearAllClicked(ActionEvent actionEvent){

        System.out.println("Clearing all"); //console message

        list.clearAll(); //call list's clearAll() method

        tableView.getItems().clear(); //also clear from the table display
    }


    public void editItemClicked(ActionEvent actionEvent){
        //get the selected item from the table
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();

        //console message
        System.out.print("Editing item: ");
        System.out.println(selectedItem.getDescription() + " ");
        System.out.println(selectedItem.getDueDate() + "\n");

        //create new temp variables and assign with information from fields
        String newDescription = descriptionTextField.getText();
        LocalDate newDueDate = dueDatePicker.getValue();

        //call list's updateItem() method by passing new temp variables
        list.updateItem(selectedItem, newDescription, newDueDate);
        //sort by date again
        sortByDate();
        //refresh the table
        tableView.refresh();

        //clear the text field and date picker
        clearField();
    }

    public void helpBtnClicked(ActionEvent actionEvent) throws IOException {
        //open a new separate window
        Parent helpScreenParent = FXMLLoader.load(getClass().getResource("HelpScreen.fxml"));

        //scroll pane, makes the window scrollable
        ScrollPane sp = new ScrollPane();
        sp.setContent(helpScreenParent);

        //sets stage and scene with width and height
        Stage helpStage = new Stage();
        helpStage.setTitle("Help");
        helpStage.setScene(new Scene(sp, 720, 500));
        helpStage.show(); //displays
    }

    //gets selected row/item from the table and displays it
    public void selectedRow(){
        //gets item
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        //display
        displayItem(selectedItem);
    }

    //displays item in fields
    public void displayItem(Item item){
        //sets the fields and date picker to selected items' values
        descriptionTextField.setText(item.getDescription());
        dueDatePicker.setValue(item.getDueDate());
    }

    public void clearField(){
        //clears text fields and date picker
        descriptionTextField.setText("");
        dueDatePicker.setValue(null);
    }
}
