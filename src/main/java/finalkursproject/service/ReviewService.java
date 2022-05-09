package finalkursproject.service;


import finalkursproject.entity.Review;
import finalkursproject.exception.ServiceException;

import java.util.List;

public interface ReviewService {
    void addReview(int idClient,String reviewText,int mark) throws ServiceException;

    List<Review> findAllReviews() throws ServiceException;

}

