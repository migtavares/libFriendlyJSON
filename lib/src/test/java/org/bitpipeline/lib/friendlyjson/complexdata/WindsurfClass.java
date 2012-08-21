/**
 * 
 */
package org.bitpipeline.lib.friendlyjson.complexdata;

/** The class of windsurf.
 * @author mtavares */
public enum WindsurfClass {
	Formula,
	Freerace,
	Freeride,
	Freestyle,
	Raceboard, // IMCO, RS:X, RS:1
	Slalom,
	Speed,
	Wave,
	Kids,
	Beginner;
	
	public int getFlag () {
		return (1 << this.ordinal ());
	}
}
