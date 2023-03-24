import java.util.ArrayList;

public class Aiden extends ComputerPlayer
{
    private final Location lox0;
    private final Location lox1;
    private final Location lox2;
    private final Location lox3;
    private final Location lox4;
    private final Location lox5;
    private final ArrayList<Location> toHit = new ArrayList<Location>();
    private final ArrayList<Location> vir = new ArrayList<>();

    public Aiden(String name)
    {
        super(name);
        // my fault team forgive me mr. dubbard
        lox0 = new Location(0, 3);
        lox1 = new Location(1, 4);
        lox2 = new Location(2, 5);
        lox3 = new Location(1, 6);
        lox4 = new Location(0, 5);
        lox5 = new Location(0, 7);
        initialize();


    }

    /**
     * Normal One
     *  If ship is hit, go to vir
     *  when attacking, check for k.
     * -1 is false, 1 is true, 0 is try again.
     *
     * @param enemy
     * @param loc
     * @return
     */
    @Override
    public boolean attack(Player enemy, Location loc)
    {
        int sink = 0;
        while(sink == 0)
        {
//            if(vir)
//            {
//                sink = vir(enemy);
//            }

            /*
            else if(i < 4)
            {
                sink = i4(enemy);

            }
            else if(i < 8)
            {
                sink = i8(enemy);
            }
            else if(i < 20)
            {
                sink = i20(enemy);
            }
            else if(i < 26)     // increments by 6 ig
            {
                sink = i26(enemy);
            }
            else if(i < 32)
            {
                sink = i32(enemy);
            }
            else if(i < 38)
            {
                sink = i38(enemy);
            }
            else if(i < 44)
            {
                sink = i44(enemy);
            }
            */
            if(vir.size() > 0)
                if(k(vir.get(0)))
                    sink = ifHit(enemy, vir.remove(0));
                else
                {
                    vir.remove(0);
                    sink = 0;
                }
            else if(toHit.size() > 0)
                if(toHit.size() < 28)
                {
                    // insert rd here
                    int rd = (int) (Math.random() * toHit.size());
                    boolean wah = false;
                    while(!wah && !k(toHit.get(rd)))
                    {


                        toHit.remove(rd);
                        rd = (int) (Math.random() * toHit.size());
                        if(toHit.size() == 0)
                            wah = true; // and sink = 0
                    }
                    if(!wah)
                        sink = ifHit(enemy, toHit.remove(rd));
                    else
                        sink = 0;
                }
                else
                    if(k(toHit.get(0)))
                    {
                        sink = ifHit(enemy, toHit.remove(0));
                    }
                    else
                    {
                        toHit.remove(0);
                        sink = 0;
                    }

            else
            {
//                System.out.println("WE ARE NO LONGER COOKING");
                sink = flop(enemy);
            }
        }
        return sink > 0;
    }

