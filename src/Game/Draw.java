package Game;

import java.io.*;
import java.util.Arrays;

public class Draw {
    public static void DrawArt(ASCII ascii){
        int num = ascii.getNumber();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("ASCIIArt.txt"));
            String line;
            boolean correctNum = false;

            while((line=bfr.readLine()) != null) {
                if(line.equals(String.valueOf(ascii.getNumber()))) {
                    correctNum = true;
                } else if(line.equals(String.valueOf(ascii.getNumber()+1))) {
                    break;
                } else if (correctNum) {
                    System.out.println(line);
                }
            }
            bfr.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
