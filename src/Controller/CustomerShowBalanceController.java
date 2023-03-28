package Controller;

import Model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerShowBalanceController {

    static Stage stage;
    Parent root;

    public void OpenPage() throws IOException {

        stage = new Stage();

        root = FXMLLoader.load(getClass().getResource("/view/CustomerShowBalance.fxml"));
        stage.setTitle("Customer show balance");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    public void initialize () throws IOException {

        Customer.ReadData();
        balanceLabel.setText(Double.toString(Model.Customer.getBalance())+"DH");

    }

    @FXML
    private Label balanceLabel;



}
