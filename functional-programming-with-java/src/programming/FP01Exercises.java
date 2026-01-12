package programming;

import java.util.List;

public class FP01Exercises {
    public static void main(String[] args) {

//        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
//        printOddTheNumbersInListFunctional(numbers);

        List<String> courses = List.of("Spring", "Spring Boot", "API",
                "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");
//        printAllCoursesInList(courses);
//        printAllCoursesContainingSpringInList(courses);
        printAllCoursesContainingAtLeastFourLettersInList(courses);
    }

    private static void printAllCoursesInList(List<String> courses) {
        courses.stream()
                .forEach(System.out::println);
    }

    private static void printAllCoursesContainingSpringInList(List<String> courses) {
        courses.stream()
                .filter(course -> course.contains("Spring"))
                .forEach(System.out::println);
    }

    private static void printAllCoursesContainingAtLeastFourLettersInList(List<String> courses) {
        courses.stream()
                .filter(course -> course.length() >= 4)
                .forEach(System.out::println);
    }

    private static void printOddTheNumbersInListFunctional(List<Integer> numbers) {
        numbers.stream()
                .filter( number -> number % 2 == 1 )
                .forEach(System.out::println);
    }

}
