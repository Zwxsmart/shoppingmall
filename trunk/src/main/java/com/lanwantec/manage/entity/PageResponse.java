package com.lanwantec.manage.entity;

import java.util.List;
import java.util.Map;

/** 分页封装类 */
public class PageResponse {
	// 页码栏封装 来自web 数据
	private int pageNum;// 来自web PageRequest
	private int pageSize;// 每页显示记录数据 来自pageRequest传递
	// 数据库查询
	private List<Map<String, Object>> data;// dao 层 select * from table limit ?,?
	private int totalCounts;// 表的总记录数 数据库查询 dao
	// 由上述数据 完全计算出来...
	private int totalPages;// 总页码 计算出来
	private int beforePage;// 上一页
	private int nextPage;// 下一页
	// 6----15 分页栏页码数据 pageNum-5 pageNum+4
	private int[] pageBar;// 计算出来 pageNum+totalPages

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}

	public int getTotalCounts() {
		return totalCounts;
	}

	public void setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
	}

	// 总页码计算 pageSize totalCounts
	public int getTotalPages() {
		this.totalPages = (this.totalCounts + this.pageSize - 1) / this.pageSize;
		return this.totalPages;
	}

	// 上一页 pageNum-1
	public int getBeforePage() {
		this.beforePage = this.pageNum - 1;
		if (this.beforePage <= 0) {
			this.beforePage = 1;
		}
		return this.beforePage;
	}

	// 下一页封装
	public int getNextPage() {
		this.nextPage = this.pageNum + 1;
		if (this.nextPage >= this.totalPages) {
			this.nextPage = this.totalPages;
		}
		return this.nextPage;
	}

	// 分业栏每一个页码数字 pageNum totalPages;
	public int[] getPageBar() {
		int beginPage;// 页码首位数字 6
		int endPage;// 页码结尾数字 15
		// 1: 判断总页码 是否超出10个页码
		if (this.totalPages <= 10) {
			beginPage = 1;
			endPage = this.totalPages;
		} else {
			// 总页码超出10个页码 3
			beginPage = this.pageNum - 5;
			endPage = this.pageNum + 4; // 总页码: 15 当前页码 14

			if (beginPage <= 0) {
				beginPage = 1;
				endPage = beginPage + 9;
			}

			if (endPage >= this.totalPages) {
				beginPage = this.totalPages - 9;
				endPage = this.totalPages;
			}
		}
		this.pageBar = new int[endPage - beginPage + 1];
		// 根据 if else 得出 首位页码 以及末尾页码 数组 赋值...
		int index = 0;
		for (int i = beginPage; i <= endPage; i++) {
			this.pageBar[index++] = i;
		}
		return this.pageBar;
	}

}
