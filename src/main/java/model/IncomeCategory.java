package model;

public class IncomeCategory {
    private int id;
    private String incomeCatName;

    public IncomeCategory(int id, String incomeCatName) {
        this.id = id;
        this.incomeCatName = incomeCatName;
    }

    public int getId() {
        return id;
    }

    public String getIncomeCatName() {
        return incomeCatName;
    }
}
