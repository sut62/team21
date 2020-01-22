package se.g21.backend.reviewcoursesystem.entities;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.Size;
import javax.validation.constraints.Past;
import javax.validation.constraints.NotNull;
import se.g21.backend.enrollcoursesystem.entities.EnrollCourse;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "REVIEW_COURSE")
public class ReviewCourse {
    @Id
    @SequenceGenerator(name = "REVIEW_SEQ", sequenceName = "REVIEW_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEW_SEQ")
    @Column(name = "REVIEW_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = EnrollCourse.class)
    @JoinColumn(name = "EnrollCourse_ID", insertable = true)
    private EnrollCourse enrollCourse;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Rating.class)
    @JoinColumn(name = "RATING_ID", insertable = true)
    private Rating rating;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Improvement.class)
    @JoinColumn(name = "IMPROVEMENT_ID", insertable = true)
    private Improvement improvement;

    @NotNull
    @Size(max=80)
    @Column(name = "REVIEW_COMMENT")
    private String comment;

    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "REVIEW_DATE")
    private @NonNull Date reviewDate;
}