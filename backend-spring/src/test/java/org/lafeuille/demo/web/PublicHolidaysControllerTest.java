package org.lafeuille.demo.web;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.lafeuille.demo.security.SecurityConfiguration;
import org.lafeuille.demo.services.PublicHolidaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PublicHolidaysController.class)
@Import(SecurityConfiguration.class)
@EnableWebSecurity
class PublicHolidaysControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private PublicHolidaysService publicHolidaysService;

  @Test
  void GET_api_public_holidays_with_a_year_is_OK() throws Exception {
    when(publicHolidaysService.getYearHolidays(eq(Year.of(2025)), any(), eq(Optional.empty())))
        .thenReturn(getMockedHolidays());

    mockMvc.perform(get("/api/public/holidays/{year}", 2025))
        .andExpect(status().isOk())
        .andExpect(checkMockedHolidays())
        .andDo(print());
  }

  @Test
  void GET_api_public_holidays_with_a_year_and_calendar_is_OK() throws Exception {
    when(publicHolidaysService.getYearHolidays(eq(Year.of(2025)), any(), eq(Optional.of("fr"))))
        .thenReturn(getMockedHolidays());

    mockMvc.perform(get("/api/public/holidays/{year}", 2025).queryParam("calendar", "fr"))
        .andExpect(status().isOk())
        .andExpect(checkMockedHolidays())
        .andDo(print());
  }

  @Test
  void GET_api_public_holidays_with_not_a_year_is_BAD_REQUEST() throws Exception {
    mockMvc.perform(get("/api/public/holidays/{year}", "foobar"))
        .andExpect(status().isBadRequest());
  }

  private static List<Holiday> getMockedHolidays() {
    return List.of(
        new Holiday(LocalDate.of(2025, Month.JANUARY, 1), null, HolidayType.PUBLIC_HOLIDAY),
        new Holiday(LocalDate.of(2025, Month.MAY, 1), null, HolidayType.PUBLIC_HOLIDAY)
    );
  }

  private static ResultMatcher checkMockedHolidays() {
    return result -> {
      jsonPath("$[0].date").value("2025-01-01").match(result);
      jsonPath("$[0].actualDate").value("2025-01-01").match(result);
      jsonPath("$[0].observedDate").isEmpty().match(result);
      jsonPath("$[0].type").value(HolidayType.PUBLIC_HOLIDAY.name()).match(result);
      jsonPath("$[1].date").value("2025-05-01").match(result);
      jsonPath("$[1].actualDate").value("2025-05-01").match(result);
      jsonPath("$[1].observedDate").isEmpty().match(result);
      jsonPath("$[1].type").value(HolidayType.PUBLIC_HOLIDAY.name()).match(result);
    };
  }

}