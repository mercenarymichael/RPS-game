package User;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Login {
    private String username;
    private String password;
    private int wins;
    private static int instanceCount;

    public Login() {
        instanceCount++;
    }

    //van-e ilyen user a txt-be (felepites: username, password, wins, uressor)
    public boolean UserExists() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("users.txt"));
            //BufferedWriter bfw = new BufferedWriter(new FileWriter("users.txt"));
            int linePos = 0;
            String line = "";
            while((line = bfr.readLine()) != null) {
                if(linePos % 3 == 0 && line.equals(this.username)) {
                    if((line = bfr.readLine()).equals(this.password)) {
                        return true;
                    }
                }
                linePos++;
            }
            bfr.close();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        return false;
    }

    //username es password bekerese
    public void CredentialsRead(Scanner scr) {
        scr = new Scanner(System.in);

        System.out.println(instanceCount+". player:");

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
