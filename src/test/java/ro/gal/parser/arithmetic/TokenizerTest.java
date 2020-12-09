package ro.gal.parser.arithmetic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class TokenizerTest {

    @Test
    public void digit() {
        assertEquals(asList("1"), tokenize("1"));
    }

    @Test
    public void number() {
        assertEquals(asList("123"), tokenize("123"));
    }

    @Test
    public void addTwoDigits() {
        assertEquals(asList("1", "+", "2"), tokenize("1+2"));
    }

    @Test
    public void addTwoNumbers() {
        assertEquals(asList("1", "+", "223"), tokenize("1+223"));
    }

    @Test
    public void divideTwoNumbers() {
        assertEquals(asList("12345", "/", "223"), tokenize("12345/223"));
    }

    @Test
    public void parens() {
        assertEquals(asList("(", "1", "*", "2", ")", "+", "3"), tokenize("(1*2)+3"));
    }

    @Test(expected = RuntimeException.class)
    public void invalidChar() {
        Tokenizer tokenizer = new Tokenizer("12345c223");
        tokenizer.advance();
        tokenizer.advance();
    }

    private List<String> tokenize(String str) {
        ArrayList<String> tokens = new ArrayList<>();
        Tokenizer tokenizer = new Tokenizer(str);
        while (tokenizer.hasMoreTokens()) {
            tokenizer.advance();
            tokens.add(tokenizer.getToken());
        }

        return tokens;
    }
}
