package dailypractice;

public interface CalculatorService {

	Calculator add(Calculator a, Calculator b);

	Calculator subtraction(Calculator a, Calculator b);

	Calculator multiply(Calculator a, int multiply);

	Calculator modulus(Calculator a, int divisor);

}
