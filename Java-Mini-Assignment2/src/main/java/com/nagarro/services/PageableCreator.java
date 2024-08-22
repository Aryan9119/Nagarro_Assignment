package com.nagarro.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Utility class for creating a Spring Data {@link Page} from a list of elements.
 */
public class PageableCreator {

    /**
     * Creates a Spring Data {@link Page} from a list of elements and a {@link PageRequest}.
     *
     * @param list        The list of elements to be paginated.
     * @param pageRequest The pagination information, including page size and offset.
     * @param <T>         The type of elements in the list.
     * @return A paginated {@link Page} containing a subset of the input list based on the provided page request.
     */
    public static <T> Page<T> createPageFromList(List<T> list, PageRequest pageRequest) {
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), list.size());

        List<T> content = list.subList(start, end);

        return new PageImpl<>(content, pageRequest, list.size());
    }
}
