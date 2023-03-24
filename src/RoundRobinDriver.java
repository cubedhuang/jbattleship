public class RoundRobinDriver {
    // Config
    static final int GAMES = 500;
    static final int PLAYER_COUNT = 4;

    public static Player createPlayer(int i) {
        return switch (i) {
            case 0 -> new IloWawa("DAN");
            case 1 -> new Aiden("ADN");
            case 2 -> new ComputerPlayer("COM");
            case 3 -> new ComputerPlayer("CM2");
            default -> null;
        };
    }

    // Main
    public static void main(String[] args) {
        final Player[] PLAYERS = new Player[PLAYER_COUNT];
        for (int i = 0; i < PLAYERS.length; i++) {
            PLAYERS[i] = createPlayer(i);
        }

        int[][] scores = new int[PLAYERS.length][PLAYERS.length];

        for (int i = 0; i < PLAYERS.length; i++) {
            for (int j = 0; j < PLAYERS.length; j++) {
                scores[i][j] = runMatch(i, j);
            }
        }

        System.out.print("\n     ");
        for (int i = 0; i < PLAYERS.length; i++) {
            System.out.print(PLAYERS[i].getName() + "      ");
        }

        for (int i = 0; i < PLAYERS.length; i++) {
            System.out.print("\n" + PLAYERS[i].getName() + "  ");
            for (int j = 0; j < PLAYERS.length; j++) {
                int score = scores[i][j];

                int p1Wins = (int) ((double) GAMES / 2 + (double) score / 2);
                int p2Wins = (int) ((double) GAMES / 2 - (double) score / 2);

                StringBuilder result = new StringBuilder(p1Wins + "-" + p2Wins);

                while (result.length() < 7) {
                    result.append(" ");
                }

                if (score > 0) {
                    result = new StringBuilder("\u001B[32m" + result + "\u001B[0m");
                } else if (score < 0) {
                    result = new StringBuilder("\u001B[31m" + result + "\u001B[0m");
                }

                System.out.print(result + "  ");
            }
        }

        System.out.println();
    }

    /**
     * Runs a match between two players. Positive return value means player 1
     * won, negative means player 2 won.
     * @param i Index of player 1.
     * @param j Index of player 2.
     * @return The score of the match.
     */
    public static int runMatch(int i, int j) {
        int score = 0;

        long lastLogged = System.currentTimeMillis();

        for (int game = 0; game < GAMES; game++) {
            Battleship battleship = new Battleship();

            Player[] players = new Player[] {
                    createPlayer(i),
                    createPlayer(j)
            };

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
                score++;
            } else {
                score--;
            }

            long now = System.currentTimeMillis();
            if (now - lastLogged > 200) {
                System.out.printf("Game %d between %s and %s.\n",
                        game,
                        players[0].getName(),
                        players[1].getName());
                lastLogged = now;
            }
        }

        return score;
    }
}
