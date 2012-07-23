/**
 *
 */
package org.bitpipeline.lib.friendlyjson;

/**
 * @author mtavares */
public class JSONMappingException extends Exception {
	/** */
	private static final long serialVersionUID = -8297773030483002516L;

	/**
	 */
	public JSONMappingException () {
	}

	/**
	 * @param message */
	public JSONMappingException (String message) {
		super (message);
	}

	/**
	 * @param cause */
	public JSONMappingException (Throwable cause) {
		super (cause);

	}

	/**
	 * @param message
	 * @param cause */
	public JSONMappingException (String message, Throwable cause) {
		super (message, cause);
	}
}