    // essentially labels adjacent cells to beginning of toHit.
    private void vir(Player e, Location loc)
    {
        /*Location loc;
        if(branch != null)
            loc = branch;
        else
            loc = hit;
        if(loc.getCol() == 9)       // no rights
        {
            if (loc.getRow() == 9)
            {
                if (k(locW(loc)))
                    return ifHit(e, locW(loc));
                else if (k(locA(loc)))
                    return ifHit(e, locA(loc));
            } else if (loc.getRow() == 0)
            {
                if (k(locS(loc)))
                    return ifHit(e, locS(loc));
                else if (k(locA(loc)))
                    return ifHit(e, locA(loc));
            } else if (k(locS(loc)))
                return ifHit(e, locS(loc));
            else if (k(locA(loc)))
                return ifHit(e, locA(loc));
            else if (k(locW(loc)))
                return ifHit(e, locW(loc));
        }
        else if(loc.getCol() == 0)      // no lefts
        {
            if (loc.getRow() == 9)
            {
                if (k(locW(loc)))
                    return ifHit(e, locW(loc));
                else if (k(locD(loc)))
                    return ifHit(e, locD(loc));
            }
            else if (loc.getRow() == 0)
            {
                if (k(locS(loc)))
                    return ifHit(e, locS(loc));
                else if (k(locD(loc)))
                    return ifHit(e, locD(loc));
            }
            else
            {
                if (k(locS(loc)))
                    return ifHit(e, locS(loc));
                else if (k(locD(loc)))
                    return ifHit(e, locD(loc));
                else if (k(locW(loc)))
                    return ifHit(e, locW(loc));
            }
        }
        else
        {
            // if no col restraints but rows
            if(loc.getRow() == 9)      // bot
            {
                if (k(locW(loc)))
                    return ifHit(e, locW(loc));
                else if (k(locD(loc)))
                    return ifHit(e, locD(loc));
            }
            else if(loc.getRow() == 0) // top
            {
                if (k(locS(loc)))
                    return ifHit(e, locS(loc));
                else if (k(locD(loc)))
                    return ifHit(e, locD(loc));
            }
            else
            {
                if (k(locS(loc)))
                    return ifHit(e, locS(loc));
                else if (k(locD(loc)))
                    return ifHit(e, locD(loc));
                else if (k(locW(loc)))
                    return ifHit(e, locW(loc));
                else if (k(locS(loc)))
                    return ifHit(e, locS(loc));

            }

            // but actually if no restraints






        }
        if(loc == branch)
        {
            branch = null;
            return 0;
        }

        vir = false;
        return 0;

         */
        if(loc.getCol() == 0 && loc.getRow() == 0)
        {
            vir.add(0, new Location(1, 0));
            vir.add(0, new Location(0, 1));
        }
        else if(loc.getCol() == 0 && loc.getRow() == 9)
        {
            vir.add(0, new Location(9, 1));
            vir.add(0, new Location(8, 0));
        }
        else if(loc.getCol() == 0)
        {
            vir.add(0, locD(loc));
            vir.add(0, locS(loc));
            vir.add(0, locW(loc));
        }
        else if(loc.getCol() == 9 && loc.getRow() == 0)
        {
            vir.add(0, locA(loc));
            vir.add(0, locS(loc));
        }
        else if(loc.getCol() == 9 && loc.getRow() == 9)
        {
            vir.add(0, locA(loc));
            vir.add(0, locW(loc));
        }
        else if(loc.getCol() == 9)
        {
            vir.add(0, locA(loc));
            vir.add(0, locW(loc));
            vir.add(0, locS(loc));
        }
        else if(loc.getRow() == 0)
        {
            vir.add(0, locA(loc));
            vir.add(0, locS(loc));
            vir.add(0, locD(loc));
        }
        else if(loc.getRow() == 9)
        {
            vir.add(locW(loc));
            vir.add(locA(loc));
            vir.add(locD(loc));
        }
        else
        {
            vir.add(locW(loc));
            vir.add(locA(loc));
            vir.add(locS(loc));
            vir.add(locD(loc));
        }


    }

