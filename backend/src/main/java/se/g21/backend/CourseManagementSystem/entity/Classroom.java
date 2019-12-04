package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="Classroom")
public class Classroom {

    @Id
    @SequenceGenerator(name="classroom_seq",sequenceName="classroom_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="classroom_seq")  
    @Column(name = "Classroom_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String RoomNumber;


    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Courses> classroom;

}
