package page;

import java.util.List;

import main.Main;

public class Page<T> {
	private List<T> data;
	private int currentPage = 0;
	private int nbfield = 10;

	public Page() {

	}

	public Page(List<T> data, int nbfield) {
		super();
		this.data = data;
		this.nbfield = nbfield;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNbfield() {
		return nbfield;
	}

	public void setNbfield(int nbfield) {
		this.nbfield = nbfield;
	}

	public void display() {
		for (int i = currentPage; i < nbfield; i++) {
			System.out
					.println(data.get((currentPage * nbfield) + i).toString());
		}
		turnPages();
	}

	public void next() {
		if (currentPage < (data.size() / nbfield) - 1) {
			setCurrentPage(currentPage + 1);
		}
	}

	public void previous() {
		if (currentPage > 0) {
			setCurrentPage(currentPage - 1);
		}
	}

	public void turnPages() {
		System.out.println("< p 	e (exit)	 n >");
		System.out.print(" > ");
		String ans = Main.sc.next().substring(0, 1).toLowerCase();
		switch (ans) {
		case "p":
			previous();
			display();
			break;
		case "n":
			next();
			display();
			break;
		case "e":
			break;
		default:
			break;
		}
	}
}
