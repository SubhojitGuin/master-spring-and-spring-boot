package programming;

import java.util.List;

public class FP01Functional {
    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
//        printAllTheNumbersInListFunctional(numbers);
        printEvenTheNumbersInListFunctional(numbers);

    }

//    private static void print(int number) {
//        System.out.println(number);
//    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

    private static void printAllTheNumbersInListFunctional(List<Integer> numbers) {
        // What to do?
//        numbers.stream() // Creates a sequence of elements that provides the numbers in the list one by one to the next function
//                .forEach(FP01Functional::print); // Performs the action (method reference) for every element given by the stream
        // Here, FP01Functional::print is the method reference of the print() function


        numbers.stream()
                .forEach(System.out::println); // Here, it directly gives the reference of the static method println() in System.out
    }

    private static void printEvenTheNumbersInListFunctional(List<Integer> numbers) {
        numbers.stream()
//                .filter(FP01Functional::isEven) // Filter - Only allow even numbers, The number is added to the stream in the next step if the function returns true for that value
                .filter( number -> number % 2 == 0 ) // Lambda expression - Simplified method expression
                .forEach(System.out::println); // Method reference
    }

}
