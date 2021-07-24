package HW_TA_JAVA_2;

public enum Brand {
    BMW("BMW", 1), MERCEDES("Mercedes", 2);

    private final String brand;
    private final int id;
    Brand(String brand, int id) {
        this.brand = brand;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }
}
