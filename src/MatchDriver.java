public class MatchDriver {
    // Config
    static final int GAMES = 1001;

    public static Player[] createPlayers() {
        return new Player[] {
                new IloWawa("daniel"),
                new ComputerPlayer("computer")
        };
    }

    // Main
    public static void main(String[] args) {
        final Player[] PLAYERS = createPlayers();

        // Stats
        int p1Wins = 0;
        int p2Wins = 0;
        int p1Total = 0;
        int p2Total = 0;
        int p1Least = 100;
        int p2Least = 100;
        int p1Most = 0;
        int p2Most = 0;
        int p1Ships = 0;
        int p2Ships = 0;

        long startTime = System.currentTimeMillis();
        long lastLogged = startTime;

        for (int game = 0; game < GAMES; game++) {
            Battleship battleship = new Battleship();

            Player[] players = createPlayers();

            if (game % 2 == 0) {
                battleship.addPlayer(players[0]);
                battleship.addPlayer(players[1]);
            } else {
                battleship.addPlayer(players[1]);
                battleship.addPlayer(players[0]);
            }

            int p1Turns = 0;
            int p2Turns = 0;

            while (!battleship.gameOver()) {
                Player p1 = battleship.getPlayer(0);
                Player p2 = battleship.getPlayer(1);

                p1.attack(p2, new Location(0, 0));
                p1Turns++;
                battleship.upkeep();

                if (battleship.gameOver())
                    break;

                p2.attack(p1, new Location(0, 0));
                p2Turns++;

                battleship.upkeep();
            }

            if (battleship.getWinner().equals(players[0])) {
                p1Wins++;
                p1Total += p1Turns;
                p1Least = Math.min(p1Least, p1Turns);
                p1Most = Math.max(p1Most, p1Turns);
                p1Ships += players[0].getNumberOfShips();
            } else {
                p2Wins++;
                p2Total += p2Turns;
                p2Least = Math.min(p2Least, p2Turns);
                p2Most = Math.max(p2Most, p2Turns);
                p2Ships += players[1].getNumberOfShips();
            }

            long now = System.currentTimeMillis();
            if (now - lastLogged > 200) {
                System.out.printf("Game %d: %d-%d\n", game, p1Wins, p2Wins);
                lastLogged = now;
            }
        }

        System.out.printf("\n--- %s (%s) ---\n",
                PLAYERS[0].getName(),
                PLAYERS[0].getClass().getSimpleName());
        System.out.println("Wins: " + p1Wins);
        System.out.println("Avg Turns: " + (double) p1Total / p1Wins);
        System.out.println("Least-Most Turns: " + p1Least + "-" + p1Most);
        System.out.println("Avg Ships Left: " + (double) p1Ships / p1Wins);

        System.out.printf("\n--- %s (%s) ---\n",
                PLAYERS[1].getName(),
                PLAYERS[1].getClass().getSimpleName());
        System.out.println("Wins: " + p2Wins);
        System.out.println("Avg Turns: " + (double) p2Total / p2Wins);
        System.out.println("Least-Most Turns: " + p2Least + "-" + p2Most);
        System.out.println("Avg Ships Left: " + (double) p2Ships / p2Wins);

        System.out.println("\n" + "All stats are per win.");
        System.out.println("Total time: " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