    private void initialize()
    {
        toHit.add(new Location(0, 0));  // first 4
        toHit.add(new Location(0, 9));
        toHit.add(new Location(9, 9));
        toHit.add(new Location(9, 0));

        toHit.add(new Location(4, 4));  // + 4
        toHit.add(new Location(4, 5));
        toHit.add(new Location(5, 4));
        toHit.add(new Location(5, 5));

        toHit.add(new Location(1, 1));  // + 12
        toHit.add(new Location(1, 8));
        toHit.add(new Location(8, 8));
        toHit.add(new Location(8, 1));
        toHit.add(new Location(2, 2));
        toHit.add(new Location(2, 7));
        toHit.add(new Location(7, 7));
        toHit.add(new Location(7, 2));
        toHit.add(new Location(3, 3));
        toHit.add(new Location(3, 6));
        toHit.add(new Location(6, 6));
        toHit.add(new Location(6, 3));

        // i20! SO IF LESS THAN 49
        toHit.add(lox0);
        toHit.add(lox1);
        toHit.add(lox2);
        toHit.add(lox3);
        toHit.add(lox4);
        toHit.add(lox5);

        toHit.add(new Location(9 - lox0.getCol(), lox0.getRow()));
        toHit.add(new Location(9 - lox1.getCol(), lox1.getRow()));
        toHit.add(new Location(9 - lox2.getCol(), lox2.getRow()));
        toHit.add(new Location(9 - lox3.getCol(), lox3.getRow()));
        toHit.add(new Location(9 - lox4.getCol(), lox4.getRow()));
        toHit.add(new Location(9 - lox5.getCol(), lox5.getRow()));

        toHit.add(new Location(9 - lox0.getRow(), 9 - lox0.getCol()));
        toHit.add(new Location(9 - lox1.getRow(), 9 - lox1.getCol()));
        toHit.add(new Location(9 - lox2.getRow(), 9 - lox2.getCol()));
        toHit.add(new Location(9 - lox3.getRow(), 9 - lox3.getCol()));

        toHit.add(new Location(lox0.getCol(), 9 - lox0.getRow()));
        toHit.add(new Location(lox1.getCol(), 9 - lox1.getRow()));
        toHit.add(new Location(lox2.getCol(), 9 - lox2.getRow()));
        toHit.add(new Location(lox3.getCol(), 9 - lox3.getRow()));
        toHit.add(new Location(lox3.getCol(), 9 - lox4.getRow()));
        toHit.add(new Location(lox5.getCol(), 9 - lox5.getRow()));

        // now past i44

        toHit.add(new Location(9, 7));
        toHit.add(new Location(0, 2));
        toHit.add(new Location(2, 9));
        toHit.add(new Location(7, 0));

        toHit.add(new Location(8, 7));
        // IF ADD MROE CRY ABOUT IT (i think in attack)



    }




/*
    private int i4(Player e)
    {
        Location loc;
        if(i == 0)
            loc = new Location(0, 0);
        else if(i == 1)
            loc = new Location(0, 9);
        else if(i == 2)
            loc = new Location(9, 9);
        else        // 3
            loc = new Location(9, 0);
        i++;
        if(k(loc))
            return ifHit(e, loc);
        return 0;
    }
    private int i8(Player e)
    {
        Location loc;
        if(i == 4)
            loc = new Location(4, 4);
        else if(i == 5)
            loc = new Location(4, 5);
        else if(i == 6)
            loc = new Location(5, 4);
        else        // 7
            loc = new Location(5, 5);
        i++;
        if(k(loc))
            return ifHit(e, loc);
        else
            System.out.println("cant put that there");
        return 0;

    }
    private int i20(Player e)
    {
        Location loc;
        if(i == 8)
            loc = new Location(1, 1);
        else if(i == 9)
            loc = new Location(1, 8);
        else if(i == 10)
            loc = new Location(8, 8);
        else if(i == 11)
            loc = new Location(8, 1);
        else if (i == 12)
            loc = new Location(2, 2);
        else if(i == 13)
            loc = new Location(2, 7);
        else if(i == 14)
            loc = new Location(7, 7);
        else if(i == 15)
            loc = new Location(7, 2);
        else if(i == 16)
            loc = new Location(3, 3);
        else if(i == 17)
            loc = new Location(3, 6);
        else if(i == 18)
            loc = new Location(6, 6);
        else        // 19
            loc = new Location(6, 3);
        i++;
        if(k(loc))
            return ifHit(e, loc);
        return 0;

    }
    private int i26(Player e)
    {
        Location loc;
        if(i == 20)
            loc = lox0;
        else if(i == 21)
            loc = lox1;
        else if(i == 22)
            loc = lox2;
        else if(i == 23)
            loc = lox3;
        else if(i == 24)
            loc = lox4;
        else    // 25
            loc = lox5;
        i++;
        if(k(loc))
            return ifHit(e, loc);
        return 0;
    }
    private int i32(Player e)
    {
        Location loc;
        if(i == 26)
            loc = new Location(9 - lox0.getCol(), lox0.getRow());
        else if(i == 27)
            loc = new Location(9 - lox1.getCol(), lox1.getRow());
        else if(i == 28)
            loc = new Location(9 - lox2.getCol(), lox2.getRow());
        else if(i == 29)
            loc = new Location(9 - lox3.getCol(), lox3.getRow());
        else if(i == 30)
            loc = new Location(9 - lox4.getCol(), lox4.getRow());
        else    // 31
            loc = new Location(9 - lox5.getCol(), lox5.getRow());
        i++;
        if(k(loc))
            return ifHit(e, loc);
        return 0;

    }
    private int i38(Player e)
    {
        Location loc;
        if(i == 32)
            loc = new Location(9 - lox0.getRow(), 9 - lox0.getCol());
        else if(i == 33)
            loc = new Location(9 - lox1.getRow(), 9 - lox1.getCol());
        else if(i == 34)
            loc = new Location(9 - lox2.getRow(), 9 - lox2.getCol());
        else if(i == 35)
            loc = new Location(9 - lox3.getRow(), 9 - lox3.getCol());
        else if(i == 36)
            loc = new Location(9 - lox4.getRow(), 9 - lox4.getCol());
        else    // 37
            loc = new Location(9 - lox5.getRow(), 9 - lox5.getCol());
        i++;
        if(k(loc))
            return ifHit(e, loc);
        return 0;

    }
    private int i44(Player e)
    {
        Location loc;
        if(i == 38)
            loc = new Location(lox0.getCol(), 9 - lox0.getRow());
        else if(i == 39)
            loc = new Location(lox1.getCol(), 9 - lox1.getRow());
        else if(i == 40)
            loc = new Location(lox2.getCol(), 9 - lox2.getRow());
        else if(i == 41)
            loc = new Location(lox3.getCol(), 9 - lox3.getRow());
        else if(i == 42)
            loc = new Location(lox4.getCol(), 9 - lox4.getRow());
        else    // 43
            loc = new Location(lox5.getCol(), 9 - lox5.getRow());
        i++;
        if(k(loc))
            return ifHit(e, loc);
        return 0;

    }
*/
    /**
     * if it doesn't alr have something ther
     * @param loc
     * @return
     */
    private boolean k(Location loc)
    {
        return getGuessBoard()[loc.getRow()][loc.getCol()] == 0;
    }
    private Location locW(Location loc)
    {
        return (new Location(loc.getRow() - 1, loc.getCol()));
    }
    private Location locA(Location loc)
    {
        return (new Location(loc.getRow(), loc.getCol() - 1));
    }
    private Location locS(Location loc)
    {
        return (new Location(loc.getRow() + 1, loc.getCol()));
    }
    private Location locD(Location loc)
    {
        return (new Location(loc.getRow(), loc.getCol() + 1));
    }

