/**
 * Copyright 2012 J. Miguel P. Tavares
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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/** The base of all JSON entities.
 * Classes that extend this abstract class can be easily serialized into JSON... well, with some very "strict" rules.
 *
 * Classes that extend this class can have fields defined as:
 * <ul>
 *    <li>java primitives (boolean, byte, char, short, int, long, float and double)</li>
 *    <li>java.util.Map&lt;String, ?&gt;</li>
 *    <li>java.util.List&lt;?&gt;</li>
 * </ul>
 *
 * To convert the the instances to JSON objects just call the method
 * <tt>toJSON ()</tt>. To create a instance of the object from JSON use the
 * constructor with <tt>JSONObject</tt> parameter. */
public abstract class JSONEntity {
	public JSONEntity () {
	}

	/** Des-serialize a JSON object.
	 * @throws JSONException
	 * @throws JSONMappingException */
	public JSONEntity (JSONObject json) throws JSONMappingException {
		if (json == null)
			return;
		for (Field field : this.getClass ().getDeclaredFields ()) {
			if ( (field.getModifiers () & Modifier.TRANSIENT) != 0) { // don't care about transient fields.
				continue;
			}

			String jsonName = field.getName ();
			if (jsonName.equals ("this$0"))
				continue;

			boolean accessible = field.isAccessible ();
			if (!accessible)
				field.setAccessible (true);

			Class<?> type = field.getType ();

			try {
				FieldSetter setter = SETTERS.get (type.getName ());
				if (setter != null)
					setter.setField (this, field, json, jsonName);
				else
					System.out.println ("No setter for " + field);
			} catch (IllegalArgumentException e) {
				throw new JSONMappingException (e);
			} catch (IllegalAccessException e) {
				throw new JSONMappingException (e);
			} catch (JSONException e) {
				throw new JSONMappingException (e);
			}

			if (!accessible)
				field.setAccessible (false);
		}
	}

	private static interface FieldSetter {
		void setField (Object obj, Field field, JSONObject json, String jsonName) throws JSONException, IllegalArgumentException, IllegalAccessException;
	}

	private static interface JSON2Obj {
		Object convert (Object json) throws JSONException;
	}

	final private static HashMap<String, FieldSetter> SETTERS = new HashMap<String, FieldSetter> ();
	final private static HashMap<String, JSON2Obj> CONVERTERS = new HashMap<String, JSON2Obj> ();

