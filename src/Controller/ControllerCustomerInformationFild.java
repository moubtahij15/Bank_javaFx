package Controller;

import Connector.Auth;
import build.InputsAnalyzer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCustomerInformationFild {

    private String nationalID;
    private String fullName;
    private String fatherName;
    private String bankId;
    private String phoneNumber;
    private String email;
    private String homeAdress;
    private String password;
    private String loanID;
    private String accountType;

    final ObservableList<String> AcountTypesArrayList = FXCollections.observableArrayList
            ("Savings Account","Profitable Account");

    static Stage stage;
    static Parent root;

    public void initialize(){

        accountTypeComboBox.setItems(AcountTypesArrayList);

    }

    public void Open() throws IOException {

       stage = new Stage();

       root = FXMLLoader.load(getClass().getResource("/view/CustomerInformationFild.fxml"));
       stage.setTitle("Information Field");
       stage.setScene(new Scene(root , 600 , 400));
       stage.show();


    }


    @FXML
    private TextField fullNameText;

    @FXML
    private TextField fatherNameText;

    @FXML
    private TextField nationalIDText;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField addressText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField passwordText;

    @FXML
    private Button saveButton;

    @FXML
    private Label fullNameErrorLabel;

    @FXML
    private Label fatherNameErrorLabel;

    @FXML
    private Label nationalIDErrorLabel;

    @FXML
    private Label phoneErrorLabel;

    @FXML
    private Label emailErrorLabel;

    @FXML
    private Label passwordErrorLabel;

    @FXML
    private ComboBox<String> accountTypeComboBox;

    @FXML
    void saveButtonAction(ActionEvent event) throws IOException {

        boolean flaq = IsFieldsOk();
        ButtonAction(flaq);

    }

    void ButtonAction (boolean isFieldsOk) throws IOException {


        if (isFieldsOk)
        {


            nationalID = nationalIDText.getText();
            fullName = fullNameText.getText();
            fatherName = fatherNameText.getText();
            phoneNumber = phoneText.getText();
            email = emailText.getText();
            homeAdress = addressText.getText();
            password = passwordText.getText();
            accountType = accountTypeComboBox.getValue();

            Auth database = new Auth();

            //database.CreateDataBase();
            database.setNationalID(nationalID);
            database.setFullName(fullName);
            database.setFatherName(fatherName);
            database.setPhoneNumber(phoneNumber);
            database.setEmail(email);
            database.setHomeAdress(homeAdress);
            database.setPassword(password);
            database.setBankId(bankId);
            database.setAccountType(accountType);

            System.out.println("Write to dataBase Done");

            stage.close();


            String save = "Card ID is : " + bankId;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(save);
            alert.show();

        }

        else
            System.out.println("Write to dataBase fail because fields are not Ok");

    }

    boolean IsFieldsOk(){

        InputsAnalyzer inputsAnalyzer = new InputsAnalyzer();

        fullNameErrorLabel.setText("");
        fatherNameErrorLabel.setText("");
        nationalIDErrorLabel.setText("");
        phoneErrorLabel.setText("");
        emailErrorLabel.setText("");
        passwordErrorLabel.setText("");

        nationalID = nationalIDText.getText();
        fullName = fullNameText.getText();
        fatherName = fatherNameText.getText();
        phoneNumber = phoneText.getText();
        email = emailText.getText();
        homeAdress = addressText.getText();
        password = passwordText.getText();

        boolean [] result = inputsAnalyzer.CheckAll(fullName,fatherName,nationalID
                ,phoneNumber,email,password);


        if (result[0] == true)
            return true;

        else
        {
            if(result[1]==false)
                fullNameErrorLabel.setText("❗");

            if(result[2]==false)
                fatherNameErrorLabel.setText("❗");

            if(result[3]==false)
                nationalIDErrorLabel.setText("❗");

            if(result[4]==false)
                phoneErrorLabel.setText("❗");

            if(result[5]==false)
                emailErrorLabel.setText("❗");

            if(result[6]==false)
                passwordErrorLabel.setText("❗");

            return false;
        }

    }



}
