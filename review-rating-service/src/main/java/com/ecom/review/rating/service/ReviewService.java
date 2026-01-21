package com.ecom.review.rating.service;

import java.util.List;

import com.ecom.review.rating.request.ReviewRequest;
import com.ecom.review.rating.response.ReviewResponse;

public interface ReviewService {

	public long writeReview(ReviewRequest reviewRequest);

	public List<ReviewResponse> fetchReview(long productId);
}
