package ait.cohort49.shop_49.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "customer")
@Schema(description = "Class Customer")
public class Customer {
    @Schema(description = "Unique ID", example = "15", accessMode = Schema.AccessMode.READ_ONLY)
    @Id  //Главный ключ
    @Column(name = "id") //Связывает поля класса с колонкой в БД
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Name", example = "Simon")
    @Column
    private String name;

    @Schema(description = "isActive", accessMode = Schema.AccessMode.READ_ONLY)
    @Column
    private boolean active;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return String.format("Customer: id - %d, name - %s, active - %s",
                id, name, active ? "yes" : "no");
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Customer customer)) return false;

        return active == customer.active && Objects.equals(id, customer.id) && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Boolean.hashCode(active);
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
