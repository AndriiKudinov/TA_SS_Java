package HW_TA_JAVA_2;

public enum Menu {
    ADD("Add car", 1), REMOVE("Remove a car", 2),
    SHOW("Show all cars", 3), SORT("Sort cars", 4),
    CHOOSE("Choose a car", 5), SPEED_UP("Speed up the selected car", 6),
    SLOW_DOWN("Slow down the selected car", 7), ALARM("Turn alarm on/off", 8),
    MAX_SPEED("Set the maximum speed of the car", 9), EXIT("Exit the program", 10);


    private final String item;
    private final int id;
    Menu(String item, int id) {
        this.item = item;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getItem() {
        return item;
    }
}
