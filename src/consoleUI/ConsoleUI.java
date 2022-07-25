package consoleUI;

import core.Field;
import core.GameState;
import core.Stones;
import core.UserInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleUI implements UserInterface {
    private Field field;
    String playerName = null;

    /**
     * Input reader.
     */
    private final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private Throwable ex;

    /**
     * Reads line of text from the reader.
     *
     * @return line as a string
     */
    private String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }



    @Override
    public void newGameStarted(Field field) {
        this.field = field;

        /*TODO input from Settings*/

        do {
            update();
            if (field.getState() == GameState.SOLVED) {

             /*   System.out.println("Congrats, you winning");
                BestTimes bt = Minesweeper.getInstance().getBestTimes();
                bt.addPlayerTime(playerName == null ? System.getProperty("user.name") : playerName,
                        Minesweeper.getInstance().getPlayingSeconds());
                System.out.println("Bestplayers:\n" + bt);
                */
                System.exit(0);
            } else if (field.getState() == GameState.FAILED) {

                System.out.println("Sorry, you losing");
                System.exit(0);
            }


            processInput();
            // throw new UnsupportedOperationException("Resolve the game state - winning or loosing condition.");
        } while (true);
    }

    @Override
    public void update() {

        System.out.printf("Cas hrania: %d%n", Stones.getInstance().getPlayingSeconds()/1000);

        System.out.printf("    ");
        for (int i = 1; i < field.getColumnCount() + 1; i++) {
            System.out.printf("%4s", i);
        }

        System.out.println();

        char c = 'A';

        for (int i = 0; i < field.getRowCount(); i++) {
            c = (char) (i + 65);
            System.out.printf("%4s", c);

            for (int j = 0; j < field.getColumnCount(); j++) {
               /* if (j > 9) {
                    System.out.print(" ");
                }*/
                System.out.printf("%4s", field.getTile(i, j)); // netreba toString = automaticky
            }

            System.out.println();
        }

    }
        private void processInput() {
            //throw new UnsupportedOperationException("Method processInput not yet implemented");
            String a = " new ";
            String b = "exit";
            String c = "w(up)";
            String d = "s(down)";
            String e = "a(left)";
            String f = "d(right)";
           // String g = "N";
            String g = "(nick)NAME";


            System.out.printf("Please select your location  <%s>  <%s> <%s>  <%s>, <%s>  <%s>, <%s> : ", a, b, c, d, e, f,g);

            try {

                String s = readLine();

                handleInput(s);

            } catch (WrongInputFormatException ex) {
                System.out.println(ex.getMessage());

                processInput();
            }
        }

        private void handleInput(String input) throws WrongInputFormatException {
        String inputup =  input.toString().trim().toUpperCase();


            Pattern pat;
            Matcher mat;
            boolean found;

            if (inputup.length() > 5 || inputup.length() < 1 ) {
                throw new WrongInputFormatException("Too many chracters! Please enter again");

            }
//Exit
            if (inputup.equals("EXIT"))
                System.exit(0);

            //New
            if (inputup.equals("NEW"))
                newGameStarted(field);

//Name of the player
            char n = inputup.charAt(0);
            if (n == 'N') {
                System.out.println("Please enter your (nick)Name: ");
                playerName = readLine();
                System.out.println("Hi "+ playerName+ " Good luck!\n");
                Stones.getInstance().setStartTime(System.currentTimeMillis());
            }


// W/ UP
            pat = Pattern.compile("([W])||([UP])");
            mat = pat.matcher(inputup);


            if (mat.matches()) {
                field.move(0, 1);
            }
  // S/DOWN
            pat = Pattern.compile("([S])||([DOWN])");
            mat = pat.matcher(inputup);

            if (mat.matches()) {
            field.move(0, -1);

            }
  // A/LEFT
            pat = Pattern.compile("([A])||([LEFT])");
            mat = pat.matcher(inputup);

            if (mat.matches()) {
                field.move(-1, 0);

            }
  // D/Right
            pat = Pattern.compile("([D])||([RIGHT])");
            mat = pat.matcher(inputup);

            if (mat.matches()) {
                field.move(1, 0);

            }

            update();

        }

    }




