//Yuan Yu and Dev Sethi

package battleship;
  
import static org.junit.Assert.*;
  
import org.junit.Before;
import org.junit.Test;
  
public class ShipTest {
    private Submarine submarine;
    private Destroyer destroyer;
    private Cruiser cruiser;
    private Battleship battleship;
    private Ocean ocean;
  
    @Before
    public void setUp() throws Exception {
        ocean = new Ocean();
        submarine = new Submarine();
        submarine.setBowColumn(3);
        submarine.setBowRow(2);
        submarine.setHorizontal(false);
        destroyer = new Destroyer();
        cruiser = new Cruiser();
        battleship = new Battleship();
    }
  
    @Test
    public void testGetLength() {
        assertEquals(1, submarine.length);
        assertEquals(2, destroyer.length);
        assertEquals(3, cruiser.length);
        assertEquals(4, battleship.length);
    }
  
    @Test
    public void testGetBowRow() {
        assertEquals(2, submarine.getBowRow());
    }
  
    @Test
    public void testGetBowColumn() {
        assertEquals(3, submarine.getBowColumn());
    }
  
    @Test
    public void testIsHorizontal() {
        assertEquals(false, submarine.isHorizontal());
    }
  
    @Test
    public void testGetShipType() {
        assertEquals("submarine", submarine.getShipType());
        assertEquals("destroyer", destroyer.getShipType());
        assertEquals("cruiser", cruiser.getShipType());
        assertEquals("battleship", battleship.getShipType());
    }
  
    @Test
    public void testOkToPlaceShipAt() {
        destroyer.placeShipAt(2, 2, false, ocean);
        assertEquals(false, submarine.okToPlaceShipAt(3, 2, true, ocean));
    }
  
    @Test
    public void testPlaceShipAt() {
        submarine.placeShipAt(5, 5, false, ocean);
        assertEquals("submarine", ocean.ships[5][5].getShipType());
        cruiser.placeShipAt(2, 1, true, ocean);
        assertEquals("cruiser", ocean.ships[2][2].getShipType());
    }
  
    @Test
    public void testShootAt() {
        submarine.placeShipAt(2, 2, false, ocean);
        assertEquals(false, submarine.shootAt(2,2));
        battleship.placeShipAt(1, 1, true, ocean);
        assertEquals(true, battleship.shootAt(1, 3));
    }
      
    @Test
    public void testIsSunk() {
        submarine.shootAt(2, 3);
        assertEquals(true, submarine.isSunk());
    }
  
    @Test
    public void testToString() {
        submarine.placeShipAt(0, 0, false, ocean);
        ocean.shootAt(0, 0);
        assertEquals("x", ocean.ships[0][0].toString());
    }
} 