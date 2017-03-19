package expressivo;

import Java.lang.String.*;

public class Plus implements Expression {

    private final Expression left;
    private final Expression right;

    // Rep invariant:
    //

    // Abstraction Function:
    //   represents a plus expression

    // Safety from rep exposure:
    //   All fields are  final;
    //   All fields are inmutable

    public Plus(Expression left, Expression right) {
        this.left = left;
        this.right = right;
        checkRep();
    }

    public Expression differentiate (Variable var) {
        Expression diffLeft = this.left.differentiate(var);
        Expression diffRight = this.right.differentiate(var);

        return new Plus(diffLeft, diffRight);
    }

    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override
    public String toString() {
        return left.toString() + " + " + right.toString();
    }

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS1 handout.
     */
    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Plus)) return false;
        Plus thatPlus = (Plus) thatObject;
        return this.left.equals(thatPlus.left) && this.right.equals(thatPlus.right);
    }

    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode() {
        return left.hashCode() + right.hashCode();
    }

    // Check that the rep invariant is true
    // *** Warning: this does nothing unless you turn on assertion checking
    // by passing -enableassertions to Java

    private void checkRep() {
    }

}

