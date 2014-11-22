//Dev Sethi and Ce Wang
//CIT 590 

package calculator; 
  
import static org.junit.Assert.assertEquals; 
import org.junit.Before; 
import org.junit.Test; 
  
public class CalculatorChipTest { 
  
    CalculatorChip chip; 
    Double[] tester = { -2., -.5, 0., .5, 2. }; 
  
    @Before
    public void setUp() throws Exception { 
        chip = new CalculatorChip(); 
        chip.first = 2.5; 
        chip.second = -0.74; 
        chip.memoryFirst = 29.43; 
        chip.decCount = 1; 
        chip.memAlternative = 34.; 
  
    } 
  
    public void reset() { 
        chip.first = 2.5; 
        chip.second = -0.74; 
        chip.memoryFirst = 29.43; 
        chip.decCount = 1; 
        chip.memAlternative = 34.; 
    } 
  
    @Test
    public void testCalculatorChip() { 
        // Nothing to test 
    } 
  
    @Test
    public void testClearAll() { 
        chip.clearAll(); 
        assertEquals(chip.first, (Double) 0.); 
        assertEquals(chip.second, (Double) 0.); 
    } 
  
    @Test
    public void testClear() { 
        chip.clear(); 
        assertEquals(chip.first, (Double) (2.5)); 
        assertEquals(chip.second, (Double) 0.); 
    } 
  
    @Test
    public void testMemClear() { 
        chip.memClear(); 
        assertEquals(chip.memoryFirst, (Double) 0.); 
    } 
  
    @Test
    public void testMemRead() { 
        // no test needed 
    } 
  
    @Test
    public void testMemPlus() { 
        reset(); 
        chip.memPlus(); // memAlternative != 0 so memFirst = memAlternative += 
                        // second; 
        assertEquals(chip.memRead(), Double.toString(63.43)); 
        reset(); 
        chip.memAlternative = 0.; 
        chip.memPlus(); // memoryFirst += second (29.43 += -0.74 = 28.69) 
        assertEquals(chip.memRead(), Double.toString(28.69)); 
    } 
  
    @Test
    public void testMemMinus() { 
        reset(); 
        chip.memMinus(); // memAlternative != 0 so memFirst = memAlternative -= 
                            // second; 
        assertEquals(chip.memRead(), Double.toString(-4.57)); 
        reset(); 
        chip.memAlternative = 0.; 
        chip.memMinus(); 
        assertEquals(chip.memoryFirst, 30.17, .0001); // can't use memRead 
                                                        // because of 
                                                        // .toString() 
  
    } 
  
    @Test
    public void testDigit() { 
        chip.second = 0.; 
        chip.digit(2); 
        assertEquals(chip.second, (Double) 2.); 
        chip.digit(0); 
        assertEquals(chip.second, (Double) 20.); 
        chip.digit(8); 
        assertEquals(chip.second, (Double) 208.); 
        chip.decimalPoint(); 
        chip.digit(4); 
        assertEquals(chip.second, (Double) 208.4); 
        chip.changeSign(); 
        assertEquals(chip.second, (Double) (-208.4)); 
        chip.digit(1); 
        assertEquals(chip.second, (Double) (-208.41)); 
    } 
  
    @Test
    public void testDecimalPoint() { 
        // Tested implicitly in testDigit() 
    } 
  
    @Test
    public void testAdd() { 
        for (int i = 0; i < 5; i++) { 
            for (int j = 0; j < 5; j++) { 
                chip.first = tester[i]; 
                chip.second = tester[j]; 
                chip.firstDone = true; 
                chip.operator = "+"; 
                assertEquals(chip.add(), Double.toString(tester[i] + tester[j])); 
                chip.clearAll(); 
            } 
        } 
  
    } 
  
    @Test
    public void testSubtract() { 
        for (int i = 0; i < 5; i++) { 
            for (int j = 0; j < 5; j++) { 
                chip.first = tester[i]; 
                chip.second = tester[j]; 
                chip.firstDone = true; 
                chip.operator = "-"; 
                assertEquals(chip.subtract(), 
                        Double.toString(tester[i] - tester[j])); 
                chip.clearAll(); 
            } 
        } 
    } 
  
    @Test
    public void testMultiply() { 
        for (int i = 0; i < 5; i++) { 
            for (int j = 0; j < 5; j++) { 
                chip.first = tester[i]; 
                chip.second = tester[j]; 
                chip.firstDone = true; 
                chip.operator = "x"; 
                assertEquals(chip.multiply(), 
                        Double.toString(tester[i] * tester[j])); 
                chip.clearAll(); 
            } 
        } 
    } 
  
    @Test
    public void testDivide() { 
        for (int i = 0; i < 5; i++) { 
            for (int j = 0; j < 5; j++) { 
                chip.first = tester[i]; 
                chip.second = tester[j]; 
                chip.firstDone = true; 
                chip.operator = "/"; 
                if (tester[j] != 0) { 
                    assertEquals(chip.divide(), 
                            Double.toString(tester[i] / tester[j])); 
                } else { 
                    assertEquals(chip.divide(), "Error"); 
                } 
                chip.clearAll(); 
            } 
        } 
    } 
  
