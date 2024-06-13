package com.springapp.clinic.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PageableUtil {

    private static final Logger log = LoggerFactory.getLogger(PageableUtil.class);

    public static Pageable buildPageable(Integer page, Integer size, List<String> sort) {

        int pageNumber = page != null ? page : 0;
        int sizeNumber = size != null ? size : 20;
        Sort sort1 = null;

        if (sort != null && sort.size() == 2) {
            try {
                Sort.Direction direction = Sort.Direction.fromString(sort.get(0));
                sort1 = Sort.by(direction, sort.get(1));
            } catch (IllegalArgumentException ex) {
                log.info("Illegal argument Direction in sort", ex);
            }
        }

        if (sort1 == null)
            return PageRequest.of(pageNumber, sizeNumber);
        else
            return PageRequest.of(pageNumber, sizeNumber, sort1);
    }
}
