package core;

import consoleUI.WrongInputFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Field {
    /**
     * Playing field tiles.
     */
    private final Tile[][] tiles;

    private int rowCount;

    private int columnCount;

    private int emptyField_x = 0;
    private int emptyField_y = 0;


    private GameState state = GameState.PLAYING;

    private final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private Throwable ex;

    /**
     * Constructor.
     *
     * @param rowCount    row count
     * @param columnCount column count
     */
    public Field(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        // this.mineCount = mineCount;
        // tiles = new Tile[rowCount][columnCount];

        System.out.println("Enter playing field in format rows x columns");


        try {

            String s = readLine();

            handleInput(s);

        } catch (WrongInputFormatException ex) {
            System.out.println(ex.getMessage());

        }

        System.out.println("Row: " + rowCount);
        System.out.println("Column: " + columnCount);

        tiles = new Tile[rowCount][columnCount];

        //generate the field content
        generate();

    }


    /**
     * Field row count. Rows are indexed from 0 to (rowCount - 1).
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Column count. Columns are indexed from 0 to (columnCount - 1).
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * Game state.
     */
    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Tile getTile(int row, int column) {
        if (tiles[row][column] != null && tiles[0].length != 0)
            return tiles[row][column];
        return null;
    }

    private String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }


    private void handleInput(String input) throws WrongInputFormatException {
        String inputup = input.toString().trim().toUpperCase();


        Pattern pat;
        Matcher mat;
        boolean found;

        pat = Pattern.compile("([1-9]{1,2})'X'([1-9]{1,2})");
        mat = pat.matcher(inputup);


        if (mat.matches()) {
            rowCount = Integer.parseInt(mat.group(1));
            columnCount = Integer.parseInt(mat.group(2));
        }

    }

    private void generate() {

        int val = 1;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] == null)
                    if (val > (rowCount * columnCount) - 1) {
                        System.out.println("Som v empty");

                        tiles[i][j] = new Empty();
                        tiles[i][j].setState(Tile.State.EMPTY);
                        emptyField_x = i;
                        emptyField_y = j;

                    } else {
                        tiles[i][j] = new Cover(val++);
                    }

            }
        }
        // mix the field
        move(0, -1);
         move(-1, 0);
       move(0, -1);
        move(-1, 0);
    }

    public void move(int di, int dj) {
       /* if (emptyField_x + di < rowCount - 1 && emptyField_x + di > 0 && emptyField_y + dj < columnCount - 1 &&
                emptyField_y + dj > 0) {*/
            Tile tmp = tiles[emptyField_x][emptyField_y];
            Tile change = tiles[emptyField_x + di][emptyField_y + dj];
            tiles[emptyField_x][emptyField_y] = change;
            tiles[emptyField_x + di][emptyField_y + dj] = tmp;

            emptyField_x = emptyField_x + di;
            emptyField_y = emptyField_y + dj;
       // }
    }


}