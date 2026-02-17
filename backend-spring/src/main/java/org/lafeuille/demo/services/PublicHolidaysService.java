package org.lafeuille.demo.services;

import static java.util.stream.Collectors.toList;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.threeten.extra.LocalDateRange;

@Service
public class PublicHolidaysService {

  public List<Holiday> getHolidays(LocalDateRange dateRange, Locale locale, Optional<String> country) {
    return getManager(locale, country)
        .getHolidays(dateRange.getStart(), dateRange.getEnd())
        .stream()
        .sorted(Comparator.comparing(Holiday::getDate))
        .collect(toList());
  }

  public List<Holiday> getYearHolidays(Year isoYear, Locale locale, Optional<String> country) {
    return getManager(locale, country)
        .getHolidays(isoYear)
        .stream()
        .sorted(Comparator.comparing(Holiday::getDate))
        .collect(toList());
  }

  private HolidayManager getManager(Locale locale, Optional<String> country) {
    var managerParameter = country.map(ManagerParameters::create)
        .orElseGet(() -> ManagerParameters.create(locale));
    return HolidayManager.getInstance(managerParameter);
  }

}
