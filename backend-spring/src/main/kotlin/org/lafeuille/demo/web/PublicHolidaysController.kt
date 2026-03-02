package org.lafeuille.demo.web

import de.focus_shift.jollyday.core.Holiday
import org.lafeuille.demo.services.PublicHolidaysService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.threeten.extra.LocalDateRange
import java.time.LocalDate
import java.time.Year
import java.util.Locale
import java.util.Optional

@RestController
@RequestMapping("api/public/holidays")
class PublicHolidaysController(
    private val publicHolidaysService: PublicHolidaysService,
) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getHolidays(
        @RequestParam start: LocalDate,
        @RequestParam end: LocalDate,
        locale: Locale,
        @RequestParam calendar: Optional<String>,
    ): List<Holiday> = publicHolidaysService.getHolidays(LocalDateRange.of(start, end), locale, calendar)

    @GetMapping(path = ["{isoYear}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getYearHolidays(
        @PathVariable isoYear: Year,
        locale: Locale,
        @RequestParam calendar: Optional<String>,
    ): List<Holiday> = publicHolidaysService.getYearHolidays(isoYear, locale, calendar)
}
