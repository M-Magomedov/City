import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class CityUtils {
    public static ArrayList<City> readFile() {
        ArrayList<City> cities = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("city_list.csv"));
            while (scanner.hasNextLine()) {
                String[] splitLine = scanner.nextLine().split(";", 6);
                if (splitLine[5].isEmpty())
                    cities.add(new City(splitLine[1], splitLine[2], splitLine[3], splitLine[4], null));
                else
                    cities.add(new City(splitLine[1], splitLine[2], splitLine[3], splitLine[4], splitLine[5]));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }

//     Сортировка списка городов по наименованию в алфавитном порядке по убыванию без учета регистра
    public static ArrayList<City> sortByName(ArrayList<City> cities) {
        cities.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        return cities;
    }

//    Сортировка списка городов по федеральному округу и наименованию города внутри каждого
//    федерального округа в алфавитном порядке по убыванию с учетом регистра;
    public static ArrayList<City> sortByNameAndDistrict(ArrayList<City> cities) {
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
        return cities;
    }

//    Поиск города с наибольшим количеством жителей
    public static void findMaxPopulation(ArrayList<City> cities) {
        City[] array = cities.toArray(new City[0]);
        int maxPopulation = 0;
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (Integer.parseInt(array[i].getPopulation()) > maxPopulation) {
                maxPopulation = Integer.parseInt(array[i].getPopulation());
                index = i;
            }
        }
        System.out.println(MessageFormat.format("[{0}] = {1}", index, maxPopulation));
    }

//    Определение количества городов в каждом регионе
    public static void findNumberRegion(ArrayList<City> cities) {
        HashMap<String, Integer> regionMap = new HashMap<>();
        cities.forEach(city -> regionMap.merge(city.getRegion(), 1, Integer::sum));
        regionMap.forEach((key, value) -> System.out.println(MessageFormat.format("{0} - {1}", key, value)));
    }
    public static void print(ArrayList<City> cities) {
        cities.forEach(System.out::println);
    }
}