    // finds point and attacks.
    private int ifHit(Player enemy, Location loc)
    {
        Ship ship = enemy.getShip(loc);
        if (enemy.hasShipAtLocation(loc))
        {
            ship.takeHit(loc);
            getGuessBoard()
                    [loc.getRow()][loc.getCol()] = 7;

            vir(enemy, loc);
            if(ship.isSunk())
            {
                enemy.removeShip(ship);
//                System.out.println("removed a ship" + ship);
//                System.out.println(getName() + ": " + enemy.getNumberOfShips());



//                if(toHit.size() > 4)        // technically an error BAD BAD BAD
//                    for(int i = 0; i < 4; i++)
//                        toHit.remove(0);
//                else
//                    toHit.clear();
//                if(vir.size() > 4)
//                    for(int i = 0; i < 4; i++)            // scared of removing this
//                        vir.remove(0);
//                else
                    vir.clear();
                return 1;
            }
        }
        else
            getGuessBoard()
                    [loc.getRow()][loc.getCol()] = -8;
        return -1;
    }

    private int flop(Player enemy)
    {
//        if(i == 44)
//            if(k(new Location(9, 7)))       // xslx, check that, specific case
//                return ifHit(enemy, new Location(9, 7));
//            else
//                i++;
//        else if(i == 45)
//            if(k(new Location(2, 9)))
//                return ifHit(enemy, new Location(2, 9));
//            else
//                i++;
        int row = (int) (Math.random() * 10);        // excludes bottom row,         // could be 10
        int col = (int) (Math.random() * 10);       // excludes rightmost column
        while(!k(new Location(row, col)) && hikari(enemy, new Location(row, col)))
        {
            row = (int) (Math.random() * 10);
            col = (int) (Math.random() * 10);
        }
        return ifHit(enemy, new Location(row, col));
    }
    private boolean hikari(Player enemy, Location loc) // is this like a chess reference or smtg?
    {       // btw, it's RinHara5aki

        return true;
        // if right and bottom are empty, then proceed!
//        return k(new Location(loc.getRow() + 1, loc.getCol())) ||
//                k(new Location(loc.getRow(), loc.getCol() + 1));
    }




