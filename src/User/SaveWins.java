package User;

import Run.Main;

import java.io.*;

public class SaveWins {
    public static void save(String username, int wins) {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(Main.file));
            StringBuilder sb = new StringBuilder();
            int linePos = 0;
            String line;
            while((line = bfr.readLine()) != null) {
                sb.append(line).append("\n");
                if(line.equals(username) && linePos % 4 == 0) {
                    line = bfr.readLine();
                    sb.append(line).append("\n");

                    line = bfr.readLine();
                    line = String.valueOf(wins+Integer.parseInt(line));
                    sb.append(line).append("\n");
                }
            }
            bfr.close();

            //System.out.println(sb.toString());
            //sb.deleteCharAt(sb.length()-1);
            BufferedWriter bfw = new BufferedWriter(new FileWriter(Main.file));
            bfw.write(sb.toString());
            bfw.close();
            System.out.println("Save completed!");
        } catch (IOException e) {
            System.err.println("IOException in save()");
        }
    }
}
