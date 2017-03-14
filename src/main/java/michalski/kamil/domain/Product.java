package michalski.kamil.domain;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private long productId;

    @NotEmpty
    private String name;

    @NotNull
    private Double unitPrice;

    private Boolean discontinued;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String conditions;

    private Integer unitsInStock;

    private Integer unitsInOrder;

    private String manufacturer;

    @Transient
    private CommonsMultipartFile productImage;

    public CommonsMultipartFile getProductImage() {
        return productImage;
    }

    public void setProductImage(CommonsMultipartFile productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public long getProductId() {
        return productId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Boolean getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Boolean discontinued) {
        this.discontinued = discontinued;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public Integer getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Integer unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public Integer getUnitsInOrder() {
        return unitsInOrder;
    }

    public void setUnitsInOrder(Integer unitsInOrder) {
        this.unitsInOrder = unitsInOrder;
    }
}
