/* Copyright (c) 2015-2017 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }




    // Creator
//            ExpressionNumber: Receives a number
//            ExpressionVariable: Receives an str
//            ExpressionPlus: Receives an str
//            ExpressionMult: Receives an str
//            The only creator I need is the parser

    // Producer
//            ExpressionPlus: Receives two Expressions
//            ExpressionMult: Receives two Expressions
    // Parser
        // an int number
        // a decimal number
        // an invalid decimal number with spaces
        // a Variable
        // Plus two numbers 5 + 4 with spaces, without spaces...
        // Mult two numbers 5* 4
        // Plus with variables ....

    // To string  (sames as parser)
    // Equals
    // Hashcode
    // plus
        // an ExpressionNumber + VariableNumber
        // ...
    // mult
    
    // TODO tests for Expression
    
}
