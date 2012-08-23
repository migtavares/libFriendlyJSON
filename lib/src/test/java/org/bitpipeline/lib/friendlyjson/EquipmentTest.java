/**
 * Copyright 2012 J. Miguel P. Tavares <mtavares@bitpipeline.eu>
 *         BitPipeline
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/
package org.bitpipeline.lib.friendlyjson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.bitpipeline.lib.friendlyjson.complexdata.EquipmentBoard;
import org.bitpipeline.lib.friendlyjson.complexdata.EquipmentBrand;
import org.bitpipeline.lib.friendlyjson.complexdata.EquipmentCollection;
import org.bitpipeline.lib.friendlyjson.complexdata.EquipmentPak;
import org.bitpipeline.lib.friendlyjson.complexdata.FinFittingType;
import org.bitpipeline.lib.friendlyjson.complexdata.WindsurfClass;
import org.json.JSONObject;
import org.junit.Test;

public class EquipmentTest {
	
	EquipmentBrand brand = new EquipmentBrand ("Brand", null, "This is a BRAND!");
	EquipmentCollection coll = new EquipmentCollection ("2012", null, "2012 collection", brand);
	EquipmentBoard board = new EquipmentBoard (
			"Beginner", coll, null, "A beginners board",
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

	@Test
	public void testEquipmentEntities () throws JSONMappingException {

		brand.putInfo ("Founded", "2012");

		JSONObject json = board.toJson ();
		EquipmentBoard copy = new EquipmentBoard (json);

		EquipmentCollection collection = copy.getCollection ();
		assertNotNull (collection);
		assertEquals (board.toString (), copy.toString ());
	}

	@Test
	public void testMapsOfEntities () throws JSONMappingException {
		EquipmentPak pak = new EquipmentPak ();
		pak.addBrand (brand);
		pak.addCollection (coll);
		pak.addBoard (board);

		EquipmentPak copy = new EquipmentPak (pak.toJson ());

		assertEquals (1, copy.getBrands ().size ());
		assertEquals (1, copy.getCollections ().size ());
		List<EquipmentBoard> cBoards = copy.getBoards ();
		assertNotNull (cBoards);
		assertEquals (1, cBoards.size ());
		EquipmentBoard cBoard = cBoards.get (0);
		EquipmentCollection cColl = cBoard.getCollection ();
		assertNotNull (cColl);
		EquipmentBrand cBrand = cColl.getBrand ();
		assertNotNull (cBrand);

		assertEquals (board.getName (), cBoard.getName ());
		assertEquals (coll.getName (), cColl.getName ());
		assertEquals (brand.getName (), cBrand.getName ());
	}
}
