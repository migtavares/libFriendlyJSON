package org.bitpipeline.lib.friendlyjson;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.Test;

public class EnumTest {

	static enum Finger {
		Thunmb, Index, Middle, Ring, Pinky
	}

	static class Hand extends JSONEntity {
		private Finger[] distendedFingers;
		
		public Hand (Finger ... f) {
			this.distendedFingers = f;
		}
		
		public Hand (JSONObject json) throws JSONMappingException {
			super (json);
		}
		
		public Finger[] getDistendedFingers () {
			return this.distendedFingers;
		}
	}

	@Test
	public void enumTest () throws JSONMappingException {
		Hand hand = new Hand (Finger.Index, Finger.Pinky);

		Hand copy = new Hand (hand.toJson ());
		assertNotNull (copy);
		assertNotNull (copy.getDistendedFingers ());
		for (Object o : copy.getDistendedFingers ()) {
			assertNotNull (o);
			assertTrue (o.getClass ().isEnum ());
			assertTrue (o instanceof Finger);
		}
	}

}
