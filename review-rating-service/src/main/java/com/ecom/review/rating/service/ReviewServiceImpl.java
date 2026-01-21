package com.ecom.review.rating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.review.rating.client.ProductClient;
import com.ecom.review.rating.entity.ReviewEntity;
import com.ecom.review.rating.exception.InvalidProductIdException;
import com.ecom.review.rating.exception.NoProductFoundException;
import com.ecom.review.rating.repository.ReviewRepository;
import com.ecom.review.rating.request.ReviewRequest;
import com.ecom.review.rating.response.ProductResponse;
import com.ecom.review.rating.response.ReviewResponse;

@Service
public class ReviewServiceImpl implements ReviewService
{
	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	ProductClient productClient;
	
	@Override
	public long writeReview(ReviewRequest reviewRequest) {
		
		long productId =reviewRequest.getProductId();
		ProductResponse response= productClient.fetchProduct(productId);
		if(response.getProductName()==null) {
			throw new NoProductFoundException("No productFound for the product id: "+productId);
		}
		ReviewEntity entity = new ReviewEntity();
		
		entity.setProductId(reviewRequest.getProductId());
		entity.setRatingStars(reviewRequest.getRatingStars());
		entity.setReview(reviewRequest.getReview());
		entity = reviewRepository.save(entity);
		return entity.getReviewId();
	}

	@Override
	public ReviewResponse fetchReview(long productId) {
		ReviewEntity entity = reviewRepository.findByProductId(productId);
		if(entity==null) {
			throw new InvalidProductIdException("No record found! Invalid product id: "+productId);
		}
		ReviewResponse response = new ReviewResponse();
		response.setReviewId(entity.getProductId());
		response.setReview(entity.getReview());
		response.setRatingStars(entity.getRatingStars());
		return response;
	}

}
