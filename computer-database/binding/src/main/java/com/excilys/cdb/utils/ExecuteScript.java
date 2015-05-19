package com.excilys.cdb.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class for prepare db to test
 * 
 * @author ahammani
 *
 */
public class ExecuteScript {
	public static final String CHEMIN = "./src/main/scripts";

	public static void execute() {
		ProcessBuilder pb = new ProcessBuilder("./refresh-db-test.sh");
		pb.directory(new File(CHEMIN));
		try {
			Process process = pb.start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			while ((br.readLine()) != null)
				;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
