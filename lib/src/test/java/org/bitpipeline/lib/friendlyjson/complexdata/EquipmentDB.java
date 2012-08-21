/**
 * Copyright 2012
 *         J. Miguel P. Tavares <mtavares@bitpipeline.eu>
 *         BitPipeline
 */
package org.bitpipeline.lib.friendlyjson.complexdata;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bitpipeline.lib.friendlyjson.JSONMappingException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author mtavares */
public class EquipmentDB {
	final static private List<EquipmentBoard> MEM_BOARDS = new ArrayList<EquipmentBoard> ();
	final static private List<EquipmentSail> MEM_SAILS = new ArrayList<EquipmentSail> ();
	final static private List<EquipmentFin> MEM_FINS = new ArrayList<EquipmentFin> ();

	public EquipmentDB () {
		EquipmentDB.MEM_BOARDS.addAll (Arrays.asList (GenericEquipment.BOARDS));
		EquipmentDB.MEM_SAILS.addAll (Arrays.asList (GenericEquipment.SAILS));
		EquipmentDB.MEM_FINS.addAll (Arrays.asList (GenericEquipment.FINS));
	}

	public List<EquipmentBoard> getAllBoards () {
		return MEM_BOARDS;
	}

	public List<EquipmentSail> getAllSails () {
		return MEM_SAILS;
	}

	public List<EquipmentFin> getAllFins () {
		return MEM_FINS;
	}

	static class EquipmentPackageEntity  {
		final List<EquipmentBrand> brands = new ArrayList<EquipmentBrand> ();
		final List<EquipmentCollection> collections = new ArrayList<EquipmentCollection> ();
		final List<EquipmentBoard> boards = new ArrayList<EquipmentBoard> ();
		final List<EquipmentSail> sails = new ArrayList<EquipmentSail> ();
		final List<EquipmentFin> fins = new ArrayList<EquipmentFin> ();
		
		final HashMap<String, EquipmentBrand> brandMap = new HashMap<String, EquipmentBrand> ();
		
		public EquipmentPackageEntity (String source)  throws JSONMappingException, JSONException {
			JSONObject json = new JSONObject (source);
			parseBrands (json);
		}

		private void parseBrands (JSONObject root) throws JSONException {
			if (root == null)
				return;
			JSONObject jsBrands = (JSONObject) root.get ("brands");
			Iterator<String> keys = jsBrands.keys ();
			while (keys.hasNext ()) {
				String grandId = keys.next ();
				JSONObject jsBrand = (JSONObject) jsBrands.get (grandId);
				String name = (String) jsBrand.get ("name");
				String url = (String) jsBrand.get ("url");
				String imageUrl = (String) jsBrand.get ("imagePath");
				System.out.println ("\tbrand: "  + name);
				System.out.println ("\timage: "  + imageUrl);
				System.out.println ("\turl: "  + url);
			}
		}
		public List<EquipmentBrand> getBrands () {
			return this.brands;
		}

		public List<EquipmentCollection> getCollection () {
			return this.collections;
		}

	}

	static class PackageException extends Exception {
		/** */
		private static final long serialVersionUID = 4061079066832628538L;

		public PackageException (Throwable e) {
			super (e);
		}
	}

	private void clearFiles () {
		// TODO
	}
	
	private boolean addFile (String fileName) throws PackageException {
		JarFile f;
		String fIndex;
		try {
			f = new JarFile (fileName, false);
			JarEntry e = f.getJarEntry (fileName);
			int size = (int) e.getSize ();
			
			char[] buffer = new char[size];
			
			Reader isReader = new InputStreamReader (f.getInputStream(e));
			StringWriter sw = new StringWriter((int) (e.getCompressedSize()*2));
			isReader.read (buffer);
			sw.write (buffer);
			f.close ();
			
			fIndex = sw.toString ();
		} catch (IOException e) {
			throw new PackageException (e);
		};

		EquipmentPackageEntity pak;
		try {
			pak = new EquipmentPackageEntity (fIndex);
		} catch (Exception e) {
			e.printStackTrace ();
			return false;
		}

		System.out.println (fileName + "\n" + pak.toString ());
		if (pak.getBrands () != null)
			for (EquipmentBrand brand : pak.getBrands ()) {
				System.out.println (brand.getName ());
			}
		return true;
	}
}
