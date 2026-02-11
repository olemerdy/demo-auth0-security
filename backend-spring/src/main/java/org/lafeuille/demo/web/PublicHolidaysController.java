package org.lafeuille.demo.web;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.Locale;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RestController
@RequestMapping("api/public/holidays")
public class PublicHolidaysController {

    @GetMapping("{isoYear}")
    public Set<Holiday> getYearHolidays(@PathVariable Year isoYear, Locale locale, @RequestParam(required = false) Set<HolidayType> types) {
        var holidayManager = HolidayManager.getInstance(ManagerParameters.create(locale));
        var holidays = holidayManager.getHolidays(isoYear);
        return holidays.stream().filter(holiday -> types == null || types.isEmpty() || types.contains(holiday.getType())).collect(toSet());
    }
}
