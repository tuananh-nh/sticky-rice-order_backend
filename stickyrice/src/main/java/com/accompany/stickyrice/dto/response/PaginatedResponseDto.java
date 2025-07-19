package com.accompany.stickyrice.dto.response;

import java.util.List;

public class PaginatedResponseDto<T> {
    private List<T> data;
    private int currentPage;
    private int totalPages;
    private long totalItems;

    public PaginatedResponseDto() {
    }

    public PaginatedResponseDto(List<T> data, int currentPage, int totalPages, long totalItems) {
        this.data = data;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }
}

