package ru.sber.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import ru.sber.model.Film;
import ru.sber.model.Type;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class FilmRepositoryTest {
    @Autowired
    private FilmRepository repository;

    @Test
    void shouldPersistInstance() {
        Film expected = new Film("a", "a", Type.FULL_LENGTH, "a", LocalDate.of(2020,12,12));
        assertEquals(expected, repository.save(expected));
    }

    @Test
    void shouldPersistListInstances() {
        Film film1 = new Film("a", "a", Type.FULL_LENGTH, "a", LocalDate.of(2020,12,12));
        Film film2 = new Film("b", "b", Type.FULL_LENGTH, "b", LocalDate.of(2019,10,10));
        List<Film> expected = Arrays.asList(film1, film2);
        assertEquals(expected, repository.saveAll(expected));
    }

    @Test
    void shouldFindAllByName() {
        String name = "Bond";
        Film film1 = new Film(1L,"Bond", "film great", Type.SERIAL, "action movie", LocalDate.of(2017,12,20));
        Film film2 = new Film(2L, "Bond", "film great", Type.FULL_LENGTH, "comedy", LocalDate.of(2016,11,18));
        List<Film> expected = Arrays.asList(film1, film2);
        assertEquals(expected, repository.findAllByName(name));
    }

    @Test
    void shouldFindAllByType() {
        Type type = Type.FULL_LENGTH;
        Film film1 = new Film(2L,"Bond", "film great", Type.FULL_LENGTH, "comedy", LocalDate.of(2016,11,18));
        Film film2 = new Film(3L, "Karlson", "film great", Type.FULL_LENGTH, "comedy", LocalDate.of(2016,11,18));
        Film film3 = new Film(4L, "Aquaman", "film great", Type.FULL_LENGTH, "comedy", LocalDate.of(2016,11,18));
        Film film4 = new Film(6L, "Sherlock", "film great", Type.FULL_LENGTH, "detective", LocalDate.of(2020,5,20));
        List<Film> expected = Arrays.asList(film1, film2, film3, film4);
        assertEquals(expected, repository.findAllByType(type));
    }

    @Test
    void shouldFindAllByDateRelease() {
        LocalDate date = LocalDate.of(2019, 10, 18);
        Film film1 = new Film(8L,"Shameless", "film great", Type.SERIAL, "comedy", date);
        Film film2 = new Film(9L, "Flash", "film great", Type.SERIAL, "comedy", date);
        Film film3 = new Film(10L, "Money Heist", "film great", Type.SERIAL, "detective", date);
        List<Film> expected = Arrays.asList(film1, film2, film3);
        assertEquals(expected, repository.findAllByDateRelease(date));
    }

    @Test
    void shouldThrowConstraintViolationException() {
        assertThrows(ConstraintViolationException.class, () -> repository.save(new Film()));
    }

    @Test
    void shouldThrowInvalidDataAccessApiUsageException() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> repository.save(null));
    }
}
