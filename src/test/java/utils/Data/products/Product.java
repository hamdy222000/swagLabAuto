package utils.Data.products;

public enum Product {
    Number_One(0 ),
    Number_Two(1 ),
    Number_Three(2),
    Number_Four(3),
    Number_Five(4),
    Number_Six(5);

    private final int index;

    Product(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
