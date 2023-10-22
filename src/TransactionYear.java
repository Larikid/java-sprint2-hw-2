public class TransactionYear { // классы для полей
    public int month;
    public int amount;
    public boolean isExpense;

    public TransactionYear(int month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
