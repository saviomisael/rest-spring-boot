package io.github.saviomisael.mapper;

import io.github.saviomisael.dto.PageResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageMapper {
    private PageMapper() {
    }

    public static <T> PageResponseDto<T> fromPageToPageResponse(Page<T> page) {
        return new PageResponseDto<T>().setContent(page.getContent()).setTotalPages(page.getTotalPages()).setPageNumber(page.getNumber());
    }
}
