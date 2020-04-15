package buy.baabaashop.common;

import java.io.Serializable;

public class PaginationRequestParam {
    private Integer page = 1;
    private Integer pageSize = 10;

    private Integer from = 0;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getFrom() {
        return (page * pageSize) - pageSize;
    }
}
