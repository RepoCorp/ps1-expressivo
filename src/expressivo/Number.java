package expressivo;

import expressivo.Expression;

import Java.lang.String.*;

public class Number implements Expression {

    private final double number;

    // Rep invariant:
    //   number is nonnegative in decimal representation

    // Abstraction Function:
    //   represents a number

    // Safety from rep exposure:
    //   All fields are  final;
    //   All fields are inmutable

    public Number(double num) {
        this.number = num;
        checkRep();
    }

    public Expression differentiate (Variable var) {
        return new Number(0);
    }

    public double simplify() {
        return number;
    }
    
    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override
    public String toString() {
        if(number == (long) number)
            return String.format("%d",(long)number);
        else
            return String.format("%s",number);
    }

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS1 handout.
     */
    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Number)) return false;
        Number thatNumber = (Number) thatObject;
        return this.number == thatNumber.simplify();
    }

    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode() {
        return (int)number;
    }

    // Check that the rep invariant is true
    // *** Warning: this does nothing unless you turn on assertion checking
    // by passing -enableassertions to Java

    private void checkRep() {
        assert number >= 0;
    }

}

