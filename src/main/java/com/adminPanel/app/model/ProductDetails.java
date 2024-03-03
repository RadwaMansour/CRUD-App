package com.adminPanel.app.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;

@Entity
@Table(name = "product_details")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("This is the model class responsible of Product Details")
public class ProductDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("The id of the Product Details")
    private int id;

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "price")
    private Double price;

    @Column(name = "available")
    private Boolean available;

    @JsonIgnore
    @OneToOne(mappedBy = "productDetails" , cascade = CascadeType.ALL)
    private Product product;

    public ProductDetails(int id, String name, Date expirationDate, String manufacturer, Double price, Boolean available) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
        this.manufacturer = manufacturer;
        this.price = price;
        this.available = available;
    }

    public ProductDetails(String name, Date expirationDate, String manufacturer, Double price, Boolean available) {

        this.name = name;
        this.expirationDate = expirationDate;
        this.manufacturer = manufacturer;
        this.price = price;
        this.available = available;
    }


}
