public class HumanPlayer extends Player
{
    public HumanPlayer(String name)
    {
        super(name);
    }

    /**
     * Attack the specified Location loc.  Marks
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
     * @param enemy
     * @param loc
     * @return
     */
    @Override
    public boolean attack(Player enemy, Location loc)
    {
        if (enemy.hasShipAtLocation(loc)) {
            Ship ship = enemy.getShip(loc);

            ship.takeHit(loc);

            getGuessBoard()[loc.getRow()][loc.getCol()] = 1;

            if (ship.isSunk()) {
                enemy.removeShip(ship);
                return true;
            }

            return false;
        } else {
            getGuessBoard()[loc.getRow()][loc.getCol()] = -1;

            return false;
        }
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
