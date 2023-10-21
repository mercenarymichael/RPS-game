package Game;

import User.Player;

import java.util.Scanner;

public class RockPaperScissors{
    private Player p1;
    private Player p2;
    private Bot bot;
    private ASCII drawing;
    private int playerCount;

    public RockPaperScissors(Player p1, Bot bot, Scanner scr, int playerCount) {
        this.p1 = p1;
        this.bot = bot;
        this.playerCount = playerCount;
        Game(scr, playerCount);
    }

    public RockPaperScissors(Player p1, Player p2, Scanner scr, int playerCount) {
        this.p2 = p2;
        this.p1 = p1;
        Game(scr, playerCount);
    }

    public void Game(Scanner scr, int playerCount){
        scr = new Scanner(System.in);
        while(true) {
            System.out.print(p1.getUserName()+"'s move (rock/paper/scissors): ");
            p1.setMove(scr.nextLine());
            if(playerCount == 1) {
                bot.botMove();
                evaluateWinner(p1.getMove(), bot.getMove());
            } else {
                System.out.print(p2.getUserName()+"'s move (rock/paper/scissors): ");
                p2.setMove(scr.nextLine());
                evaluateWinner(p1.getMove(), p2.getMove());
            }
            System.out.print("Do you want to play another round?(y/n): ");
            if(scr.nextLine().equals("n")) break;
        }
    }


    private void evaluateWinner(String action1, String action2) {
        if (action1.equals(action2)) {
            System.out.println("It's a draw!");
        } else if (player1Wins(action1, action2)) {
            System.out.println(p1.getUserName()+" wins!");
        } else if (playerCount == 1){
            System.out.println("Computer wins!");
        } else {
            System.out.println(p2.getUserName()+" wins!");
        }
    }

    private boolean player1Wins(String action1, String action2) {
        if (action1.equals("rock")) {
            return action2.equals("scissors");
        } else if (action1.equals("paper")) {
            return action2.equals("rock");
        } else {
            return action2.equals("paper");
        }
    }
}
