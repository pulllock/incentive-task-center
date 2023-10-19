package fun.pullock.activity.api.model.common;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {

    private int total;

    private List<T> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PagedResult{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
