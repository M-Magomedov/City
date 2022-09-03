import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<City> cities = CityUtils.readFile();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.print("1. Отобразить список городов\n" +
                    "2. Отобразить отсортированный список городов по названию без учета регистра\n" +
                    "3. Отобразить отсортированный список городов по названию и федеральному округу без учета регистра\n" +
                    "4. Отобразить город с наибольшим количеством жителей\n" +
                    "5. Отобразить количество городов в каждом регионе\n" +
                    "0. Выход\n" +
                    "Ваш выбор: ");
            switch (choice = scanner.nextInt()) {
                case 1:
                    CityUtils.print(cities);
                    break;
                case 2:
                    CityUtils.print(CityUtils.sortByName(cities));
                    break;
                case 3:
                    CityUtils.print(CityUtils.sortByNameAndDistrict(CityUtils.readFile()));
                    break;
                case 4:
                    CityUtils.findMaxPopulation(cities);
                    break;
                case 5:
                    CityUtils.findNumberRegion(cities);
                    break;
                default:
                    break;
            }
            System.out.println();
        }
    }
}