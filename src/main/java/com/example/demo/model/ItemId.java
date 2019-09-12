package com.example.demo.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ItemId implements Serializable {

	private static final long serialVersionUID = -8286181718708320578L;
	
	private Long InvoiceId;
	private Long ItemId;
	
	

	public Long getInvoiceId() {
		return InvoiceId;
	}

	public ItemId(Long invoiceId, Long itemId) {
		InvoiceId = invoiceId;
		ItemId = itemId;
	}

	public void setInvoiceId(Long invoiceId) {
		InvoiceId = invoiceId;
	}

	public Long getItemId() {
		return ItemId;
	}

	public Long setItemId(Long itemId) {
		return ItemId = itemId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getInvoiceId(), getItemId());
	}

	public ItemId() {
	}


	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	        if (!(o instanceof InvoiceId)) return false;
	        InvoiceId that = (InvoiceId) o;
	        return Objects.equals(getInvoiceId(), that.getItemId()) &&
	                Objects.equals(getInvoiceId(), that.getItemId());
	}
	

	
}
