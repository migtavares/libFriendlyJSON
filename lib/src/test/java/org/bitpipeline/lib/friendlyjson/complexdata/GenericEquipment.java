/**
 * 
 */
package org.bitpipeline.lib.friendlyjson.complexdata;


/**
 * @author mtavares */
public class GenericEquipment {
	static final public EquipmentBrand BRAND = new EquipmentBrand ("Generic", null, "White brand");
	static final public EquipmentCollection COLLECTION = new EquipmentCollection ("2012", null, "2012 collection", GenericEquipment.BRAND);
	static final public EquipmentBoard BOARD_BEGINNER = new EquipmentBoard (
			"Beginner", GenericEquipment.COLLECTION, null, "A beginners board",
			new WindsurfClass[] {WindsurfClass.Kids, WindsurfClass.Beginner},
			new EquipmentBoard.BoardSpecs (150f, 2.70f, 0.70f, 2.0f,  8.0f, FinFittingType.DaggerBoard, FinFittingType.PowerBox),
			new EquipmentBoard.BoardSpecs (155f, 2.70f, 0.75f, 2.0f,  8.0f, FinFittingType.DaggerBoard, FinFittingType.PowerBox),
			new EquipmentBoard.BoardSpecs (160f, 2.75f, 0.75f, 2.0f, 10.0f, FinFittingType.DaggerBoard, FinFittingType.PowerBox),
			new EquipmentBoard.BoardSpecs (175f, 2.75f, 0.80f, 2.0f, 10.0f, FinFittingType.DaggerBoard, FinFittingType.PowerBox),
			new EquipmentBoard.BoardSpecs (190f, 2.80f, 0.80f, 2.0f, 10.0f, FinFittingType.DaggerBoard, FinFittingType.PowerBox),
			new EquipmentBoard.BoardSpecs (205f, 2.80f, 0.85f, 2.0f, 10.0f, FinFittingType.DaggerBoard, FinFittingType.PowerBox),
			new EquipmentBoard.BoardSpecs (220f, 2.85f, 0.85f, 2.0f, 10.0f, FinFittingType.DaggerBoard, FinFittingType.PowerBox),
			new EquipmentBoard.BoardSpecs (240f, 2.90f, 0.85f, 2.0f, 10.0f, FinFittingType.DaggerBoard, FinFittingType.PowerBox),
			new EquipmentBoard.BoardSpecs (250f, 2.90f, 0.90f, 2.0f, 10.0f, FinFittingType.DaggerBoard, FinFittingType.PowerBox));

	static final public EquipmentBoard[] BOARDS = {
		GenericEquipment.BOARD_BEGINNER};

	static final public EquipmentSail SAIL_KIDS = new EquipmentSail (
			"Kids", SailType.Kid, GenericEquipment.COLLECTION, null, "A soft and forgiving sail.",
			new EquipmentSail.SailSpecs ( 1.0f, 2.0f, 0.90f, MastType.EPX),
			new EquipmentSail.SailSpecs ( 1.5f, 2.1f, 1.00f, MastType.EPX),
			new EquipmentSail.SailSpecs ( 2.0f, 2.5f, 1.10f, MastType.EPX),
			new EquipmentSail.SailSpecs ( 2.5f, 2.8f, 1.20f, MastType.EPX),
			new EquipmentSail.SailSpecs ( 3.0f, 3.0f, 1.30f, MastType.EPX),
			new EquipmentSail.SailSpecs ( 3.5f, 3.3f, 1.40f, MastType.EPX),
			new EquipmentSail.SailSpecs ( 4.0f, 3.5f, 1.50f, MastType.EPX));	
	
	
	static final public EquipmentSail[] SAILS = {
			GenericEquipment.SAIL_KIDS};

	static final public EquipmentFin FIN_FREERIDE = new EquipmentFin (
			"Freeride", FinType.Freeride, GenericEquipment.COLLECTION, null, "Free ride fin",
			new EquipmentFin.FinSpecs (0.220f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.230f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.240f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.250f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.260f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.270f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.280f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.290f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.300f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.310f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.320f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.330f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.340f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.350f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.360f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.370f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.380f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.390f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.400f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.410f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.420f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.430f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.440f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle),
			new EquipmentFin.FinSpecs (0.450f, 0.0f, FinFittingType.PowerBox, FinFittingType.USBox, FinFittingType.TuttleBox, FinFittingType.DeepTuttle));

	
	static final public EquipmentFin[] FINS = {
		GenericEquipment.FIN_FREERIDE};
}
