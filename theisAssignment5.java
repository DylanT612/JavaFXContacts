/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 6/8/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Simple Address Book - Main
Description: Address book in JavaFX that allows a user to create new contact, update that contact, or delete that contact.

References:
https://cdn3.iconfinder.com/data/icons/network-and-communications-8/32/network_contact_list_contact_log_contact_book-512.png

https://www.geeksforgeeks.org/javafx-tutorial/

https://www.javatpoint.com/first-javafx-application
 */

// Importing javafx tools
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Importing java tools
import java.io.*;
import java.util.Optional;
import java.util.ArrayList;

// Extension of the JavaFX Application
public class theisAssignment5 extends Application implements Serializable {

    // Observable list of contacts for listView
    private ObservableList<Contact> contacts = FXCollections.observableArrayList();
    private ListView<Contact> listView = new ListView<>();
    // Making new private TextAreas for the fields
    private TextField firstNameField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField streetField = new TextField();
    private TextField cityField = new TextField();
    private ComboBox<String> stateComboBox = new ComboBox<>();
    private TextField phoneField = new TextField();
    private TextField emailField = new TextField();
    private TextArea notesArea = new TextArea();

    // Set up the UI and handlers
    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();

        // Set up the ListView on the left
        listView.setItems(contacts);
        listView.setCellFactory(lv -> new ListCell<Contact>() {
            @Override
            protected void updateItem(Contact contact, boolean empty) {
                super.updateItem(contact, empty);
                // If nothing in the listView of contacts display blank white
                if (empty || contact == null) {
                    setText(null);
                    setStyle("");
                // If something is in listView of contacts display alternating gray/white lines
                } else {
                    setText(contact.toString());
                    if (getIndex() % 2 == 0) {
                        setStyle("-fx-background-color: lightgray;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        // When selecting a contact on the listView fill with contact information
        listView.setOnMouseClicked(event -> {
            Contact selectedContact = listView.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                firstNameField.setText(selectedContact.getFirstName());
                lastNameField.setText(selectedContact.getLastName());
                streetField.setText(selectedContact.getStreet());
                cityField.setText(selectedContact.getCity());
                stateComboBox.setValue(selectedContact.getState());
                phoneField.setText(selectedContact.getPhone());
                emailField.setText(selectedContact.getEmail());
                notesArea.setText(selectedContact.getNotes());
            }
        });

        // Set up the GridPane for the address book
        GridPane gridPane = new GridPane();
        // Set spacing around gridPane
        gridPane.setPadding(new Insets(10));
        // Set spacing in between columns and rows
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Adding an image and fixing its dimensions
        Image image = new Image("https://cdn3.iconfinder.com/data/icons/network-and-communications-8/32/network_contact_list_contact_log_contact_book-512.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        // Making a HBox for the header and image with padding 10 between them
        HBox imageHeaderBox = new HBox(10);
        Label headerLabel = new Label("Dylan's Contact List");
        // Setting the size and weight of the header font
        headerLabel.setStyle("-fx-font-size: 24pt; -fx-font-weight: bold;");
        // adding the image and the header to the imageHeaderBox
        imageHeaderBox.getChildren().addAll(imageView, headerLabel);
        // The box starts at 0, 0, spans 5 columns and 1 row
        gridPane.add(imageHeaderBox, 0, 0, 5, 1);
        // Center the box 
        imageHeaderBox.setStyle("-fx-alignment: center;");

        // Wrap ListView in a VBox with preferred height 
        VBox listViewContainer = new VBox();
        // Set spacing between each element
        listViewContainer.setSpacing(10);
        // Set padding around container
        listViewContainer.setPadding(new Insets(10));
        listViewContainer.setPrefHeight(300);
        // add listView to the container
        listViewContainer.getChildren().add(listView);
        // Container starts at 0, 1, spans 1 column and 6 rows
        gridPane.add(listViewContainer, 0, 1, 1, 6);

        // Adding fields to the gridPane column, row
        gridPane.add(new Label("First Name:"), 1, 1);
        gridPane.add(firstNameField, 2, 1);
        gridPane.add(new Label("Last Name:"), 3, 1);
        gridPane.add(lastNameField, 4, 1);
        gridPane.add(new Label("Street:"), 1, 2);
        // Have streetField start at 2,2, spanning 3 columns and 1 row
        gridPane.add(streetField, 2, 2, 3, 1); 
        gridPane.add(new Label("City:"), 1, 3);
        gridPane.add(cityField, 2, 3);
        gridPane.add(new Label("State:"), 3, 3);
        gridPane.add(stateComboBox, 4, 3);
        gridPane.add(new Label("Phone:"), 1, 4);
        gridPane.add(phoneField, 2, 4);
        gridPane.add(new Label("Email:"), 1, 5);
        gridPane.add(emailField, 2, 5);
        gridPane.add(new Label("Notes:"), 1, 6);
        // Have notesArea start at 2,6, spanning 3 columns and 1 row
        gridPane.add(notesArea, 2, 6, 3, 1);
        notesArea.setPrefHeight(200);

        // Populate stateComboBox dropdown options
        stateComboBox.setItems(FXCollections.observableArrayList(
                "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA",
                "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD",
                "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
                "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
                "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
        ));
        
        // Set up the HBox for buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        // When clicked creates new contact with name New New
        Button newButton = new Button("New");
        newButton.setOnAction(this::handleNewButton);
        // When clicked saves contact information
        Button updateButton = new Button("Update");
        updateButton.setOnAction(this::handleUpdateButton);
        // When clicked prompts confirmation
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(this::handleDeleteButton);
        // Add all these buttons to the buttonBox
        buttonBox.getChildren().addAll(newButton, updateButton, deleteButton);
        // Add buttonBox to gridPane
        gridPane.add(buttonBox, 2, 7, 3, 1);

        // Set up borderPane with gridPane in the center
        borderPane.setCenter(gridPane);

        // Set up the scene using borderPane as root node
        Scene scene = new Scene(borderPane, 800, 600);
        // Show scene in main window and give it a title
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Address Book");
        // Saves contacts to file when closing
        primaryStage.setOnCloseRequest(event -> saveContactsToFile());
        // Make the window visible to the user
        primaryStage.show(); 

        // Load contacts from file when the program starts
        loadContactsFromFile();
    }



    // When the New button is clicked
    private void handleNewButton(ActionEvent event) {
        // Only fill first and last of newContact with New
        Contact newContact = new Contact("New", "New", "", "", "", "", "", "");
        // Add new contact to contacts 
        contacts.add(newContact);
        // Creates a New New contact in the listView
        listView.getSelectionModel().select(newContact);
    }

    // WHen the Update button is clicked
    private void handleUpdateButton(ActionEvent event) {
        // Get currently selected contact info
        Contact selectedContact = listView.getSelectionModel().getSelectedItem(); 
        // Set the information to corresponding field
        if (selectedContact != null) {
            selectedContact.setFirstName(firstNameField.getText());
            selectedContact.setLastName(lastNameField.getText());
            selectedContact.setStreet(streetField.getText());
            selectedContact.setCity(cityField.getText());
            selectedContact.setState(stateComboBox.getValue());
            selectedContact.setPhone(phoneField.getText());
            selectedContact.setEmail(emailField.getText());
            selectedContact.setNotes(notesArea.getText());
            listView.refresh();
        }
    }

    // When the delete button is clicked
    private void handleDeleteButton(ActionEvent event) {
        // Get currently selected contact info
        Contact selectedContact = listView.getSelectionModel().getSelectedItem();
        // If contact contains some information
        if (selectedContact != null) {
            // Display an alert with the following information
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("You Are About to Delete This Contact");
            alert.setContentText("Are you sure you want to delete this contact?");

            // Button for OK and cancel
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                contacts.remove(selectedContact);
            }
        }
    }

    // When exiting JavaFX program save file
    private void saveContactsToFile() {
        // Write serialized contacts to file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("contacts.dat"))) {
            outputStream.writeObject(new ArrayList<>(contacts));
        // Catch error if cannot write to file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // To load contacts from a file
    private void loadContactsFromFile() {
        // Open and view file
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("contacts.dat"))) {
            contacts.setAll(FXCollections.observableArrayList((ArrayList<Contact>) inputStream.readObject()));
        // Catch error if cannot open file
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Launching the application
    public static void main(String[] args) {
        launch(args);
    }
}

