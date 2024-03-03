package com.adminPanel.app.controller;

import com.adminPanel.app.exception.EmptyDataException;
import com.adminPanel.app.exception.ProductNotFoundException;
import com.adminPanel.app.model.Product;
import com.adminPanel.app.model.ProductDetails;
import com.adminPanel.app.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.List;


//@RestController = @Controller + @RequestBody
@RestController

@Api(tags = "This is the documentation for product Apis")
public class HomeController
{
    @Autowired
    private ProductService productService;


    // add Product
    @PostMapping("/products")
    @ApiOperation(value = "This is api wil be used to insert the product")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "this is the successful response"),
            @ApiResponse(code = 404,message = "Not found product"),
            @ApiResponse(code = 500,message = "error of server")
    }
    )
    public ProductDetails insertProduct (@RequestBody ProductDetails productDetails) throws EmptyDataException {
      return productService.insert(productDetails);
    }

    // update Product
    @PutMapping("/products")
    @ApiOperation(value = "This is api wil be used to update the product")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "this is the successful response"),
            @ApiResponse(code = 404,message = "Not found product"),
            @ApiResponse(code = 500,message = "error of server")
    }
    )
    public ProductDetails updateProduct (@RequestBody ProductDetails productDetails)
    {
       return productService.update(productDetails);
    }


    // delete Product
    @DeleteMapping("/products")
    @ApiOperation(value = "This is api wil be used to delete the product")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "this is the successful response"),
            @ApiResponse(code = 404,message = "Not found product"),
            @ApiResponse(code = 500,message = "error of server")
    }
    )
    public void deleteProduct(@RequestParam("productId") int id) {

        productService.deleteById(id);
    }

    // search by id --> productDetails
    @GetMapping("/products/productDetailsById")
    @ApiOperation(value = "This is api wil be used to get the product details by id")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "this is the successful response"),
            @ApiResponse(code = 404,message = "Not found product"),
            @ApiResponse(code = 500,message = "error of server")
    }
    )
    public ProductDetails getProductDetailsById(@RequestParam("productId") int id) throws ProductNotFoundException {

        //get the product details related to the id
        return productService.findById(id).getProductDetails();
    }

    // search by name --> productDetails
    @GetMapping("/products/productDetailsByName")
    @ApiOperation(value = "This is api wil be used to get the product details by name")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "this is the successful response"),
            @ApiResponse(code = 404,message = "Not found product"),
            @ApiResponse(code = 500,message = "error of server")
    }
    )
    public ProductDetails getProductDetailsByName(@RequestParam("productName") String name) {

        //get the product related to the name
        return productService.findByName(name).get(0).getProductDetails();
    }

    // get all products
    @GetMapping("/products/list")
    @ApiOperation(value = "This is api wil be used to get list of the products")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "this is the successful response"),
            @ApiResponse(code = 404,message = "Not found product"),
            @ApiResponse(code = 500,message = "error of server")
    }
    )
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    // search by id --> product
    @GetMapping("/products")
    @ApiOperation(value = "This is api wil be used to get the product by id")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "this is the successful response"),
            @ApiResponse(code = 404,message = "Not found product"),
            @ApiResponse(code = 500,message = "error of server")
    }
    )
    public Product getProductById(@RequestParam("productId") int id) throws ProductNotFoundException {

        //get the product related to the id
        Product product = productService.findById(id);

        return product;
    }

    // test app
    @GetMapping("/testApp")
    @ApiIgnore
    public String testAppStatus(){
        return "the app is now running";
    }
}
