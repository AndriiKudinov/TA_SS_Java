package HW_TA_JAVA_2;

public enum UI {
    MENU("Menu:"), ENTER("Enter the menu item number"),
    CHOOSE_BRAND("Choose a brand:"), ENTER_MODEL("Enter a model"),
    UNKNOWN_ID("Unknown id"), REMOVE("Which car to remove?"),
    ALL_CARS("All cars:"), REMOVED("Removed"),
    NO_CAR("There is no car!"), SORTED("Sorted"),
    CHOSEN("Car is chosen"), HOW_MUCH("How much?"),
    WRONG_INPUT("Wrong input!"), CURRENT_SPEED("Current speed is - ");

    private final String message;
    UI(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
