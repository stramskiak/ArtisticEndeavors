/**
 * DiscountException A specialized exception class. This class defines one
 *  new property, the InvoiceItem that caused the exception to be thrown.
 *
 */
package com.leatherswan.artisticendeavors.app;

import com.leatherswan.artisticendeavors.model.ListItem;

/**
 * The Class DiscountException.
 *
 * @artist stramska
 */
public class DiscountException extends Exception {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Instantiates a new discount exception.
     *
     * @param invoiceItem the invoice item
     */
    public DiscountException(final int invoiceDiscount, 
    		final ListItem listItem) {

    	super("DISCOUNT EXCEPTION: Discount is Invalid: \n"
    			+ "Invoice Discount: "
    			+ invoiceDiscount + "% "
    			+ " Item SKU: "
    			+ listItem.getItem().getItemid());
	}

}
