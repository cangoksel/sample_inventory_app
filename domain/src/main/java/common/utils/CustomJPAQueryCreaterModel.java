package common.utils;


import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by tozyurek on 12.11.2015.
 */
public interface CustomJPAQueryCreaterModel {
    JPAQuery<? extends Object>  mapJPAQuery(EntityManager entityManager);
    Expression[] getProjections();
    List<SorguSonucColumn> getSorguSonucColumnList();

    default void setSortColumn(SorguSonucColumn sortColumn) {

    }
}
