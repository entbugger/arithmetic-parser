package ro.gal.parser.arithmetic;

import static java.lang.Character.isDigit;

public class Tokenizer {

    private final String str;
    private int pos;//unprocessed position

    public Tokenizer(String str) {
        this.str = str;
    }

    public String nextToken() {
        String token = "";

        while (pos<str.length()) {
            char c = str.charAt(pos);
            if (isNewToken(token, c)) {
                return token;
            }

            if (c=='(' || c==')' || c=='+' || c=='-' || c=='/' || c=='*') {
                pos++;
                return "" + c;
            }
            if (isDigit(c)) {
                token += c;
                pos++;
            } else {
                throw new RuntimeException("Wrong char at pos " + pos);
            }
        }

        if (token.isEmpty()) {
            return null;
        } else {
            return token;
        }
    }

    private boolean isNewToken(String token, char c) {
        if (token.isEmpty()) {
            return false;
        }
        if (isDigit(token.charAt(token.length()-1)) && !isDigit(c)) {
            return true;
        } else {
            return false;
        }
    }
}
