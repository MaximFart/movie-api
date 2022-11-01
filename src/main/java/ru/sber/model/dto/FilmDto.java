package ru.sber.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import ru.sber.model.Type;

import java.time.LocalDate;
import java.util.Objects;

@Schema(description = "Film Entity")
public class FilmDto {

    @Schema(description = "Identifier")
    private Long id;
    @Schema(description = "Name of film")
    private String name;
    @Schema(description = "Description of film")
    private String description;
    @Schema(description = "Type of film")
    private Type type;
    @Schema(description = "Style of film")
    private String style;
    @Schema(description = "Release date of the film")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateRelease;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        FilmDto filmDTO = (FilmDto) o;
        return Objects.equals(id, filmDTO.id) && Objects.equals(name, filmDTO.name) && Objects.equals(description, filmDTO.description) && Objects.equals(style, filmDTO.style) && Objects.equals(dateRelease, filmDTO.dateRelease);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, style, dateRelease);
    }

    @Override
    public String toString() {
        return "FilmDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", style='" + style + '\'' +
                ", dateRelease=" + dateRelease +
                '}';
    }
}
