package com.ecom.review.rating.service;

import com.ecom.review.rating.request.ReviewRequest;
import com.ecom.review.rating.response.ReviewResponse;

public interface ReviewService {

	public long writeReview(ReviewRequest reviewRequest);

	public ReviewResponse fetchReview(long productId);
}
