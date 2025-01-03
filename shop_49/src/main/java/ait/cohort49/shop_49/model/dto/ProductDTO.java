package ait.cohort49.shop_49.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Objects;


@Schema(description = "Class ProductDTO")
public class ProductDTO {

    @Schema(description = "Unique ID", example = "15", accessMode = Schema.AccessMode.READ_ONLY)
    @Id  //Главный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Title", example = "Banana")
    @NotNull(message = "Product title cannot be null") // title = null
    @NotBlank(message = "Product title cannot be empty") //title = "";
//    @Pattern(regexp = "^[A-ZА-Я][a-zа-я ]{2,}$", message = "Product title should be at least 3 characters long, start with a capital letter")
    @Pattern(regexp = "^[A-Z][a-z][a-zA-Z0-9 ]+$", message = "Product title should be at least 3 characters long, start with a capital letter")
    private String title;

    @Schema(description = "Price", example = "8.55")
    @DecimalMin(value = "0.0", message = "Product price shout be greater or equals then 0.0")
    @DecimalMax(value = "100000.0",inclusive = false, message = "Product price shout be les 100000.0")
    private BigDecimal price;


    public ProductDTO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof ProductDTO)) return false;

        ProductDTO that = (ProductDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(price);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Product: id - %d, title - %s, price - %s",
                id, title, price);
    }
}
