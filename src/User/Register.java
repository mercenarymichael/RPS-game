package User;

import java.io.*;
import java.util.Scanner;

public class Register extends Login{
    private File file;
    private String username;
    private String password;
    public Register(Scanner scr, File file) {
        super(file);
        this.file = file;
        System.out.println("Registration:");
        super.CredentialsRead(scr);


        if(isUsernameTaken()) {
            System.out.println("Usename is taken, try again!");
        } else {
            createUser(super.getUsername(), super.getPassword());
        }
    }


    private boolean isUsernameTaken() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(this.file));
            String line;
            int linePos = 0;
            while((line = bfr.readLine()) != null) {
                if(linePos % 4 == 0 && line.equals(super.getUsername())) {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
