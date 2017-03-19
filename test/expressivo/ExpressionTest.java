/* Copyright (c) 2015-2017 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;

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

    // Number
        // Error with negative
        // integer
        // 0
        // decimal

    @Test
    public void testCreatesNumFromInteger() {
        Number testNumber = new Number(34);
        assertThat(testNumber, instanceOf(Number.class));
    }
    
    @Test(expected=AssertionError.class)
    public void testErrorNumWithNegativeNumber() {
        Number testNumber = new Number(-34);
    }    

    @Test
    public void testCreatesNumFromDecimal() {
        Number testNumber = new Number(34.456);
        assertThat(testNumber, instanceOf(Number.class));
    }
    
    @Test
    public void testNumToStringDecimal() {
        Number testNumber = new Number(34.987);
        assertEquals("Number to string is not what expected", "34.987", testNumber.toString());
    }

    @Test
    public void testNumToStringInt() {
        Number testNumber = new Number(34);
        assertEquals("Number to string is not what expected", "34", testNumber.toString());
    }

    @Test
    public void testNumbersAreEqual() {
        Number testNumber1 = new Number(34);
        Number testNumber2 = new Number(34);

        assertEquals("", testNumber1, testNumber2);
    }

    @Test
    public void testNumbersAreEqualWithDecimals() {
        Number testNumber1 = new Number(34);
        Number testNumber2 = new Number(34.0000);

        assertEquals("", testNumber1, testNumber2);
    }

    @Test
    public void tesNumbersArenotEqual() {
        Number testNumber1 = new Number(34);
        Number testNumber2 = new Number(34.1);

        assertNotEquals("", testNumber1, testNumber2);    
    }


    // Variable
    // Error with names with non letters


    @Test
    public void testCreatesVarFromString() {
        Variable testVariable = new Variable("cata");
        assertThat(testVariable, instanceOf(Variable.class));
    }

    @Test(expected=AssertionError.class)
    public void testErrorVarWithNonLetters() {
        Variable testVariable = new Variable("cata ");
    }

    @Test(expected=AssertionError.class)
    public void testErrorVarWithEmpty() {
        Variable testVariable = new Variable("");
    }

    @Test
    public void testVarToString() {
        Variable testVariable = new Variable("cata");
        assertEquals("Var to string is not what expected", "cata", testVariable.toString());
    }

    @Test
    public void testVarToStringCases() {
        Variable testVariable = new Variable("cataDe");
        assertEquals("Var to string is not what expected", "cataDe", testVariable.toString());
    }
    
    @Test
    public void testVarHashCode() {
        Variable testVariable1 = new Variable("cataDe");
        Variable testVariable2 = new Variable("cataDe");

        assertEquals("", testVariable1.hashCode(), testVariable2.hashCode());
    }

    @Test
    public void testVarAreEqual() {
        Variable testVariable1 = new Variable("cataDe");
        Variable testVariable2 = new Variable("cataDe");

        assertEquals("", testVariable1, testVariable2);
    }

    @Test
    public void testVarAreNotEqual() {
        Variable testVariable1 = new Variable("cataDe");
        Variable testVariable2 = new Variable("catade");

        assertNotEquals("", testVariable1, testVariable2);
    }


    // Plus
    //


    @Test
    public void testCreatesPlusFromTwoVars() {
        Variable testVariable = new Variable("cataDe");

        Plus testPlus = new Plus(testVariable, testVariable);
        assertThat(testPlus, instanceOf(Plus.class));
    }

    @Test
    public void testCreatesPlusFromTwoNum() {
        Number testNumber = new Number(678);

        Plus testPlus = new Plus(testNumber, testNumber);
        assertThat(testPlus, instanceOf(Plus.class));
    }
    
    @Test
    public void testCreatesPlusFromTwoPlus() {
        Number testNumber = new Number(678);
        Plus testPlus1 = new Plus(testNumber, testNumber);

        Plus testPlus = new Plus(testPlus1, testPlus1);
        assertThat(testPlus, instanceOf(Plus.class));
    }
    
    @Test
    public void testPlusToString() {
        Number testNumber = new Number(678);
        Plus testPlus = new Plus(testNumber, testNumber);
        
        assertEquals("Plus to string is not what expected", "678 + 678", testPlus.toString());
    }
    
    @Test
    public void testPlusMultipleToString() {
        Number testNumber = new Number(678);
        Plus testPlus = new Plus(testNumber, testNumber);
        Plus testPlusPlus = new Plus(testPlus, testNumber);
        
        assertEquals("Plus to string is not what expected", "678 + 678 + 678", testPlusPlus.toString());
    }
    
    @Test
    public void testPlusHashCode() {
        Number testNumber = new Number(678);
        Plus testPlus = new Plus(testNumber, testNumber);
        Plus testPlus2 = new Plus(testNumber, testNumber);

        assertEquals("", testPlus.hashCode(), testPlus2.hashCode());
    }

    @Test
    public void testPlusAreEqual() {
        Number testNumber = new Number(678);
        Number testNumber2 = new Number(678);

        Plus testPlus = new Plus(testNumber, testNumber);
        Plus testPlus2 = new Plus(testNumber2, testNumber2);

        assertEquals("", testPlus, testPlus2);
    }

    @Test
    public void testPluAreNotEqual() {
        Number testNumber = new Number(678);
        Number testNumber2 = new Number(45);
        Plus testPlus = new Plus(testNumber, testNumber);
        Plus testPlus2 = new Plus(testNumber, testNumber2);

        assertNotEquals("", testPlus, testPlus2);
    }


    // Mult
    //


    @Test
    public void testCreatesMultFromTwoVars() {
        Variable testVariable = new Variable("cataDe");

        Mult testMult = new Mult(testVariable, testVariable);
        assertThat(testMult, instanceOf(Mult.class));
    }

    @Test
    public void testCreatesMultFromTwoNum() {
        Number testNumber = new Number(678);

        Mult testMult = new Mult(testNumber, testNumber);
        assertThat(testMult, instanceOf(Mult.class));
    }

    @Test
    public void testCreatesMultFromTwoPlus() {
        Number testNumber = new Number(678);
        Mult testMult1 = new Mult(testNumber, testNumber);

        Mult testMult = new Mult(testMult1, testMult1);
        assertThat(testMult, instanceOf(Mult.class));
    }

    @Test
    public void testMultToString() {
        Number testNumber = new Number(678);
        Mult testMult = new Mult(testNumber, testNumber);

        assertEquals("Mult to string is not what expected", "(678 * 678)", testMult.toString());
    }

    @Test
    public void testMultMultipleToString() {
        Number testNumber = new Number(678);
        Mult testMult = new Mult(testNumber, testNumber);
        Mult testMultMult = new Mult(testMult, testNumber);

        assertEquals("Mult to string is not what expected", "((678 * 678) * 678)", testMultMult.toString());
    }

    @Test
    public void testMultHashCode() {
        Number testNumber = new Number(678);
        Mult testMult = new Mult(testNumber, testNumber);
        Mult testMult2 = new Mult(testNumber, testNumber);

        assertEquals("", testMult.hashCode(), testMult.hashCode());
    }

    @Test
    public void testMultAreEqual() {
        Number testNumber = new Number(678);
        Number testNumber2 = new Number(678);

        Mult testMult = new Mult(testNumber, testNumber);
        Mult testMult2 = new Mult(testNumber2, testNumber2);

        assertEquals("", testMult, testMult2);
    }

    @Test
    public void testMultAreNotEqual() {
        Number testNumber = new Number(678);
        Number testNumber2 = new Number(45);
        Mult testMult = new Mult(testNumber, testNumber);
        Mult testMult2 = new Mult(testNumber, testNumber2);

        assertNotEquals("", testMult, testMult2);
    }



    // TODO tests for Expression
    
}
