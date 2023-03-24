import java.util.Random;

public class ComputerPlayer extends Player
{
    public static final Random rand = new Random();

    public ComputerPlayer(String name) {
        super(name);
    }

    /**
     * Randomly chooses a Location that has not been
     *   attacked (Location loc is ignored).  Marks
     *   the attacked Location on the guess board
     *   with a positive number if the enemy Player
     *   controls a ship at the Location attacked;
     *   otherwise, if the enemy Player does not
     *   control a ship at the attacked Location,
     *   guess board is marked with a negative number.
     *
     * If the enemy Player controls a ship at the attacked
     *   Location, the ship must add the Location to its
     *   hits taken.  Then, if the ship has been sunk, it
     *   is removed from the enemy Player's list of ships.
     *
     * Return true if the attack resulted in a ship sinking;
     *   false otherwise.
     *
     * @param enemy The Player to attack.
     * @param _loc The Location to attack.
     * @return
     */
    @Override
    public boolean attack(Player enemy, Location _loc)
    {
        // Count the number of spaces we haven't hit yet.
        int emptyCount = 0;

        for (int[] row : getGuessBoard())
            for (int cell : row)
                if (cell == 0)
                    emptyCount++;

        // Choose which one of the unknown spaces we should hit.
        int i = 0;
        int spot = rand.nextInt(emptyCount);
        Location loc = null;

        // Iterate until we get to the right index.
        outer: for (int row = 0; row < getGuessBoard().length; row++) {
            for (int col = 0; col < getGuessBoard()[row].length; col++) {
                if (getGuessBoard()[row][col] == 0) {
                    if (i == spot) {
                        loc = new Location(row, col);
                        break outer;
                    }

                    i++;
                }
            }
        }

        if (loc == null) {
            throw new Error("Something went wrong. The game should be over by now because all spots have been hit.");
        }

        if (enemy.hasShipAtLocation(loc)) {
            Ship ship = enemy.getShip(loc);

            ship.takeHit(loc);

            getGuessBoard()[loc.getRow()][loc.getCol()] = 1;

            if (ship.isSunk()) {
                enemy.removeShip(ship);
                return true;
            }

            return false;
        }

        getGuessBoard()[loc.getRow()][loc.getCol()] = -1;

        return false;
    }

    /**
     * Construct an instance of
     *
     *   AircraftCarrier,
     *   Destroyer,
     *   Submarine,
     *   Cruiser, and
     *   PatrolBoat
     *
     * and add them to this Player's list of ships.
     */
    @Override
    public void populateShips()
    {
        Ship carrier = new AircraftCarrier(
                new Location(0, 0),
                new Location(0, 1),
                new Location(0, 2),
                new Location(0, 3),
                new Location(0, 4)
        );
        Ship destroyer = new Destroyer(
                new Location(1, 0),
                new Location(1, 1),
                new Location(1, 2),
                new Location(1, 3)
        );
        Ship submarine = new Submarine(
                new Location(2, 0),
                new Location(2, 1),
                new Location(2, 2)
        );
        Ship cruiser = new Cruiser(
                new Location(3, 0),
                new Location(3, 1),
                new Location(3, 2)
        );
        Ship patrolBoat = new PatrolBoat(
                new Location(4, 0),
                new Location(4, 1)
        );

        this.addShip(carrier);
        this.addShip(destroyer);
        this.addShip(submarine);
        this.addShip(cruiser);
        this.addShip(patrolBoat);
    }
}
