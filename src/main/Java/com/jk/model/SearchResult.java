package com.jk.model;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {
    private static final long serialVersionUID = -3853097450295556668L;
    //商品列表
    private List<Dldshop> itemList;
    //总记录数
    private long recordCount;
    //总页数
    private long pageCount;
    //当前页
    private long curPage;

    public List<Dldshop> getItemList() {
        return itemList;
    }
    public void setItemList(List<Dldshop> itemList) {
        this.itemList = itemList;
    }
    public long getRecordCount() {
        return recordCount;
    }
    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }
    public long getPageCount() {
        return pageCount;
    }
    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }
    public long getCurPage() {
        return curPage;
    }
    public void setCurPage(long curPage) {
        this.curPage = curPage;
    }

}
