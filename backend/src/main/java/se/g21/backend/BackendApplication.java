package se.g21.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import lombok.*;
import java.util.stream.Stream;
import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Collections;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Optional;

// import Employee system
import se.g21.backend.employeesystem.entities.*;
import se.g21.backend.employeesystem.repository.*;

// import Student system
import se.g21.backend.studentsystem.entities.*;
import se.g21.backend.studentsystem.repository.*;

// import Course system
import se.g21.backend.coursesystem.entities.*;
import se.g21.backend.coursesystem.repository.*;

//EnrollCourse System
import se.g21.backend.enrollcoursesystem.entities.*;
import se.g21.backend.enrollcoursesystem.repository.*;

//RecordExpense System
import se.g21.backend.recordexpensesystem.entities.*;
import se.g21.backend.recordexpensesystem.repository.*;

//Reviewcourse System
import se.g21.backend.reviewcoursesystem.entities.*;
import se.g21.backend.reviewcoursesystem.repository.*;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	ApplicationRunner init(EmployeeRepository employeeRepository, // Employee system
			PositionRepository positionRepository, ProvinceRepository provinceRepository,
			GenderRepository genderRepository, NametitleRepository nametitleRepository,
			StudentRepository studentRepository, // Student system
			CourseRepository courseRepository, // Course system
			RoomRepository roomRepository, SubjectsRepository subjectsRepository, TimeRepository timeRepository,
			EnrollCourseRepository enrollCourseRepository, // EnrollCourse System
			ComputerRepository computerRepository, RecordExpenseRepository recordexpenseRepository, // RecordExpense
																									// System
			ExpenseTypeRepository expenseTypeRepository, RatingRepository ratingRepository, // ReviewCourse
			ImprovementRepository improvementRepository, ReviewCourseRepository reviewCourseRepository) {
		return args -> {

			// Employee system
			Stream.of("นาย", "นางสาว", "นาง", "เด็กชาย", "เด็กหญิง").forEach(newnametitle -> {
				Nametitle nametitle = new Nametitle();
				nametitle.setNametitle(newnametitle);
				nametitleRepository.save(nametitle);
			});

			Stream.of("ชาย", "หญิง").forEach(newgender -> {
				Gender gender = new Gender();
				gender.setGender(newgender);
				genderRepository.save(gender);
			});

			Object[][] positions = new Object[][] { { "พนักงานทำความสะอาด", 12000.01 },
					{ "พนักงานเคาท์เตอร์", 18000.05 }, { "ติวเตอร์", 25000.00 }, { "ผู้จัดการ", 36000.99 } };

			for (int i = 0; i < positions.length; i++) {
				Position position = new Position();
				position.setPosition((String) positions[i][0]);
				position.setSalary((Double) positions[i][1]);
				positionRepository.save(position);
			}

			Stream.of("เชียงราย", "เชียงใหม่", "น่าน", "พะเยา", "แพร่", "แม่ฮ่องสอน", "ลำปาง", "ลำพูน", "อุุตรดิตถ์",
					"กาฬสินธุ์", "ขอนแก่น", "ชัยภูมิ", "นครพนม", "นครราชสีมา", "บึงกาฬ", "บุรีรัมย์", "มหาสารคาม",
					"มุกดาหาร", "ยโสธร", "ร้อยเอ็ด", "เลย", "สกลนคร", "สุรินทร์", "ศรีสะเกษ", "หนองคาย", "หนองบัวลำภู",
					"อุดรธานี", "อุบลราชธานี", "อำนาจเจริญ", "กำแพงเพชร", "ชัยนาท", "นครนายก", "นครปฐม", "นครสวรรค์",
					"นนทบุรี", "ปทุมธานี", "พระนครศรีอยุธยา", "พิจิตร", "พิษณุโลก", "เพชรบูรณ์", "ลพบุรี",
					"สมุทรปราการ", "สมุทรสงคราม", "สมุทรสาคร", "สิงห์บุรี", "สุโขทัย", "สุพรรณบุรี", "สระบุรี",
					"อ่างทอง", "อุทัยธานี", "จันทบุรี", "ฉะเชิงเทรา", "ชลบุรี", "ตราด", "ปราจีนบุรี", "ระยอง",
					"สระแก้ว", "กาญจนบุรี", "ตาก", "ประจวบคีรีขันธ์", "เพชรบุรี", "ราชบุรี", "กระบี่", "ชุมพร", "ตรัง",
					"นครศรีธรรมราช", "นราธิวาส", "ปัตตานี", "พังงา", "พัทลุง", "ภูเก็ต", "ระนอง", "สตูล", "สงขลา",
					"สุราษฏร์ธานี", "ยะลา", "กรุงเทพมหานครฯ").forEach(newprovince -> {
						Province province = new Province();
						province.setProvince(newprovince);
						provinceRepository.save(province);
					});

			Object[][] user = new Object[][] { { 1, 1, "มงคลกิต พิชิตใจ", "cn001", "123", 1, 16, "อำเภอบ้านกรวด" },
					{ 1, 1, "ประหยัด จันทร์อังคาร", "ct001", "123", 2, 16, "อำเภอกระบุรี" },
					{ 1, 2, "ประวัติ เกือบดี", "ct002", "123", 2, 18, "อำเภอดินดงเเดง" },
					{ 1, 1, "บ็อบ บาบาดุก", "tt001", "123", 3, 51, "อำเภอบ้านบิน", },
					{ 2, 2, "ทองกีมา บาหลี", "tt002", "123", 3, 45, "อำเภอบ้านอีสานนอก", },
					{ 2, 2, "กอบ จำกี", "mg001", "123", 4, 16, "อำเภอบ้านกรวด" } };

			for (int i = 0; i < user.length; i++) {

				Employee employee = new Employee();

				Nametitle titleusing = nametitleRepository.findById((int) user[i][0]);
				employee.setNametitle(titleusing);

				Gender genderusing = genderRepository.findById((int) user[i][1]);
				employee.setGender(genderusing);

				employee.setFullname((String) user[i][2]);

				employee.setUsername((String) user[i][3]);

				employee.setPassword((String) user[i][4]);

				Position positionusing = positionRepository.findById((int) user[i][5]);
				employee.setPosition(positionusing);

				Province provinceusing = provinceRepository.findById((int) user[i][6]);
				employee.setProvince(provinceusing);

				employee.setAddress((String) user[i][7]);

				employee.setRecorddate(new Date());

				employeeRepository.save(employee);
			}

			// Student syatem
			Object[][] dataStudent = new Object[][] {
					{ 1, 1, "อรธิวา เจริญศักดิ์", "stu001", "123",
							"279 หอหัก KP Place หมู่10 ต.ลำเลียง อ.เวียงอิง  85110", 19, "0902654562",
							"newler53@gmail.com", 23, 1 },
					{ 1, 1, "วันชนะชัย เทียมภาค", "stu002", "123", "9 หอหัก QQplace หมู่3 ต.เวียงสาน อ.วานนา 30000", 19,
							"0902654562", "newsada53@gmail.com", 55, 1 },
					{ 1, 1, "วรวิทย์ แก้วกองกาด", "stu003", "123", "13/2 หอหัก KD link หมู่9 ต.มหาหิง อ.กว 95810", 19,
							"0902654562", "g23ewwq@gmail.com", 14, 1 } };
			for (int i = 0; i < dataStudent.length; i++) {
				Student student = new Student();

				Nametitle nametitle = nametitleRepository.findById((int) dataStudent[i][0]);
				student.setNametitle(nametitle);

				Gender gender = genderRepository.findById((int) dataStudent[i][1]);
				student.setGender(gender);

				student.setFullname((String) dataStudent[i][2]);

				student.setUsername((String) dataStudent[i][3]);

				student.setPassword((String) dataStudent[i][4]);

				student.setAddress((String) dataStudent[i][5]);

				student.setOld((int) dataStudent[i][6]);

				student.setTel((String) dataStudent[i][7]);

				student.setEmail((String) dataStudent[i][8]);

				Province province = provinceRepository.findById((int) dataStudent[i][9]);
				student.setProvince(province);

				Employee emp = employeeRepository.findById((int) dataStudent[i][10]);
				student.setEmployee(emp);

				studentRepository.save(student);
			}

			// Course system
			Object[] dataRoom = new Object[] { "R001", "R002", "R003", "R004" };
			for (int i = 0; i < dataRoom.length; i++) {
				Room room = new Room();
				room.setRoom((String) dataRoom[i]);
				roomRepository.save(room);
			}

			Object[] dataSubjects = new Object[] { "ภาษาไทย", "สังคม", "สุขศึกษา", "ฟิสิกส์", "เคมี", "ชีววิทยา",
					"คณิตศาสตร์", "ภาษาอังกฤษ", };
			for (int i = 0; i < dataSubjects.length; i++) {
				Subjects subjects = new Subjects();
				subjects.setSubjectsName((String) dataSubjects[i]);
				subjectsRepository.save(subjects);
			}

			Object[][] dataTime = new Object[][] { { "จันทร์", "16:00", "18:00" }, { "จันทร์", "18:00", "20:00" },
					{ "จันทร์", "20:00", "22:00" }, { "อังคาร", "16:00", "18:00" }, { "อังคาร", "18:00", "20:00" },
					{ "อังคาร", "20:00", "22:00" }, { "พุธ", "16:00", "18:00" }, { "พุธ", "18:00", "20:00" },
					{ "พุธ", "20:00", "22:00" }, { "พฤหัสบดี", "16:00", "18:00" }, { "พฤหัสบดี", "18:00", "20:00" },
					{ "พฤหัสบดี", "20:00", "22:00" }, { "คุกร์", "16:00", "18:00" }, { "คุกร์", "18:00", "20:00" },
					{ "คุกร์", "20:00", "22:00" }, { "เสาร์", "16:00", "18:00" }, { "เสาร์", "18:00", "20:00" },
					{ "เสาร์", "20:00", "22:00" }, { "อาทิตย์", "16:00", "18:00" }, { "อาทิตย์", "18:00", "20:00" },
					{ "อาทิตย์", "20:00", "22:00" }

			};
			for (int i = 0; i < dataTime.length; i++) {
				Time time = new Time();
				time.setDay((String) dataTime[i][0]);

				LocalTime start_time = LocalTime.parse((String) dataTime[i][1]);
				LocalTime end_time = LocalTime.parse((String) dataTime[i][2]);

				time.setStart_time(start_time);
				time.setEnd_time(end_time);

				timeRepository.save(time);
			}

			Object[][] dataCourse = new Object[][] { { "Course A", 450.00, 6, 1, 1, 4 },
					{ "Course B", 500.00, 1, 1, 5, 4 }, { "Course C", 700.00, 2, 2, 3, 5 },
					{ "Course D", 900.00, 3, 2, 4, 5 } };
			for (int i = 0; i < dataCourse.length; i++) {
				Course course = new Course();

				course.setCourseName((String) dataCourse[i][0]);
				course.setPrice((Double) dataCourse[i][1]);

				Subjects subjects = subjectsRepository.findById((int) dataCourse[i][2]);
				course.setSubjects(subjects);

				Room room = roomRepository.findById((int) dataCourse[i][3]);
				course.setRoom(room);

				Time time = timeRepository.findById((int) dataCourse[i][4]);
				course.setTime(time);

				Employee employee = employeeRepository.findById((int) dataCourse[i][5]);
				course.setEmployee(employee);

				courseRepository.save(course);
			}

			// EnrollCourse System
			for (int i = 0; i < 80; i++) {
				int roomId = 0;
				if (i < 20) {
					roomId = 1;
				} else if (i < 40) {
					roomId = 2;
				} else if (i < 60) {
					roomId = 3;
				} else if (i < 80) {
					roomId = 4;
				}

				Computer com = new Computer();
				String num = "";
				if ((i + 1) > 9) {
					num = "PC" + String.valueOf(i + 1);
				} else {
					num = "PC0" + String.valueOf(i + 1);
				}
				com.setPcNumber(num);
				Room room = roomRepository.findById(roomId);
				com.setRoom(room);
				computerRepository.save(com);
			}

			Object[][] dataEnrollCourse = new Object[][] { { 1, 1, 1, "2019-08-12 10:12:56", 3 },
					{ 1, 2, 15, "2019-08-13 11:30:20", 4 }, { 2, 3, 22, "2019-08-14 10:12:56", 3 },
					{ 2, 4, 39, "2019-09-02 11:30:20", 4 }, { 3, 4, 40, "2019-09-12 10:12:56", 4 },
				 };

			for (int i = 0; i < dataEnrollCourse.length; i++) {
				EnrollCourse enrollCourse = new EnrollCourse();

				Student student = studentRepository.findById((int) dataEnrollCourse[i][0]);
				enrollCourse.setStudent(student);

				Course course = courseRepository.findById((int) dataEnrollCourse[i][1]);
				enrollCourse.setCourse(course);

				Computer computer = computerRepository.findById((int) dataEnrollCourse[i][2]);
				enrollCourse.setComputer(computer);

				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dataDate = LocalDateTime.parse((String) dataEnrollCourse[i][3], dateFormat);
				enrollCourse.setDate(dataDate);

				enrollCourse.setNote("-");

				Employee employee = employeeRepository.findById((int) dataEnrollCourse[i][4]);
				enrollCourse.setEmployee(employee);
				enrollCourseRepository.save(enrollCourse);

			}

			// RecordExpense System
			String[] dataExpenseType = new String[] { "รับเงินจากนักเรียน", "จ่ายเงินให้พนักงาน" };
			for (int i = 0; i < dataExpenseType.length; i++) {
				ExpenseType expenseType = new ExpenseType();
				expenseType.setType(dataExpenseType[i]);
				expenseTypeRepository.save(expenseType);
			}

			// ReviewCoursRepositorySystem
			// Rating Entity
			Object[] dataRating = new Object[] { "มากที่สุด", "มาก", "ปานกลาง", "น้อย", "น้อยที่สุด" };
			for (int i = 0; i < dataRating.length; i++) {
				Rating rating = new Rating();
				rating.setRatingType((String) dataRating[i]);
				ratingRepository.save(rating);
			}

			// Improvement Entity
			Object[] dataImprovement = new Object[] { "วิธีการสอน", "หลักสูตรการสอน", "ครูผู้สอน",
					"สื่อที่ใช้ในกาเรียนการสอน" };
			for (int i = 0; i < dataImprovement.length; i++) {
				Improvement improvement = new Improvement();
				improvement.setImprovementType((String) dataImprovement[i]);
				improvementRepository.save(improvement);
			}

			// ReviewCourse Entiry
			Object[][] dataReviewCourse = new Object[][] { { 1, 1, 1, "คนสอนหล่อมาก", "2018-02-01 06:07:59" },
					{ 2, 2, 2, "สอนอิหยังหนิ", "2018-02-01 06:07:59" }, { 3, 3, 3, "เดอะเบส", "2018-02-01 06:07:59" } };
			for (int i = 0; i < dataReviewCourse.length; i++) {
				ReviewCourse reviewcourse = new ReviewCourse();

				EnrollCourse enrollCourse = enrollCourseRepository.findById((int) dataReviewCourse[i][0]);
				reviewcourse.setEnrollCourse(enrollCourse);

				Rating rating = ratingRepository.findById((int) dataReviewCourse[i][1]);
				reviewcourse.setRating(rating);

				Improvement improvement = improvementRepository.findById((int) dataReviewCourse[i][2]);
				reviewcourse.setImprovement(improvement);

				reviewcourse.setComment((String) dataReviewCourse[i][3]);

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date dateRec = new Date();
				try {
					dateRec = formatter.parse((String) dataReviewCourse[i][4]);

				} catch (Exception e) {
				}

				reviewcourse.setReviewDate(dateRec);

				reviewCourseRepository.save(reviewcourse);
			}

		};
	}

	@Bean
	public FilterRegistrationBean simpleCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// *** URL below needs to match the Vue client URL and port ***
		config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
