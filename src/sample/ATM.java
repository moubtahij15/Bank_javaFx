package sample;

import Controller.CustomerLoginPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class ATM extends Application {

    public static void main (String []args){

        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {

        CustomerLoginPageController customerLoginPageController = new CustomerLoginPageController();
        customerLoginPageController.OpenPage();

    }
}


