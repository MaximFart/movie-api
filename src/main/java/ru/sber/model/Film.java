package ru.sber.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import ru.sber.model.dto.FilmDto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "types")
    private Type type;
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "style")
    private String style;
    @NotNull
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "date_release")
    private LocalDate dateRelease;

    public Film() {
    }

    public Film(@NotEmpty String name, @NotEmpty String description, @NotNull Type type, @NotEmpty String style, @NotNull LocalDate dateRelease) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.style = style;
        this.dateRelease = dateRelease;
    }

    public Film(Long id, @NotEmpty String name, @NotEmpty String description, @NotNull Type type, @NotEmpty String style, @NotNull LocalDate dateRelease) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.style = style;
        this.dateRelease = dateRelease;
    }

    public FilmDto toFilmDto() {
        FilmDto dto = new FilmDto();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(description);
        dto.setStyle(style);
        dto.setType(type);
        dto.setDateRelease(dateRelease);
        return dto;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public LocalDate getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(LocalDate dateRelease) {
        this.dateRelease = dateRelease;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(id, film.id) && Objects.equals(name, film.name) && Objects.equals(description, film.description) && type == film.type && Objects.equals(style, film.style) && Objects.equals(dateRelease, film.dateRelease);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, type, style, dateRelease);
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", style='" + style + '\'' +
                ", dateRelease=" + dateRelease +
                '}';
    }
}
