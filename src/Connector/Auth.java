package Connector;

import Model.Customer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;

public class Auth {



    /*
     * NationalID
     * FullName
     * fatherName
     * BankID
     * PhoneNumber
     * Email
     * HomeAdress
     * Password
     * LoanID
     * AccountType
     * Balance
     */

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
    private double balance;

    private String[][] TableData = new String[100][12];


    public Auth() {

    }


    public void ReadInformaiton(String newBankID) throws IOException {
// 1. Construct the API endpoint URL with the appropriate query parameters
        String apiUrl = "http://localhost:8081/clients/";
        URL url = new URL(apiUrl + Customer.getId());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

// 2. Add an authorization header to the request with the token
        String authToken = "your_auth_token_here"; // Replace with your actual auth token
        conn.setRequestProperty("Authorization", "Bearer " + Customer.getToken());

// 3. Retrieve the response from the server
        int responseCode = conn.getResponseCode();
        InputStream inputStream;
        if (responseCode >= 400) {
            inputStream = conn.getErrorStream();
        } else {
            inputStream = conn.getInputStream();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder responseBuilder = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            responseBuilder.append(inputLine);
        }

        in.close();
        String response = responseBuilder.toString();

// 4. Parse the response data using Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response);

// 5. Extract the product data from the parsed response object

        nationalID = rootNode.get("cin").asText();
        fullName = rootNode.get("nom").asText();
        fatherName = rootNode.get("prenom").asText();
        phoneNumber = rootNode.get("tel").asText();
        email = rootNode.get("email").asText();
        homeAdress = rootNode.get("adresse").asText();

// 1. Construct the API endpoint URL with the appropriate query parameters
        String apiUrl1 = "http://localhost:8081/clients/" + Customer.getId() + "/comptesById";
        URL url1 = new URL(apiUrl1);
        HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
        conn1.setRequestMethod("GET");

// 2. Add an authorization header to the request with the token
        conn1.setRequestProperty("Authorization", "Bearer " + Customer.getToken());
// 3. Retrieve the response from the server
        int responseCode1 = conn1.getResponseCode();
        InputStream inputStream1;
        if (responseCode1 >= 400) {
            inputStream1 = conn1.getErrorStream();
        } else {
            inputStream1 = conn1.getInputStream();
        }

        BufferedReader in1 = new BufferedReader(new InputStreamReader(inputStream1));
        StringBuilder responseBuilder1 = new StringBuilder();
        String inputLine1;

        while ((inputLine1 = in1.readLine()) != null) {
            responseBuilder1.append(inputLine1);
        }

        in1.close();
        String response1 = responseBuilder1.toString();

// 4. Parse the response data using Jackson
        ObjectMapper objectMapper1 = new ObjectMapper();
        JsonNode rootNode1 = objectMapper1.readTree(response1);

// 5. Extract the product data from the parsed response object
        accountType = rootNode1.get("_embedded").get("comptes").get(0).get("type").asText();
        balance = rootNode1.get("_embedded").get("comptes").get(0).get("sold").asDouble();


    }


    public boolean isCardIDAndPasswordRight(String cardID, String Password) throws SQLException, UnirestException {
        boolean isTrue = false;


        try {

            System.out.println(cardID);
            System.out.println(Password);
            String json = "{\"grantType\": \"password\", \"username\": \"" + cardID + "\", \"password\": \"" + Password + "\", \"role\": \"CLIENT\"}";
            String url1 = "http://localhost:8081/token";
            URL url = new URL(url1);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");


            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(json);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            int status = conn.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
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
                try {
                    if (responseJson.get("statut").asText().equals("error")) {
                        return false;
                    }
                } catch (Exception e) {
                    Customer.setToken(responseJson.get("accessToken").asText());
                    Customer.setId(responseJson.get("client").get("id").asText());
                    return true;
                }
                System.out.println(responseJson.get("statut").asText());
                return true;
            } else {
                System.out.println("Failed with HTTP error code: " + status);
                return true;

            }
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//            reader.close();
//            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

//        Statement statement = connection.createStatement();
//
//        String save = "SELECT Password FROM information WHERE BankID='%s'";
//        save = String.format(save, cardID);
//        String newPassword = null;
//        System.out.println("test11" + Password);
//
//        try {
//            ResultSet result = statement.executeQuery(save);
//            System.out.println("inside try");
//            while (result.next()) {
//                System.out.println("inside while");
//
//                newPassword = result.getString("Password");
//                System.out.println("grrrt" + newPassword);
//                System.out.println("Paassword new +" + Password);
//
//
//            }
//
//            result.close();
//            System.out.println("Paassword new +" + newPassword);
//            System.out.println("Paassword new +" + password);
//
//            if (newPassword.equals(Password))
//                isTrue = true;
//
//            statement.close();
//            closeConnection();
//
//            return isTrue;
//
//        } catch (SQLException e) {
//            statement.close();
//            closeConnection();
//            return isTrue;
//        }

    }


    /* ============================= */
    public String GetNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAdress() {
        return homeAdress;
    }

    public void setHomeAdress(String homeAdress) {
        this.homeAdress = homeAdress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoanID() {
        return loanID;
    }

    public void setLoanID(String loanID) {
        this.loanID = loanID;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    /* ================================== */

}


