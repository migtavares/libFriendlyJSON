/**
 * Copyright 2012 
 *         J. Miguel P. Tavares <mtavares@bitpipeline.eu>
 *         BitPipeline
 */
package org.bitpipeline.lib.friendlyjson.complexdata;

import java.awt.Image;

/**
 * 
 * @author mtavares */
public class ViewableResourceBasedObject implements ViewableObjectInterface {
	private final String name;
	private final String description;
	private final String imageUrl;

	public ViewableResourceBasedObject (String name, String imageUrl, String description) {
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public Image getImage () {
		return null;
	}
	
	public String getImageURI () {
		return imageUrl;
	}

	public String getName () {
		return this.name;
	}

	public String getDescription () {
		return this.description;
	}

}
