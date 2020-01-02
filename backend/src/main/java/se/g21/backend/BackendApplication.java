package se.g21.backend;

import se.g21.backend.employeesystem.controllers.*;
import se.g21.backend.employeesystem.entities.*;
import se.g21.backend.employeesystem.repository.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.stream.Stream;
import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Collections;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	ApplicationRunner init(EmployeeRepository employeeRepository, PositionRepository positionRepository,
			ProvinceRepository provinceRepository, GenderRepository genderRepository,
			NametitleRepository nametitleRepository) {
		return args -> {

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
					{ "พนักงานเคาท์เตอร์", 18000.05 }, { "ผู้จัดการ", 36000.99 } };

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

			Object[][] user = new Object[][] { { 1, "พงศกร มาประโคน", 1, 3, 16, "อำเภอบ้านกรวด", "leo", "leo" }, };

			for (int i = 0; i < user.length; i++) {
				Employee employee = new Employee();
				Nametitle titleusing = nametitleRepository.findById((int) user[i][0]);
				employee.setNametitle(titleusing);
				employee.setFullname((String) user[i][1]);
				Gender genderusing = genderRepository.findById((int) user[i][2]);
				employee.setGender(genderusing);
				Position positionusing = positionRepository.findById((int) user[i][3]);
				employee.setPosition(positionusing);
				Province provinceusing = provinceRepository.findById((int) user[i][4]);
				employee.setProvince(provinceusing);
				employee.setAddress((String) user[i][5]);
				employee.setUsername((String) user[i][6]);
				employee.setPassword((String) user[i][7]);
				employee.setRecorddate(new Date());
				employeeRepository.save(employee);
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