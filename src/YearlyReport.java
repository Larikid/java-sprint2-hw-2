import java.util.ArrayList;
import java.util.Scanner;

public class YearlyReport { // годовой отчёт
    Scanner scanner = new Scanner(System.in);
    public ArrayList<TransactionYear> transactionsYear = new ArrayList<>();
    FileReader fileReader = new FileReader();

    public void getDataForYear() {
        ArrayList<String> lines = fileReader.readFileContents("y.2021.csv");
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

    public void averageSumYearFalse() { //доход
        int month = 0;
        double sum = 0;
        for (TransactionYear transactionYear : transactionsYear) {
            if (!transactionYear.isExpense) {
                month++;
                sum += transactionYear.amount;
            }
        }
        sum /= month;
        System.out.println("Средний доход за имеющиеся операции в году : " + sum);
    }

    public void averageSumYearTrue() { // расход
        int month = 0;
        double sum = 0;
        for (TransactionYear transactionYear : transactionsYear) {
            if (transactionYear.isExpense) {
                month++;
                sum += transactionYear.amount;
            }
        }
        sum /= month;
        System.out.println("Средний расход за имеющиеся операции в году : " + sum + ".");
    }

    public void printAmountMonth() {
        int month = 0;
        for (TransactionYear transactionYear : transactionsYear) {
            if (!transactionYear.isExpense) {
                month++;
                System.out.println("Прибыль за " + month + " месяц составила: " + transactionYear.amount + ".");
            }
        }
    }

    public void examination() {
        System.out.println("Информация за 2021 год.");
        if (!transactionsYear.isEmpty()) {
            System.out.println("Данные годового отчёта считаны.");
        } else if (transactionsYear.isEmpty()) {
            System.out.println("Данные годового отчёта не считаны, выберите команду:");
            System.out.println("1 - Cчитывание годового отчёта. И вывод информации о годовом отчёте.");
            System.out.println("0 - Выход из программы.");
            int command = scanner.nextInt();
            if (command == 1) {
                getDataForYear();
            } else if (command == 0) {
                System.out.println("До свидания!");
                System.exit(command);
            } else {
                System.out.println("Неверно ведена команда.");
            }
        }
    }
}