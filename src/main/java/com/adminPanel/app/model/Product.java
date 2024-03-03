package com.adminPanel.app.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Setter
@Getter
@NoArgsConstructor
@ApiModel("This is the model class responsible of product")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("The id of the product")
    private int id;

    @Column(name = "name")
    private String name;
    
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "product_details_id")
    private ProductDetails productDetails;

    public Product(String name) {
        this.name = name;
    }

    public Product(int id) {
        this.id = id;
    }

}
