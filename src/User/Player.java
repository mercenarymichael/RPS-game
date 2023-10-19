package User;

public class Player extends ChangeAndDelete implements userMethods{
    private String userName;
    private String password;
    private String move;
    private int wins;


    public Player(String userName, String password, int wins) {
        this.userName = userName;
        this.password = password;
        this.wins = wins;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public int getWins() {
        return this.wins;
    }

    @Override
    public void setMove(String move) {
        this.move = move;
    }

    @Override
    public String getMove() {
        return this.move;
    }
}
