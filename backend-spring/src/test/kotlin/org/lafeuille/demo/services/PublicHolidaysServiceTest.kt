package org.lafeuille.demo.services

import de.focus_shift.jollyday.core.Holiday
import de.focus_shift.jollyday.core.HolidayCalendar
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.DayOfWeek
import java.time.Year
import java.util.Locale
import java.util.Optional
import java.util.stream.Stream

class PublicHolidaysServiceTest {
    lateinit var service: PublicHolidaysService

    @BeforeEach
    fun setUp() {
        service = PublicHolidaysService()
    }

    @ParameterizedTest
    @MethodSource("factory")
    fun holidays(
        calendar: HolidayCalendar,
        isoYear: Int,
        expectedDayCount: Int,
    ) {
        val actualWeekDayCount = numberOfHolidaysNotOnWeekendPerYear(calendar, isoYear)
        assertThat(actualWeekDayCount).isEqualTo(expectedDayCount.toLong())
    }

    fun numberOfHolidaysNotOnWeekendPerYear(
        calendar: HolidayCalendar,
        isoYear: Int,
    ): Long {
        val holidays: List<Holiday> =
            service.getYearHolidays(Year.of(isoYear), Locale.getDefault(), Optional.of(calendar.id))
        return holidays
            .stream()
            .filter { h: Holiday -> h.date.getDayOfWeek().value < DayOfWeek.SATURDAY.value }
            .count()
    }

    companion object {
        @JvmStatic
        fun factory(): Stream<Arguments> =
            Stream.of(
                Arguments.of(HolidayCalendar.FRANCE, 2020, 9),
                Arguments.of(HolidayCalendar.SWEDEN, 2020, 10),
                Arguments.of(HolidayCalendar.UNITED_KINGDOM, 2020, 6),
                Arguments.of(HolidayCalendar.FRANCE, 2021, 7),
                Arguments.of(HolidayCalendar.SWEDEN, 2021, 8),
                Arguments.of(HolidayCalendar.UNITED_KINGDOM, 2021, 6),
                Arguments.of(HolidayCalendar.FRANCE, 2022, 7),
                Arguments.of(HolidayCalendar.SWEDEN, 2022, 7),
                Arguments.of(HolidayCalendar.UNITED_KINGDOM, 2022, 8),
                Arguments.of(HolidayCalendar.FRANCE, 2023, 9),
                Arguments.of(HolidayCalendar.SWEDEN, 2023, 9),
                Arguments.of(HolidayCalendar.UNITED_KINGDOM, 2023, 7),
                Arguments.of(HolidayCalendar.FRANCE, 2024, 10),
                Arguments.of(HolidayCalendar.SWEDEN, 2024, 11),
                Arguments.of(HolidayCalendar.UNITED_KINGDOM, 2024, 6),
                Arguments.of(HolidayCalendar.FRANCE, 2025, 10),
                Arguments.of(HolidayCalendar.SWEDEN, 2025, 12),
                Arguments.of(HolidayCalendar.UNITED_KINGDOM, 2025, 6),
            )
    }
}
