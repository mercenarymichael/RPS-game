package Run;

import Game.Bot;
import Game.Draw;
import Game.RockPaperScissors;
import User.Player;
import User.Login;
import User.Register;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static int playerCount;
    private static final File file = new File("users.txt");
    public static void main(String[] args) {
        Draw.DrawArt(1);
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
            while(!logins[i].UserExists(logins[i].getUsername(), logins[i].getPassword())){
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
        int num = scr.nextInt();
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
            System.out.println("Do you want to register an account? (y/n)");
            if(scr.nextLine().equals("y"))  {
                new Register(scr, file);
            } else {
                break;
            }
        }
    }
}