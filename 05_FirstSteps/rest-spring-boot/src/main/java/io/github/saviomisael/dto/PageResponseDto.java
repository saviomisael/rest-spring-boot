package io.github.saviomisael.dto;

import java.util.List;

public class PageResponseDto<T> {
    private List<T> content;
    private int pageNumber;
    private int totalPages;

    public List<T> getContent() {
        return content;
    }

    public PageResponseDto<T> setContent(List<T> content) {
        this.content = content;
        return this;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public PageResponseDto<T> setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber + 1;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public PageResponseDto<T> setTotalPages(int totalPages) {
        this.totalPages = totalPages == 0 ? 1 : totalPages;
        return this;
    }
}
