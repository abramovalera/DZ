public class JavaPointOne {
    public static void main(String[] args) {
        // Применить несколько арифметических операций ( + , -, * , /) над двумя примитивами типа int
        // Объявляем переменные
        int a = 2;
        int b = 5;
        int apple = 10;
        int pears = 8;
        double juse = 1.5;

        // Выполнение арифметических операций
        int sum = a + b;
        int difference = a - b;
        int product = a * b;
        int quotient = a / b; // Деление целочисленное
        int remainder = a % b; // Остаток от деления


        // Вывод результатов в консоль
        System.out.println("Переменные a = " + a + ", b = " + b);
        System.out.println("a+b = " + sum);
        System.out.println("a-b = " + difference);
        System.out.println("a*b = " + product);
        System.out.println("a/b = " + quotient);
        System.out.println("a%b = " + remainder);

        // Применить несколько арифметических операций над int и double в одном выражении на примере яблока и сока
        double result = (apple * 2) / juse;
        System.out.println("Яблоко и сок : " + result);

        //применить несколько логических операций ( < , >, >=, <= )
        System.out.println("Рынок:");
        System.out.println("Яблок меньше, чем груш = " + (apple < pears));
        System.out.println("Яблок больше или равно чем груш = " + (apple >= pears));
        System.out.println("Яблок больше, чем груш = " + (apple > pears));

        // Диапазон float ( непонятна, разобраться потом)
        System.out.println("Шляпа с мин и мах значениями:");
        System.out.println("Диапазон float:");
        System.out.println("Минимальное положительное значение: " + Float.MIN_VALUE);
        System.out.println("Максимальное значение: " + Float.MAX_VALUE);

        // Диапазон double ( непонятна, разобраться потом)
        System.out.println("Диапазон double:");
        System.out.println("Минимальное положительное значение: " + Double.MIN_VALUE);
        System.out.println("Максимальное значение: " + Double.MAX_VALUE);

        // Получить переполнение при арифметической операции
        float overflowFloat = Float.MAX_VALUE * 2; // Переполнение
        System.out.println("Переполнение float: " + overflowFloat); // Выведет: Infinity

        double overflowDouble = Double.MAX_VALUE * 2; // Переполнение
        System.out.println("Переполнение double: " + overflowDouble); // Выведет: Infinity

    }
}
