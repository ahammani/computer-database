package model;

/**
 * 
 * @author ahammani
 * Class which represents Computer Entity
 */
import java.time.LocalDateTime;

public class Computer {
	private String name = "";
	private LocalDateTime intro_date;
	private LocalDateTime dis_date;
	private Company company;
	private long id;

	public long getId() {
		return id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany_id(Company company) {
		if (company.getId() < 1) {
			System.out.println("Impossible set to a negative number");
		} else {
			this.company = company;
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
		String cid = (company.getId() != 0) ? "COMPANY ID :" + company.getId()
				: "";
		return aid + cname + intro + dis + cid;
	}

	public Computer(String name) {
		this.name = name;
	}

	public Computer(String name, LocalDateTime intro, LocalDateTime dis,
			Company comp, long c_id) {
		this.name = name;
		this.intro_date = intro;
		this.dis_date = dis;
		this.company = comp;
		this.id = c_id;
	}

	public Computer(String name, LocalDateTime intro, LocalDateTime dis,
			Company comp) {
		this.name = name;
		this.intro_date = intro;
		this.dis_date = dis;
		this.company = comp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getIntro_date() {
		return intro_date;
	}

	public void setIntro_date(LocalDateTime intro_date) {
		this.intro_date = intro_date;
	}

	public LocalDateTime getDis_date() {
		return dis_date;
	}

	public void setDis_date(LocalDateTime dis_date) {
		this.dis_date = dis_date;
	}

}
