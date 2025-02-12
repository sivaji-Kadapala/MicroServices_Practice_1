package com.redis.demo.repo;

import com.redis.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductDao {
    public static final String HASH_KEY="Product";
    @Autowired
    private RedisTemplate<String,Object> template;
    public Product save(Product product) {
        // Convert id to String
        template.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        List<Object> objects = template.opsForHash().values(HASH_KEY);
        return objects.stream()
                .map(object -> (Product) object)
                .collect(Collectors.toList());
    }

    public Product findProductById(String id) {
        // Convert id to String
        return (Product) template.opsForHash().get(HASH_KEY, String.valueOf(id));
    }

    public String deleteProduct(String id) {
        // Convert id to String
        template.opsForHash().delete(HASH_KEY, String.valueOf(id));
        return "product removed !!";
    }
}
