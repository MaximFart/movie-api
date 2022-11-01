package ru.sber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.model.Film;
import ru.sber.model.Type;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findAllByName(String name);
    List<Film> findAllByType(Type type);
    List<Film> findAllByDateRelease(LocalDate date);
}
