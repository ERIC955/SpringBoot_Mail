package com.eric.springbootmall.dao;

import com.eric.springbootmall.constant.ProductCategory;
import com.eric.springbootmall.dto.ProductQueryParams;
import com.eric.springbootmall.dto.ProductRequest;
import com.eric.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    Integer conutProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void updateStock(Integer productId,Integer stock);

    void deleteProductById(Integer productId);
}
