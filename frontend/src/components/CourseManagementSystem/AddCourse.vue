<template>
  <v-container fluid>
    <v-hover v-slot:default="{ hover }">
      <v-card width="600" :elevation="hover ? 12 : 5">
        <v-app-bar dark color="#1A76D2">
          <v-btn icon>
            <v-icon large>mdi-label</v-icon>
          </v-btn>

          <v-toolbar-title>เพิ่มคอร์สเรียน</v-toolbar-title>

          <v-spacer></v-spacer>

         
        </v-app-bar>

        <v-container style="margin-top: 50px; padding-bottom: 30px;">
          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-text-field v-model="course.course_name" label="กรอกชื่อคอร์ส" outlined></v-text-field>
            </v-col>
          </v-row>

          

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select
                v-model="course.subjects_id"
                :items="subjects"
                item-text="subjectsName"
                item-value="id"
                label="เลือกวิชา"
                outlined
              ></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select
                v-model="course.room_id"
                :items="rooms"
                item-text="room"
                item-value="id"
                label="เลือกห้องเรียน"
                outlined
              ></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select
                v-model="course.dateTime"
                :items="timesTmp"
                label="เลือกเวลา"
                outlined
                @change="getTimesTmp"
              ></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-text-field v-model="course.price" label="กรอกราคา" outlined></v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select
                disabled
                v-model="course.employee_id"
                :items="employees"
                item-text="fullname"
                item-value="id"
                label="พนักงานที่บันทึก"
                outlined
              ></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-btn
              style="margin: auto;"
              large
              color="#1A76D2"
              dark
              @click="saveCourse"
            >บันทึกข้อมูล</v-btn>
          </v-row>
        </v-container>
      </v-card>
    </v-hover>
  </v-container>
</template>
<script>
import http from "../../http-common";
export default {
  name: "AddCourse",
  data: () => ({
    dateTime: "",
    course: {
      subjects_id: "",
      room_id: "",
      time_id: "",
      employee_id: "",
      price: "",
      course_name: ""
    },
    subjects: [],
    employees: [],
    rooms: [],
    times: [],
    timesTmp: [],
    courses: [],
    checkroomtime: ""
  }),
  methods: {
    /* eslint-disable no-console */
    saveCourse() {
      this.getCourses();
      for (let elem in this.courses) {
        if (
          this.course.room_id == this.courses[elem].room.id &&
          this.course.time_id == this.courses[elem].time.id
        ) {
          alert("มีคอร์สที่ใช้ห้องเรียนและเวลาเรียนนี้แล้วกรุณาเลือกใหม่");
          this.checkroomtime = 1;
          break;
        }
      }
      if (this.checkroomtime != 1) {
        this.saveCourses();
        this.getCourses();
      }
      this.checkroomtime = 0;
    },

    saveCourses() {
      http
        .post(
          "/course/" +
            this.course.subjects_id +
            "/" +
            this.course.room_id +
            "/" +
            this.course.time_id +
            "/" +
            this.course.employee_id +
            "/" +
            this.course.price +
            "/" +
            this.course.course_name,
          this.course
        )
        .then(response => {
          console.log(response.data);
          alert("บันทึกสำเร็จ");
        })
        .catch(e => {
          console.log(e);
          alert("บันทึกไม่สำเร็จ กรุณากรอกข้อมูลให้ครบถ้วนและถูกต้อง");
        });
    },
    getEmployees() {
      http
        .get("/employee")
        .then(response => {
          this.employees = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    getSubjects() {
      http
        .get("/subjects/")
        .then(response => {
          this.subjects = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getRooms() {
      http
        .get("/room/")
        .then(response => {
          this.rooms = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getTimesTmp() {
      for (let elem in this.times) {
        if (
          this.course.dateTime ==
          this.times[elem].day +
            " " +
            this.times[elem].start_time +
            " - " +
            this.times[elem].end_time
        ) {
          this.course.time_id = this.times[elem].id;
        }
      }
    },
    getTimes() {
      http
        .get("/time/")
        .then(response => {
          this.times = response.data;
          console.log(response.data);

          let timesTmp = [];
          for (let elem in this.times) {
            timesTmp[elem] =
              this.times[elem].day +
              " " +
              this.times[elem].start_time +
              " - " +
              this.times[elem].end_time;
          }
          this.timesTmp = timesTmp;
        })
        .catch(e => {
          console.log(e);
        });
    },

    getCourses() {
      http
        .get("/course/")
        .then(response => {
          this.courses = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    setCreatedBy() {
      this.course.employee_id = this.$session.get("userId");
    }
  },
  mounted() {
    this.setCreatedBy();
    this.getEmployees();
    this.getSubjects();
    this.getRooms();
    this.getTimes();
    this.getCourses();
  }
};
</script>
