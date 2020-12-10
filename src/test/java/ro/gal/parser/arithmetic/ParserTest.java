package ro.gal.parser.arithmetic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    @Test
    public void addTwoNumbers() {
        assertEquals(3, compute("1+2"));
    }

    @Test
    public void multiplyTwoNumbers() {
        assertEquals(20, compute("10*2"));
    }

    @Test
    public void oneParen() {
        assertEquals(50, compute("10*(2+3)"));
    }

    @Test
    public void multiplicationOfSerialExpressions() {
        assertEquals(-60, compute("(1-2)*(10+2)*(2+3)"));
    }

    @Test
    public void nestedParens() {
        assertEquals(-21, compute("(1-2*(10+2))+2"));
    }


    private int compute(String expression) {
        return new Parser(new Tokenizer(expression)).compute();
    }
}