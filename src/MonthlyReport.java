import java.util.ArrayList;

public class MonthlyReport { // месячный отчёт
    int year;
    int month;
    ArrayList<TransactionMonth> expenses;
    ArrayList<TransactionMonth> earnings;

    public MonthlyReport(int year, int month, ArrayList<TransactionMonth> expenses, ArrayList<TransactionMonth> earnings) {
        this.year = year;
        this.month = month;
        this.expenses = expenses;
        this.earnings = earnings;
    }
}

