import java.util.*;

public class Main {
    private String model;
    private int ram;
    private int hdd;
    private String os;
    private String color;

    public Main(String model, int ram, int hdd, String os, String color) {
        this.model = model;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    public static void main(String[] args) {
        List<Main> notebooks = new ArrayList<>();
        notebooks.add(new Main("Lenovo", 8, 512, "Windows", "Black"));
        notebooks.add(new Main("HP", 16, 1024, "Linux", "Silver"));
        notebooks.add(new Main("Dell", 12, 750, "Windows", "White"));
        notebooks.add(new Main("HP", 32, 2048, "Windows", "Black"));
        notebooks.add(new Main("Dell", 6, 256, "Windows", "Silver"));

        Map<String, Object> filters = new HashMap<>();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            System.out.println("1 - ОЗУ");
            System.out.println("2 - Объем ЖД");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");

            int criterion = scanner.nextInt();

            switch (criterion) {
                case 1:
                    System.out.println("Введите минимальный объем ОЗУ:");
                    int minRam = scanner.nextInt();
                    filters.put("ram", minRam);
                    break;
                case 2:
                    System.out.println("Введите минимальный объем ЖД:");
                    int minHdd = scanner.nextInt();
                    filters.put("hdd", minHdd);
                    break;
                case 3:
                    System.out.println("Введите операционную систему:");
                    String os = scanner.next();
                    filters.put("os", os);
                    break;
                case 4:
                    System.out.println("Введите цвет:");
                    String color = scanner.next();
                    filters.put("color", color);
                    break;
                default:
                    System.out.println("Некорректный ввод");
                    return;
            }
        }

        List<Main> filteredNotebooks = filterNotebooks(notebooks, filters);
        for (Main notebook : filteredNotebooks) {
            System.out.println(notebook.getModel());
        }
    }

    public static List<Main> filterNotebooks(List<Main> notebooks, Map<String, Object> filters) {
        List<Main> filteredNotebooks = new ArrayList<>(notebooks);
        for (String key : filters.keySet()) {
            switch (key) {
                case "ram":
                    filteredNotebooks.removeIf(notebook -> notebook.getRam() < (int) filters.get(key));
                    break;
                case "hdd":
                    filteredNotebooks.removeIf(notebook -> notebook.getHdd() < (int) filters.get(key));
                    break;
                case "os":
                    filteredNotebooks.removeIf(notebook -> !notebook.getOs().equals(filters.get(key)));
                    break;
                case "color":
                    filteredNotebooks.removeIf(notebook -> !notebook.getColor().equals(filters.get(key)));
                    break;
            }
        }
        return filteredNotebooks;
    }
}