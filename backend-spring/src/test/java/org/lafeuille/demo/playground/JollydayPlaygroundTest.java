package org.lafeuille.demo.playground;

import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DayOfWeek;
import java.time.Year;
import java.util.stream.Stream;

import static de.focus_shift.jollyday.core.HolidayCalendar.*;
import static org.assertj.core.api.Assertions.assertThat;

public class JollydayPlaygroundTest {

    static Stream<Arguments> factory() {
        return Stream.of(
                Arguments.of(FRANCE, 2020, 9),
                Arguments.of(SWEDEN, 2020, 10),
                Arguments.of(UNITED_KINGDOM, 2020, 6),

                Arguments.of(FRANCE, 2021, 7),
                Arguments.of(SWEDEN, 2021, 8),
                Arguments.of(UNITED_KINGDOM, 2021, 6),

                Arguments.of(FRANCE, 2022, 7),
                Arguments.of(SWEDEN, 2022, 7),
                Arguments.of(UNITED_KINGDOM, 2022, 8),

                Arguments.of(FRANCE, 2023, 9),
                Arguments.of(SWEDEN, 2023, 9),
                Arguments.of(UNITED_KINGDOM, 2023, 7),

                Arguments.of(FRANCE, 2024, 10),
                Arguments.of(SWEDEN, 2024, 11),
                Arguments.of(UNITED_KINGDOM, 2024, 6),

                Arguments.of(FRANCE, 2025, 10),
                Arguments.of(SWEDEN, 2025, 12),
                Arguments.of(UNITED_KINGDOM, 2025, 6)
        );
    }

    @ParameterizedTest
    @MethodSource("factory")
    void holidays(HolidayCalendar calendar, int isoYear, int expectedDayCount) {
        var actualWeekDayCount = numberOfHolidaysNotOnWeekendPerYear(calendar, isoYear);
        assertThat(actualWeekDayCount).isEqualTo(expectedDayCount);
    }

    long numberOfHolidaysNotOnWeekendPerYear(HolidayCalendar calendar, int isoYear) {
        var holidayManager = HolidayManager.getInstance(ManagerParameters.create(calendar));
        var year = Year.of(isoYear);
        var holidays = holidayManager.getHolidays(year);
        return holidays.stream().filter(h -> h.getDate().getDayOfWeek().getValue() < DayOfWeek.SATURDAY.getValue()).count();
    }
}
