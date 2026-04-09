package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate from = LocalDate.parse(dateFrom.trim(), FORMAT);
        LocalDate to = LocalDate.parse(dateTo.trim(), FORMAT);
        int[] totalSalary = new int[names.length];

        for (String record : data) {

            String[] parts = record.split(" ");
            LocalDate recordDate = LocalDate.parse(parts[0], FORMAT);
            String name = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);

            if (!recordDate.isBefore(from) && !recordDate.isAfter(to)) {

                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        totalSalary[i] += hours * rate;
                    }
                }
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            result.append(names[i])
                    .append(" - ")
                    .append(totalSalary[i])
                    .append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
