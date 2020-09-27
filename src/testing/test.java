package testing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
Задача по алгоритмам Ӏ Java Syntax: 9 уровень, 11 лекция
*/

public class test {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String s = reader.readLine();
            if (s.isEmpty()) {
                break;
            }
            list.add(s);
        }

        String[] array = list.toArray(new String[0]);
        sort(array);

        for (String x : array) {
            System.out.println(x);
        }
    }

    public static void sort(String[] array) {
        // напишите тут ваш код
        String tempStr = "";
        int tempIndexForMax = 0;
        int max = 0;
        boolean isThereBigger = false;

        for (int i = 0; i < array.length; i++) {
            //System.out.println(Arrays.toString(array));
            for (int j = 0; j < array.length; j++) {
                // 1ое условие, если элемент массива - это строка, а не число
                if (!isNumber(array[j])) {

                    int find_Next_Pos_Of_Str = j;
                    int k = j + 1;
                    // Поиск следуюущего слова в массиве
                    while (true) {
                        if (k == array.length) {
                            break;
                        }
                        if (!isNumber(array[k])) {
                            find_Next_Pos_Of_Str = k;
                            break;
                        }
                        k++;
                    } // Нашли индекс след слова и записали в find_Next_Pos_Of_Str
                    if (isGreaterThan(array[j], array[find_Next_Pos_Of_Str])) {
                        tempStr = array[j];
                        array[j] = array[find_Next_Pos_Of_Str];
                        array[find_Next_Pos_Of_Str] = tempStr;
                    }
                }
            }
        }

        for (int i = 0; i < array.length; i++) {
            if (isNumber(array[i])){
                max = Integer.parseInt(array[i]);
            }
            for (int j = i; j < array.length; j++) {
                if (isNumber(array[j]) && isNumber(array[i])) {
                    if (Integer.parseInt(array[j]) > max) {
                        max = Integer.parseInt(array[j]);
                        tempIndexForMax = j; // Индекс максимального числа на его место будем ставить i - ый элемент массива
                        isThereBigger = true;
                    }
                }
            }
            if (isThereBigger) {
                array[tempIndexForMax] = array[i]; // i - ый элемент ставим на место где был максимальный
                array[i] = Integer.toString(max); // а на место i - ого ставим максимальное найденное значение
            }
            isThereBigger = false;
        }
    }

    // Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }


    // Переданная строка - это число?
    public static boolean isNumber(String text) {
        if (text.length() == 0) {
            return false;
        }

        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char character = chars[i];

            // есть '-' внутри строки
            if (i != 0 && character == '-') {
                return false;
            }

            // не цифра и не начинается с '-'
            if (!Character.isDigit(character) && character != '-') {
                return false;
            }

            // одиночный '-'
            if (chars.length == 1 && character == '-') {
                return false;
            }
        }
        return true;
    }
}
