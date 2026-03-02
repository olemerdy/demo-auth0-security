package org.lafeuille.demo.services

import de.focus_shift.jollyday.core.Holiday
import de.focus_shift.jollyday.core.HolidayManager
import de.focus_shift.jollyday.core.ManagerParameters
import org.springframework.stereotype.Service
import org.threeten.extra.LocalDateRange
import java.time.Year
import java.util.Locale
import java.util.Optional
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collectors

@Service
class PublicHolidaysService {
    fun getHolidays(
        dateRange: LocalDateRange,
        locale: Locale,
        calendar: Optional<String>,
    ): List<Holiday> =
        getManager(locale, calendar)
            .getHolidays(dateRange.start, dateRange.end)
            .stream()
            .sorted(Comparator.comparing(Function { obj: Holiday -> obj.date }))
            .collect(Collectors.toList())

    fun getYearHolidays(
        isoYear: Year,
        locale: Locale,
        calendar: Optional<String>,
    ): List<Holiday> =
        getManager(locale, calendar)
            .getHolidays(isoYear)
            .stream()
            .sorted(Comparator.comparing(Function { obj: Holiday -> obj.date }))
            .collect(Collectors.toList())

    private fun getManager(
        locale: Locale,
        calendar: Optional<String>,
    ): HolidayManager {
        val managerParameter =
            calendar
                .map(Function { calendarPart: String -> ManagerParameters.create(calendarPart) })
                .orElseGet(Supplier { ManagerParameters.create(locale) })
        return HolidayManager.getInstance(managerParameter)
    }
}
