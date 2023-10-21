package Game;

public enum ASCII {
    ROCK_ROCK("2"),
    PAPER_PAPER("4"),
    SCISSORS_SCISSORS("3"),
    ROCK_PAPER("5"),
    PAPER_ROCK("6"),
    ROCK_SCISSORS("7"),
    SCISSORS_ROCK("8"),
    PAPER_SCISSORS("9"),
    SCISSORS_PAPER("10"),
    TITLE("1");

    private String stringNumber;

    public int getNumber() {
        return Integer.parseInt(this.stringNumber);
    }

    ASCII(String number) {
        this.stringNumber = number;
    }
}
