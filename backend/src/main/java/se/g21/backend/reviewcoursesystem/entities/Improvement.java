package se.g21.backend.reviewcoursesystem.entities;
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
import javax.validation.constraints.NotNull;

@Data
@Entity
@Getter 
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="IMPROVEMENT")
public class Improvement {
    @Id    
    @SequenceGenerator(name="IMPROVEMENT_SEQ",sequenceName="IMPROVEMENT_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="IMPROVEMENT_SEQ")  
    @Column(name="IMPROVEMENT_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @NotNull
    private String improvementType;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<ReviewCourse> reviewCourse;
}