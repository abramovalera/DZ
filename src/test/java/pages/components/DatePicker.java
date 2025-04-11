package pages.components;

import static com.codeborne.selenide.Selenide.$;

// Компонент для работы с календарем
public class DatePicker {

    // Выбор месяца
    public DatePicker selectMonth(String month) {
        $(".react-datepicker__month-select").selectOption(month);
        return this; // Возвращает текущий объект для цепочки вызовов
    }

    // Выбор года
    public DatePicker selectYear(String year) {
        $(".react-datepicker__year-select").selectOption(year);
        return this;
    }

    // Выбор дня
    public DatePicker selectDay(String day) {
        String selector = String.format(
                ".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)",
                day
        );
        $(selector).click();
        return this;
    }
}