    @Test
    public void testEquals() { 
        for (int i = 0; i < 5; i++) { 
            for (int j = 0; j < 5; j++) { 
                chip.clearAll(); 
                chip.second = tester[i]; 
                chip.add(); 
                chip.second = tester[j]; 
                chip.digitFlag = true; 
                assertEquals(chip.equals(), 
                        Double.toString(tester[i] + tester[j])); 
                chip.clearAll(); 
  
                chip.second = tester[i]; 
                chip.add(); 
                assertEquals(chip.equals(), Double.toString(tester[i] * 2)); 
                assertEquals(chip.equals(), Double.toString(tester[i] * 3)); 
                assertEquals(chip.equals(), Double.toString(tester[i] * 4)); 
                chip.clearAll(); 
  
                chip.second = tester[i]; 
                chip.subtract(); 
                chip.second = tester[j]; 
                chip.digitFlag = true; 
                assertEquals(chip.equals(), 
                        Double.toString(tester[i] - tester[j])); 
                chip.clearAll(); 
  
                chip.second = tester[i]; 
                chip.subtract(); 
                assertEquals(chip.equals(), 
                        Double.toString(tester[i] - (tester[i]) * 1)); 
                assertEquals(chip.equals(), 
                        Double.toString(tester[i] - (tester[i]) * 2)); 
                assertEquals(chip.equals(), 
                        Double.toString(tester[i] - (tester[i]) * 3)); 
                chip.clearAll(); 
  
                chip.second = tester[i]; 
                chip.multiply(); 
                chip.second = tester[j]; 
                chip.digitFlag = true; 
                assertEquals(chip.equals(), 
                        Double.toString(tester[i] * tester[j])); 
                chip.clearAll(); 
                  
                chip.second = tester[i]; 
                chip.multiply(); 
                assertEquals(chip.equals(), 
                        Double.toString(tester[i] * tester[i])); 
                assertEquals(chip.equals(), 
                        Double.toString(tester[i] * Math.pow(tester[i], 2))); 
                assertEquals(chip.equals(), 
                        Double.toString(tester[i] * Math.pow(tester[i], 3))); 
                chip.clearAll(); 
  
                chip.second = tester[i]; 
                chip.divide(); 
                chip.second = tester[j]; 
                chip.digitFlag = true; 
                if (tester[j] != 0) { 
                    assertEquals(chip.equals(), 
                            Double.toString(tester[i] / tester[j])); 
                } else { 
                    assertEquals(chip.equals(), "Error"); 
                } 
                chip.clearAll(); 
                  
                chip.second = tester[i]; 
                if (tester[i] != 0.){ 
                chip.divide(); 
                assertEquals(chip.equals(), 
                        Double.toString(tester[i] / tester[i])); 
                assertEquals(chip.equals(), 
                        Double.toString(Math.pow(tester[i], -1))); 
                assertEquals(chip.equals(), 
                        Double.toString(Math.pow(tester[i], -2))); 
                }else{ 
                    chip.divide(); 
                    assertEquals(chip.equals(),"Error"); 
                } 
                chip.clearAll(); 
            } 
        } 
    } 
  
    @Test
    public void testSwitchOp() { 
        // Tested implicitly in the operation methods 
    } 
  
    @Test
    public void testSqrt() { 
        chip.clearAll(); 
        chip.second = -.74; 
        assertEquals(chip.sqrt(), "Error"); // second = -0.74 < 0 
        chip.second = 81.; 
        assertEquals(chip.sqrt(), Double.toString(9.)); 
        chip.second = 6.25; 
        assertEquals(chip.sqrt(), Double.toString(2.5)); 
        chip.second = 0.25; 
        assertEquals(chip.sqrt(), Double.toString(.5)); 
        chip.second = 0.; 
        assertEquals(chip.sqrt(), Double.toString(0.)); 
        chip.second = 9.; 
        chip.add(); 
        assertEquals(chip.sqrt(), Double.toString(3.)); 
  
    } 
  
    @Test
    public void testPercent() { 
        chip.clearAll(); 
        chip.second = -.74; 
        assertEquals(chip.percent(), Double.toString(-0.0074)); 
        for (int i = 0; i < 5; i++) { 
            chip.second = tester[i]; 
            assertEquals(chip.percent(), Double.toString(tester[i] * .01)); 
        } 
        chip.second = 9.; 
        chip.add(); 
        assertEquals(chip.percent(), Double.toString(.09)); 
    } 
  
    @Test
    public void testInvert() { 
        chip.clearAll(); 
        chip.second = -.74; 
        assertEquals(chip.invert(), Double.toString(-50. / 37.)); 
        for (int i = 0; i < 5; i++) { 
            chip.second = tester[i]; 
            if (tester[i] != 0) { 
                assertEquals(chip.invert(), Double.toString(1. / tester[i])); 
            } else { 
                assertEquals(chip.invert(), "Error"); 
            } 
        } 
        chip.second = 9.; 
        chip.add(); 
        assertEquals(chip.invert(), Double.toString(1. / 9.)); 
    } 
  
    @Test
    public void testChangeSign() { 
        // tested implicitly in testDigit 
    } 
  
    @Test
    public void testGetText() { 
        // Tested implicitly in GUI 
    } 
  
} 