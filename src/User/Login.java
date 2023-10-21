package User;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Login {
    private String username;
    private String password;
    private int wins;
    private File file;

    public Login(File file) {
        this.file = file;
    }
    //Checks for user credentials in the users.txt file (structure: username, password, wins, empty line)
    public static boolean UserExists(String username, String password) {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("users.txt"));
            int linePos = 0;
            String line;
            while((line = bfr.readLine()) != null) {
                if(linePos % 4 == 0 && line.equals(username)) {
                    if((line = bfr.readLine()).equals(password)) {
                        return true;
                    }
                }
                linePos++;
            }
            bfr.close();
        } catch (IOException ioe) {
            System.err.println("IOException in UserExists()");
        }
        return false;
    }

    //username es password bekerese
    public void CredentialsRead(Scanner scr) {
        scr = new Scanner(System.in);

        System.out.print("Username: ");
        this.username = scr.nextLine();

        System.out.print("Password: ");
        this.password = scr.nextLine();

        System.out.println("-------------------------------------------");
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getWins() {
        return wins;
    }

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", wins=" + wins +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        Login login = (Login) o;
        return Objects.equals(username, login.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, wins);
    }
}
