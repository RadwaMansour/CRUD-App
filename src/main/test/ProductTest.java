package test;

import com.adminPanel.app.model.Product;
import com.adminPanel.app.model.ProductDetails;
import com.sun.xml.internal.ws.policy.AssertionSet;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class ProductTest {

    private RestTemplate restTemplate = new RestTemplate();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");

    @Test
    public void testFindById() {

        String url = "http://localhost:8080/products?productId=2";
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(url, Product.class);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testInsertionProduct() throws ParseException {

        ProductDetails productDetails = new ProductDetails("Tea", dateFormat.parse("2028-5-5"),
                "Tea", 20.0, true);

        String url = "http://localhost:8080/products";
        ProductDetails product = restTemplate.postForObject(url, productDetails,
                ProductDetails.class);
        System.out.println(product.getName());
        System.out.println(productDetails.getName());
        Assert.assertEquals(product.getName(),productDetails.getName());
    }


    @Test
    public void testDeletionProduct() throws ParseException {


        String url = "http://localhost:8080/products?productId=11";

        try {
            ResponseEntity<Product> responseEntity = restTemplate.getForEntity(url, Product.class);
            restTemplate.delete(url);
             Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            System.out.println("deleted");

        } catch (HttpClientErrorException.NotFound ex) {
            String responseBody = ex.getResponseBodyAsString();
            System.out.println("404 Error: " + responseBody);
        }


    }




    @Test
    public void testUpdateProduct() throws ParseException {

        String url = "http://localhost:8080p/products?productId=1";
        ProductDetails updatedDetails = new ProductDetails(1,
                "Tea", dateFormat.parse("2028-5-5"),
                "Tea", 60.0, true);
        HttpEntity<ProductDetails> requestEntity = new HttpEntity<>(updatedDetails);

        ResponseEntity<ProductDetails> responseEntity = restTemplate.exchange(
                url, HttpMethod.PUT, requestEntity, ProductDetails.class);

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

    }



}
