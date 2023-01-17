import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void benchmark(Object object, Method method, BigDecimal n) throws Exception {
        long start = System.nanoTime();
        method.invoke(object, n);
        long finish = System.nanoTime();
        long time = finish - start;
        int miliseconds = (int)TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS);
        int seconds = (int)TimeUnit.SECONDS.convert(time, TimeUnit.NANOSECONDS);
        System.out.println(method.getName() + ": " + miliseconds + "ms" + " | " + seconds + "s");
    }

    public static void main(String[] args) throws Exception {
        Factorial factorial = new Factorial();
        BigDecimal n = BigDecimal.valueOf(100000);


        benchmark(factorial, factorial.getClass().getMethod("iterative", BigDecimal.class), n);
        benchmark(factorial, factorial.getClass().getMethod("recursion", BigDecimal.class), n);
        benchmark(factorial, factorial.getClass().getMethod("streamApi", BigDecimal.class), n);

    }
}
