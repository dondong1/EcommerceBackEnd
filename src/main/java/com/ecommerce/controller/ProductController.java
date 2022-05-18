package com.ecommerce.controller;

import java.util.List;

import antlr.debug.ParserListener;
import javassist.compiler.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	public ProductService productService;

	@Autowired
	public ProductDao productDao;

	@PostMapping("/addProductToCategory/{idCategory}")
	public Product addProductToCategory(@RequestBody Product product, @PathVariable Long idCategory) {
		return productService.addProductToCategory(product, idCategory);
	}

	@PutMapping("/editProduct/{id}")
	public Product editProduct(@RequestBody Product product, @PathVariable long id) {
		 return productService.editProduct(product, id);
	}

	@GetMapping("/findProductById/{id}")
	public Product findProductById(@PathVariable long id) {
		return productService.findProductById(id);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public void deleteProduct(@PathVariable long id) {
		productService.deleteProduct(id);
	}

	@GetMapping("/findAllProducts")
	public List<Product> findAllProducts() {
		return productService.findAllProducts();
	}

	@GetMapping("/findProductsForCategory/{idCategory}")
	public List<Product> findProductsForCategory(@PathVariable long idCategory) {
		return productService.findProductsForCategory(idCategory);
	}

	@GetMapping("/findByName/{name}")
	public List<Product> findByName(@PathVariable String name) {
		return productDao.findByName("%" + name + "%");
	}

}
