package com.pluralsight.NorthwindTradersAPI3.controllers;



import com.pluralsight.NorthwindTradersAPI3.dao.interfaces.IProductDao;
import com.pluralsight.NorthwindTradersAPI3.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private  IProductDao productDao;

    @Autowired
    public ProductsController(IProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable int productId) {
        return productDao.getById(productId);
    }
}
