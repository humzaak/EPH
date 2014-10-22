package com.magint.h.epicurefoodhunting;

public class RecipeListItem {
	
	private String _title;
	private String _imgURL;
	private String _introText;
	
	
	public RecipeListItem(){}


	
	public RecipeListItem(String title, String introText, String imgURL){
		this._title = title;
		this._imgURL = imgURL;
		this._introText = introText;
	}
	
	public String getTitle(){
		return this._title;
	}
	
	public String getImgURL(){
		return this._imgURL;
	}
	
	public String getIntroText(){
		return this._introText;
	}
	
	
	public void setTitle(String title){
		this._title = title;
	}
	
	public void setIcon(String imgUrl){
		this._imgURL = imgUrl;
	}
	
	public void setCount(String introText){
		this._introText = introText;
	}
	
	
}
