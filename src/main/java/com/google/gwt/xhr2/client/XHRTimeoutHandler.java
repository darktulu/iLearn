package com.google.gwt.xhr2.client;
/**
 * A timeout callback for an {@link XMLHttpRequest2} object.
 * 
 * From "client" side (for {@link com.google.gwt.xhr2.client.RequestBuilder}) use {@link com.google.gwt.xhr2.client.ErrorHandler}
 */
public interface XHRTimeoutHandler {
	/**
	 * This is called when the author specified timeout 
	 * has passed before the request could complete.
	 * See {@link XMLHttpRequest2#setOnTimeout}.
	 * 
	 * @param xhr
	 *            the object whose state has changed.
	 */
	void onTimeout(ProgressEvent event);
}
