package org.lafeuille.demo.web;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.util.Locale;
import java.util.Set;

@RestController
@RequestMapping("api/public/holidays")
public class PublicHolidaysController {

    @GetMapping("{isoYear}")
    public Set<Holiday> getYearHolidays(@PathVariable Year isoYear, Locale locale) {
        var holidayManager = HolidayManager.getInstance(ManagerParameters.create(locale));
        var holidays = holidayManager.getHolidays(isoYear);
        System.out.println(holidays);
        return holidays;
    }
}
