package pl.qaaacademy.simple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SimpleJunitTest {

    @Test
    //create meaningful names of test methods
    public void addingTwoArgumentsProducesItsSum() {
        Calculator calc = new Calculator();
        int expectedResult = 5;
        int actualResult = calc.add(2,3);
        assertEquals(actualResult,expectedResult);
    }
}
