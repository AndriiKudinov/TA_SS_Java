package HW_TA_JAVA_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
            enterUserInterval(scanner);
            printSumOddEven();
            buildFibonacciNumbers(scanner);
            printPercentage();
        }
    }

    private static int[] interval;
    private static final List<Integer> set = new ArrayList<>();

    private static void enterUserInterval(Scanner scanner) {
        System.out.println("Введите интервал вида [min;max] .");
        String intervalString;
        intervalString = scanner.nextLine();
        interval = parseInterval(intervalString);
        if(interval == null) {
            interval = new int[2];
            interval[0] = 1;
            interval[1] = 100;
            System.out.println("Введенная строка пустая или неправильно введена! Задан интервал [1,100] .");
        } else {
            System.out.println("Вы ввели " + Arrays.toString(interval) + " .");
        }
    }

    private static void printSumOddEven() {
        int evenSum = 0;
        int oddSum = 0;
        int min = interval[0];
        int max = interval[1];
        for(int index = min; index <= max; index++) {
            if(isEven(index)) {
                evenSum += index;
            } else {
                oddSum += index;
            }
        }
        System.out.println("Сумма всех четных чисел их интервала: " + evenSum);
        System.out.println("Сумма всех нечетных чисел их интервала: " + oddSum);
    }

    private static void buildFibonacciNumbers(Scanner scanner) {
        System.out.println("Введите размер набора чисел Фибоначчи");
        int n;
        int next;
        int biggestOdd = 0;
        int biggestEven = 1;
        n = scanner.nextInt();
        if(n == 0) {
            System.out.println("Введено 0. Набор пуст");
            return;
        }
        if(n >= 1) {
            set.add(0);
            if(n == 1) {
                outputFibonacci();
                System.out.println("Самое большое четное число 0. Нечетных чисел нет");
                return;
            }
        }
        if(n >= 2) {
            set.add(1);
            if(n == 2) {
                outputFibonacci();
                System.out.println("Самое большое четное число 0. Самое большое нечетное - 1");
                return;
            }
        }
        for(int index = 3; index <= n; index++) {
            next = set.get(index - 2) + set.get(index - 3);
            if(isEven(next)) {
                biggestEven = next;
            } else {
                biggestOdd = next;
            }
            set.add(next);
        }
        outputFibonacci();
        System.out.format(  "Самое большое четное число %d. Самое большое нечетное - %d\r\n",
                biggestEven, biggestOdd);
    }

    private static void printPercentage() {
        int odds = 0;
        int evens = 0;
        for(Integer number: set) {
            if(isEven(number)){
                evens += 1;
            } else {
                odds += 1;
            }
        }
        int oddPercent = (int) Math.round(odds * 100.0 / set.size());
        int evenPercent = (int) Math.round(evens * 100.0 / set.size());
        System.out.println("Процент нечетных чисел - " + oddPercent);
        System.out.println("Процент четных чисел - " + evenPercent);
    }

    private static int[] parseInterval(String intervalString) {
        if (intervalString == null || intervalString.isEmpty()) {
            return null;
        }
        int[] interval = new int[2];
        try {
            intervalString = intervalString.trim();
            int lastIndex = intervalString.length() - 1;
            intervalString = intervalString.substring(1, lastIndex);
            String[] minMaxStrings = intervalString.split(";");
            interval[0] = Integer.parseInt(minMaxStrings[0]);
            interval[1] = Integer.parseInt(minMaxStrings[1]);
        } catch (NumberFormatException exception) {
            return null;
        }
        return interval;
    }

    private static boolean isEven(int number) {
        return ( number % 2 ) == 0;
    }

    private static void outputFibonacci() {
        System.out.println("Числа Фибоначчи: " + set);
    }
}