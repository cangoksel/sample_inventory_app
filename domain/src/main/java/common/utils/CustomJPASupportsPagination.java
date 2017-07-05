package common.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by tozyurek on 12.11.2015.
 */
public interface CustomJPASupportsPagination extends SupportsPagination {
    Page findAll(CustomJPAQueryCreaterModel model, Pageable pageable);
}
