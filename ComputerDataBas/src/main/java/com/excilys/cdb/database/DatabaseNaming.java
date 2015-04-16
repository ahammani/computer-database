package com.excilys.cdb.database;

public class DatabaseNaming {
	public static final String USER = "admincdb-test";
	public static final String PWD = "qwerty1234";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String DATABASE_NAME = "computer-database-db-test";
	public static final String URL = "jdbc:mysql://localhost:3306/"
			+ DATABASE_NAME + "?zeroDateTimeBehavior=convertToNull";

	// Nom des tables
	/** The Constant COMPUTER_TABLE. */
	public static final String COMPUTER_TABLE = "computer";

	/** The Constant COMPANY_TABLE. */
	public static final String COMPANY_TABLE = "company";

	// Attributs de la table Computer
	/** The Constant COMPUTER_ID. */
	public static final String COMPUTER_ID = "id";

	/** The Constant COMPUTER_NAME. */
	public static final String COMPUTER_NAME = "name";

	/** The Constant COMPUTER_INTRODUCED. */
	public static final String COMPUTER_INTRODUCED = "introduced";

	/** The Constant COMPUTER_DISCONTINUED. */
	public static final String COMPUTER_DISCONTINUED = "discontinued";

	/** The Constant COMPUTER_COMPANYID. */
	public static final String COMPUTER_COMPANYID = "company_id";

	// Attributs de la table Company
	/** The Constant COMPANY_ID. */
	public static final String COMPANY_ID = "id";

	/** The Constant COMPANY_NAME. */
	public static final String COMPANY_NAME = "name";
}
