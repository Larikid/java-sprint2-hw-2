import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ReportEngine engine = new ReportEngine();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            if (userInput == 1) {
                engine.readMonthlyReports();
               System.out.println("Файлы успешно считаны.");
            } else if (userInput == 2) {
                engine.readYearReport();
                System.out.println("Файл успешно считан.");
            } else if (userInput == 3) {
                engine.reconciliationReports();
            } else if (userInput == 4) {
                engine.topProduct();
                engine.biggestWaste();
            } else if (userInput == 5) {
                engine.printStatistic();
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
}
