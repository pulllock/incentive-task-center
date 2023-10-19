package fun.pullock.activity.api.model.common;

import java.io.Serializable;

public class PageQuery<T> implements Serializable {

    private int pageNo = 1;

    private int pageSize = 10;

    private T query;

    public PageQuery() {
    }

    public PageQuery(T t) {
        query = t;
    }

    public int getStart() {
        return Math.max(0, pageNo * pageSize - pageSize);
    }


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getQuery() {
        return query;
    }

    public void setQuery(T query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "PageQuery{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", query=" + query +
                '}';
    }
}
