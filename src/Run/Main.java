package Run;

import Game.Bot;
import Game.Draw;
import Game.RockPaperScissors;
import User.Player;
import User.Login;

import java.util.Scanner;

public class Main {
    private static int playerCount;

    public static void main(String[] args) {
        Draw.DrawArt(1);
        Scanner scr = new Scanner(System.in);
        playerCount = gameMode(scr);

        Player[] players = new Player[playerCount];
        Login[] logins = new Login[playerCount];

        //megnezzuk, hogy sikeres-e a bejelentkezes, ha igen akkor player adatok feltoltese
        for(int i = 0; i < playerCount; i++) {
            logins[i] = new Login();
            logins[i].CredentialsRead(scr);
            while(!logins[i].UserExists()){
                System.out.println("Username or password is wrong, try again!");
                logins[i].CredentialsRead(scr);
            }
            while(i > 0 && logins[0].equals(logins[1])) {
                System.out.println("This user is already logged in, try with another account!");
                logins[i].CredentialsRead(scr);
            }

            players[i] = new Player(logins[i].getUsername(), logins[i].getPassword(), logins[i].getWins());
        }
        System.out.println(logins[0].equals(logins[1]));

        //Player vs Bot vagy Player vs Player
        if(playerCount == 1) {
            RockPaperScissors rps = new RockPaperScissors(players[0], new Bot(), scr, playerCount);
        } else {
            RockPaperScissors rps = new RockPaperScissors(players[0], players[1], scr, playerCount);
        }

        scr.close();
    }

    //hany player jatszik
    private static int gameMode(Scanner scr) {
        System.out.print("1 or 2 Players?: ");
        int num = scr.nextInt();
        System.out.println("-------------------------------------------");
        return num;
    }
}