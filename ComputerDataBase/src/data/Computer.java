package data;

/**
 * 
 * @author ahammani
 * Class which represents Computer Entity
 */
import java.sql.Timestamp;

public class Computer {
	private String name = "";
	private Timestamp intro_date;
	private Timestamp dis_date;
	private int company_id;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public Computer() {
	}

	@Override
	public String toString() {
		return "Id : " + id + " Computer name : " + name + ", date d'entree : "
				+ intro_date + ", date hors service : " + dis_date
				+ ", company_id :" + company_id;
	}

	public Computer(String name) {
		this.name = name;
	}

	public Computer(String name, Timestamp intro, Timestamp dis, int id) {
		this.name = name;
		this.intro_date = intro;
		this.dis_date = dis;
		this.company_id = id;
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
