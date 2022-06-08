package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;

public interface ProductService {

    public List<Product> findAll();
    
    public Product findById(int id);
    
    public int register(Product pd);
    
    public int delete(int id);
    
    public List<Product> findByKey(String key);
    
    public int update(Product pd);

	public List<Product> test();

}