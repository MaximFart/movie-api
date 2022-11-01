package ru.sber.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sber.model.Film;
import ru.sber.model.Type;
import ru.sber.model.dto.FilmDto;
import ru.sber.model.exception.NotFoundException;
import ru.sber.service.FilmService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping
@Tag(name = "Films controller", description = "Working with films")
public class FilmsController {

    private final FilmService service;

    @Autowired
    public FilmsController(FilmService service) {
        this.service = service;
    }

    @PostMapping("/films")
    @Operation(summary = "Create film")
    public FilmDto save(@io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Film to add",
                        required = true,
                        content = @Content(schema = @Schema(implementation = Film.class))
                        ) @RequestBody @Valid Film film) {
        return service.save(film).toFilmDto();
    }

    @PostMapping("/films/list")
    @Operation(summary = "Create films")
    public List<FilmDto> saveAll(@io.swagger.v3.oas.annotations.parameters.RequestBody(
                                 description = "Films to add",
                                 required = true,
                                 content = @Content(schema = @Schema(implementation = Film.class))
                                 ) @RequestBody List<@Valid Film> films) {
        return service.saveAll(films).stream()
                .map(Film::toFilmDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/films/name/{name}")
    @Operation(summary = "Find films by name")
    public Page<FilmDto> findAllByName(@Parameter(description = "Name of film", required = true)
                                       @PathVariable("name") String name,
                                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        List<FilmDto> films = service.findAllByName(name).stream()
                .map(Film::toFilmDto)
                .collect(Collectors.toList());
        return new PageImpl<>(films, pageable, films.size());
    }
    @GetMapping("/films/type/{type}")
    @Operation(summary = "Find films by type")
    public Page<FilmDto> findAllByType(@Parameter(description = "Type of film", required = true)
                                       @PathVariable("type") String type,
                                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        List<FilmDto> films = service.findAllByType(Type.valueOf(type.toUpperCase())).stream()
                .map(Film::toFilmDto)
                .collect(Collectors.toList());
        return new PageImpl<>(films, pageable, films.size());
    }

    @GetMapping("/films/date_release")
    @Operation(summary = "Find films by date release")
    public Page<FilmDto> findAllByDateRelease(@Parameter(description = "Date release of film")
                                              @RequestParam(name = "date", required = false)
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        if (date == null)
            throw new NotFoundException("Date not found", HttpStatus.NOT_FOUND);
        List<FilmDto> films = service.findAllByDateRelease(date).stream()
                .map(Film::toFilmDto)
                .collect(Collectors.toList());
        return new PageImpl<>(films, pageable, films.size());
    }
}
