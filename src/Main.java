import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();
        MonthTotalPerYear monthTotalPerYear = new MonthTotalPerYear(monthlyReport, yearlyReport);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            if (userInput == 1) {
                monthlyReport.getDataForMonth("Январь", "m.202101.csv");
                monthlyReport.getDataForMonth("Февраль", "m.202102.csv");
                monthlyReport.getDataForMonth("Март", "m.202103.csv");
                System.out.println("Файлы успешно считаны.");
            } else if (userInput == 2) {
                yearlyReport.getDataForYear();
                System.out.println("Файл успешно считан.");
            } else if (userInput == 3) {
                monthlyReport.examination();
                yearlyReport.examination();
                monthTotalPerYear.check();
            } else if (userInput == 4) {
                monthlyReport.examination();
                monthlyReport.topProduct("Январь");
                monthlyReport.biggestWaste("Январь");
                monthlyReport.topProduct("Февраль");
                monthlyReport.biggestWaste("Февраль");
                monthlyReport.topProduct("Март");
                monthlyReport.biggestWaste("Март");
            } else if (userInput == 5) {
                yearlyReport.examination();
                yearlyReport.printAmountMonth();
                yearlyReport.averageSumYearTrue();
                yearlyReport.averageSumYearFalse();
            } else if (userInput == 0) {
                System.out.println("До свидания!");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }


    public static void printMenu() { // печать мен
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты.");
        System.out.println("2 - Считать годовой отчёт.");
        System.out.println("3 - Сверить отчёты.");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах.");
        System.out.println("5 - Вывести информацию о годовом отчёте.");
        System.out.println("0 - Выход.");
    }
}
