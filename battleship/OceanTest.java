//Yuan Yu and Dev Sethi

package battleship;
  
import static org.junit.Assert.*;
  
import org.junit.Before;
import org.junit.Test;
  
public class OceanTest {
    Ocean ocean;
    Submarine submarine;
  
    @Before
    public void setUp() throws Exception {
        ocean = new Ocean();
        submarine = new Submarine();
        submarine.placeShipAt(2, 2, true, ocean);
    }
  
    @Test
    public void testIsOccupied() {
        assertEquals(false, ocean.isOccupied(0, 0));
        assertEquals(true, ocean.isOccupied(2, 2));
    }
  
    @Test
    public void testShootAt() {
        assertEquals(false, ocean.shootAt(2, 1));
        assertEquals(true, ocean.shootAt(2, 2));
        ocean.shootAt(2, 2);
        assertEquals(false, ocean.shootAt(2, 2));
    }
  
    @Test
    public void testGetShotsFired() {
        ocean.shootAt(0, 1);
        ocean.shootAt(1, 2);
        assertEquals(2, ocean.getShotsFired());
    }
  
    @Test
    public void testGetHitCount() {
        ocean.shootAt(0, 1);
        ocean.shootAt(1, 2);
        ocean.shootAt(2, 2);
        assertEquals(1, ocean.getHitCount());
        ocean.shootAt(2, 2);
        assertEquals(2, ocean.getHitCount());
    }
  
    @Test
    public void testGetShipsSunk() {
        ocean.shootAt(0, 1);
        ocean.shootAt(1, 2);
        assertEquals(0, ocean.getShipsSunk());
        ocean.shootAt(2, 2);
        assertEquals(1, ocean.getShipsSunk());
    }
  
    @Test
    public void testIsGameOver() {
        Submarine s1 = new Submarine();
        Submarine s2 = new Submarine();
        Submarine s3 = new Submarine();
        Submarine s4 = new Submarine();
        Submarine s5 = new Submarine();
        Submarine s6 = new Submarine();
        Submarine s7 = new Submarine();
        Submarine s8 = new Submarine();
        Submarine s9 = new Submarine();
        s1.placeShipAt(1, 4, true, ocean);
        s2.placeShipAt(1, 6, true, ocean);
        s3.placeShipAt(1, 8, true, ocean);
        s4.placeShipAt(3, 6, true, ocean);
        s5.placeShipAt(5, 7, true, ocean);
        s6.placeShipAt(9, 0, true, ocean);
        s7.placeShipAt(9, 5, true, ocean);
        s8.placeShipAt(4, 1, true, ocean);
        s9.placeShipAt(3, 8, true, ocean);
        assertEquals(false, ocean.isGameOver());
        ocean.shootAt(4, 1);
        ocean.shootAt(3, 8);
        ocean.shootAt(1, 4);
        ocean.shootAt(1, 6);
        ocean.shootAt(1, 8);
        ocean.shootAt(2, 2);
        ocean.shootAt(3, 6);
        ocean.shootAt(5, 7);
        ocean.shootAt(9, 0);
        ocean.shootAt(9, 5);
        assertEquals(true, ocean.isGameOver());
    }
  
    @Test
    public void testGetShipArray() {
        Ship[][] ships = new Ship[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ships[i][j] = new EmptySea();
            }
        }
        ships.equals(ocean.getShipArray());
    }
} 