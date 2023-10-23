package Game;

import User.SaveWins;

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
                whichDrawing(p1.getMove(), bot.getMove());
                evaluateWinner(p1.getMove(), bot.getMove());
            } else {
                System.out.print(p2.getUserName()+"'s move (rock/paper/scissors): ");
                p2.setMove(scr.nextLine());
                whichDrawing(p1.getMove(), p2.getMove());
                evaluateWinner(p1.getMove(), p2.getMove());
            }
            System.out.print("Do you want to play another round?(y/n): ");
            if(scr.nextLine().equals("n")) {
                if(playerCount == 2) {
                    SaveWins.save(p1.getUserName(), p1.getWins());
                    SaveWins.save(p2.getUserName(), p2.getWins());
                } else {
                    SaveWins.save(p1.getUserName(), p1.getWins());
                }
                break;
            }
        }
    }

    private void whichDrawing(String action1, String action2) {
        if(action1.equals("rock") && action2.equals("rock")) Draw.DrawArt(ASCII.ROCK_ROCK);
        else if(action1.equals("paper") && action2.equals("paper")) Draw.DrawArt(ASCII.PAPER_PAPER);
        else if(action1.equals("scissors") && action2.equals("scissors")) Draw.DrawArt(ASCII.SCISSORS_SCISSORS);
        else if(action1.equals("rock") && action2.equals("paper")) Draw.DrawArt(ASCII.ROCK_PAPER);
        else if(action1.equals("rock") && action2.equals("scissors")) Draw.DrawArt(ASCII.ROCK_SCISSORS);
        else if(action1.equals("paper") && action2.equals("rock")) Draw.DrawArt(ASCII.PAPER_ROCK);
        else if(action1.equals("paper") && action2.equals("scissors")) Draw.DrawArt(ASCII.PAPER_SCISSORS);
        else if(action1.equals("scissors") && action2.equals("rock")) Draw.DrawArt(ASCII.SCISSORS_ROCK);
        else Draw.DrawArt(ASCII.SCISSORS_PAPER);
    }

    private void evaluateWinner(String action1, String action2) {
        String result;
        if (action1.equals(action2)) {
            result = "It's a draw!";
        } else if (player1Wins(action1, action2)) {
            result = p1.getUserName()+" wins!";
            p1.incrementWins();
        } else if (playerCount == 1){
            result = "Computer wins!";
        } else {
            result = p2.getUserName()+" wins!";
            p2.incrementWins();
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < result.length()+2; j++) {
                if(i == 1) {
                    sb.append("*"+result+"*");
                    break;
                } else {
                    sb.append("*");
                }
            }
            sb.append("\n");
        }
        //center the result text
        System.out.println(sb.toString().indent((Draw.ASCIILength-(result.length()+2))/2));
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
