import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Main {

    public static void benchmark(Object object,Method method) throws Exception {
        long start = System.nanoTime();
        method.invoke(object);
        long finish = System.nanoTime();
        long time = finish - start;
        int miliseconds = (int) TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS);
        System.out.println(method.getName() + ": " + miliseconds + "ms");
    }
    public static void benchmarkWithParameters(Object object,Method method, Ingredient ingredient) throws Exception {
        long start = System.nanoTime();
        method.invoke(object, ingredient);
        long finish = System.nanoTime();
        long time = finish - start;
        int miliseconds = (int) TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS);
        System.out.println(method.getName() + ": "  + miliseconds + "ms");
    }

    public static void main(String[] args) throws Exception {
        PizzaService pizzaService = new PizzaService();

        benchmark(pizzaService,pizzaService.getClass().getMethod("formatedMenu"));
        benchmark(pizzaService,pizzaService.getClass().getMethod("formatedMenuParallel"));

        benchmark(pizzaService, pizzaService.getClass().getMethod("findCheapestThinVegetarian"));
        benchmark(pizzaService, pizzaService.getClass().getMethod("findCheapestThinVegetarianParallel"));

        benchmarkWithParameters(pizzaService, pizzaService.getClass().getMethod("withoutAllergen", Ingredient.class), Ingredient.BECON );
        benchmarkWithParameters(pizzaService, pizzaService.getClass().getMethod("withoutAllergenParallel", Ingredient.class), Ingredient.BECON );

        benchmark(pizzaService,pizzaService.getClass().getMethod("groupByIngredients"));
        benchmark(pizzaService,pizzaService.getClass().getMethod("groupByIngredientsParallel"));

    }
}
