package Controller;

import Model.Customer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.sql.SQLException;

public class CustomerTransferMoneyController {

    boolean isCardIDExiests = false;

    static Stage stage;
    Parent root;

    public void OpenPage() throws IOException {

        Customer.ReadData();
        stage = new Stage();

        root = FXMLLoader.load(getClass().getResource("/view/CustomerTransferMoney.fxml"));
        stage.setTitle("Customer Login Page");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }


    @FXML
    private TextField cardIDTextField;

    @FXML
    private TextField amountTextField;

    @FXML
    private Button transferButton;

    @FXML
    private Label fullNameLabel;

    @FXML
    private Button checkButton;

    @FXML
    private Label errorLabel;

    @FXML
    void checkButtonAction(ActionEvent event) {
    }

    @FXML
    void transferButtonAction(ActionEvent event) throws SQLException, IOException {
        transferButtonAction();
    }



    void transferButtonAction() throws SQLException, IOException {

//        if (isCardIDExiests)
//        {

        double save = Double.parseDouble(amountTextField.getText());
        double balance = Customer.getBalance();
        System.out.println(cardIDTextField.getText());
        System.out.println(balance);


        String json = "{\"ribDestinataire\": \"" + cardIDTextField.getText() + "\", \"transaction\": {\"idCompte\": \"" + Customer.getId() + "\", \"montant\": " + save + ", \"dotation\": \"local\"}}";

        try {
        String url1 = "http://localhost:8081/api/client/virement";
        URL url = new URL(url1);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + Customer.getToken());

        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
        writer.write(json);
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        int status = conn.getResponseCode();

            if (status == HttpURLConnection.HTTP_CREATED) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                System.out.println(content.toString());

                ObjectMapper mapper = new ObjectMapper();
                JsonNode responseJson = mapper.readTree(content.toString());


                if (responseJson.get("status").asText().equals("ok")) {
                    System.out.println("done");
                    double newBalance = Customer.getBalance() - save;
                    Customer.setBalance(newBalance);
                    Customer.WriteData();
                    stage.close();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Done!");
                    alert.show();
                } else {
                    errorLabel.setText("your amount is not enough or error Rib");
                }
            }

        }catch(Exception e){
            errorLabel.setText("your amount is not enough or error Rib");

        }


//        if (balance >= save) {
//            Customer.setBalance(balance - save);
//            Customer.WriteData();
//            DataBaseConnect dataBaseConnect = new DataBaseConnect();
//            double userBalance = dataBaseConnect.ReadBalance(cardIDTextField.getText());
//            dataBaseConnect.ChangeBalance(userBalance + save, cardIDTextField.getText());
//
//            stage.close();
//
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setContentText("Done!");
//            alert.show();
//        } else {
//            errorLabel.setText("your balance is not enough");
//        }


    }


}
