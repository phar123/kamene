package core;

public class Cover extends  Tile {
    private final int value;

    public Cover(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (getState() == State.EMPTY){
            return "   ";
        } else if (getState() ==  State.OCCUPIED) {
            return Integer.toString(getValue());
        } else {
            return "-";
        }
    }

}
