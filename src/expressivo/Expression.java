package expressivo;

import java.io.File;
import java.io.IOException;

import lib6005.parser.*;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * <p>PS1 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {
    
    // Datatype definition
    //    IntegerExpression = Number(n:int)
    //            + Variable(s:string)
    //            + Plus(left:IntegerExpression, right:IntegerExpression)
    //            + Mult(left:IntegerExpression, right:IntegerExpression)

    enum ExpressionGrammar {ROOT, SUM, MUL, PRIMITIVE, NUMBER, WHITESPACE, FACTOR, VARIABLE};

    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS1 handout.
     * @return expression AST for the input
     * @throws IOException 
     * @throws UnableToParseException 
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) throws UnableToParseException, IOException {
        Parser<ExpressionGrammar> parser =
                GrammarCompiler.compile(new File("src/expressivo/Expression.g"), ExpressionGrammar.ROOT);
        ParseTree<ExpressionGrammar> tree = parser.parse(input);
        System.out.println(tree.toString());
        // tree.display();
        return Expression.buildAST(tree);
    }

    /**
     * Function converts a ParseTree to an IntegerExpression.
     * @param p
     *  ParseTree<IntegerGrammar> that is assumed to have been constructed by the grammar in IntegerExpression.g
     * @return
     */
    public static Expression buildAST(ParseTree<ExpressionGrammar> p){

        switch(p.getName()){
        /*
         * Since p is a ParseTree parameterized by the type IntegerGrammar, p.getName()
         * returns an instance of the IntegerGrammar enum. This allows the compiler to check
         * that we have covered all the cases.
         */
            case NUMBER:
            /*
             * A number will be a terminal containing a number.
             */
                return new Number(Double.parseDouble(p.getContents()));
            case VARIABLE:
            /*
             * A number will be a terminal containing a number.
             */
                return new Variable(p.getContents());
            case PRIMITIVE:
            case FACTOR:

            /*
             * A primitive will have either a number or a sum as child (in addition to some whitespace)
             * By checking which one, we can determine which case we are in.
             */

                if(!p.childrenByName(ExpressionGrammar.NUMBER).isEmpty()){
                    return buildAST(p.childrenByName(ExpressionGrammar.NUMBER).get(0));
                }else if(!p.childrenByName(ExpressionGrammar.VARIABLE).isEmpty()){
                    return buildAST(p.childrenByName(ExpressionGrammar.VARIABLE).get(0));
                }else if(p.childrenByName(ExpressionGrammar.SUM).isEmpty()){
                    return buildAST(p.childrenByName(ExpressionGrammar.MUL).get(0));
                }else{
                    return buildAST(p.childrenByName(ExpressionGrammar.SUM).get(0));
                }

            case SUM:
            /*
             * A sum will have one or more children that need to be summed together.
             * Note that we only care about the children that are primitive. There may also be
             * some whitespace children which we want to ignore.
             */
                boolean first = true;
                Expression result = null;
                for(ParseTree<ExpressionGrammar> child : p.childrenByName(ExpressionGrammar.PRIMITIVE)){
                    if(first){
                        result = Expression.buildAST(child);
                        first = false;
                    }else{
                        result = new Plus(result, Expression.buildAST(child));
                    }
                }
                if (first) {
                    throw new RuntimeException("sum must have a non whitespace child:" + p);
                }
                return result;
            case MUL:
            /*
             * A sum will have one or more children that need to be summed together.
             * Note that we only care about the children that are primitive. There may also be
             * some whitespace children which we want to ignore.
             */
                boolean firstMul = true;
                Expression resultMul = null;
                for(ParseTree<ExpressionGrammar> child : p.childrenByName(ExpressionGrammar.FACTOR)){
                    if(firstMul){
                        resultMul = Expression.buildAST(child);
                        firstMul = false;
                    }else{
                        resultMul = new Mult(resultMul, Expression.buildAST(child));
                    }
                }
                if (firstMul) {
                    throw new RuntimeException("sum must have a non whitespace child:" + p);
                }
                return resultMul;
            case ROOT:
            /*
             * The root has a single sum or mul child, in addition to having potentially some whitespace.
             */

                return buildAST(p.childrenByName(ExpressionGrammar.SUM).get(0));

            case WHITESPACE:
            /*
             * Since we are always avoiding calling buildAST with whitespace,
             * the code should never make it here.
             */
                throw new RuntimeException("You should never reach here white:" + p);
        }
        /*
         * The compiler should be smart enough to tell that this code is unreachable, but it isn't.
         */
        throw new RuntimeException("You should never reach here:" + p);
    }

    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     * Doesn't include any spaces and only shows decimal point if not integer
     */
    @Override 
    public String toString();

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS1 handout.
     */
    @Override
    public boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();
    
    // TODO more instance methods

    public Expression differentiate (Variable var);


    /* Copyright (c) 2015-2017 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires permission of course staff.
     */
}
