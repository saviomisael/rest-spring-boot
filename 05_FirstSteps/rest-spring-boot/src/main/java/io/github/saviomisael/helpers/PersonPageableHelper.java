package io.github.saviomisael.helpers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;

public class PersonPageableHelper {
    private PersonPageableHelper() {
    }

    public static Pageable getPageable(int page,
                                       String sort,
                                       String direction,
                                       String filterByGender) {
        Pageable defaultPageable = PageRequest.of(page < 2 ? 0 : page - 1, 9);

        if(!filterByGender.isBlank()) return defaultPageable;

        if (!sort.isBlank() && sort.equals("gender")) {
            return direction.equalsIgnoreCase("asc")
                    ? PageRequest.of(page < 2 ? 0 : page - 1, 9, Sort.by(sort).ascending())
                    : PageRequest.of(page < 2 ? 0 : page - 1, 9, Sort.by(sort).descending());
        }

        return defaultPageable;
    }
}
