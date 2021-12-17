package io.example.tennis;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class QueryGraph<T> {

    @Getter
    private final Set<T> rootCollection;

    @Getter
    private final Class<T> tClass;

    private final EntityManager entityManager;

    public QueryGraph(
            Collection<T> startingElements,
            Class<T> tClass,
            EntityManager entityManager
    ) {
        this.rootCollection = new HashSet<>(startingElements);
        this.tClass = tClass;
        this.entityManager = entityManager;
    }

    public QueryGraph(
            T startingElement,
            Class<T> tClass,
            EntityManager entityManager
    ) {
        this.rootCollection = new HashSet<>(Set.of(startingElement));
        this.tClass = tClass;
        this.entityManager = entityManager;
    }

    public Set<T> get() {
        return rootCollection;
    }

    /**
     * Given a collection of objects and the setter and getter of a property, sets the fields with the freshly populated
     * objects.
     * <p>
     * This is what Hibernate would do for you if you were to call the getter on the property, but this method does the
     * query for all objects at once, avoiding the N+1 queries problem.
     *
     * @param field the name of the field
     * @param uClass  the class of the referenced objects
     * @param <U>   the type of the property
     */
    public <U> QueryGraph<U> joinFetch(
            String field,
            Class<U> uClass
    ) {
        //noinspection unchecked
        var ignored = entityManager.createQuery(
                                "from " + tClass.getSimpleName() + " t " +
                                "join fetch t." + field + " u " +
                                "where t in :entities"
                )
                .setParameter("entities", rootCollection)
                .getResultList();
        return rootCollection.stream()
                .map(e -> {
                    try {
                        Field field = FieldUtils.getField(employee.getClass(), field, true);
                        firstName = (String) field.get(employee);
                    } catch (Exception e) {
                        System.err.print(ExceptionUtils.getStackTrace(e));
                    }
                })
        return new QueryGraph<>(pairs, uClass, entityManager);
    }
}
