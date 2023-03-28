package Model;

import Connector.Auth;

import java.io.IOException;

public class Customer {

    private static String nationalID;
    private static String fullName;
    private static String fatherName;
    private static String bankId;
    private static String phoneNumber;
    private static String email;
    private static String homeAdress;

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Customer.id = id;
    }

    private static String id;
    private static String password;
    private static String loanID;
    private static String accountType;
    private static double balance;
    public static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Customer.token = token;
    }

    public static void ReadData() throws IOException {

        Auth auth = new Auth();
        auth.ReadInformaiton(bankId);

        Model.Customer.setNationalID(auth.GetNationalID());
        Model.Customer.setFullName(auth.getFullName());
        Model.Customer.setFatherName(auth.getFatherName());
        Model.Customer.setBankId(auth.getBankId());
        Model.Customer.setPhoneNumber(auth.getPhoneNumber());
        Model.Customer.setEmail(auth.getEmail());
        Model.Customer.setHomeAdress(auth.getHomeAdress());
        Model.Customer.setPassword(auth.getPassword());
        Model.Customer.setLoanID(auth.getLoanID());
        Model.Customer.setAccountType(auth.getAccountType());
        Model.Customer.setBalance(auth.getBalance());

    }


    public static void WriteData() throws IOException {

        Auth auth = new Auth();
        auth.ReadInformaiton(bankId);

        auth.setNationalID(nationalID);
        auth.setFullName(fullName);
        auth.setFatherName(fatherName);
        auth.setBankId(bankId);
        auth.setPhoneNumber(phoneNumber);
        auth.setEmail(email);
        auth.setHomeAdress(homeAdress);
        auth.setPassword(password);
        auth.setLoanID(loanID);
        auth.setAccountType(accountType);
        auth.setBalance(balance);


    }


    public String getNationalID() {
        return nationalID;
    }

    public static void setNationalID(String newNationalID) {
        nationalID = newNationalID;
    }

    public static String getFullName() {
        return fullName;
    }

    public static void setFullName(String newfullName) {
        fullName = newfullName;
    }

    public static String getFatherName() {
        return fatherName;
    }

    public static void setFatherName(String newfatherName) {
        fatherName = newfatherName;
    }

    public static String getBankId() {
        return bankId;
    }

    public static void setBankId(String newbankId) {
        bankId = newbankId;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String newphoneNumber) {
        phoneNumber = newphoneNumber;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String newemail) {
        email = newemail;
    }

    public static String getHomeAdress() {
        return homeAdress;
    }

    public static void setHomeAdress(String newhomeAdress) {
        homeAdress = newhomeAdress;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String newpassword) {
        password = newpassword;
    }

    public static String getLoanID() {
        return loanID;
    }

    public static void setLoanID(String newloanID) {
        loanID = newloanID;
    }

    public static String getAccountType() {
        return accountType;
    }

    public static void setAccountType(String newaccountType) {
        accountType = newaccountType;
    }

    public static double getBalance() {
        return balance;
    }

    public static void setBalance(double newbalance) {
        balance = newbalance;
    }
}
