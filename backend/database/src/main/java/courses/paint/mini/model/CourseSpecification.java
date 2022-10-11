package courses.paint.mini.model;

import courses.paint.mini.entity.course.CourseEntity;
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
                                    .get("game"),
                            courseFilters.gameId())
            );
        }

        if (courseFilters.factionId() != null) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.join("miniature")
                                    .get("faction"),
                            courseFilters.factionId())
            );
        }

        if (courseFilters.miniatureId() != null) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.get("miniature"),
                            courseFilters.miniatureId())
            );
        }

        if (courseFilters.paintId() != null) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.join("steps")
                                    .join("usedPaints")
                                    .get("paint_id"),
                            courseFilters.paintId())
            );
        }

        if (courseFilters.userId() != null) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.get("user"),
                            courseFilters.userId())
            );
        }

        if (courseFilters.miniatureProducerId() != null) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.join("miniature")
                                    .get("producer"),
                            courseFilters.miniatureProducerId())
            );
        }

        if (courseFilters.modelingProductId() != null) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.join("steps")
                                    .join("usedOtherModelingProducts")
                                    .get("modeling_product_id"),
                            courseFilters.modelingProductId())
            );
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private String toPattern(String text) {
        return "%" + text.toLowerCase() + "%";
    }

}
