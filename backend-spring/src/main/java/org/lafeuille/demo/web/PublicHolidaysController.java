package org.lafeuille.demo.web;

import de.focus_shift.jollyday.core.Holiday;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.lafeuille.demo.services.PublicHolidaysService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.Locale;
import org.threeten.extra.LocalDateRange;

@RestController
@RequestMapping("api/public/holidays")
@RequiredArgsConstructor
public class PublicHolidaysController {

  private final PublicHolidaysService publicHolidaysService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Holiday> getHolidays(@RequestParam LocalDate start, @RequestParam LocalDate end,
      Locale locale, @RequestParam Optional<String> country) {
    return publicHolidaysService.getHolidays(LocalDateRange.of(start, end), locale, country);
  }

  @GetMapping(path = "{isoYear}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Holiday> getYearHolidays(@PathVariable Year isoYear, Locale locale,
      @RequestParam Optional<String> country) {
    return publicHolidaysService.getYearHolidays(isoYear, locale, country);
  }
}
