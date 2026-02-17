package org.lafeuille.demo.web;

import org.junit.jupiter.api.Test;
import org.lafeuille.demo.security.SecurityConfiguration;
import org.lafeuille.demo.services.PublicHolidaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    void fun() throws Exception {
        mockMvc.perform(get("/api/public/holidays/2025"))
                .andExpect(status().isOk());
    }

}