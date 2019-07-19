import java.awt.Image;

public class Movie 
{
	private String movieSearchedByUser; // store userName so that we know who searched this movie
	private int id;
   private String name;
   private String numberofReviewsByCritics;
   private String score;//tomatometer score
   private String likedPercentage;
   private String userRatings;
   private String imageUrl;
   public Movie()
   {
	   // initialize all variables so that we dont get nullPointer exception
	   name = "";
	   id = 0;
	   numberofReviewsByCritics = "";
	   score = "";
	   likedPercentage = "";
	   userRatings = "";
	   imageUrl = "";
	   
   }
   public Movie(int id,String newName,String newScore,String newNumberOfReviews,String newLikedPercentage,
		   String newURating, String imgUrl,String movieSearchedByUser)
   {
	   setName(newName);
	   setNumberOfReviews(newNumberOfReviews);
	   setScore(newScore);
	   setLikedPercentage(newLikedPercentage);
	   setUserRatings(newURating);
	   setImageUrl(imgUrl);
	   setMovieSearchedByUser(movieSearchedByUser);
	   setId(id);
   }
   
public void  setId(int id)
	{
		this.id = id;
	}
	public int  getId()
	{
		return id;
	}
   public void  setMovieSearchedByUser(String userName)
	{
		movieSearchedByUser = userName;
	}
	public String  getItemOwner()
	{
		return movieSearchedByUser;
	}
   public void setName(String s)
   {
	   name = s;
   }
   public void setNumberOfReviews(String s)
   {
	   numberofReviewsByCritics = s;
   }
   public void setScore(String s)
   {
	   score = s;
   }
   public void setLikedPercentage(String s)
   {
	   likedPercentage = s;
   }
   public void setUserRatings(String s)
   {
	   userRatings = s;
   }
  
   public void setImageUrl(String url)
   {
	   imageUrl = url;
   }
   public String getName()
   {
	   return name;
   }
   public String getNumberOfReviews()
   {
	   return numberofReviewsByCritics;
   }
   public String getScore()
   {
	   return score;
   }
   public String getLikedPercentage()
   {
	   return likedPercentage;
   }
   public String getUserRatings()
   {
	   return userRatings;
   }
   public String getImageUrl()
   {
	   return imageUrl;
   }
}
