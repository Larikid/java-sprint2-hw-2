import java.util.ArrayList;

public class YearlyReport { // годовой отчёт
    public static ArrayList<TransactionYear> transactionsYear = new ArrayList<>();

    public static void getDataForYear() {
        ArrayList<String> lines = FileReader.readFileContents("y.2021.csv");
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]); // является ли запись тратой (true) или доходом (false)
            TransactionYear transaction = new TransactionYear(month, amount, isExpense);
            transactionsYear.add(transaction);
        }
    }
}
