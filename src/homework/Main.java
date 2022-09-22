package homework;

import homework.file.BinaryFile;
import homework.menu.Menu;
import homework.model.Manufacturer;
import homework.model.Souvenir;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

/*
        List<Manufacturer> manufacturers = new ArrayList<>(List.of(
                new Manufacturer("Adidas","США"),
                new Manufacturer("ATB","Україна"),
                new Manufacturer("Фокстрот","Україна")
        ));

        List<Souvenir> souvenirs = new ArrayList<>();
        souvenirs.add(new Souvenir("Магніт", manufacturers.get(0),
                Date.valueOf("2022-09-18"),1.5));
        souvenirs.add(new Souvenir("Запальничка", manufacturers.get(1),
                Date.valueOf("2021-03-12"),1.5));
        souvenirs.add(new Souvenir("Запальничка",manufacturers.get(2),
                Date.valueOf("2022-01-01"),1.7));
        souvenirs.add(new Souvenir("Магніт", manufacturers.get(2),
                Date.valueOf("2022-09-19"),0.6));
*/

        new Main().run();
    }

    private void run() {

        workWithProduction();
    }

    private List<Souvenir> getSouvenirsByNameManufacturer(List<Souvenir> souvenirs) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть назву виробника");
        String name = scanner.nextLine();
        return souvenirs.stream().filter(x -> x.getManufacturer().getName().equals(name)).toList();
    }

    private List<Souvenir> getSouvenirsByCountryManufacturer(List<Souvenir> souvenirs) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть країну виробника");
        String country = scanner.nextLine();
        return souvenirs.stream().filter(x -> x.getManufacturer().getCountry().equals(country)).toList();
    }

    private Map<String, List<Souvenir>> getSouvenirsByDate(List<Souvenir> souvenirs) {

        return souvenirs.stream().collect(Collectors.groupingBy(x -> x.getReleaseDate().toString().substring(0,4)));
    }

    private List<Manufacturer> getManufacturerByPrice(List<Souvenir> souvenirs, List<Manufacturer> manufacturerList) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть назву сувеніру");
        String name = scanner.nextLine();
        System.out.println("Введіть ціну");
        double price = scanner.nextDouble();

        return souvenirs.stream().filter(x -> x.getName().equals(name) && x.getPrice() < price)
                .map(Souvenir::getManufacturer).toList();

    }

    private void workWithProduction() {
        int item;
        List<Souvenir> souvenirList = new ArrayList<>();
        List<Manufacturer> manufacturerList = new ArrayList<>();
        BinaryFile<Souvenir> binaryFile1 = new BinaryFile<>();
        BinaryFile<Manufacturer> binaryFile2 = new BinaryFile<>();
        do {
            Menu.showSouvenirMenu();
            item = Menu.getSelection();
            switch (item) {
                case 1:
                    souvenirList = binaryFile1.readBinaryFromFile("souvenirs.dat");
                    manufacturerList = binaryFile2.readBinaryFromFile("manufacturers.dat");
                    break;
                case 2:
                    binaryFile1.writeBinaryToFile(souvenirList,"souvenirs.dat");
                    binaryFile2.writeBinaryToFile(manufacturerList,"manufacturers.dat");
                    break;
                case 3:
                    changeData(souvenirList,manufacturerList);
                    break;
                case 4:
                    getSouvenirsByNameManufacturer(souvenirList).forEach(System.out::println);
                    break;
                case 5:
                    addData(souvenirList,manufacturerList);
                    break;
                case 6:
                    deleteData(souvenirList,manufacturerList);
                    break;
                case 7:
                    getSouvenirsByCountryManufacturer(souvenirList).forEach(System.out::println);
                    break;
                case 8:
                    Map<String, List<Souvenir>> souvenirsByDate = getSouvenirsByDate(souvenirList);
                    for (Map.Entry<String, List<Souvenir>> entry : souvenirsByDate.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    break;
                case 9:
                    printAllSouvenir(souvenirList);
                    break;
                case 10:
                    printAllManufacturer(manufacturerList);
                    break;
                case 11:
                    getManufacturerByDateAndNameSouvenir(souvenirList,manufacturerList).forEach(System.out::println);
                    break;
                case 12:
                    getManufacturerByPrice(souvenirList,manufacturerList).forEach(System.out::println);
                    break;
                case 13:
                    Map<Manufacturer, List<Souvenir>> information = getInformationOnAllManufacturersAndSouvenirs(manufacturerList, souvenirList);
                    for (Map.Entry<Manufacturer, List<Souvenir>> entry : information.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    break;
            }
        } while (item != 0);
    }

    private void addData(List<Souvenir> souvenirList, List<Manufacturer> manufacturerList) {

        int item;
        do {
            Menu.showAddMenu();
            item = Menu.getSelection();
            switch (item) {
                case 1:
                    addSouvenir(souvenirList,manufacturerList);
                    break;
                case 2:
                    addManufacturer(manufacturerList);
                    break;
            }
        } while (item != 0);
    }

    private void changeData(List<Souvenir> souvenirList, List<Manufacturer> manufacturerList) {

        int item;
        do {
            Menu.showEditingMenu();
            item = Menu.getSelection();
            switch (item) {
                case 1:
                    changeSouvenir(souvenirList,manufacturerList);
                    break;
                case 2:
                    changeManufacturer(souvenirList,manufacturerList);
                    break;
            }
        } while (item != 0);
    }

    private void deleteData(List<Souvenir> souvenirList, List<Manufacturer> manufacturerList) {

        int item;
        do {
            Menu.showMainDeleteMenu();
            item = Menu.getSelection();
            switch (item) {
                case 1:
                    deleteSouvenir(souvenirList);
                    break;
                case 2:
                    deleteManufacturer(manufacturerList,souvenirList);
                    break;
            }
        } while (item != 0);
    }

    private void editingManufacturer(List<Souvenir> souvenirList, int number, List<Manufacturer> manufacturerList) {

        int item;
        Scanner scanner = new Scanner(System.in);
        do {
            Menu.showManufacturerEditingMenu();
            item = Menu.getSelection();
            switch (item) {
                case 1:
                    System.out.println("Введіть нову назву виробника");
                    String name = scanner.nextLine();
                    if (manufacturerList.stream().anyMatch(x -> x.getName().equals(name))) {
                        System.out.println("Такий виробник вже є");
                    } else  {
                        String oldName = manufacturerList.get(number).getName();

                        manufacturerList.get(number).setName(name);
                        Manufacturer manufacturer = manufacturerList.get(number);

                        souvenirList.stream().filter(x -> x.getManufacturer().getName().equals(oldName))
                                .forEach(x -> x.setManufacturer(manufacturer));
                    }
                    break;
                case 2:
                    System.out.println("Введіть країну виробника");
                    String country = scanner.nextLine();
                    manufacturerList.get(number).setCountry(country);

                    Manufacturer manufacturer = manufacturerList.get(number);

                    souvenirList.stream().filter(x -> x.getManufacturer().getName().equals(manufacturer.getName()))
                            .forEach(x -> x.setManufacturer(manufacturer));
                    break;
            }
        } while (item != 0);
    }

    private void editingSouvenir(List<Souvenir> souvenirList, int number, List<Manufacturer> manufacturerList) {

        int item;
        Scanner scanner = new Scanner(System.in);
        do {
            Menu.showSouvenirEditingMenu();
            item = Menu.getSelection();
            switch (item) {
                case 1:
                    System.out.println("Введіть нову назву сувеніра");
                    String name = scanner.nextLine();
                    souvenirList.get(number).setName(name);
                    break;
                case 2:
                    System.out.println("Введіть назву нового виробника");
                    String name_manufacturer = scanner.nextLine();
                    if (manufacturerList.stream().anyMatch(x -> x.getName().equals(name_manufacturer))) {
                        souvenirList.get(number).getManufacturer().setName(name_manufacturer);
                        souvenirList.get(number).setManufacturer(manufacturerList.stream().
                                filter(x -> x.getName().equals(name_manufacturer)).findFirst().get());
                    } else System.out.println("Такого виробника не знайдено");
                    break;
                case 3:
                    System.out.println("Введіть нову дату випуску у форматі \"рік-місяць-день\"");
                    String date = scanner.nextLine();
                    souvenirList.get(number).setReleaseDate(Date.valueOf(date));
                    break;
                case 4:
                    System.out.println("Введіть нову ціну сувеніру");
                    double price = scanner.nextDouble();
                    souvenirList.get(number).setPrice(price);
                    break;
            }
        } while (item != 0);
    }

    private void deleteManufacturer(List<Manufacturer> manufacturerList, List<Souvenir> souvenirList) {

        printAllManufacturer(manufacturerList);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть номер виробника");
        int n = scanner.nextInt();

        if (n < 0 || n >= souvenirList.size()) {
            System.out.println("Такий номер не знайдено");
        } else  {
            Manufacturer manufacturer = manufacturerList.get(n);
            souvenirList.removeIf(x -> x.getManufacturer().getName().equals(manufacturer.getName()));
            manufacturerList.remove(n);
        }
    }

    private void deleteSouvenir(List<Souvenir> souvenirList) {

        printAllSouvenir(souvenirList);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть номер сувеніру");
        int n = scanner.nextInt();

        if (n < 0 || n >= souvenirList.size()) {
            System.out.println("Такий номер не знайдено");
        } else  {
            souvenirList.remove(n);
        }
    }

    private void addSouvenir(List<Souvenir> souvenirList, List<Manufacturer> manufacturerList) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть назву сувеніра");
        String name = scanner.nextLine();

        System.out.println("Введіть назву виробника");
        String name_manufacturer = scanner.nextLine();

        System.out.println("Введіть дату випуску у форматі \"рік-місяць-день\"");
        String date = scanner.nextLine();

        System.out.println("Введіть ціну сувеніра");
        double price = scanner.nextDouble();

        if (manufacturerList.stream().anyMatch(x -> x.getName().equals(name_manufacturer))) {
            souvenirList.add(new Souvenir(name,manufacturerList.stream().
                    filter(x -> x.getName().equals(name_manufacturer)).findFirst().get(),Date.valueOf(date),price));
        } else  System.out.println("Такого виробника не знайдено. Будь ласка, додайте спочатку виробника");
    }

    private void addManufacturer(List<Manufacturer> manufacturerList) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть назву виробника");
        String name = scanner.nextLine();

        System.out.println("Введіть країну виробника");
        String country = scanner.nextLine();

        if (manufacturerList.stream().anyMatch(x -> x.getName().equals(name))) {
            System.out.println("Виробник з такою ж назвою вже існує. Будь ласка, введіть іншу назву");
        } else {
            manufacturerList.add(new Manufacturer(name,country));
        }
    }

    private Map<Manufacturer, List<Souvenir>> getInformationOnAllManufacturersAndSouvenirs(List<Manufacturer> manufacturerList, List<Souvenir> souvenirList) {

        return souvenirList.stream().collect(Collectors.groupingBy(Souvenir::getManufacturer));
    }

    private List<Manufacturer> getManufacturerByDateAndNameSouvenir(List<Souvenir> souvenirList, List<Manufacturer> manufacturerList) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введіть назву сувеніру");
            String souvenir_name = scanner.nextLine();

            System.out.println("Введіть рік");
            String year = scanner.nextLine();

            return souvenirList.stream().
                    filter(x -> x.getReleaseDate().toString().substring(0,4).equals(year) && x.getName().equals(souvenir_name))
                    .map(Souvenir::getManufacturer).toList();
    }

    private void printAllManufacturer(List<Manufacturer> manufacturerList) {
        int count = 0;
        for (Manufacturer manufacturer : manufacturerList) {
            System.out.println(count + " " + manufacturer);
            count++;
        }
    }

    private void printAllSouvenir(List<Souvenir> souvenirList) {
        int count = 0;
        for (Souvenir souvenir : souvenirList) {
            System.out.println(count + " " + souvenir);
            count++;
        }
    }

    private void changeSouvenir(List<Souvenir> souvenirList, List<Manufacturer> manufacturerList) {

        printAllSouvenir(souvenirList);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть номер сувеніру");
        int number = scanner.nextInt();

        if (number >= 0 && number < souvenirList.size()) {
            editingSouvenir(souvenirList,number,manufacturerList);
        } else System.out.println("Такий сувенір не знайдено");
    }

    private void changeManufacturer(List<Souvenir> souvenirList, List<Manufacturer> manufacturerList) {
        printAllManufacturer(manufacturerList);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть номер виробника");
        int number = scanner.nextInt();

        if (number >= 0 && number < manufacturerList.size()) {
            editingManufacturer(souvenirList,number,manufacturerList);
        } else System.out.println("Такого виробника не знайдено");
    }
}
