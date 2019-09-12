package com.example.demo.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class InvoiceId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@Column(name = "InvoiceId")
	private Long invoiceId;

	@Column(name = "ItemId")
	private Long itemId;

	public InvoiceId() {
	}

	public InvoiceId(Long invoiceId, Long itemId) {
		this.invoiceId = invoiceId;
		this.itemId = itemId;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getInvoiceId(), getItemId());
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
