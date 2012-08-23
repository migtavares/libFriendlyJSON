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

import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

/**
 * 
 * @author mtavares */
public class ErrorHandlingTest {


	class EntityWithoutJsonConstructor extends JSONEntity {
		String name;
		
		public EntityWithoutJsonConstructor (String name) {
			this.name = name;
		}
	}

	class EntityWithJsonConstructor extends JSONEntity {
		EntityWithoutJsonConstructor entity;
		
		public EntityWithJsonConstructor (JSONObject json) throws JSONMappingException {
			super (json);
		}
		
		public EntityWithJsonConstructor (EntityWithoutJsonConstructor entity) {
			this.entity = entity;
		}
	}

	@Test (expected = org.bitpipeline.lib.friendlyjson.JSONMappingException.class)
	public void errorClassWithoutConstructor () throws JSONMappingException {
		EntityWithoutJsonConstructor without = new EntityWithoutJsonConstructor ("Test");
		EntityWithJsonConstructor with = new EntityWithJsonConstructor (without);

		JSONObject json = with.toJson ();

		@SuppressWarnings ("unused")
		EntityWithJsonConstructor copy = new EntityWithJsonConstructor (json);
	}

	class Entity extends JSONEntity {
		private List<Integer> numbers;

		public Entity (Integer ... nums) {
			this.numbers = Arrays.asList (nums);
		}
		
		public Entity (JSONObject json) throws JSONMappingException {
			super (json);
		}
		
		public Entity (String source) throws JSONMappingException, JSONException {
			super (source);
		}

		public List<Integer> getNumbers () {
			return this.numbers;
		}
	}

	@SuppressWarnings ("unused")
	@Test(expected=JSONMappingException.class)
	public void badValueOnList () throws JSONMappingException, JSONException {
		String goodJson = "{\"numbers\":[1,2,3,4]}";
		String badJson = "{\"numbers\":[\"one\",2,3,4]}";

		Entity good = new Entity (goodJson);
		Entity bad = new Entity (badJson);
	}
}
