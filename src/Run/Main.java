package Run;

import Game.*;
import User.Login;
import User.Register;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    //TODO: ASCII drawings, drawing selector logic, text decorations,
    //      maybe rework the User package(inheritances)
    private static int playerCount;
    public static final File file = new File("users.txt");
    public static void main(String[] args) {
        Draw.DrawArt(ASCII.TITLE);
        Scanner scr = new Scanner(System.in);

        registerAccount(scr);
        playerCount = gameMode(scr);

        Player[] players = new Player[playerCount];
        Login[] logins = new Login[playerCount];

        //Login users
        for(int i = 0; i < playerCount; i++) {
            logins[i] = new Login(file);
            System.out.println(i+1+". player:");
            logins[i].CredentialsRead(scr);
            while(!Login.UserExists(logins[i].getUsername(), logins[i].getPassword())){
                System.out.println("Username or password is wrong, try again!");
                logins[i].CredentialsRead(scr);
            }
            while(i > 0 && logins[0].equals(logins[1])) {
                System.out.println("This user is already logged in, try with another account!");
                logins[i].CredentialsRead(scr);
            }

            players[i] = new Player(logins[i].getUsername(), logins[i].getPassword(), logins[i].getWins());
        }

        //Player vs Bot or Player vs Player
        if(playerCount == 1) {
            new RockPaperScissors(players[0], new Bot(), scr, playerCount);
        } else {
            new RockPaperScissors(players[0], players[1], scr, playerCount);
        }
        scr.close();
    }

    //how many players are playing
    private static int gameMode(Scanner scr) {
        System.out.print("1 or 2 Players?: ");
        String s = scr.nextLine();
        while(!s.equals("1") && !s.equals("2")) {
            System.out.println("Wrong input, try again! (1/2)");
            s = scr.nextLine();
        }
        int num = Integer.parseInt(s);
        System.out.println("-------------------------------------------");
        return num;
    }

    private static void registerAccount(Scanner scr) {
        try {
            if(!file.exists()) {
                file.createNewFile();
                System.out.println("There is no registered accounts!");
                new Register(scr, file);
            }
        } catch (IOException ioe) {
            System.err.println("IOException in registerAccount()");
        }

        while(true) {
            System.out.print("Do you want to register an account? (y/n): ");
            String yesOrNo = scr.nextLine().toLowerCase();

            while(!yesOrNo.equals("y") && !yesOrNo.equals("n")) {
                System.out.println("Wrong input, try again! (y/n): ");
                yesOrNo = scr.nextLine();
            }

            if(yesOrNo.equals("y"))  {
                new Register(scr, file);
            } else {
                break;
            }
        }
    }
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }

        return true;
    }
}