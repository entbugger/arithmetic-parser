package ro.gal.parser.arithmetic;

public class Parser {

    private final Tokenizer t;

    public Parser(Tokenizer tokenizer) {
        this.t = tokenizer;
    }

    public int compute() {
        t.advance();
        return compileExpression();
    }

    private int compileExpression() {
        int res = compileMul();
        while (t.hasMoreTokens()) {
            if ("+".equals(t.getToken())) {
                t.advance();
                res += compileMul();
            } else if ("-".equals(t.getToken())) {
                t.advance();
                res -= compileMul();
            } else {
                break;
            }
        }
        return res;
    }

    private int compileMul() {
        int res = compileTerm();
        while (t.hasMoreTokens()) {
            if ("*".equals(t.getToken())) {
                t.advance();
                res *= compileTerm();
            } else if ("/".equals(t.getToken())) {
                t.advance();
                res /= compileTerm();
            } else {
                break;
            }
        }
        return res;
    }

    private int compileTerm() {
        int res;
        if ("(".equals(t.getToken())) {
            t.advance();
            res = compileExpression();
            if (")".equals(t.getToken())) {
                t.advance();
            } else {
                throw new RuntimeException("Unexpected token: " + t.getToken());
            }
        } else {
            res = Integer.valueOf(t.getToken());
            t.advance();
        }
        return res;
    }

}
