package pages.components;

import static com.codeborne.selenide.Selenide.$;

public class DatePicker {
    public DatePicker selectMonth(String month) {
        $(".react-datepicker__month-select").selectOption(month);
        return this;
    }

    public DatePicker selectYear(String year) {
        $(".react-datepicker__year-select").selectOption(year);
        return this;
    }

    public DatePicker selectDay(String day) {
        String dayLocator = String.format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", day);
        $(dayLocator).click();
        return this;
    }
}