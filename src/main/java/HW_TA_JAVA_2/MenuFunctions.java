package HW_TA_JAVA_2;

import java.io.*;
import java.util.*;

public class MenuFunctions {
    private final List<Car> list = new ArrayList<>();
    private Car chosenCar = null;
    private boolean isWorking = true;
    public void start() {
        deserialize();
        try(Scanner scanner = new Scanner(System.in)) {
            int item;
            while (isWorking) {
                System.out.println(UI.MENU.getMessage());
                for (Menu value : Menu.values()) {
                    System.out.println("\t" + value.getId() + " " + value.getItem());
                }
                System.out.println(UI.ENTER.getMessage());
                try {
                    item = scanner.nextInt();
                } catch (InputMismatchException exception) {
                    System.out.println(UI.WRONG_INPUT.getMessage());
                    continue;
                } finally {
                    scanner.nextLine();
                }
                try {
                    doFunction(item, scanner);
                } catch (NullPointerException exception) {
                    System.out.println(UI.WRONG_INPUT.getMessage());
                }
            }
        }
    }

    public void doFunction(int item, Scanner scanner) {
        Menu menuItem = Menu.EXIT;
        for (Menu value : Menu.values()) {
            if (item == value.getId()) {
                menuItem = value;
            }
        }
        switch (menuItem) {
            case ADD:
                add(scanner);
                break;
            case REMOVE:
                remove(scanner);
                break;
            case SHOW:
                show();
                break;
            case SORT:
                sort();
                break;
            case CHOOSE:
                choose(scanner);
                break;
            case SPEED_UP:
                changeSpeed(scanner, chosenCar::speedUp);
                break;
            case SLOW_DOWN:
                changeSpeed(scanner, chosenCar::slowDown);
                break;
            case ALARM:
                alarm();
                break;
            case MAX_SPEED:
                changeSpeed(scanner, chosenCar::setMaxSpeed);
                break;
            case EXIT:
                exit();
                break;
        }
    }

    private void add(Scanner scanner) {
        Car car = null;
        System.out.println(UI.CHOOSE_BRAND.getMessage());
        for(Brand brand: Brand.values()) {
            System.out.println("\t" + brand.getId() + " " + brand.getBrand());
        }
        int id;
        try {
            id = scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println(UI.WRONG_INPUT);
            return;
        } finally {
            scanner.nextLine();
        }
        System.out.println(UI.ENTER_MODEL.getMessage());
        String model = scanner.nextLine();
        if(id == Brand.BMW.getId()) {
            car = new BMW(model);
        } else if (id == Brand.MERCEDES.getId()) {
            car = new Mercedes(model);
        }
        if(car != null) {
            list.add(car);
        } else {
            System.out.println(UI.UNKNOWN_ID.getMessage());
        }
    }

    private void remove(Scanner scanner) {
        show();
        int index;
        System.out.println(UI.REMOVE.getMessage());
        try {
            index = scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println(UI.UNKNOWN_ID);
            return;
        }
        if(index < list.size()) {
            list.remove(index);
            System.out.println(UI.REMOVED.getMessage());
        } else {
            System.out.println(UI.NO_CAR.getMessage());
        }
    }

    private void show() {
        System.out.println(UI.ALL_CARS.getMessage());
        for(Car car: list) {
            System.out.println("\t" + list.indexOf(car) + " " + car.getBrand() + " " + car.getModel());
        }
    }

    private void sort() {
        list.sort(Comparator.comparing(Car::getBrand));
        System.out.println(UI.SORTED.getMessage());
    }

    private void choose(Scanner scanner) {
        if(list.isEmpty()) {
            System.out.println(UI.NO_CAR.getMessage());
            return;
        }
        System.out.println(Menu.CHOOSE.getItem());
        int index;
        show();
        try {
            index = scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println(UI.UNKNOWN_ID);
            return;
        }
        if(index < list.size()) {
            chosenCar = list.get(index);
            System.out.println(UI.CHOSEN.getMessage());
        } else {
            System.out.println(UI.NO_CAR.getMessage());
        }
    }

    private void alarm() {
        if(chosenCar != null) {
            System.out.println(chosenCar.alarm());
        } else {
            System.out.println(UI.NO_CAR.getMessage());
        }
    }

    private void changeSpeed(Scanner scanner, ChangeableSpeed<Integer> function) {
        System.out.println(UI.HOW_MUCH.getMessage());
        int speed;
        try {
            speed = scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println(UI.WRONG_INPUT.getMessage());
            return;
        }
        if(chosenCar != null) {
            function.apply(speed);
            System.out.println(UI.CURRENT_SPEED.getMessage() + chosenCar.getSpeed());
        } else {
            System.out.println(UI.NO_CAR.getMessage());
        }
    }

    private void exit() {
        isWorking = false;
        if(!list.isEmpty()) {
            serialize();
        }
    }

    private void serialize() {
        try (FileOutputStream fos = new FileOutputStream("CarData")) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(list);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void deserialize() {
        File file = new File("CarData");
        if(file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                    List<?> desList = (ArrayList<?>) ois.readObject();
                    desList.forEach((item) -> list.add((Car) item));
                }
            } catch (IOException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        }
    }
}
