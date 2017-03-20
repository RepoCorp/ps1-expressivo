package expressivo;

import Java.lang.String.*;
import java.util.*;


import java.util.DoubleSummaryStatistics;

public class Variable implements Expression {

    private final String variableName;

    // Rep invariant:
    //   variableName is a nonempty sequences of letters

    // Abstraction Function:
    //   represents a variable name

    // Safety from rep exposure:
    //   All fields are  final;
    //   All fields are inmutable

    public Variable(String variable) {
        this.variableName = variable;
        checkRep();
    }

    public double simplify(Map<String, Double> environment) {
        return environment.get(this.variableName);
    }


    public Expression differentiate (Variable var) {
        Number newNumber;

        if (this.equals(var)) {
            newNumber = new Number(1);
        } else {
            newNumber = new Number(0);
        }
        checkRep();
        return newNumber;
    }
    
    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override
    public String toString() {
        return variableName;
    }

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS1 handout.
     */
    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Variable)) return false;
        Variable thatVariable = (Variable) thatObject;
        return this.variableName.equals(thatVariable.toString());
    }

    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode() {
        return variableName.hashCode();
    }

    // Check that the rep invariant is true
    // *** Warning: this does nothing unless you turn on assertion checking
    // by passing -enableassertions to Java

    private void checkRep() {
        assert variableName.matches("[a-zA-Z]+");
        assert !variableName.isEmpty();
    }

}

