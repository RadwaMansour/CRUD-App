package com.adminPanel.app.service;

import com.adminPanel.app.exception.EmptyDataException;
import com.adminPanel.app.exception.ProductNotFoundException;
import com.adminPanel.app.model.Product;
import com.adminPanel.app.model.ProductDetails;

import java.util.List;


public interface ProductService
{
    ProductDetails insert(ProductDetails product) throws EmptyDataException;
    Product findById(int id) throws ProductNotFoundException;
    void deleteById(int id);
    ProductDetails update(ProductDetails product);
    List<Product> getAllProducts();

    List<Product> findByName(String searchKey);

}
