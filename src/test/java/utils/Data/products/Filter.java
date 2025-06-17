package utils.Data.products;

public enum Filter {
    A_TO_Z(0, "Name (A to Z)"),
    Z_TO_A(1, "Name (Z to A)"),
    LOW_TO_HIGH(2, "Price (low to high)"),
    HIGH_TO_LOW(3, "Price (high to low)");

    private final int value;
    private final String message;

    Filter(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }


}


