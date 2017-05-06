package com.imd.util;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义分页
 * Created by jinyao on 2017/4/21.
 */
public class MyPageInfo<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String keyWord;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //当前页的数量
    private int size;
    //总记录数
    private int total;
    //总页数
    private int pages;
    //结果集
    private List<T> list;
    //前一页
    private int prePage;
    //下一页
    private int nextPage;
    //是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;
    //是否有前一页
    private boolean hasPreviousPage = false;
    //是否有下一页
    private boolean hasNextPage = false;
    //导航页码数
    private int navigatePages;
    //所有导航页号
    private int[] navigatepageNums;
    //导航条上的第一页
    private int navigateFirstPage;
    //导航条上的最后一页
    private int navigateLastPage;

    public MyPageInfo() {

    }

    /**
     * 包装MyPageInfo
     *
     * @param pageNum  传入的页码号
     * @param pageSize 每页的大小
     * @param list     一共数据
     */
    public MyPageInfo(int pageNum, int pageSize, List<T> list) {
        if (pageNum == 0) {
            this.pageNum = 1;
        }
        this.total = list.size(); //记录的条数
        this.pageNum = pageNum;
        this.pageSize = pageSize;

        this.pages = this.total / this.pageSize + 1;//计算页数

        //计算每页的记录
        int offset = (this.pageNum - 1) * this.pageSize; //已经用去的记录数
        if (offset >= this.total) {  //当前页数为0
            this.list = null;
            this.size = 0;
            this.hasNextPage = false;
            this.isLastPage = false;
            if (this.pageNum == 1) {
                this.hasPreviousPage = false;
                this.isFirstPage = true;
            } else {
                this.hasPreviousPage = true;
                this.prePage = this.pageNum - 1;
                this.isFirstPage = false;
            }
        } else { //当前页数不为零
            int restNum = this.total - offset;
            if (restNum > this.pageSize) { //剩余记录数大于pageSize
                this.list = list.subList(offset, offset + pageSize);
                this.size = 10;
                this.hasNextPage = true;
                this.isLastPage = false;
                this.nextPage = this.pageNum + 1;
                if (this.pageNum == 1) {
                    this.hasPreviousPage = false;
                    this.isFirstPage = true;
                } else {
                    this.isFirstPage = false;
                    this.hasPreviousPage = true;
                    this.prePage = this.pageNum + 1;
                }
            } else {
                this.list = list.subList(offset, this.total);
                this.size = this.list.size();
                this.hasNextPage = false;
                this.isLastPage = true;
                if (this.pageNum == 1) {
                    this.isFirstPage = true;
                    this.hasPreviousPage = false;
                } else {
                    this.isFirstPage = false;
                    this.hasPreviousPage = true;
                    this.prePage = this.pageNum - 1;
                }
            }
        }

    }


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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
