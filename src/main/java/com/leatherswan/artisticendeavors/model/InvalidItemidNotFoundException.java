/**
 * DiscountException A specialized exception class. This class defines one
 *  new property, the InvoiceItem that caused the exception to be thrown.
 *
 */
package com.leatherswan.artisticendeavors.model;

/**
 * The Class DiscountException.
 *
 * @author stramska
 */
public class InvalidItemidNotFoundException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new invalid isbn not found exception.
     *
     * @param isbn the isbn
     */
    public InvalidItemidNotFoundException(final String isbn) {

    	super("ISBN EXCEPTION: ISBN is Invalid: \n"
    			+ "Requested ISBN : "
    			+ isbn);
	}

}
