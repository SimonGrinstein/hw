package ait.cohort49.shop_49.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Objects;

@Schema(description = "Class CustomerDTO")
public class CustomerDTO {

    @Schema(description = "Unique ID", example = "55", accessMode = Schema.AccessMode.READ_ONLY)
    @Id  //Главный ключ
    @Column(name = "id") //Связывает поля класса с колонкой в БД
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


    public CustomerDTO() {
    }

    @Override
    public String toString() {
        return String.format("Customer: id - %d, name - %s",
                id, name);
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof CustomerDTO that)) return false;

        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        return result;
    }

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


}
