package store;

import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

@Table(name = "products")
@Entity
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4096, nullable = false)
    @NotNull (message = "Поле не должно быть пустым")
    @Size (min = 2, max = 20, message = "Поле должно содержать от 4 до 10 символов")
    private String name;

    @Column(length = 10000)
    @NotNull (message = "Поле не должно быть пустым")
    @Size (min = 2, max = 20, message = "Поле должно содержать от 4 до 10 символов")
    private String description;

    @Column
    @NotNull (message = "Поле не должно быть пустым")
    @Digits (integer = 6, fraction = 2)
    private BigDecimal price;

    public Catalog() {
    }

    public Catalog(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return Objects.equals(id, catalog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}