package courses.paint.mini.model;

import courses.paint.mini.entity.course.CourseEntity;
import courses.paint.mini.entity.product.PaintEntity;
import courses.paint.mini.model.course.CourseFilters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CourseSpecification implements Specification<CourseEntity> {

    private final CourseFilters courseFilters;

    @Override
    public Predicate toPredicate(Root<CourseEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (courseFilters.courseTitle() != null) {
            predicates.add(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(
                                    root.get("title")
                            ),
                            toPattern(courseFilters.courseTitle()))
            );
        }

        if (courseFilters.gameId() != null) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.join("miniature")
                                    .join("faction")
                                    .join("game")
                                    .get("id"),
                            courseFilters.gameId())
            );
        }

        if (courseFilters.factionId() != null) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.join("miniature")
                                    .join("faction")
                                    .get("id"),
                            courseFilters.factionId())
            );
        }

        if (courseFilters.miniatureId() != null) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.join("miniature")
                                    .get("id"),
                            courseFilters.miniatureId())
            );
        }

        if (courseFilters.username() != null) {
            predicates.add(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(
                                    root.join("user")
                                            .get("username")
                            ),
                            toPattern(courseFilters.username()))
            );
        }

        if (courseFilters.miniatureProducerId() != null) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.join("miniature")
                                    .join("faction")
                                    .join("game")
                                    .join("producer")
                                    .get("id"),
                            courseFilters.miniatureProducerId())
            );
        }

        if (courseFilters.modelingProductId() != null) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.join("steps")
                                    .join("usedOtherModelingProducts")
                                    .get("id"),
                            courseFilters.modelingProductId())
            );
        }

        if (courseFilters.paintId() != null) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.join("steps")
                                    .joinMap("usedPaints")
                                    .key(),
                            new PaintEntity(courseFilters.paintId(), null, null, null)
                    )
            );
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private String toPattern(String text) {
        return "%" + text.toLowerCase() + "%";
    }

}
