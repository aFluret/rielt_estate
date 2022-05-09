package finalkursproject.dao;


import finalkursproject.entity.Review;
import finalkursproject.exception.DaoException;

import java.util.List;

public interface IReviewDao extends AbstractDao {
    void addReview(int idClient,String reviewText,int mark) throws DaoException;

    List<Review> findAllReviews() throws DaoException;

}
