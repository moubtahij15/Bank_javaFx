package Controller;

import Connector.Auth;
import Model.Customer;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerLoginPageController {

    static Stage stage;
    Parent root;

    public void OpenPage() throws IOException {

        stage = new Stage();

        root = FXMLLoader.load(getClass().getResource("/view/CustomerLoginPage.fxml"));
        stage.setTitle("Customer Login Page");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        
    }

    @FXML
    private Text cardIDDD;

    @FXML
    private TextField userNameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorLabel;

    @FXML
    void LoginAction(ActionEvent event) throws IOException, SQLException, UnirestException {

        ButtonAction();

    }


    void ButtonAction() throws IOException, SQLException, UnirestException {

        String userName = userNameText.getText();
        String password = passwordText.getText();

        Auth auth = new Auth();
        boolean isTrue = auth.isCardIDAndPasswordRight(userName , password);

        if (isTrue)
        {
            Customer.setBankId(userNameText.getText());
            Customer.ReadData();
            ControllerCustomerServicesPage controllerCustomerServicesPage = new ControllerCustomerServicesPage();
            controllerCustomerServicesPage.OpenPage();
            stage.close();

        }

        else
        {
            errorLabel.setText("UserName or Password is Wrong");
        }

    }


}
