import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PizzaService {

    public String formatedMenuParallel(){
        return Arrays.stream(Pizza.values()).parallel()
                .map(pizza -> String.format("%s: %s - %d",
                        pizza.getName(),
                        pizza.getIngredients(),
                        pizza.getIngredients().stream().mapToInt(Ingredient::getPrice).sum()))
                .collect(Collectors.joining("\n"));
    }

    public String formatedMenu(){
        return Arrays.stream(Pizza.values())
                .map(pizza -> String.format("%s: %s - %d",
                        pizza.getName(),
                        pizza.getIngredients(),
                        pizza.getIngredients().stream().mapToInt(Ingredient::getPrice).sum()))
                .collect(Collectors.joining("\n"));
    }

    public Pizza findCheapestThinVegetarian(){
        return Arrays.stream(Pizza.values())
                .filter(pizza -> pizza.getIngredients().stream().noneMatch(Ingredient::isMeat))
                .min(Comparator.comparing(x -> (Integer) x.getIngredients().stream().parallel().mapToInt(Ingredient::getPrice).sum()))
                .orElseThrow(NoSuchElementException::new);
    }

    public Pizza findCheapestThinVegetarianParallel(){
        return Arrays.stream(Pizza.values()).parallel()
                .filter(pizza -> pizza.getIngredients().stream().parallel().noneMatch(Ingredient::isMeat))
                .min(Comparator.comparing(x -> (Integer) x.getIngredients().stream().parallel().mapToInt(Ingredient::getPrice).sum()))
                .orElseThrow(NoSuchElementException::new);
    }

    public List withoutAllergen(Ingredient ingredient){
        return Arrays.stream(Pizza.values())
                .filter(e -> e.getIngredients().stream().noneMatch(x -> Objects.equals(x.getName(), ingredient.getName()))).collect(Collectors.toList());
    }
    public List withoutAllergenParallel(Ingredient ingredient){
        return Arrays.stream(Pizza.values()).parallel()
                .filter(e -> e.getIngredients().stream().parallel().noneMatch(x -> Objects.equals(x.getName(), ingredient.getName()))).collect(Collectors.toList());
    }


    public Map groupByIngredients(){
        return Arrays.stream(Ingredient.values())
                .collect(Collectors.groupingBy(e -> e.isSpicy() & e.isMeat() ? "SPICY_MEAT" : e.isMeat() ? "MEAT"
                        : e.isSpicy() ? "SPICY" : "OTHERS", Collectors.toSet()));
    }

    public Map groupByIngredientsParallel(){
        return Arrays.stream(Ingredient.values()).parallel()
                .collect(Collectors.groupingBy(e -> e.isSpicy() & e.isMeat() ? "SPICY_MEAT" : e.isMeat() ? "MEAT"
                        : e.isSpicy() ? "SPICY" : "OTHERS", Collectors.toSet()));
    }

}
