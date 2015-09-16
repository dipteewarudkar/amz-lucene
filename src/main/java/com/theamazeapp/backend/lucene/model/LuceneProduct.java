package com.theamazeapp.backend.lucene.model;

import java.util.Date;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.SortedSetDocValuesField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.util.BytesRef;

public class LuceneProduct implements BaseLuceneObject{

	private String id;
	private String brandKey;


	private List<String> categories;
	private Double price;
	private Boolean sale;
	private String color;
	private String title;
	private List<String> segments;
	private List<String> occasions;
	private Date curatedAt;
	private Boolean isCurated;
	private Boolean available;
	private Boolean relevant;
	private Boolean imageAvailable;
	
	public String getId() {
		return id;
	}



	public LuceneProduct(String brandKey,List<String> categories,Double price,Boolean sale,String color,String title,Boolean available,Boolean relevant,
			String sku,List<String> occasions2, List<String>  segments2, Date curatedAt2,String imageUrl) {

		this.brandKey=brandKey;
		this.categories=categories;
		this.price=price;
		this.sale=sale;
		this.color=color;
		this.title=title;
		this.available=available;
		this.relevant=relevant;

		this.id = sku;
		this.segments = segments2;
		this.occasions =occasions2;
		this.curatedAt = curatedAt2;
		this.isCurated = curatedAt2!=null;
		this.imageAvailable=imageUrl!=null;


	}
	public LuceneProduct() {
		// TODO Auto-generated constructor stub
	}



	public void setId(String id) {
		this.id = id;
	}


	public String getBrandKey() {
		return brandKey;
	}


	public void setBrandKey(String brandKey) {
		this.brandKey = brandKey;
	}


	public List<String> getCategories() {
		return categories;
	}


	public void setCategories(List<String> categories) {
		this.categories = categories;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Boolean getSale() {
		return sale;
	}


	public void setSale(Boolean sale) {
		this.sale = sale;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public List<String> getSegments() {
		return segments;
	}


	public void setSegments(List<String> segments) {
		this.segments = segments;
	}


	public List<String> getOccasions() {
		return occasions;
	}


	public void setOccasions(List<String> occasions) {
		this.occasions = occasions;
	}


	public Date getCuratedAt() {
		return curatedAt;
	}


	public void setCuratedAt(Date curatedAt) {
		this.curatedAt = curatedAt;
	}


	public Boolean getIsCurated() {
		return isCurated;
	}


	public void setIsCurated(Boolean isCurated) {
		this.isCurated = isCurated;
	}


	public Boolean getAvailable() {
		return available;
	}


	public void setAvailable(Boolean available) {
		this.available = available;
	}


	public Boolean getRelevant() {
		return relevant;
	}


	public void setRelevant(Boolean relevant) {
		this.relevant = relevant;
	}


	public Boolean getImageAvailable() {
		return imageAvailable;
	}


	public void setImageAvailable(Boolean imageAvailable) {
		this.imageAvailable = imageAvailable;
	}



	@Override
	public Document toLuceneDocument() {
		Document s = new Document();
		s.add(new Field("id", id,StringField.TYPE_STORED));

		if(brandKey!=null){
			s.add(new Field("brandKey", brandKey,StringField.TYPE_NOT_STORED));
		}


		if(categories!=null){
			for(String cat : categories){
				s.add(new Field("categories", cat,StringField.TYPE_NOT_STORED));
			}
		}

		if(segments!=null){
			for(String segment : segments)
				s.add(new SortedSetDocValuesField("segments", new BytesRef(segment)));
		}

		if(occasions!=null){
			for(String occasion : occasions)
				s.add(new Field("occasions", occasion,StringField.TYPE_NOT_STORED));
		}


		if(color!=null){
			s.add(new Field("color",color,TextField.TYPE_NOT_STORED));
		}
		if(curatedAt!=null){
			s.add(new Field("curatedAt", curatedAt.toString(),StringField.TYPE_NOT_STORED));
		}

		if(imageAvailable!=null){
			s.add(new Field("imageAvailable", imageAvailable.toString(),StringField.TYPE_NOT_STORED));
		}


		if(isCurated!=null){
			s.add(new Field("isCurated", isCurated.toString(),StringField.TYPE_NOT_STORED));
		}

		if(price!=null){
			DoubleField pricedoubleField=new DoubleField("price",price, DoubleField.TYPE_NOT_STORED);
			s.add(pricedoubleField);
		}

		if(available!=null){
			s.add(new Field("available", available.toString(),StringField.TYPE_NOT_STORED));
		}


		if(relevant!=null){
			s.add(new Field("relevant",relevant.toString(),StringField.TYPE_NOT_STORED));
		}


		if(sale!=null){
			s.add(new Field("sale",sale.toString(),StringField.TYPE_NOT_STORED));
		}

		if(title!=null){
			s.add(new Field("title",title.toString(),StringField.TYPE_NOT_STORED));
		}


		return s;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


}
