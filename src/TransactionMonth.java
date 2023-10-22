public class TransactionMonth { // классы для полей
    public String product;
    public boolean isExpense;
    public int count;
    public int price;
    public String monthName;

    public TransactionMonth(String product, boolean isExpense, int count, int price, String monthName) {
        this.product = product;
        this.isExpense = isExpense;
        this.count = count;
        this.price = price;
        this.monthName = monthName;
    }
}