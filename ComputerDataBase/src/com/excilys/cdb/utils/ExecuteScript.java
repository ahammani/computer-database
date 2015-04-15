package com.excilys.cdb.utils;

import java.io.IOException;

public class ExecuteScript {

	public static void execute() {
		ProcessBuilder pb = new ProcessBuilder("./refresh-db-test.sh");
		try {
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
