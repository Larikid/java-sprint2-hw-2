import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            if (userInput == 1) {
                MonthlyReport.getDataForMonth();
                System.out.println("Файлы успешно считаны.");
            } else if (userInput == 2) {
                YearlyReport.getDataForYear();
                System.out.println("Файл успешно считан.");
            } else if (userInput == 3) {
                ReportEngine.checkIncomeAndExpense();
            } else if (userInput == 4) {
                ReportEngine.topProduct();
                ReportEngine.biggestWaste();
            } else if (userInput == 5) {
                ReportEngine.printAmountMonth();
                ReportEngine.averageSumYearTrue();
                ReportEngine.averageSumYearFalse();
            } else if (userInput == 0) {
                System.out.println("До свидания!");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    public static void printMenu() { // печать меню
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты.");
        System.out.println("2 - Считать годовой отчёт.");
        System.out.println("3 - Сверить отчёты.");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах.");
        System.out.println("5 - Вывести информацию о годовом отчёте.");
        System.out.println("0 - Выход.");
    }

    public static void checkFileReadForYear() { // проверяет считаны ли файлы за год
        Scanner scanner = new Scanner(System.in);
        if (!YearlyReport.transactionsYear.isEmpty()) {
        } else if (YearlyReport.transactionsYear.isEmpty()) {
            System.out.println("Данные годового отчёта не считаны, выберите команду:");
            System.out.println("1 - Cчитывание годового отчёта. И вывод информации о годовом отчёте.");
            System.out.println("0 - Выход из программы.");
            int command = scanner.nextInt();
            if (command == 1) {
                YearlyReport.getDataForYear();
            } else if (command == 0) {
                System.out.println("До свидания!");
                System.exit(command);
            } else {
                System.out.println("Неверно ведена команда.");
            }
        }
    }

    public static void checkFileReadForMonth() { // проверяет считаны ли файлы за месяц
        Scanner scanner = new Scanner(System.in);
        if (!MonthlyReport.monthExpenseMap.isEmpty() && !MonthlyReport.monthIncomeMap.isEmpty()) {
        } else if (MonthlyReport.monthExpenseMap.isEmpty() && MonthlyReport.monthIncomeMap.isEmpty()) {
            System.out.println("Данные месячных отчётов не считаны, выберите команду:");
            System.out.println("1 - Cчитывание месячных отчётов. И вывод информации обо всех месячных отчётах.");
            System.out.println("0 - Выход из программы.");
            int command = scanner.nextInt();
            if (command == 1) {
                MonthlyReport.getDataForMonth();
            } else if (command == 0) {
                System.out.println("До свидания!");
                System.exit(command);
            } else {
                System.out.println("Неверно ведена команда.");
            }
        }
    }
}
