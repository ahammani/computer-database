package page;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.servlet.dto.ComputerDTO;
import com.excilys.cdb.utils.Utils;

public class Page {

	private int page = 1;
	private int limit = 3;
	private int offset = 0;
	private int maxPages = 1;
	List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
	int maxComputers = 0;

	public Page() {

	}

	public Page(int number, int limit) {
		super();
		this.page = number;
		this.limit = limit;
		this.offset = (page - 1) * limit;
		this.offset = (offset < 0) ? 0 : offset;
	}

	public Page(String number, String lim) {
		this.page = Utils.StringToInt(number, 1);
		this.limit = Utils.StringToInt(lim, 3);
		this.offset = (this.page - 1) * this.limit;
		this.offset = (offset < 0) ? 0 : offset;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMaxPages() {
		return maxPages;
	}

	public void setMaxPages(int maxComputers) {
		this.maxPages = (maxComputers % limit == 0) ? (maxComputers / limit)
				: (maxComputers / limit) + 1;
		this.page = (this.page > this.maxPages) ? (this.maxPages - 1)
				: this.page;
		this.page = (page <= 0) ? 1 : this.page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		if (offset >= 0)
			this.offset = offset;
		else
			this.offset = 0;
	}

	public List<ComputerDTO> getComputers() {
		return computers;
	}

	public void setComputers(List<ComputerDTO> computers) {
		if (computers == null) {
			this.maxPages = 1;
			this.page = 1;
			this.maxComputers = 0;
		}
		this.computers = computers;
	}

	public int getMaxComputers() {
		return maxComputers;
	}

	public void setMaxComputers(int maxComputers) {
		if (maxComputers >= 0)
			this.maxComputers = maxComputers;
		else
			this.maxComputers = 0;
	}

	@Override
	public String toString() {
		return "Page [page=" + page + ", limit=" + limit + ", offset=" + offset
				+ ", maxPages=" + maxPages + ", computers=" + computers
				+ ", maxComputers=" + maxComputers + "]";
	}

}
