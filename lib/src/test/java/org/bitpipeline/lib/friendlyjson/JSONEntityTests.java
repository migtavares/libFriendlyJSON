/**
 * 
 */
package org.bitpipeline.lib.friendlyjson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

/**
 * @author mtavares
 *
 */
public class JSONEntityTests {

	class Entity extends JSONEntity {
		boolean aBoolean;
		byte aByte;
		char aChar;
		short aShort;
		int aInt;
		long aLong;
		float aFloat;
		double aDouble;
		
		String aString;
		Map<String, String> aMap;
		Map<String, List<String>> aMapOfLists;
		
		transient int transientValue;
		
		public Entity () {
			super ();
		}
		
		public Entity (JSONObject json) throws JSONMappingException {
			super (json);
		}

	}

	Entity createEntity () {
		Entity e = new Entity ();
		e.aBoolean = true;
		e.aByte = 8;
		e.aChar = 'c';
		e.aShort = 127;
		e.aInt = 32000;
		e.aLong = 1000;
		e.aFloat = 3.14f;
		e.aDouble = 3.1415;
		
		e.aString = "String";
		
		e.transientValue = 1001;
		
		e.aMap = new HashMap<String, String> ();
		
		e.aMap.put ("author", "Max Payne");
		
		e.aMapOfLists = new HashMap<String, List<String>> ();
		
		e.aMapOfLists.put ("listA", Arrays.asList (new String[]{"val1a", "val2a", "val3a"}));
		e.aMapOfLists.put ("listB", Arrays.asList (new String[]{"val1b", "val2b", "val3b"}));
		
		return e;
	}
	
	@Test
	public void testBoardSerialization () throws JSONMappingException, JSONException  {
		Entity orig = createEntity ();
		
		JSONObject json = orig.toJson ();
		assertNotNull (json);
		Entity copy = new Entity (json);
		assertNotNull (copy);
		assertEquals (orig.aBoolean, copy.aBoolean);
		assertEquals (orig.aByte, copy.aByte);
		assertEquals (orig.aChar, copy.aChar);
		assertEquals (orig.aShort, copy.aShort);
		assertEquals (orig.aInt, copy.aInt);
		assertEquals (orig.aLong, copy.aLong);
		assertEquals (orig.aFloat, copy.aFloat, Float.MIN_VALUE * 10.0f);
		assertEquals (orig.aDouble, copy.aDouble, Double.MIN_VALUE * 10.0);
		assertEquals (orig.aString, copy.aString);
		
		assertFalse (orig.transientValue == copy.transientValue);
		
		assertNotNull (copy.aMap);
		for (String key : orig.aMap.keySet ()) {
			assertEquals (orig.aMap.get (key), copy.aMap.get (key));
		}
		
		assertNotNull (copy.aMapOfLists);
		for (String key : orig.aMapOfLists.keySet ()) {
			assertEquals (orig.aMapOfLists.get (key), copy.aMapOfLists.get (key));
		}
		
		System.out.println ("ORIG:\n" + orig.toJson ().toString (4));
		System.out.println ("COPY:\n" + copy.toJson ().toString (4));
	}
}