    public void file(Ship ship, int row, int col, String direction, int len)
    {
        Location loc = new Location(row, col);
        Location[] locs = new Location[len];
        if(direction.equals("up"))
        {
            for(int i = 0; i < len; i++)
                locs[i] = new Location(loc.getRow() - i, loc.getCol());
        }
        else if(direction.equals("right"))
        {
            for(int i = 0; i < len; i++)
                locs[i] = new Location(loc.getRow(), loc.getCol() + i);
        }
        else if(direction.equals("down"))
        {
            for(int i = 0; i < len; i++)
            {
                locs[i] = new Location(loc.getRow() + i, loc.getCol());
            }
        }
        else // left
        {
            for(int i = 0; i < len; i++)
                locs[i] = new Location(loc.getRow(), loc.getCol() - i);
        }
        ship.addLocation(locs);
        addShip(ship);
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
//        int choicebetweenlifeanddeath = (int) (Math.random() * 4);
//        if(choicebetweenlifeanddeath == 0)
//            setup0();
//        else if(choicebetweenlifeanddeath == 1)
//            setup1();
//        else if(choicebetweenlifeanddeath == 2)
//            setup2();
//        else // 3
//            setup3();

//
        Ship[] ships = setuprd();
        for(Ship ship : ships)
        {
//            System.out.println(ship.getClass());
//            for(Location loc : ship.getLocations())
//                System.out.println("Row: " + loc.getRow() + "     Col: " + loc.getCol());
            addShip(ship);
        }

    }
    public void setup0() // 45 no more (still not great tho)
    {
        AircraftCarrier air = new AircraftCarrier();
        Destroyer des = new Destroyer();
        Submarine sub = new Submarine();
        Cruiser cru = new Cruiser();
        PatrolBoat pat = new PatrolBoat();

        file(air, 1, 8, "left", 5);
        file(des, 3, 1, "right", 4);
        file(sub, 3, 6, "right", 3);
        file(cru, 7, 8, "up", 3);
        file(pat, 8, 2, "right", 2);

    }
    public void setup1()
    {
        AircraftCarrier air = new AircraftCarrier();
        Destroyer des = new Destroyer();
        Submarine sub = new Submarine();
        Cruiser cru = new Cruiser();
        PatrolBoat pat = new PatrolBoat();

        file(air, 1, 3, "right", 5);
        file(des, 3, 2, "down", 4);
        file(sub, 4, 6, "right", 3);
        file(cru, 6, 9, "down", 3);
        file(pat, 8, 6, "right", 2);



    }
    public void setup2()
    {
        AircraftCarrier air = new AircraftCarrier();
        Destroyer des = new Destroyer();
        Submarine sub = new Submarine();
        Cruiser cru = new Cruiser();
        PatrolBoat pat = new PatrolBoat();

        file(air, 9, 0, "right", 5);
        file(des, 3, 8, "down", 4);
        file(sub, 3, 4, "right", 3);
        file(cru, 0, 1, "down", 3);
        file(pat, 1, 6, "right", 2);


    }
    public void setup3()
    {
        AircraftCarrier air = new AircraftCarrier();
        Destroyer des = new Destroyer();
        Submarine sub = new Submarine();
        Cruiser cru = new Cruiser();
        PatrolBoat pat = new PatrolBoat();

        file(air, 2, 1, "down", 5);
        file(des, 8, 3, "right", 4);
        file(sub, 3, 3, "right", 3);
        file(cru, 5, 9, "down", 3);
        file(pat, 1, 6, "right", 2);


    }

    public Ship[] setuprd() {
        int[] lengths = {5, 4, 3, 3, 2};
        Ship[] ships = {
                new AircraftCarrier(),
                new Destroyer(),
                new Submarine(),
                new Cruiser(),
                new PatrolBoat()
        };

        for (int i = 0; i < 5; i++) {
            Location start = new Location((int) (Math.random() * 10), (int) (Math.random() * 10));
            int length = lengths[i];
            boolean isHorizontal = Math.random() > 0.5;

            while (!isValidShip(ships, start, length, isHorizontal)) {
                start = new Location((int) (Math.random() * 10), (int) (Math.random() * 10));
                length = lengths[i];
                isHorizontal = Math.random() > 0.5;
            }

            Location[] locations = generateLocations(start, length, isHorizontal);

            ships[i].addLocation(locations);
        }

        return ships;
    }

    private boolean isValidShip(Ship[] ships, Location start, int length, boolean isHorizontal) {
        Location[] locations = generateLocations(start, length, isHorizontal);

        for (Location location : locations) {
            int row = location.getRow();
            int col = location.getCol();

            if (row < 0 || row > 9 || col < 0 || col > 9)
                return false;

            for (Ship ship : ships)
                if (ship.getLocations().contains(location))
                    return false;
        }

        return true;
    }

