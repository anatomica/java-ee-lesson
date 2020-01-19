package store;

import javax.inject.Named;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Named
public class Catalog {

    private Long id;
    @NotNull (message = "Поле не должно быть пустым")
    @Size (min = 2, max = 20, message = "Поле должно содержать от 4 до 10 символов")
    private String name;
    @NotNull (message = "Поле не должно быть пустым")
    @Size (min = 2, max = 20, message = "Поле должно содержать от 4 до 10 символов")
    private String description;
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

}