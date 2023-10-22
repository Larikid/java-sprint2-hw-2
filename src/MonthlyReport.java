import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class MonthlyReport { // месячный отчёt
    Scanner scanner = new Scanner(System.in);
    public HashMap<String, ArrayList<TransactionMonth>> reportMonth = new HashMap<>();
    FileReader fileReader = new FileReader();
    public ArrayList<TransactionMonth> transactionsMonth = new ArrayList<>();

    public void getDataForMonth(String monthName, String fileName) {
        ArrayList<String> lines = fileReader.readFileContents(fileName);
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");
            String product = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);// является ли запись тратой (true) или доходом (false)
            int count = Integer.parseInt(parts[2]);
            int price = Integer.parseInt(parts[3]);
            TransactionMonth transactionMonth = new TransactionMonth(product, isExpense, count, price, monthName);
            transactionsMonth.add(transactionMonth);
        }
    }

    public void topProduct(String monthName) {
        String product = "";
        int maxSum = 0;
        int sum;
        for (TransactionMonth transactionMonth : transactionsMonth) {
            if (transactionMonth.monthName.equals(monthName)) {
                if (!transactionMonth.isExpense) {
                    sum = transactionMonth.count * transactionMonth.price;
                    if (maxSum < sum) {
                        maxSum = sum;
                        product = transactionMonth.product;

                    }
                }
            }
        }
        System.out.println("Отчёт за: " + monthName + ".");
        System.out.println("Самый прибыльный товар " + product + " на сумму " + maxSum + ".");

    }

    public void biggestWaste(String monthName) {
        String spending = "";
        int maxSum = 0;
        int sum;
        for (TransactionMonth transactionMonth : transactionsMonth) {
            if (transactionMonth.monthName.equals(monthName)) {
                if (transactionMonth.isExpense) {
                    sum = transactionMonth.count * transactionMonth.price;
                    if (maxSum < sum) {
                        maxSum = sum;
                        spending = transactionMonth.product;

                    }
                }
            }
        }
        System.out.println("Самая большая трата " + spending + " на сумму " + maxSum + ".");
    }

    public void examination() {
        if (!transactionsMonth.isEmpty()) {
            System.out.println("Данные месячных отчётов считаны.");
        } else if (transactionsMonth.isEmpty()) {
            System.out.println("Данные месячных отчётов не считаны, выберите команду:");
            System.out.println("1 - Cчитывание месячных отчётов. И вывод информации обо всех месячных отчётах.");
            System.out.println("0 - Выход из программы.");
            int command = scanner.nextInt();
            if (command == 1) {
                getDataForMonth("Январь", "m.202101.csv");
                getDataForMonth("Февраль", "m.202102.csv");
                getDataForMonth("Март", "m.202103.csv");
            } else if (command == 0) {
                System.out.println("До свидания!");
                System.exit(command);
            } else {
                System.out.println("Неверно ведена команда.");
            }
        }
    }
}

