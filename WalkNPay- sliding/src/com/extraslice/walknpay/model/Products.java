package com.extraslice.walknpay.model;

import android.graphics.Color;

public class Products {

	String productdesc,productcode;
	double productprice;
	int productcount = 1;
	int color=Color.RED;
	public Products() {
		// TODO Auto-generated constructor stub
	}

	public String getProductdesc() {
		return productdesc;
	}
	public String getProductcode()
	{
		return productcode;
	}
	public void setProductcode(String productcode)
	{
		this.productcode=productcode;
	}
	public void setProductdesc(String productdesc) {
		this.productdesc = productdesc;
	}

	public double getProductprice() {
		return productprice;
	}

	public void setProductprice(double productprice) {
		this.productprice = productprice;
	}
	public void setbgcolor(int color)
	{
		this.color= Color.RED;
	}
public int getbgcolor()
{
	return color;
}
	public int getProductcount() {
		return productcount;
	}

	public void setProductcount(int productcount) {
		this.productcount = productcount;
	}

}
