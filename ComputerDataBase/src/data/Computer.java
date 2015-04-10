package data;

import java.sql.Timestamp;

public class Computer {
	private String name;
	private Timestamp intro_date;
	private Timestamp dis_date;

	public Computer() {
	}

	public Computer(String name) {
		this.name = name;
	}

	public Computer(String name, Timestamp intro, Timestamp dis) {
		this.name = name;
		this.intro_date = intro;
		this.dis_date = dis;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getIntro_date() {
		return intro_date;
	}

	public void setIntro_date(Timestamp intro_date) {
		this.intro_date = intro_date;
	}

	public Timestamp getDis_date() {
		return dis_date;
	}

	public void setDis_date(Timestamp dis_date) {
		this.dis_date = dis_date;
	}

}
