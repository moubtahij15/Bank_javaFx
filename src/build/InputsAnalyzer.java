package build;

public class InputsAnalyzer {


    public boolean FullName (String fullName){

        boolean isOk = true;
        int i = 0;

        while(i<fullName.length() && isOk)
        {
            char saveCh = fullName.charAt(i);
            int save = (char)saveCh;

           if (!((save >= 65 && save <= 90) || (save >= 97 && save <= 122) || (save >= 1568 && save <= 1610)
                   || (save == 1711) || (save == 1670) || (save == 1662) || (save == 1688) ||(save == 32)))
               isOk = false;

           if (save == 1600)
               isOk = false;

           i++;

        }

        if (fullName.equals(""))
            isOk = false;

        System.out.println("FullName Checked");
        return isOk;
    }

    public boolean NationalID (String nationalID){

        boolean isOk = true;
        int i = 0;

        if (!(nationalID.length() == 10))
            isOk = false;

        while(i<nationalID.length() && isOk)
        {
            char saveCh = nationalID.charAt(i);
            int save = (char)saveCh;

            if (!(save >= 48 && save <= 57))
                isOk = false;

            i++;
        }

        if (nationalID.equals(""))
            isOk = false;

        System.out.println("NationalID Checked");
        return isOk;
    }

    public boolean PhoneNumber(String phoneNumber){

        boolean isOk = true;
        int i = 0;

        if (!(phoneNumber.length() == 11))
            isOk = false;

        while(i<phoneNumber.length() && isOk)
        {
            char saveCh = phoneNumber.charAt(i);
            int save = (char)saveCh;

            if (!(save >= 48 && save <= 57))
                isOk = false;

            i++;
        }

        if (phoneNumber.equals(""))
            isOk = false;

        System.out.println("PhoneNumber Checked");
        return isOk;
    }

    public boolean Email(String email){

        boolean isOk = true;
        int i = 0;

        while (i < email.length() && isOk)
        {
            char saveCh = email.charAt(i);
            int save = (char)saveCh;

            if (!((save >= 65 && save <= 90) || (save >= 97 && save <= 122) ||
                    (save >= 48 && save <= 57) || (save == 95) || (save == 64) || (save == 46)))
                isOk = false;

            i++;
        }

        i = 0;
        int count = 0;

        while (i<email.length() && isOk)
        {
            char saveCh = email.charAt(i);
            int save = (char)saveCh;

            if(save == '@')
                count++;

            i++;
        }

        if (count != 1)
            isOk = false;

        if (email.equals(""))
            isOk = false;

        System.out.println("Email Checked");
        return isOk;
    }

    public boolean Password(String password){

        boolean isOk = true;
        int i = 0;

        if (!(password.length() == 4))
            isOk = false;

        while(i<password.length() && isOk)
        {
            char saveCh = password.charAt(i);
            int save = (char)saveCh;

            if (!(save >= 48 && save <= 57))
                isOk = false;

            i++;
        }

        if (password.equals(""))
            isOk = false;

        System.out.println("Password Checked");
        return isOk;
    }

    public boolean[] CheckAll(String fullName , String fatherName , String nationalID
            , String phone , String email , String password){

        // result is the array of boolean for return all the inputs be correct
        // result [0] is for all of members

        boolean [] result = new boolean[7];

        result[1] = FullName(fullName);
        result[2] = FullName(fatherName);
        result[3] = NationalID(nationalID);
        result[4] = PhoneNumber(phone);
        result[5] = Email(email);
        result[6] = Password(password);

        boolean flaq = true;

        int i = 1 ;
        while(i < 7 && flaq)
        {
            if (result[i] == false)
                flaq = false;
            i++;
        }

        if (flaq == true)
            result[0] = true;
        else
            result[0] = false;

        System.out.println("All Checked");
        return result;


    }


}
