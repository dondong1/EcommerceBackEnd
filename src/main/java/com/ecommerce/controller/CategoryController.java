package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	public CategoryService categoryService;

	@PostMapping("/addCategoryToUser/{idUser}")
	public Category addCategoryToUser(@RequestBody Category category, @PathVariable long idUser) {
		return categoryService.addCategoryToUser(category, idUser);
	}

	@PutMapping("/editCategory/{id}")
	public Category editCategory(@RequestBody Category category, @PathVariable long id) {
		return categoryService.editCategory(category, id);
	}

	@GetMapping("/findCategoryById/{id}")
	public Category findCategoryById(@PathVariable long id) {
		return categoryService.findCategoryById(id);
	}

	@DeleteMapping("/deleteCategory/{id}")
	public void deleteCategory(@PathVariable long id) {
		categoryService.deleteCategory(id);
	}

	@GetMapping("/findAllCategories")
	public List<Category> findAllCategories() {
		return categoryService.findAllCategories();
	}

	@GetMapping("/findCategoriesForUser/{id}")
	public List<Category> findCategoriesForUser(@PathVariable long id) {
		return categoryService.findCategoriesForUser(id);
	}

}
