package com.market.carmarketservice.model.product;

import com.market.carmarketservice.model.brand.Brand;
import com.market.carmarketservice.model.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String image;
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private Integer price;
    private String type;
    private String size;
    private String fuel;
    private String power;
    private String color;
    private String description;
    private String details;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Brand brand;
}
