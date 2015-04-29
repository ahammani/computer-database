package com.excilys.cdb.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.excilys.cdb.servlet.dto.ComputerDTO;
import com.excilys.cdb.utils.Utils;

/**
 * Object which represent a web page. Parameters are checked with the method
 * validate() and if bad put a default value. The method validate() MUST be call
 * each time a page is created
 * 
 * @author ahammani
 *
 */
public class Page {
	private static final List<String> fields = Arrays.asList("c_name",
			"introduced", "discontinued", "name");
	private static final List<String> orders = Arrays.asList("asc", "desc");

	private String tmpPage;
	private int page = 1;
	private String tmpLimit;
	private int limit = 3;
	private int offset = 0;
	private int maxPages = 1;
	private List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
	private int maxComputers = 0;
	private String search = "";
	private String field_order = "";
	private String order = "asc";

	public Page() {

	}

	public Page(int number, int limit) {
		super();
		this.page = number;
		this.limit = limit;
	}

	public Page(String number, String lim, String search, String field_order,
			String order) {
		this.tmpPage = number;
		this.tmpLimit = lim;
		this.search = search;
		this.field_order = field_order;
		this.order = order;
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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getField_order() {
		return field_order;
	}

	public void setField_order(String field_order) {
		this.field_order = field_order;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
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

	public void validate() {
		this.page = Utils.stringToInt(tmpPage, 1);
		this.limit = Utils.stringToInt(tmpLimit, 3);
		this.offset = (this.page - 1) * this.limit;
		this.offset = (offset < 0) ? 0 : offset;

		if (field_order == null || !fields.contains(field_order.toLowerCase())) {
			this.field_order = "";
		}
		if (order == null || !orders.contains(order.toLowerCase())) {
			this.order = "asc";
		}
		if (search == null) {
			search = "";
		}
	}

	@Override
	public String toString() {
		return "Page [page=" + page + ", limit=" + limit + ", offset=" + offset
				+ ", maxPages=" + maxPages + ", computers=" + computers
				+ ", maxComputers=" + maxComputers + "]";
	}

}
