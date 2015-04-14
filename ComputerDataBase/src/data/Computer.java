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

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		if (company_id < 1) {
			System.out.println("Impossible set to a negative number");
		} else {
			this.company_id = company_id;
		}
	}

	public Computer() {
	}

	@Override
	public String toString() {
		String intro = (intro_date != null) ? "INTRODUCED : "
				+ intro_date.toString() + "\t " : "";
		String dis = (dis_date != null) ? "DISCONTINUED : "
				+ dis_date.toString() + "\t " : "";
		String aid = "ID : " + id + "\t ";
		String cname = "COMPUTER NAME : " + name + "\t ";
		String cid = (company_id != 0) ? "COMPANY ID :" + company_id : "";
		return aid + cname + intro + dis + cid;
	}

	public Computer(String name) {
		this.name = name;
	}

	public Computer(String name, Timestamp intro, Timestamp dis, int cid, int id) {
		this.name = name;
		this.intro_date = intro;
		this.dis_date = dis;
		this.company_id = cid;
		this.id = id;
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
