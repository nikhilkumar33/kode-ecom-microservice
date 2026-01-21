package com.ecom.review.rating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.review.rating.request.ReviewRequest;
import com.ecom.review.rating.response.ReviewResponse;
import com.ecom.review.rating.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@PostMapping
	public ResponseEntity<String> addProductReview(@RequestBody ReviewRequest reviewRequest)
	{
		System.out.println("dsvsbzfaskdsshkk");
		long reviewId = reviewService.writeReview(reviewRequest);
		return ResponseEntity.ok("Review added successfully. Review id: "+reviewId);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<ReviewResponse> getReview(@PathVariable long productId)
	{
		ReviewResponse response= reviewService.fetchReview(productId);
		return ResponseEntity.ok(response);
	}
}
