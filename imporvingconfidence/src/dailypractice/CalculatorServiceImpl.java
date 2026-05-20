package dailypractice;

import java.math.BigDecimal;

public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public Calculator add(Calculator a, Calculator b) {
        validateCurrency(a, b);
        return new Calculator(
                a.getAmount().add(b.getAmount()),
                a.getCurrency()
        );
    }

    @Override
    public Calculator subtraction(Calculator a, Calculator b) {
        validateCurrency(a, b);
        return new Calculator(
                a.getAmount().subtract(b.getAmount()),
                a.getCurrency()
        );
    }

    @Override
    public Calculator multiply(Calculator a, int multiply) {
        return new Calculator(
                a.getAmount().multiply(BigDecimal.valueOf(multiply)),
                a.getCurrency()
        );
    }

    @Override
    public Calculator modulus(Calculator a, int divisor) {
        return new Calculator(
                a.getAmount().remainder(BigDecimal.valueOf(divisor)),
                a.getCurrency()
        );
    }

    private void validateCurrency(Calculator a, Calculator b) {
        if (!a.getCurrency().equals(b.getCurrency())) {
            throw new IllegalArgumentException("Currency mismatch");
        }
    }
}
