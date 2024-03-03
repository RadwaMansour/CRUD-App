package com.adminPanel.app.service;

import com.adminPanel.app.dao.ProductDAO;
import com.adminPanel.app.exception.EmptyDataException;
import com.adminPanel.app.exception.ProductNotFoundException;
import com.adminPanel.app.model.Product;
import com.adminPanel.app.model.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductDAO productDAO;


    public ProductDetails insert(ProductDetails productDetails) throws EmptyDataException {
        if(!productDetails.getName().isEmpty()) {
            productDAO.insert(productDetails);
            return productDetails;
        }
        else
            throw new EmptyDataException("there is missing data");
    }


    public Product findById(int id) throws ProductNotFoundException {
        if(id <=0 )
            throw new ProductNotFoundException("the id is invalid - must be > 0");
        else {
            Product product=productDAO.findById(id);
            if(product==null)
                throw new ProductNotFoundException("there is no product with id = "+id);
            else
                return product;
        }
    }

    public void deleteById(int id) {
         productDAO.deleteById(id);
    }


    public ProductDetails update(ProductDetails productDetails) {

            Product product = productDAO.findByProductDetails(productDetails);
            if (product != null)
            {
                productDAO.update(productDetails);
            }
            else
                throw new NullPointerException();
        return productDetails;
    }

    public List<Product> getAllProducts() {
        try {
            return productDAO.getAllProducts();
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }


    public List<Product> findByName(String searchKey) {
        return productDAO.findByName(searchKey);
    }
}
