package ru.sber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sber.model.Film;
import ru.sber.model.Type;
import ru.sber.repository.FilmRepository;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

@Service
public class FilmService {
    private final FilmRepository repository;

    @Autowired
    public FilmService(FilmRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Film save(Film film) {
        return repository.save(film);
    }

    @Transactional
    public List<Film> saveAll(List<Film> films) {
        return repository.saveAll(films);
    }

    @Transactional
    public List<Film> findAllByName(String name) {
        return repository.findAllByName(name);
    }

    @Transactional
    public List<Film> findAllByType(Type type) {
        return repository.findAllByType(type);
    }

    @Transactional
    public List<Film> findAllByDateRelease(LocalDate date) {
        return repository.findAllByDateRelease(date);
    }
}
