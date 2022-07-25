package core;

public abstract class Tile {
    public enum State {
        /**
         * Empty tile.
         */
        EMPTY,
        /**
         * OCCUPIED tile.
         */
        OCCUPIED,
    }
        private State state = State.OCCUPIED;

        /**
         * Returns current state of this tile.
         * @return current state of this tile
         */
        public State getState() {
            return state;
        }

        /**
         * Sets current current state of this tile.
         * @param state current state of this tile
         */
        void setState(State state) {
            this.state = state;
        }

    }


