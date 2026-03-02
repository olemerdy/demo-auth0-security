package org.lafeuille.demo.web

import de.focus_shift.jollyday.core.Holiday
import de.focus_shift.jollyday.core.HolidayType
import org.junit.jupiter.api.Test
import org.lafeuille.demo.security.SecurityConfiguration
import org.lafeuille.demo.services.PublicHolidaysService
import org.mockito.ArgumentMatchers
import org.mockito.kotlin.whenever
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDate
import java.time.Month
import java.time.Year
import java.util.Optional

@WebMvcTest(PublicHolidaysController::class)
@Import(SecurityConfiguration::class)
@EnableWebSecurity
class PublicHolidaysControllerTest(
    private val mockMvc: MockMvc,
) {
    @MockitoBean
    private val publicHolidaysService: PublicHolidaysService? = null

    @Test
    @Throws(Exception::class)
    fun get_api_public_holidays_with_a_year_is_OK() {
        whenever {
            publicHolidaysService!!.getYearHolidays(
                ArgumentMatchers.eq(Year.of(2025)),
                ArgumentMatchers.any(),
                ArgumentMatchers.eq(
                    Optional.empty<String>(),
                ),
            )
        }.thenReturn(mockedHolidays)

        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/public/holidays/{year}", 2025))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(checkMockedHolidays())
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @Throws(Exception::class)
    fun get_api_public_holidays_with_a_year_and_calendar_is_OK() {
        whenever {
            publicHolidaysService!!.getYearHolidays(
                ArgumentMatchers.eq(Year.of(2025)),
                ArgumentMatchers.any(),
                ArgumentMatchers.eq(
                    Optional.of<String>("fr"),
                ),
            )
        }.thenReturn(mockedHolidays)

        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/public/holidays/{year}", 2025).queryParam("calendar", "fr"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(checkMockedHolidays())
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @Throws(Exception::class)
    fun get_api_public_holidays_with_not_a_year_is_BAD_REQUEST() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/public/holidays/{year}", "foobar"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
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
            ResultMatcher { result: MvcResult? ->
                MockMvcResultMatchers.jsonPath("$[0].date").value("2025-01-01").match(result)
                MockMvcResultMatchers.jsonPath("$[0].actualDate").value("2025-01-01").match(result)
                MockMvcResultMatchers.jsonPath("$[0].observedDate").isEmpty().match(result)
                MockMvcResultMatchers.jsonPath("$[0].type").value(HolidayType.PUBLIC_HOLIDAY.name).match(result)
                MockMvcResultMatchers.jsonPath("$[1].date").value("2025-05-01").match(result)
                MockMvcResultMatchers.jsonPath("$[1].actualDate").value("2025-05-01").match(result)
                MockMvcResultMatchers.jsonPath("$[1].observedDate").isEmpty().match(result)
                MockMvcResultMatchers.jsonPath("$[1].type").value(HolidayType.PUBLIC_HOLIDAY.name).match(result)
            }
    }
}
