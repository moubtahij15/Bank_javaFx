package Model;

import Connector.Auth;

import java.io.IOException;

public class Customer2 {

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


    public void ReadData() throws IOException {

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


    public void WriteData() throws IOException {

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

//        dataBaseConnect.ChangeBalance(balance,bankId);
//        dataBaseConnect.ChangePassword(password,bankId);

    }



    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String newNationalID) {
        nationalID = newNationalID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String newfullName) {
        fullName = newfullName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String newfatherName) {
        fatherName = newfatherName;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String newbankId) {
        bankId = newbankId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String newphoneNumber) {
        phoneNumber = newphoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newemail) {
        email = newemail;
    }

    public String getHomeAdress() {
        return homeAdress;
    }

    public void setHomeAdress(String newhomeAdress) {
        homeAdress = newhomeAdress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newpassword) {
        password = newpassword;
    }

    public String getLoanID() {
        return loanID;
    }

    public void setLoanID(String newloanID) {
        loanID = newloanID;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String newaccountType) {
        accountType = newaccountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double newbalance) {
        balance = newbalance;
    }


}
