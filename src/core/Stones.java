package core;

import consoleUI.ConsoleUI;

public class Stones {
    private UserInterface userInterface;
    long startMillis;
  //  private BestTimes bestTimes = new BestTimes();
    private static Stones instance;  //Singleton pattern
   // private Settings setting;

    private Stones() {
        instance = this;

        Field field = new Field (4, 4);
        userInterface = new ConsoleUI();
        userInterface.newGameStarted(field);

}
    public static Stones getInstance() {
        if (instance == null) {
            new Stones();
        }
        return instance;
    }

   /* TODO
   public Settings getSetting() {
        return setting;
    }
    */

    public static void main(String[] args) {
        Stones.getInstance();



    }

    public int getPlayingSeconds() {
        return (int) ((System.currentTimeMillis() - startMillis) / 1000);
    }

    public void setStartTime(long startMillis) {
        this.startMillis = startMillis;
    }
}
