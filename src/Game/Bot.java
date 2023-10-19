package Game;

import java.util.Random;

public class Bot {
    private static final int upperBound = 3;
    String move;
    public void botMove() {
        Random rand = new Random();
        int randomNum = rand.nextInt(3)+1;
        this.move = numToAction(randomNum);
        System.out.println("Computer's move: "+move);
    }

    private String numToAction(int num) {
        switch (num) {
            case 1: return "rock";
            case 2: return "paper";
            case 3: return "scissors";
        }
        return "";
    }

    public String getMove() {
        return this.move;
    }
}
