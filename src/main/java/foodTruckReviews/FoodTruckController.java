package foodTruckReviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FoodTruckController {

	@Resource
	CuisineRepository cuisineRepo;
	
	@Resource
	ReviewRepository reviewRepo;
	
	
	
//	@Resource
//	FoodTruckRepository foodTruckRepo;
//	
//	@RequestMapping("food-truck")
//	public String findOneFoodTruck(@RequestParam(value = "id")Long id, Model model) {
//		Optional<FoodTruck> foodTruck = reviewRepo.findById(id);
//	}
//	
	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value = "id")Long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);
		
		if(review.isPresent()) {
			model.addAttribute("reviews", review.get());
			return ("review");
		}
		throw new ReviewNotFoundException();
		
	}
	
	@RequestMapping("/show-reviews")
	public String findAllReviews(Long id, Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return ("reviews");
		
	}
	
	@RequestMapping("/show-cuisine")
	public String findOneCuisine(@RequestParam(value = "id")Long id, Model model) throws CuisineNotFoundException {
		Optional<Cuisine> cuisine = cuisineRepo.findById(id);
		
		if(cuisine.isPresent()) {
			model.addAttribute("cuisines", cuisine.get());
			return ("cuisine");
		}
		throw new CuisineNotFoundException();
		
	}
	
	@RequestMapping("/showall-cuisines")
	public String findAllCuisines(Long id, Model model) {
		model.addAttribute("cusines", cuisineRepo.findAll());
		return ("cuisines");
	}
}
