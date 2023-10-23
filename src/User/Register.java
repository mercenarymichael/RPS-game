package User;

import java.io.*;
import java.util.Scanner;

public class Register {
    private File file;
    private String username;
    private String password;
    private static final int usernameLength = 10;
    public Register(Scanner scr, File file) {
        this.file = file;
        System.out.println("Registration:");
        this.CredentialsRead(scr);


        if(isUsernameTaken()) {
            System.out.println("Username is taken, try again!");
        } else {
            createUser(this.username, this.password);
        }
    }


    private boolean isUsernameTaken() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(this.file));
            String line;
            int linePos = 0;
            while((line = bfr.readLine()) != null) {
                if(linePos % 4 == 0 && line.equals(this.username)) {
                    return true;
                }
                linePos++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFound in isUsernameTaken()");
        } catch (IOException e) {
            System.err.println("IOException in isUsernameTaken()");
        }
        return false;
    }

    private void createUser(String username, String password) {
        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter(this.file, true));

            bfw.write(username);
            bfw.newLine();
            bfw.write(password);
            bfw.newLine();
            bfw.write("0");
            bfw.newLine();
            bfw.newLine();
            bfw.close();
            System.out.println("Registration completed!");
        } catch (IOException e) {
            System.out.println("Registration has failed! IOException in createUser()");
        }
    }

    private void CredentialsRead(Scanner scr) {
        scr = new Scanner(System.in);

        System.out.print("Username: ");
        this.username = scr.nextLine();
        //Check for username criteria
        while(!Character.isAlphabetic(this.username.charAt(0)) ||
                this.username.length() > usernameLength ||
                this.username.contains(" ")) {

            if(this.username.length() > 10) System.out.println("Username maximum length is "+usernameLength+"!");
            else if(this.username.contains(" ")) System.out.println("Username cannot contain spaces!");
            else System.out.println("The username should start with a letter!");
            System.out.print("Username: ");
            this.username = scr.nextLine();
        }

        System.out.print("Password: ");
        //Check for password criteria
        this.password = scr.nextLine();
        while(this.password.length() < 3 || this.password.length() > 10) {
            if(this.password.length() < 3) System.out.println("Password must be at least 3 characters long!");
            else System.out.println("Password must be up to 10 characters long");
            this.password = scr.nextLine();
        }

        System.out.println("-------------------------------------------");
    }
}
