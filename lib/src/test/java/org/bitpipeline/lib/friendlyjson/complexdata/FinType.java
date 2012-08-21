package org.bitpipeline.lib.friendlyjson.complexdata;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public enum FinType {
	Freeride    (0.08f, 0.08f, WindsurfClass.Beginner, WindsurfClass.Freerace, WindsurfClass.Freeride, WindsurfClass.Kids, WindsurfClass.Raceboard, WindsurfClass.Slalom),
	Wave        (0.05f, 0.05f, WindsurfClass.Freeride, WindsurfClass.Wave),
	Race        (0.02f, 0.02f, WindsurfClass.Formula, WindsurfClass.Freerace, WindsurfClass.Freeride, WindsurfClass.Raceboard, WindsurfClass.Slalom, WindsurfClass.Speed),
	Weed        (0.08f, 0.08f, WindsurfClass.Beginner, WindsurfClass.Freerace, WindsurfClass.Freeride, WindsurfClass.Kids, WindsurfClass.Raceboard, WindsurfClass.Slalom, WindsurfClass.Speed),
	Freestyle   (0.04f, 0.02f, WindsurfClass.Freestyle, WindsurfClass.Wave),
	Longboard   (0.00f, 0.00f, WindsurfClass.Beginner, WindsurfClass.Formula, WindsurfClass.Freerace, WindsurfClass.Kids, WindsurfClass.Raceboard, WindsurfClass.Slalom, WindsurfClass.Speed),
	DaggerBoard (0.00f, 0.00f, WindsurfClass.Beginner, WindsurfClass.Freerace, WindsurfClass.Kids, WindsurfClass.Raceboard);
	
	private final float efficCoef;
	private final float powerCoef;
	
	private final EnumSet<WindsurfClass> wsClasses;
	
	private FinType (float efficiencyCoefficient, float powerCoefficient, WindsurfClass a) {
		this.efficCoef = efficiencyCoefficient;
		this.powerCoef = powerCoefficient;
		this.wsClasses = EnumSet.of (a);
	}
	
	private FinType (float efficiencyCoefficient, float powerCoefficient, WindsurfClass a, WindsurfClass b) {
		this.efficCoef = efficiencyCoefficient;
		this.powerCoef = powerCoefficient;
		this.wsClasses = EnumSet.of (a, b);
	}
	
	private FinType (float efficiencyCoefficient, float powerCoefficient, WindsurfClass a, WindsurfClass b, WindsurfClass c) {
		this.efficCoef = efficiencyCoefficient;
		this.powerCoef = powerCoefficient;
		this.wsClasses = EnumSet.of (a, b, c);
	}
	
	private FinType (float efficiencyCoefficient, float powerCoefficient, WindsurfClass a, WindsurfClass b, WindsurfClass c, WindsurfClass d) {
		this.efficCoef = efficiencyCoefficient;
		this.powerCoef = powerCoefficient;
		this.wsClasses = EnumSet.of (a, b, c, d);
	}
	
	private FinType (float efficiencyCoefficient, float powerCoefficient, WindsurfClass a, WindsurfClass b, WindsurfClass c, WindsurfClass d, WindsurfClass e) {
		this.efficCoef = efficiencyCoefficient;
		this.powerCoef = powerCoefficient;
		this.wsClasses = EnumSet.of (a, b, c, d, e);
	}
	
	private FinType (float efficiencyCoefficient, float powerCoefficient, WindsurfClass a, WindsurfClass ... others) {
		this.efficCoef = efficiencyCoefficient;
		this.powerCoef = powerCoefficient;
		this.wsClasses = EnumSet.of (a, others);
	}
	
	/** @return the efficiency coefficient for this type of fin */
	public float getEfficiencyCoefficiency () { return this.efficCoef; }
	
	/** @return the power coefficient for this type of fin */
	public float getPowerCoefficiency () { return this.powerCoef; }
	
	public boolean worksForWindsurfClass (Set<WindsurfClass> wsClasses) {
		for (WindsurfClass wsClass : wsClasses)
			if (this.wsClasses.contains (wsClass))
				return true;
		return false;
	}
	
	public List<WindsurfClass> getWindsurfClasses () {
		return new ArrayList<WindsurfClass> (this.wsClasses);
	}
}