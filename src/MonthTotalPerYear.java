import java.util.HashMap;

public class MonthTotalPerYear { //сверщик
    HashMap<String, Integer> expenseAndIncome = new HashMap<>();
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;

    public MonthTotalPerYear(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }

    /* Ниже метод для того что бы сверить полученные суммы с суммой доходов и расходов в отчёте по году.
       При обнаружении несоответствия программа должна вывести месяц, где оно обнаружено.
       Если несоответствий не обнаружено, приложение должно вывести только информацию об успешном завершении операции. */
    public void check() {
        calculationExpenseAndIncome();
        boolean check;
        for (TransactionYear transactionYear : yearlyReport.transactionsYear) {
            if (!transactionYear.isExpense && transactionYear.month == 01) {
                check = expenseAndIncome.get("Январь доход") == transactionYear.amount;
                if (!check) {
                    System.out.println("Доходы за " + transactionYear.month + " месяц по месячным отчётам составили " +
                            expenseAndIncome.get("Январь доход") + ", а по годовому" + transactionYear.amount);
                }
            } else if (!transactionYear.isExpense && transactionYear.month == 02) {
                check = expenseAndIncome.get("Февраль доход") == transactionYear.amount;
                if (!check) {
                    System.out.println("Доходы за " + transactionYear.month + " месяц по месячным отчётам составили " +
                            expenseAndIncome.get("Февраль доход") + ", а по годовому" + transactionYear.amount);
                }
            } else if (!transactionYear.isExpense && transactionYear.month == 03) {
                check = expenseAndIncome.get("Март доход") == transactionYear.amount;
                if (!check) {
                    System.out.println("Доходы за " + transactionYear.month + " месяц по месячным отчётам составили " +
                            expenseAndIncome.get("Март доход") + ", а по годовому" + transactionYear.amount);
                }

            }
        }
        System.out.println("Сверка прошла успешно.");
    }

    private void calculationExpenseAndIncome() { //является ли запись тратой (true) или доходом (false). Метод для подсчёта суммы доходов и расходов
        int incomeJan = 0;
        int expenseJun = 0;
        int incomeFeb = 0;
        int expenseFeb = 0;
        int incomeMarch = 0;
        int expenseMarch = 0;
        for (TransactionMonth transactionMonth : monthlyReport.transactionsMonth) {
            if (transactionMonth.monthName.equals("Январь")) {
                if (!transactionMonth.isExpense) { // доход
                    incomeJan = transactionMonth.count * transactionMonth.price + incomeJan;
                    expenseAndIncome.put("Январь доход", incomeJan);
                } else if (transactionMonth.isExpense) { // расход
                    expenseJun = transactionMonth.count * transactionMonth.price + expenseJun;
                    expenseAndIncome.put("Январь расход", incomeJan);
                }
            } else if (transactionMonth.monthName.equals("Февраль")) {
                if (!transactionMonth.isExpense) { // доход
                    incomeFeb = transactionMonth.count * transactionMonth.price + incomeFeb;
                    expenseAndIncome.put("Февраль доход", incomeFeb);
                } else if (transactionMonth.isExpense) { // расход
                    expenseFeb = transactionMonth.count * transactionMonth.price + expenseFeb;
                    expenseAndIncome.put("Февраль расход", incomeFeb);
                }
            } else if (transactionMonth.monthName.equals("Март")) {
                if (!transactionMonth.isExpense) { // доход
                    incomeMarch = transactionMonth.count * transactionMonth.price + incomeMarch;
                    expenseAndIncome.put("Март доход", incomeMarch);
                } else if (transactionMonth.isExpense) { // расход
                    expenseMarch = transactionMonth.count * transactionMonth.price + expenseMarch;
                    expenseAndIncome.put("Март расход", incomeMarch);
                }
            }
        }
    }
}




