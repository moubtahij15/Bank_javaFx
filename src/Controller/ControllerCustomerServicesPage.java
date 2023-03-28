package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerCustomerServicesPage {

    static Stage stage;
    Parent root;

    public void OpenPage() throws IOException {

        stage = new Stage();

        root = FXMLLoader.load(getClass().getResource("/view/CustomerServicesPage.fxml"));
        stage.setTitle("Customer Services Page");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    @FXML
    private Button reciveMoneyButton;

    @FXML
    private Button moneyTransfer;

    @FXML
    private Button installmentPayment;

    @FXML
    private Button changePassword;

    @FXML
    private Button balanceButton;

    @FXML
    void balanceButtonAction(ActionEvent event) throws IOException {
        new CustomerShowBalanceController().OpenPage();
    }

    @FXML
    void changePasswordAction(ActionEvent event) throws IOException, SQLException {
    }

    @FXML
    void installmentPaymentAction(ActionEvent event) {

    }

    @FXML
    void moneyTransferAction(ActionEvent event) throws IOException {
        new CustomerTransferMoneyController().OpenPage();
    }

    @FXML
    void reciveMoneyButtonAction(ActionEvent event) throws IOException {
        new CustomerResiveMoneyController().OpenPage();
    }

}
