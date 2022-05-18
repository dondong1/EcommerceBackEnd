package com.ecommerce.controller;

import com.ecommerce.model.Comment;
import com.ecommerce.service.CommentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class CommentController {
 
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/addCommentToProduct/{idProduct}")
	public Comment addCommentToProduct(@RequestBody Comment comment, @PathVariable long idProduct) {
		return commentService.addCommentToProduct(comment, idProduct);
	}
	
	@PutMapping("/editComment/{id}")
	public Comment editComment(@RequestBody Comment comment, @PathVariable long id) {
		return commentService.editComment(comment, id);
	}
	
	@GetMapping("/findCommentById/{id}")
	public Comment findCommentById(@PathVariable long id) {
		return commentService.findCommentById(id);
	}
	
	@DeleteMapping("/deleteComment/{id}")
	public void deleteComment(@PathVariable long id) {
		commentService.deleteComment(id);
	}
	
	@GetMapping("/findCommentsForProduct/{idProduct}")
	public List<Comment> findCommentsForProduct(@PathVariable long idProduct) {
		return commentService.findCommentsForProduct(idProduct);
	}
	
	@GetMapping("/findAllComments")
	public List<Comment> findAllComments() {
		return commentService.findAllComments();
	}
}
