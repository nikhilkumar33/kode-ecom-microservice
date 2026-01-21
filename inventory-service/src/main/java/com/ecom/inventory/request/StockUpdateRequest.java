package com.ecom.inventory.request;

public class StockUpdateRequest {
	
	private long productid;
	private int reduceStockQty;
	
	public long getProductid() {
		return productid;
	}
	public void setProductid(long productid) {
		this.productid = productid;
	}
	public int getReduceStockQty() {
		return reduceStockQty;
	}
	public void setReduceStockQty(int reduceStockQty) {
		this.reduceStockQty = reduceStockQty;
	}
	
	

}
