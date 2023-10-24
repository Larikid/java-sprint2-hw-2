import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport { // месячный отчёт

    public static HashMap<Integer, ArrayList<TransactionMonth>> monthExpenseMap = new HashMap<>(); // расход
    public static HashMap<Integer, ArrayList<TransactionMonth>> monthIncomeMap = new HashMap<>(); // доход

    public static void getDataForMonth() { // считывает месячные отчёты
        for (int j = 1; j <= ReportEngine.dataMonth(); j++) {
            ArrayList<TransactionMonth> monthlyExpenditure = new ArrayList<>();
            ArrayList<TransactionMonth> monthlyIncome = new ArrayList<>();
            ArrayList<String> lines = FileReader.readFileContents("m.20210" + j + ".csv"); // "m.202101.csv"
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] parts = line.split(",");
                String product = parts[0];
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                int count = Integer.parseInt(parts[2]);
                int price = Integer.parseInt(parts[3]);
                TransactionMonth transactionMonth = new TransactionMonth(product, isExpense, count, price);
                if (isExpense) {
                    monthlyExpenditure.add(transactionMonth);
                } else {
                    monthlyIncome.add(transactionMonth);
                }
            }
            monthExpenseMap.put(j, monthlyExpenditure);
            monthIncomeMap.put(j, monthlyIncome);
        }
    }
}

