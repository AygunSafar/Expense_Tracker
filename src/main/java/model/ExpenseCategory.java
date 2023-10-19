package model;

public class ExpenseCategory {

    private int id;
    private String expenseCatName;

    public ExpenseCategory(int id, String expenseCatName) {

        this.id = id;
        this.expenseCatName = expenseCatName;
    }

    public int getId() {
        return id;
    }

    public String getExpenseCatName() {
        return expenseCatName;
    }

    @Override
    public String toString() {
        return "ExpenseCategory{" +
                "id=" + id +
                ", expenseCatName='" + expenseCatName + '\'' +
                '}';
    }
}
