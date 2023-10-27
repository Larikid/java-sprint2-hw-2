import java.util.HashMap;
import java.util.ArrayList;

public class ReportEngine { // работает с отчётами
    HashMap<Integer, ArrayList<TransactionYear>> transactionYear = new HashMap<>();
    HashMap<Integer, MonthlyReport> monthlyReports = new HashMap<>();

    public void readYearReport() {
        for (int j = 1; j <= 3; ++j) {
            ArrayList<TransactionYear> transactionsYear = new ArrayList<>();
            ArrayList<String> lines = FileReader.readFileContents("y.2021.csv");
            if (lines.isEmpty()) {
                return;
            }
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] parts = line.split(",");
                int month = Integer.parseInt(parts[0]);
                int amount = Integer.parseInt(parts[1]);
                boolean isExpense = Boolean.parseBoolean(parts[2]); // является ли запись тратой (true) или доходом (false)
                TransactionYear transaction = new TransactionYear(month, amount, isExpense);
                if (month == j) {
                    transactionsYear.add(transaction);
                } else {
                    transactionYear.put(j, transactionsYear);
                }
            }
        }
    }

    void readMonthlyReports() {
        for (int i = 1; i <= dataMonth(); i++) {
            ArrayList<TransactionMonth> monthExpenses = new ArrayList<>();
            ArrayList<TransactionMonth> monthEarnings = new ArrayList<>();
            String fileName = "m.20210" + i + ".csv";
            ArrayList<String> lines = FileReader.readFileContents(fileName);
            if (lines.isEmpty()) {
                return;
            }
            for (int j = 1; j < lines.size(); j++) {
                String line = lines.get(j);
                String[] parts = line.split(",");
                String product = parts[0];
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                int count = Integer.parseInt(parts[2]);
                int price = Integer.parseInt(parts[3]);
                TransactionMonth transactionMonth = new TransactionMonth(product, isExpense, count, price);
                if (parts[1].equals("TRUE")) {
                    monthExpenses.add(transactionMonth);
                } else {
                    monthEarnings.add(transactionMonth);
                }
            }
            monthlyReports.put(i, new MonthlyReport(2021, i, monthExpenses, monthEarnings));
        }
    }

    public void topProduct() { // самая большая прибыль в каждом месяце
        if (!monthlyReports.isEmpty()) {
            String product = "";
            int maxSum = 0;
            for (int j = 1; j <= dataMonth(); j++) {
                for (MonthlyReport transaction : monthlyReports.values()) {
                    if (j == transaction.month) {
                        for (TransactionMonth earning : transaction.earnings) {
                            int sum;
                            sum = earning.count * earning.price;
                            if (maxSum < sum) {
                                maxSum = sum;
                                product = earning.product;
                            }
                        }
                    }
                }
                System.out.println("Самый прибыльный товар в " + j + " месяце: " + product + " на сумму " + maxSum + ".");
                maxSum = 0;
            }
        } else {
            System.out.println("Сначала считайте месячные отчёты.");
        }
    }

    public void biggestWaste() { // самая большая трата в каждом месяце
        if (!monthlyReports.isEmpty()) {
            String spending = "";
            int maxSum = 0;
            for (int j = 1; j <= dataMonth(); j++) {
                for (MonthlyReport transaction : monthlyReports.values()) {
                    if (j == transaction.month) {
                        for (TransactionMonth expens : transaction.expenses) {
                            int sum;
                            sum = expens.count * expens.price;
                            if (maxSum < sum) {
                                maxSum = sum;
                                spending = expens.product;
                            }
                        }
                    }
                }
                System.out.println("Самая большая трата в " + j + " месяце: " + spending + " на сумму " + maxSum + ".");
                maxSum = 0;
            }

        }

    }


    public double averageSumYearFalse() { // средний доход за год
        int month = 0;
        double sum = 0;
        for (int i = 1; i <= dataMonth(); ++i) {
            for (TransactionYear years : transactionYear.get(i)) {
                if (!years.isExpense) {
                    month++;
                    sum += years.amount;
                }
            }
        }
        sum /= month;
        return sum;
    }


    public double averageSumYearTrue() { // средний расход за год
        int month = 0;
        double sum = 0;
        for (int i = 1; i <= dataMonth(); ++i) {
            for (TransactionYear years : transactionYear.get(i)) {
                if (years.isExpense) {
                    month++;
                    sum += years.amount;
                }
            }
        }
        sum /= month;
        return sum;
    }

    public void printStatistic() { // перечисляет месяц и прибыль по каждому месяцу из годового отчёта
        if (transactionYear == null) {
            int year = 2021;
            System.out.println("Информация за " + year + " год:");
            System.out.println("Средний доход за имеющиеся операции в году: " + averageSumYearFalse() + ".");
            System.out.println("Средний расход за имеющиеся операции в году: " + averageSumYearTrue() + ".");
            for (int i = 1; i <= dataMonth(); ++i) {
                for (TransactionYear years : transactionYear.get(i)) {
                    if (!years.isExpense) {
                        System.out.println("Прибыль за " + years.month + " месяц составила: " + years.amount + ".");
                    }
                }
            }
        } else {
            System.out.println("Пожалуйста, сначала считайте месячные и годовой отчёты.");
        }
    }

    int countSum(ArrayList<TransactionMonth> records) {
        int sum = 0;
        for (TransactionMonth expens : records) {
            sum += expens.count * expens.price;
        }
        return sum;
    }

    int sumOfEarnings(ArrayList<TransactionMonth> earnings) {
        return countSum(earnings);
    }

    int sumOfExpenses(ArrayList<TransactionMonth> expenses) {
        return countSum(expenses);
    }

    public void reconciliationReports() {
        if (!monthlyReports.isEmpty() && !transactionYear.isEmpty()) {
            boolean errorsFound = false;
            for (int i = 1; i <= dataMonth(); i++) {
                MonthlyReport month = monthlyReports.get(i);
                ArrayList<TransactionYear> transactionYearPerMonth = transactionYear.get(i);
                int expenses = sumOfExpenses(month.expenses);
                int income = sumOfEarnings(month.earnings);
                for (int j = 0; j <= 1; ++j) {
                    TransactionYear sumYear = transactionYearPerMonth.get(j);
                    if (sumYear.isExpense) {
                        if (expenses != sumYear.amount) {
                            errorsFound = true;
                            System.out.println("Ошибка сверки отчёта для месяца " + sumYear.month + ". Траты по годовому отчёту: " + sumYear.amount + ". Траты по месячному отчёту: " + expenses);
                        }
                    }
                    if (!sumYear.isExpense) {
                        if (income != sumYear.amount) {
                            errorsFound = true;
                            System.out.println("Ошибка сверки отчёта для месяца " + sumYear.month + ". Доходы по годовому отчёту: " + sumYear.amount + ". Доходы по месячному отчёту: " + income);
                        }
                    }
                }
            }
            if (errorsFound) {
                System.out.println("При сверке отчётов обнаружены ошибки");
            } else {
                System.out.println("Сверка успешна. Ошибок не обнаружено.");
            }
        } else {
            System.out.println("Пожалуйста, сначала считайте месячные и годовой отчёты.");
        }
    }

    public int dataMonth() {
        return 3;
    }
}








