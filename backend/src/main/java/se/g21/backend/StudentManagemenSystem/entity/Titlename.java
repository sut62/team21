package se.g21.backend.StudentManagemenSystem.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="TITLENAME")
public class Titlename {
	@Id
	@SequenceGenerator(name="titlename_seq",sequenceName="titlename_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="titlename_seq")
	@Column(name="TITLENAME_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String name;

	@OneToMany(fetch = FetchType.EAGER)
	
	private Collection<MemberRegister> reg;
}
