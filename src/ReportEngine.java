import java.util.HashMap;

public class ReportEngine { // работает с отчётами
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;

    public ReportEngine(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }

    public static HashMap<Integer, Integer> sumMonthExpenseMap = new HashMap<>(); // расход
    public static HashMap<Integer, Integer> sumMonthIncomeMap = new HashMap<>(); // доход

    public static int dataMonth() {
        return 3;
    } // для работы цикла по месяцам hardcore mode activated

    public static void topProduct() { // самая большая прибыль в каждом месяце
        Main.checkFileReadForMonth();
        String product = "";
        int maxSum = 0;
        for (int j = 1; j <= dataMonth(); j++) {
            for (TransactionMonth transaction : MonthlyReport.monthIncomeMap.get(j)) {
                int sum;
                sum = transaction.count * transaction.price;
                if (maxSum < sum) {
                    maxSum = sum;
                    product = transaction.product;
                }
            }
            System.out.println("Самый прибыльный товар в " + j + " месяце: " + product + " на сумму " + maxSum + ".");
            maxSum = 0;
        }

    }

    public static void biggestWaste() { // самая большая трата в каждом месяце
        String spending = "";
        int maxSum = 0;
        for (int j = 1; j <= dataMonth(); j++) {
            for (TransactionMonth transaction : MonthlyReport.monthExpenseMap.get(j)) {
                int sum;
                sum = transaction.count * transaction.price;
                if (maxSum < sum) {
                    maxSum = sum;
                    spending = transaction.product;
                }
            }
            System.out.println("Самая большая трата в " + j + " месяце: " + spending + " на сумму " + maxSum + ".");
            maxSum = 0;
        }
    }

    public static void averageSumYearFalse() { // средний доход за год
        int month = 0;
        double sum = 0;
        for (TransactionYear transactionYear : YearlyReport.transactionsYear) {
            if (!transactionYear.isExpense) {
                month++;
                sum += transactionYear.amount;
            }
        }
        sum /= month;
        System.out.println("Средний доход за имеющиеся операции в году: " + sum);
    }

    public static void averageSumYearTrue() { // средний расход за год
        int month = 0;
        double sum = 0;
        for (TransactionYear transactionYear : YearlyReport.transactionsYear) {
            if (transactionYear.isExpense) {
                month++;
                sum += transactionYear.amount;
            }
        }
        sum /= month;
        System.out.println("Средний расход за имеющиеся операции в году: " + sum + ".");
    }

    public static void printAmountMonth() { // перечисляет месяц и прибыль по каждому месяцу
        int year = 2021;
        Main.checkFileReadForYear();
        int month = 0;
        System.out.println("Информация за " + year + " год:");
        for (TransactionYear transactionYear : YearlyReport.transactionsYear) {
            if (!transactionYear.isExpense) {
                month++;
                System.out.println("Прибыль за " + month + " месяц составила: " + transactionYear.amount + ".");
            }
        }
    }

    public static void calculationExpense() { // Метод для подсчёта суммы расходов
        int sumExpense = 0;
        for (int i = 1; i <= dataMonth(); i++) {
            for (TransactionMonth transaction : MonthlyReport.monthExpenseMap.get(i)) {
                int sum;
                sum = transaction.count * transaction.price;
                sumExpense += sum;
            }
            sumMonthExpenseMap.put(i, sumExpense);
            sumExpense = 0;
        }
    }


    public static void calculationIncome() { // Метод для подсчёта суммы доходов
        int sumIncome = 0;
        for (int i = 1; i <= dataMonth(); i++) {
            for (TransactionMonth transaction : MonthlyReport.monthIncomeMap.get(i)) {
                int sum;
                sum = transaction.count * transaction.price;
                sumIncome += sum;
            }
            sumMonthIncomeMap.put(i, sumIncome);
            sumIncome = 0;
        }
    }

    /* Ниже метод для того что бы сверить полученные суммы с суммой доходов и расходов в отчёте по году.
       При обнаружении несоответствия программа должна вывести месяц, где оно обнаружено.
       Если несоответствий не обнаружено, приложение должно вывести только информацию об успешном завершении операции. */
    public static void checkIncomeAndExpense() {
        Main.checkFileReadForMonth();
        Main.checkFileReadForYear();
        calculationIncome();
        calculationExpense();
        boolean check;
        for (int i = 1; i <= dataMonth(); i++) {
            for (TransactionYear transactionYear : YearlyReport.transactionsYear) { //является ли запись тратой (true) или доходом (false)
                if (transactionYear.isExpense && transactionYear.month == 0 + i) {
                    check = sumMonthExpenseMap.get(i) == transactionYear.amount;
                    if (!check) {
                        System.out.println("Проверкой было обнаружено несоответствие");
                        System.out.println("Доходы за " + transactionYear.month + " месяц по месячным отчётам составили " +
                                sumMonthExpenseMap.get(i) + ", а по годовому " + transactionYear.amount);
                    }
                }
                if (!transactionYear.isExpense && transactionYear.month == 0 + i) {
                    check = sumMonthIncomeMap.get(i) == transactionYear.amount;
                    if (!check) {
                        System.out.println("Проверкой было обнаружено несоответствие");
                        System.out.println("Расходы за " + transactionYear.month + " месяц по месячным отчётам составили " +
                                sumMonthIncomeMap.get(i) + ", а по годовому " + transactionYear.amount);
                    }
                }
            }
        }
        System.out.println("Сверка прошла успешно.");
    }
}





