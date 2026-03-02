package org.lafeuille.demo.web

import de.focus_shift.jollyday.core.Holiday
import de.focus_shift.jollyday.core.HolidayType
import org.junit.jupiter.api.Test
import org.lafeuille.demo.security.SecurityConfiguration
import org.lafeuille.demo.services.PublicHolidaysService
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate
import java.time.Month
import java.time.Year
import java.util.Optional

@WebMvcTest(PublicHolidaysController::class)
@Import(SecurityConfiguration::class)
@EnableWebSecurity
class PublicHolidaysControllerTest(
    @param:Autowired val mockMvc: MockMvc,
) {
    @MockitoBean
    lateinit var publicHolidaysService: PublicHolidaysService

    @Test
    @Throws(Exception::class)
    fun get_api_public_holidays_with_a_year_is_OK() {
        whenever(
            publicHolidaysService.getYearHolidays(
                eq(Year.of(2025)),
                any(),
                eq(
                    Optional.empty<String>(),
                ),
            )
        ).thenReturn(mockedHolidays)

        mockMvc
            .perform(get("/api/public/holidays/{year}", 2025))
            .andExpect(status().isOk())
            .andExpect(checkMockedHolidays())
            .andDo(print())
    }

    @Test
    @Throws(Exception::class)
    fun get_api_public_holidays_with_a_year_and_calendar_is_OK() {
        whenever(
            publicHolidaysService.getYearHolidays(
                eq(Year.of(2025)),
                any(),
                eq(
                    Optional.of<String>("fr"),
                ),
            )
        ).thenReturn(mockedHolidays)

        mockMvc
            .perform(get("/api/public/holidays/{year}", 2025).queryParam("calendar", "fr"))
            .andExpect(status().isOk())
            .andExpect(checkMockedHolidays())
            .andDo(print())
    }

    @Test
    @Throws(Exception::class)
    fun get_api_public_holidays_with_not_a_year_is_BAD_REQUEST() {
        mockMvc
            .perform(get("/api/public/holidays/{year}", "foobar"))
            .andExpect(status().isBadRequest())
    }

    companion object {
        private val mockedHolidays: List<Holiday>
            get() =
                listOf(
                    Holiday(
                        LocalDate.of(2025, Month.JANUARY, 1),
                        null,
                        HolidayType.PUBLIC_HOLIDAY,
                    ),
                    Holiday(
                        LocalDate.of(2025, Month.MAY, 1),
                        null,
                        HolidayType.PUBLIC_HOLIDAY,
                    ),
                )

        private fun checkMockedHolidays(): ResultMatcher =
            ResultMatcher { result: MvcResult ->
                jsonPath("$[0].date").value("2025-01-01").match(result)
                jsonPath("$[0].actualDate").value("2025-01-01").match(result)
                jsonPath("$[0].observedDate").isEmpty().match(result)
                jsonPath("$[0].type").value(HolidayType.PUBLIC_HOLIDAY.name).match(result)
                jsonPath("$[1].date").value("2025-05-01").match(result)
                jsonPath("$[1].actualDate").value("2025-05-01").match(result)
                jsonPath("$[1].observedDate").isEmpty().match(result)
                jsonPath("$[1].type").value(HolidayType.PUBLIC_HOLIDAY.name).match(result)
            }
    }
}
