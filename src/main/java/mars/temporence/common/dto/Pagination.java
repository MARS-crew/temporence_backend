package mars.temporence.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {

    private int totalPages;
    private int currentPage;
    private long totalItems;

    @Builder
    public Pagination(int totalPages, int currentPage, long totalItems) {
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
    }
}
