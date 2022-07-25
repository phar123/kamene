package core;

public class Empty extends Tile {
    @Override
    public String toString() {
        if (getState() == State.OCCUPIED) {
            return " X ";
        } else if (getState() == State.EMPTY){
            return "   ";
        } else {
            return " - ";
        }
    }
}
