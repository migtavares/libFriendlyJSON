package org.bitpipeline.lib.friendlyjson.complexdata;

import java.util.EnumSet;
import java.util.Set;

public enum SailType {
	CrossOver (WindsurfClass.Beginner, WindsurfClass.Freerace, WindsurfClass.Freeride, WindsurfClass.Raceboard, WindsurfClass.Slalom, WindsurfClass.Speed),
	Performance (WindsurfClass.Formula, WindsurfClass.Freerace, WindsurfClass.Raceboard, WindsurfClass.Slalom, WindsurfClass.Speed),
	Racing (WindsurfClass.Formula, WindsurfClass.Freerace, WindsurfClass.Raceboard, WindsurfClass.Slalom, WindsurfClass.Speed),
	Wave (WindsurfClass.Freeride, WindsurfClass.Freestyle, WindsurfClass.Wave),
	Kid (WindsurfClass.Beginner, WindsurfClass.Kids);
	
	private final EnumSet<WindsurfClass> wsClasses;
	
	private SailType (WindsurfClass a) {
		this.wsClasses = EnumSet.of (a);
	}
	
	private SailType (WindsurfClass a, WindsurfClass b) {
		this.wsClasses = EnumSet.of (a, b);
	}

	private SailType (WindsurfClass a, WindsurfClass b, WindsurfClass c) {
		this.wsClasses = EnumSet.of (a, b, c);
	}
	
	private SailType (WindsurfClass a, WindsurfClass b, WindsurfClass c, WindsurfClass d) {
		this.wsClasses = EnumSet.of (a, b, c, d);
	}
	
	private SailType (WindsurfClass a, WindsurfClass b, WindsurfClass c, WindsurfClass d, WindsurfClass e) {
		this.wsClasses = EnumSet.of (a, b, c, d, e);
	}

	private SailType (WindsurfClass a, WindsurfClass ... others) {
		this.wsClasses = EnumSet.of (a, others);
	}

	public boolean worksForWindsurfClass (Set<WindsurfClass> wsClasses) {
		for (WindsurfClass wsClass : wsClasses)
			if (this.wsClasses.contains (wsClass))
				return true;
		return false;
	}
	
	public Set<WindsurfClass> getWindsurfClasses () {
		return this.wsClasses;
	}
}