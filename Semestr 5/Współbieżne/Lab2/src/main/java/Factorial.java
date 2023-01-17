import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Factorial {
    private static BigDecimal zero = BigDecimal.ZERO;
    private static BigDecimal one = BigDecimal.ONE;

    public BigDecimal iterative(BigDecimal n){
        BigDecimal result = BigDecimal.valueOf(1);

        for (int i=1; i <= n.intValue(); i++){
            result = result.multiply(BigDecimal.valueOf(i));
        }

        return result;
    }


    public static BigDecimal recursion(BigDecimal n) {
        if (n.equals(zero))
            return one;
        else
            return n.multiply(recursion(n.subtract(one)));
    }

    public BigInteger streamApi(BigDecimal n){
        int m = n.intValue();
        return IntStream.rangeClosed(1,m).parallel()
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger::multiply)
                .orElseThrow();
    }
}
