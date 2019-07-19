// This class is for initial search results.
public class Item 
{
	
	private String name ;
	private String year ;
	private String url ;
	private String imgUrl;
	private String meterClass;
	private String meterScore;
	
	public Item(String name,String year,String url,String imgUrl,String meterClass,String meterScore)
	{
		setName(name);
		setYear(year);
		setUrl(url);
		setimgUrl(imgUrl);
		setMeterClass(meterClass);
		setMeterScore(meterScore);
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public void setYear(String year)
	{
		this.year = year;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public void setimgUrl(String imgUrl)
	{
		this.imgUrl = imgUrl;
	}
	public void setMeterClass(String meterClass)
	{
		this.meterClass = meterClass;
	}
	public void setMeterScore(String meterScore)
	{
		this.meterScore = meterScore;
	}
	public String getName()
	{
		return name;
	}
	public String getYear()
	{
		return year;
	}
	public String getUrl()
	{
		return url;
	}
	public String getImgUrl()
	{
		return imgUrl;
	}
	public String getMeterClass()
	{
		return meterClass;
	}
	public String getMeterScore()
	{
		return meterScore;
	}
}
