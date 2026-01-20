package com.ecom.inventory.request;

public class UpdateRequest {
	
	private long productid;
	private int newStockQty;
	
	public long getProductid() {
		return productid;
	}
	public void setProductid(long productid) {
		this.productid = productid;
	}
	public int getNewStockQty() {
		return newStockQty;
	}
	public void setNewStockQty(int newStockQty) {
		this.newStockQty = newStockQty;
	}
	
	

}
