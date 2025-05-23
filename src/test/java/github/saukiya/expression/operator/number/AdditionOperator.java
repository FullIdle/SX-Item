package github.saukiya.expression.operator.number;

import github.saukiya.expression.operator.NumberOperator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AdditionOperator implements NumberOperator {

    private NumberOperator left;
    private NumberOperator right;

    @Override
    public double evaluate() {
        return left.evaluate() + right.evaluate();
    }
}