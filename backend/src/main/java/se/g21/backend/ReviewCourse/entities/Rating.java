package se.g21.backend.reviewcourse.entities;
import lombok.*;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Data
@Entity
@Getter 
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="RATING")
public class Rating {
    @Id    
    @SequenceGenerator(name="RATING_SEQ",sequenceName="RATING_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RATING_SEQ")  
    @Column(name="RATING_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String ratingType;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<ReviewCourse> reviewCourse;
}