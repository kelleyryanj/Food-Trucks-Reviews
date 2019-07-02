package foodTruckReviews;


import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {


	@Id
	@GeneratedValue
	private long id;
	
	private String description;
	
	public Review() {
		
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@ManyToOne
	private Foodtruck foodtruck;

	
	
	public Review(String description, Foodtruck foodtruck) {
		this.description = description;
		this.foodtruck = foodtruck;
	
	
	}
	
	public long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Foodtruck getFoodtruck() {
		return foodtruck;
	}
	
	


}
