/**
 * 
 */
package com.tompai.mapdb;

import java.util.concurrent.ConcurrentMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

/**
 * @author Administrator
 *
 */
public class MapDBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DB db=DBMaker.fileDB("./file.db").make();
		
		HTreeMap map=db.hashMap("map").createOrOpen();
		
		map.put("1", "hello");
		
		db.close();
		
	}

}
