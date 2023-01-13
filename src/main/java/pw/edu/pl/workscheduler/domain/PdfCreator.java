package pw.edu.pl.workscheduler.domain;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.BorderRadius;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PdfCreator {
    private final Schedule schedule;

    void export(HttpServletResponse response) throws IOException {

        PdfDocument pdf = new PdfDocument(new PdfWriter(response.getOutputStream()));
        Document document = new Document(pdf, getPageSize());

        prepareDocumentHeader(document);


        document.add(prepareTable(false));
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        prepareDocumentHeader(document);
        document.add(prepareTable(true));

        document.close();
    }

    private Table prepareTable(boolean isForEmployee) {
        Table table = new Table(7);
        table.setWidth(UnitValue.createPercentValue(100));

        prepareCalendarHeaders(table);

        addBlankCells(table);

        for (ShiftDay shiftDay : schedule.getShiftDays()) {
            Cell cell = prepareCell(shiftDay);

            if (isWeekend(shiftDay)) {
                cell.setFontColor(ColorConstants.RED);
            } else {
                addShiftsToCell(cell, shiftDay, isForEmployee);
            }
            table.addCell(cell);
        }
        return table;
    }

    private PageSize getPageSize() {
        int shiftNumber = schedule.getShiftDays().get(0).getShiftsForADay().size();

        return switch (shiftNumber) {
            case 1, 2 -> PageSize.A3;
            case 3,4 -> PageSize.A2;
            default -> PageSize.A1;
        };
    }

    private void addShiftsToCell(Cell cell, ShiftDay shiftDay, boolean isForEmployee) {


        int i = 0;
        for (Shift shift : shiftDay.getShiftsForADay()) {
            Div div =
                    new Div()
                        .setBackgroundColor(
                                    getCellBackgroundColor(shift.getEmployee(), isForEmployee, i++))
                            .setBorder(new SolidBorder(ColorConstants.BLACK, 1))
                            .setBorderRadius(new BorderRadius(5))
                            .setPadding(5)
                            .setMarginBottom(5)
                            .add(
                                    new Paragraph(getEmployeeName(shift.getEmployee()))
                                            .setBold()
                                            .setFontColor(ColorConstants.BLACK))
                            .add(
                                    new Paragraph(
                                                    shift.getStartTime()
                                                                    .toLocalTime()
                                                                    .format(
                                                                            DateTimeFormatter
                                                                                    .ofPattern(
                                                                                            "HH:mm"))
                                                            + " - "
                                                            + shift.getEndTime()
                                                                    .toLocalTime()
                                                                    .format(
                                                                            DateTimeFormatter
                                                                                    .ofPattern(
                                                                                            "HH:mm")))
                                            .setStrokeWidth(20)
                                            .setStrokeColor(ColorConstants.BLACK)
                                            .setFontSize(10)
                                            .setTextAlignment(TextAlignment.CENTER));
            cell.add(div);
        }
    }

    private Color getCellBackgroundColor(Employee employee, boolean isForEmployee, int i) {

        Color[] colors = {
            new DeviceRgb(223, 194, 125),
            new DeviceRgb(199, 234, 229),
            new DeviceRgb(246, 232, 195),
            new DeviceRgb(128, 205, 193),
            new DeviceRgb(245, 245, 245),
            new DeviceRgb(191, 129, 45),
            new DeviceRgb(53, 151, 143),
            new DeviceRgb(140, 81, 10),
            new DeviceRgb(1, 102, 94)
        };

        if (employee == null) {
            return ColorConstants.RED;
        }

        if (isForEmployee) {
            return colors[(int) (employee.getId() % 9)];
        } else {
            return colors[i];
        }

    }

    private String getEmployeeName(Employee employee) {
        return employee != null ? employee.getName() : "---------";
    }

    private Cell prepareCell(ShiftDay shiftDay) {
        Cell cell =
                new Cell()
                        .add(
                                new Paragraph(String.valueOf(shiftDay.getDate().getDayOfMonth()))
                                        .setTextAlignment(TextAlignment.LEFT)
                                        .setBold());
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cell.setTextAlignment(TextAlignment.CENTER);
        return cell;
    }

    private boolean isWeekend(ShiftDay shiftDay) {
        return shiftDay.isWeekend();
    }

    private void addBlankCells(Table table) {
        int currentMonth = schedule.getMonth().getMonth().getValue();
        int currentYear = schedule.getMonth().getYear();

        Calendar calendar = new GregorianCalendar();
        calendar.set(currentYear, currentMonth, 1);
        int blankCells = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        for (int i = 0; i < blankCells; i++) {
            table.addCell("");
        }
    }

    private void prepareCalendarHeaders(Table table) {
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            Cell cell =
                    new Cell()
                            .add(
                                    new Paragraph(day)
                                            .setTextAlignment(TextAlignment.CENTER)
                                            .setBold());

            cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
            cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell.setVerticalAlignment(VerticalAlignment.MIDDLE);

            table.addCell(cell);
        }
    }

    private void prepareDocumentHeader(Document document) {
        document.add(
                new Paragraph(schedule.getScheduleName())
                        .setFontSize(30)
                        .setBold()
                        .setTextAlignment(TextAlignment.CENTER));

        document.add(
                new Paragraph("Date: " + getMonthName() + " " + schedule.getMonth().getYear())
                        .setFontSize(20)
                        .setBold()
                        .setTextAlignment(TextAlignment.CENTER));

        document.add(
                new Paragraph("Manager: " + schedule.getManagerName())
                        .setFontSize(15)
                        .setTextAlignment(TextAlignment.CENTER));
    }

    private String getMonthName() {
        String monthName = schedule.getMonth().getMonth().name();
        return monthName.substring(0, 1).toUpperCase() + monthName.substring(1).toLowerCase();
    }
}
