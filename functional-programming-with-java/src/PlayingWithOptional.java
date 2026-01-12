import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class PlayingWithOptional {
    public static void main(String[] args) {
        // Optional: A container object which may or may not contain a non-null value.
        // If a value is present, isPresent() returns true. If no value is present,
        // the object is considered empty and isPresent() returns false.

        List<String> fruits = Arrays.asList("apple", "banana", "mango");
        Predicate<? super String> predicate = fruit -> fruit.startsWith("b");
        Optional<String> optional = fruits.stream().filter(predicate).findFirst();
        System.out.println(optional);
        System.out.println(optional.isEmpty());
        System.out.println(optional.isPresent());
        System.out.println(optional.get());

        // Create Optional objects
        Optional<String> fruit = Optional.of("banana");
        Optional<String> empty = Optional.empty();
    }
}
