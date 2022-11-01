package ru.sber.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.sber.model.Film;
import ru.sber.model.Type;
import ru.sber.repository.FilmRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FilmServiceTest {
    @MockBean
    private FilmRepository repository;

    @Autowired
    private FilmService service;

    @Test
    public void shouldPersistInstance() {
        Film expected = new Film();
        when(repository.save(expected)).thenReturn(expected);
        assertEquals(expected, service.save(expected));
    }

    @Test
    public void shouldPersistListInstances() {
        List<Film> expected = new ArrayList<>();
        when(repository.saveAll(expected)).thenReturn(expected);
        assertEquals(expected, service.saveAll(expected));
    }

    @Test
    public void shouldFindAllByNameWhenReturnPage() {
        String name = "";
        List<Film> expected = Arrays.asList(new Film(), new Film());
        when(repository.findAllByName(name)).thenReturn(expected);
        assertEquals(expected, service.findAllByName(name));
    }

    @Test
    public void shouldFindAllByTypeWhenReturnPage() {
        Type type = Type.FULL_LENGTH;
        List<Film> expected = Arrays.asList(new Film(), new Film());
        when(repository.findAllByType(type)).thenReturn(expected);
        assertEquals(expected, service.findAllByType(type));
    }

    @Test
    public void shouldFindAllByDateReleaseWhenReturnPage() {
        LocalDate date = LocalDate.of(2019, 10, 18);
        List<Film> expected = Arrays.asList(new Film(), new Film());
        when(repository.findAllByDateRelease(date)).thenReturn(expected);
        assertEquals(expected, service.findAllByDateRelease(date));
    }

}