	static {
		SETTERS.put ("boolean", new FieldSetter () {
			public void setField (Object obj, Field field, JSONObject json, String jsonName) throws JSONException, IllegalArgumentException, IllegalAccessException {
				field.setBoolean (obj, json.getBoolean (jsonName));
			}
		});
		SETTERS.put ("byte", new FieldSetter () {
			public void setField (Object obj, Field field, JSONObject json, String jsonName) throws JSONException, IllegalArgumentException, IllegalAccessException {
				field.setByte (obj, (byte) json.getInt (jsonName));
			}
		});
		SETTERS.put ("char", new FieldSetter () {
			public void setField (Object obj, Field field, JSONObject json, String jsonName) throws JSONException, IllegalArgumentException, IllegalAccessException {
				String string = json.getString (jsonName);
				if (string == null || string.length () == 0)
					return;
				char charAt = string.charAt (0);
				field.setChar (obj, charAt);
			}
		});
		SETTERS.put ("short", new FieldSetter () {
			public void setField (Object obj, Field field, JSONObject json, String jsonName) throws JSONException, IllegalArgumentException, IllegalAccessException {
				field.setShort (obj, (short) json.getInt (jsonName));
			}
		});
		SETTERS.put ("int", new FieldSetter () {
			public void setField (Object obj, Field field, JSONObject json, String jsonName) throws JSONException, IllegalArgumentException, IllegalAccessException {
				field.setInt (obj, json.getInt (jsonName));
			}
		});
		SETTERS.put ("long", new FieldSetter () {
			public void setField (Object obj, Field field, JSONObject json, String jsonName) throws JSONException, IllegalArgumentException, IllegalAccessException {
				field.setLong (obj, json.getLong (jsonName));
			}
		});
		SETTERS.put ("float", new FieldSetter () {
			public void setField (Object obj, Field field, JSONObject json, String jsonName) throws JSONException, IllegalArgumentException, IllegalAccessException {
				field.setFloat (obj, (float) json.getDouble (jsonName));
			}
		});
		SETTERS.put ("double", new FieldSetter () {
			public void setField (Object obj, Field field, JSONObject json, String jsonName) throws JSONException, IllegalArgumentException, IllegalAccessException {
				field.setDouble (obj, json.getDouble (jsonName));
			}
		});

		SETTERS.put (String.class.getName (), new FieldSetter () {
			public void setField (Object obj, Field field, JSONObject json, String jsonName) throws JSONException, IllegalArgumentException, IllegalAccessException {
				field.set (obj, json.getString (jsonName));
			}
		});

		SETTERS.put (Map.class.getName (), new FieldSetter () {
			public void setField (Object obj, Field field, JSONObject json, String jsonName) throws JSONException, IllegalArgumentException, IllegalAccessException {
				JSONObject jsonMap = json.getJSONObject (jsonName);
				if (jsonMap == null)
					return;
				HashMap<Object, Object> map = new HashMap <Object, Object> (jsonMap.length ());
				Iterator<?> keys = jsonMap.keys ();
				while (keys.hasNext ()) {
					Object key = keys.next ();
					Object jsonValue = jsonMap.get (key.toString ());
					map.put (key, JSONEntity.fromJson (jsonValue));
				}
				field.set (obj, map);
			}
		});


		CONVERTERS.put (JSONObject.class.getName (), new JSON2Obj () {
			public Object convert (Object json) throws JSONException {
				JSONObject jsonMap = (JSONObject) json;
				Map<Object, Object> map = new HashMap <Object, Object> (jsonMap.length ());
				Iterator<?> keys = jsonMap.keys ();
				while (keys.hasNext ()) {
					Object key = keys.next ();
					Object jsonValue = jsonMap.get (key.toString ());
					map.put (key, JSONEntity.fromJson (jsonValue));
				}
				return map;
			}
		});

		CONVERTERS.put (JSONArray.class.getName (), new JSON2Obj () {
			public Object convert (Object json) throws JSONException {
				JSONArray jsonArray = (JSONArray) json;
				Object[] array = new Object[jsonArray.length ()];

				for (int i=0; i<jsonArray.length (); i++) {
					Object jsonValue = jsonArray.get (i);
					array[i] = JSONEntity.fromJson (jsonValue);
				}
				return Arrays.asList (array);
			}
		});
	}



	private static Object fromJson (Object json) throws JSONException {
		if (json == null)
			return null;
		JSON2Obj json2Obj = JSONEntity.CONVERTERS.get (json.getClass ().getName ());
		if (json2Obj != null) {
			return json2Obj.convert (json);
		}
		return json;
	}

	/* ---------------------- */

	/** */
	static private Object toJson (Object obj) throws JSONException {
		Object json = obj;
		if (obj instanceof Map) {
			json = new JSONObject ();
			for (Object mapKey : ((Map<?, ?>)obj).keySet ()) {
				Object jsonValue = toJson (((Map<?, ?>)obj).get (mapKey));
				((JSONObject)json).accumulate (mapKey.toString (), jsonValue);
			}

		} else if (obj instanceof List) {
			json = new JSONArray ();
			for (Object listItem : ((List<?>)obj)) {
				Object jsonListItem = toJson(listItem);
				((JSONArray)json).put (jsonListItem);
			}
		}
		return json;
	}

	/** Serialize the object into a JSON object.
	 * @throws JSONMappingException */
	public JSONObject toJson () throws JSONMappingException {
		JSONObject json = new JSONObject ();
		for (Field field : this.getClass ().getDeclaredFields ()) {

			if ( (field.getModifiers () & Modifier.TRANSIENT) != 0) { // don't care about transient fields.
				continue;
			}

			String jsonName = field.getName ();
			if (jsonName.equals ("this$0"))
				continue;

			boolean accessible = field.isAccessible ();

			if (!accessible)
				field.setAccessible (true);

			try {
				Object jsonField = toJson (field.get (this));
				json.accumulate (jsonName, jsonField);
			} catch (IllegalAccessException e) {
				throw new JSONMappingException (e);
			} catch (JSONException e) {
				throw new JSONMappingException (e);
			}

			if (!accessible)
				field.setAccessible (false);
		}

		return json;
	}
}
