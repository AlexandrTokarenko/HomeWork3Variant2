package homework.menu;

import java.util.Scanner;

public final class Menu {

    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static void showSouvenirMenu() {
        System.out.println("1. Зчитати дані сувенірів та виробників");
        System.out.println("2. Записати дані сувенірів та виробників");
        System.out.println("3. Редагувати дані");
        System.out.println("4. Вивести інформацію про сувеніри заданого виробника");
        System.out.println("5. Додати дані");
        System.out.println("6. Видалити дані");
        System.out.println("7. Вивести інформацію про сувеніри, зроблені в заданій країні");
        System.out.println("8. Для кожного року вивести список сувенірів, зроблених цього року");
        System.out.println("9. Вивести дані всіх сувенірів");
        System.out.println("10. Вивести дані всіх виробників");
        System.out.println("11. Вивести інформацію про виробників заданого сувеніру, виробленого у заданому році");
        System.out.println("12. Вивести інформацію про виробників, чиї ціни на сувеніри менше заданої");
        System.out.println("13. Вивести інформацію по всіх виробниках та для кожного виробника вивести інформацію про всі сувеніри, які він виробляє");
        System.out.println("0. Вихід");
    }

    public static void showSouvenirEditingMenu() {
        System.out.println("1. Змінити назву сувеніра");
        System.out.println("2. Змінити виробника");
        System.out.println("3. Змінити дату виготовлення");
        System.out.println("4. Змінити ціну");
        System.out.println("0. Назад");
    }

    public static void showManufacturerEditingMenu() {
        System.out.println("1. Змінити назву виробника");
        System.out.println("2. Змінити країну виробника");
        System.out.println("0. Назад");
    }

    public static int getSelection() {
        return scanner.nextInt();
    }

    public static void showEditingMenu() {

        System.out.println("1. Редагувати дані сувеніру");
        System.out.println("2. Редагувати дані виробника");
        System.out.println("0. Назад");
    }

    public static void showAddMenu() {

        System.out.println("1. Додати сувенір");
        System.out.println("2. Додати виробника");
        System.out.println("0. Назад");
    }

    public static void showMainDeleteMenu() {

        System.out.println("1. Видалити сувенір");
        System.out.println("2. Видалити виробника");
        System.out.println("0. Назад");
    }
}

