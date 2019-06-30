package foodTruckReviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByCuisinesContains(Cuisine cuisine);

	Collection<Review> findByCuisinesId(Long id);

}
