package io.example.tennis;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
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
     * @param fieldName the name of the field
     * @param uClass    the class of the referenced objects
     * @param <U>       the type of the property
     */
    public <U> QueryGraph<U> joinFetch(
            String fieldName,
            Class<U> uClass
    ) {
        //noinspection unused
        var ignored = entityManager.createQuery(
                        "from " + tClass.getSimpleName() + " t " +
                                "join fetch t." + fieldName + " u " +
                                "where t in :entities"
                )
                .setParameter("entities", rootCollection)
                .getResultList();
        Field field = FieldUtils.getField(tClass, fieldName, true);
        Set<U> newCollection = rootCollection.stream()
                .map(el -> {
                    try {
                        //noinspection unchecked
                        return (U) field.get(el);
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        System.err.print(ExceptionUtils.getStackTrace(e));
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        return new QueryGraph<>(newCollection, uClass, entityManager);
    }
}
