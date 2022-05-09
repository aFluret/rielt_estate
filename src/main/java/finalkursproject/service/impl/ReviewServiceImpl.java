package finalkursproject.service.impl;


import finalkursproject.entity.Review;
import finalkursproject.exception.DaoException;
import finalkursproject.exception.ServiceException;
import finalkursproject.factory.DaoFactory;
import finalkursproject.service.ReviewService;
import finalkursproject.util.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;


public class ReviewServiceImpl implements ReviewService {
    private static final Logger LOGGER = LogManager.getLogger(ReviewServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public void addReview(int idClient, String reviewText, int mark) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ReviewService: Start addReview");
        try {
            if(Validator.isEmptyString(reviewText)&&Validator.isNull(reviewText)) {
                daoFactory.getReviewDao().addReview(idClient,reviewText,mark);
                LOGGER.log(Level.DEBUG, "ReviewService: Finish addReview");
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public List<Review> findAllReviews() throws ServiceException {
        LOGGER.log(Level.DEBUG, "ReviewService: Start findAllReviews");
        try {
            LOGGER.log(Level.DEBUG, "ReviewService: Finish findAllReviews");
            return daoFactory.getReviewDao().findAllReviews();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

}

