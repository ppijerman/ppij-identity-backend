package org.ppijerman.ppijidentitybackend.server.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.JdbcSessionDataSourceScriptDatabaseInitializer;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
public class WebControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private JdbcSessionDataSourceScriptDatabaseInitializer jdbcSessionDataSourceScriptDatabaseInitializer;

    @BeforeEach
    public void SetUp() {
        Mockito.when(jdbcSessionDataSourceScriptDatabaseInitializer.initializeDatabase()).thenReturn(true);
    }

    @TestFactory
    public Collection<DynamicTest> testPublicUrlAccessible() {
        return Stream.of(
                "/",
                "/privacy-policy",
                "/about",
                "/register"
        ).map(url -> DynamicTest.dynamicTest("Test access " + url, () -> testUrlAccessible(url)))
                .collect(Collectors.toUnmodifiableList());
    }

    private void testUrlAccessible(String url) throws Exception {
        mvc.perform(get(url))
                .andExpect(status().is2xxSuccessful());
    }
}
