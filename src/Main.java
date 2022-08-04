public class Main {
    public static void main(String[] args) {
        Game newGame = new Game();
        System.out.println("  _______          _______           _______         \n" +
                " |__   __|        |__   __|         |__   __|        \n" +
                "    | |   _  ___     | | __ _  ___     | | ___   ___ \n" +
                "    | |  | |/ __|    | |/ _` |/ __|    | |/ _ \\ / _ \\\n" +
                "    | |  | | (__     | | (_| | (__     | | (_) |  __/\n" +
                "    |_|  |_|\\___|    |_|\\__,_|\\___|    |_|\\___/ \\___|");
        System.out.println();
        newGame.start();
    }
}
