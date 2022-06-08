package com.example.demo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao pdDao;
    
    @Override
    public List<Product> findAll() {
    	return pdDao.findAll();
    }
    
    @Override
    public Product findById(int id) {
    	return pdDao.findById(id);
    }
    
    @Override
    public int register(Product pd) {
    	return pdDao.register(pd);
    }
    
    @Override
    public int delete(int id) {
    	return pdDao.delete(id);
    }
    
    @Override
    public List<Product> findByKey(String key) {
    	return pdDao.findByKey(key);
    }
    
    @Override
    public int update(Product pd) {
    	return pdDao.update(pd);
    }

	@Override
	public List<Product> test() {
		// TODO 自動生成されたメソッド・スタブ
		return pdDao.test();
	}
    
}