    private Location[] generateLocations(Location start, int length, boolean isHorizontal) {
        int dr = isHorizontal ? 0 : 1;
        int dc = isHorizontal ? 1 : 0;

        Location[] locations = new Location[length];

        for (int i = 0; i < length; i++) {
            locations[i] = new Location(
                    start.getRow() + dr * i,
                    start.getCol() + dc * i
            );
        }

        return locations;
    }
/*
    public void setuprd()
    {
        AircraftCarrier air = new AircraftCarrier();
        Destroyer des = new Destroyer();
        Submarine sub = new Submarine();
        Cruiser cru = new Cruiser();
        PatrolBoat pat = new PatrolBoat();

        ArrayList<Location[]> loxs = new ArrayList<>();
        Location[] size5 = new Location[]{null, null, null, null, null};
        Location[] size4 = new Location[]{null, null, null, null};
        Location[] size3 = new Location[]{null, null, null};
        Location[] size2 = new Location[]{null, null};
        loxs.add(size5);
        loxs.add(size4);
        loxs.add(size3);
        loxs.add(size3);
        loxs.add(size2);
        ArrayList<Location> allLocs = new ArrayList<>();

        // 0 = air, 1 = des, 2 = sub, 3 = cru, 4 = pat
        {
            Location[] airLocs = {new Location(0, 3), new Location(0, 4), new Location(0, 5), new Location(0, 6), new Location(0, 7)};
            Location[] desLocs = {new Location(1, 3), new Location(1, 4), new Location(1, 5), new Location(1, 6)};
            Location[] subLocs = {new Location(2, 3), new Location(2, 4), new Location(2, 5)};
            Location[] cruLocs = {new Location(3, 3), new Location(3, 4), new Location(3, 5)};
            Location[] patLocs = {new Location(4, 3), new Location(4, 4)};

            loxs.add(airLocs); loxs.add(desLocs); loxs.add(subLocs); loxs.add(cruLocs); loxs.add(patLocs);
        }

        for(int i = 0; i < 5; i++)
        {
            int ParamR = 10; int ParamD = 10;
            boolean dir = !(Math.random() * 2 > .5); // true is down, false is right
            if(dir)
                ParamR = 10 - loxs.get(i).length;
            else    // 1
                ParamD = 10 - loxs.get(i).length;
//            System.out.println("loxs length: " + loxs.get(i).length);
            boolean okie = false;
            int row;
            int col;
            while(!okie)
            {
                boolean dupli = false;
                row = (int) (Math.random() * ParamD);
                col = (int) (Math.random() * ParamR);
                ArrayList<Location> locs = new ArrayList<>();
                for(int j = 0; j < loxs.get(i).length; j++)     // filler into locs
                {
                    if(dir)
                        for(int k = 0; k < loxs.get(i).length; k++)
                            locs.add(new Location(row + k, col));
                    else
                        for(int k = 0; k < loxs.get(i).length; k++)
                            locs.add(new Location(row, col + k));
                }
                for(int j = 0; j < locs.size(); j++)
                    if(locs.get(j).getRow() < 10 && locs.get(j).getCol() < 10)
                        for(Location loc : allLocs)
                            if(locs.get(j).equals(loc))
                            {
                                dupli = true;
                            }
                if(!dupli)
                {
                    for(Location loc : locs)
                    {
                        allLocs.add(loc);
                    }
                    for(int j = 0; j < loxs.get(i).length; j++)
                    {
                        loxs.get(i)[j] = locs.get(j);
//                        System.out.println(loxs.get(i)[j].getRow() + " " + loxs.get(i)[j].getCol());
                    }
                    okie = true;
                }
                else    // dupli = true!
                {
                    locs.clear();
                }

            }
        }
//        AircraftCarrier air = new AircraftCarrier();
//        Destroyer des = new Destroyer();
//        Submarine sub = new Submarine();
//        Cruiser cru = new Cruiser();
//        PatrolBoat pat = new PatrolBoat();
        air.addLocation(loxs.get(0));
        des.addLocation(loxs.get(1));
        sub.addLocation(loxs.get(2));
        cru.addLocation(loxs.get(3));
        pat.addLocation(loxs.get(4));
        for(Location[] array : loxs)
        {
            System.out.println(getClass());
            for(Location loc : array)
                System.out.println("Row: " + loc.getRow() + "     Col: " + loc.getCol());
        }
        addShip(air);
        addShip(des);
        addShip(sub);
        addShip(cru);
        addShip(pat);
    }

 */
}
