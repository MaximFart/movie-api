package ru.sber.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.sber.model.Film;
import ru.sber.model.Type;
import ru.sber.model.dto.FilmDto;
import ru.sber.service.FilmService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class FilmsControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private FilmService filmService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void shouldPersistInstance() throws Exception {
        Film expected = new Film(1L,"Bond", "film great", Type.SERIAL, "action movie", LocalDate.of(2017,12,20));
        String filmJson = mapper.writeValueAsString(expected);
        when(filmService.save(any(Film.class))).thenReturn(expected);
        MvcResult mvcResult = mockMvc.perform(post("/films").contentType(MediaType.APPLICATION_JSON)
                        .content(filmJson))
                        .andExpect(status().isOk())
                        .andReturn();
        FilmDto actual = mapper.readValue(mvcResult.getResponse().getContentAsString(), FilmDto.class);
        assertEquals(expected.toFilmDto(), actual);
    }

    @Test
    void shouldPersistAllInstances() throws Exception {
        List<Film> expected = Arrays.asList(
                new Film(1L,"Bond", "film great", Type.SERIAL, "action movie", LocalDate.of(2017,12,20)),
                new Film(2L,"Bond", "film great", Type.FULL_LENGTH, "action movie", LocalDate.of(2020,12,20)));
        String filmJson = mapper.writeValueAsString(expected);
        when(filmService.saveAll(expected)).thenReturn(expected);
        MvcResult mvcResult = mockMvc.perform(post("/films/list").contentType(MediaType.APPLICATION_JSON)
                        .content(filmJson))
                        .andExpect(status().isOk())
                        .andReturn();
        List<FilmDto> actual = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<FilmDto>>() {
        });
        assertEquals(expected.stream().map(Film::toFilmDto).collect(Collectors.toList()), actual);
    }
}
