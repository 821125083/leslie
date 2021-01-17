package com.leslie.vo;

public class NewsQueryVo {

    private Integer pageSize;
    private Integer pageNum;
    private String searchText;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public String toString() {
        return "NewsQueryVo{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", searchText='" + searchText + '\'' +
                '}';
    }
}
