<template>
  <div>
    <v-hover v-slot:default="{ hover }">
      <v-card width="600" :elevation="hover ? 12 : 5">
        <v-app-bar dark color="#1A76D2">
          <v-btn icon>
            <v-icon large>mdi-label</v-icon>
          </v-btn>

          <v-toolbar-title>ลงทะเบียนเรียน</v-toolbar-title>

          <v-spacer></v-spacer>
        </v-app-bar>

        <v-container style="margin-top: 50px; padding-bottom: 30px;">
          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select
                id="enroll001"
                v-model="enroll.students_id"
                :items="students"
                item-text="fullname"
                item-value="id"
                label="เลือกชื่อนักเรียน"
                @change="setCourse"
                outlined
              ></v-select>
              <span
                v-if="errorStudents"
                style="margin-left: 30px; color: red;"
              >คุณยังไม่ได้เลือก combobox นักเรียน</span>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select
              id="enroll05672"
                v-model="enroll.course_id"
                :items="courses"
                item-text="courseName"
                item-value="id"
                label="ชื่อคอร์ส"
                @change="getCourseDetail"
                outlined
              ></v-select>
              <span
                v-if="errorCourse"
                style="margin-left: 30px; color: red;"
              >คุณยังไม่ได้เลือก combobox คอร์ส</span>

              <div v-if="showDetail" style="margin-left: 30px;">
                <h2>#รายละเอียด คอร์ส</h2>
                <h4>ติวเตอร์: {{courseDetail.tutur}}</h4>
                <h4>ห้องเรียน: {{courseDetail.room}}</h4>
                <h4>วิชา: {{courseDetail.subjects}}</h4>
                <h4>เวลาเรียน: {{courseDetail.studyTime}}</h4>
                <h4>ราคาคอร์ส: {{courseDetail.price}}</h4>
              </div>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select
                id="enroll004543"
                v-model="enroll.computer_id"
                :items="computers"
                item-text="pcNumber"
                item-value="id"
                label="เลขที่นั่งคอมพิวเตอร์"
                @change="errorComputer = false"
                outlined
              ></v-select>
              <span
                v-if="errorComputer"
                style="margin-left: 30px; color: red;"
              >คุณยังไม่ได้เลือก combobox ที่นั่งคอมพิวเตอร์</span>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-text-field v-model="enroll.dateTime" label="วันเวลาปัจจุปัน" outlined></v-text-field>
              <!-- disabled -->
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-textarea
                id="note001"
                v-model="enroll.note"
                label="อื่นๆ"
                outlined
                dense
              ></v-textarea>
              <span
                v-if="errorNote"
                style="margin-left: 30px; color: red;"
              >คุณไม่สามารพิมพ์ข้อความเกิน 50 ตัวอักษรได้</span>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select
              id="enroll051"
                disabled
                v-model="enroll.employee_id"
                :items="employees"
                item-text="fullname"
                item-value="id"
                label="พนักงานที่บันทึก"
                @change="errorEmployee = false"
                outlined
              ></v-select>
              <span
                v-if="errorEmployee"
                style="margin-left: 30px; color: red;"
              >คุณยังไม่ได้เลือก combobox พนักงานที่บันทึก</span>
            </v-col>
          </v-row>

          <v-row>
            <v-btn id="enroll304" style="margin: auto;" large color="#1A76D2" dark @click="chackList">ลงทะเบียนเรียน</v-btn>
          </v-row>
        </v-container>
      </v-card>
    </v-hover>

    <!-- popup Success -->

    <template>
      <v-row justify="center">
        <v-dialog v-model="popup.Success" max-width="500px">
          <v-card style="background-color: #F2F3F4">
            <v-card-title>
              <span class="display-1 font-weight-light">Successful process</span>

              <v-spacer></v-spacer>

              <v-btn icon>
                <v-icon size="24px" @click="popup.Success = false">fas fa-times</v-icon>
              </v-btn>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <Label id="enroll6701" >{{popup.TextSuccess}}</Label>
                </v-row>
                <v-row>
                  <v-spacer></v-spacer>
                  <v-btn
                    id="enroll1201"
                    @click="popup.Success = false"
                    class="font-weight-light"
                    color="primary"
                    width="100"
                    height="20"
                  >close</v-btn>
                </v-row>
              </v-container>
            </v-card-text>
          </v-card>
        </v-dialog>
      </v-row>
    </template>

    <!-- popup Error -->

    <template>
      <v-row justify="center">
        <v-dialog v-model="popup.Error" max-width="500px">
          <v-card style="background-color: #F2F3F4">
            <v-card-title>
              <span class="display-1 font-weight-light">Error process</span>

              <v-spacer></v-spacer>

              <v-btn icon>
                <v-icon size="24px" @click="popup.Error = false">fas fa-times</v-icon>
              </v-btn>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <Label id="enroll78501">{{popup.TextError}}</Label>
                </v-row>
                <v-row>
                  <v-spacer></v-spacer>
                  <v-btn
                  id="enroll0231"
                    @click="popup.Error = false"
                    class="font-weight-light"
                    color="error"
                    width="100"
                    height="20"
                  >close</v-btn>
                </v-row>
              </v-container>
            </v-card-text>
          </v-card>
        </v-dialog>
      </v-row>
    </template>


  </div>
</template>
<script>
import http from "../../http-common";
export default {
  name: "AddEnrollCourse",
  data: () => ({
    popup: {
      TextSuccess: "TextSuccess",
      Success: false,
      TextError: "TextError",
      Error: false
    },
    showDetail: false,
    errorStudents: false,
    errorEmployee: false,
    errorCourse: false,
    errorComputer: false,
    errorNote: false,
    students: [],
    employees: [],
    courses: [],
    computers: [],
    courseDetail: {
      tutur: "",
      subjects: "",
      room: "",
      studyTime: "",
      price: ""
    },
    enrollCourse: [],
    enroll: {
      students_id: "",
      employee_id: "",
      course_id: "",
      computer_id: "",
      dateTime: "",
      note: ""
    }
  }),
  methods: {
    /* eslint-disable no-console */
    reloadPage() {
      window.location.reload(false);
    },
    chackList() {
      this.errorStudents = this.enroll.students_id == "" ? true : false;
      this.errorEmployee = this.enroll.employee_id == "" ? true : false;
      this.errorCourse = this.enroll.course_id == "" ? true : false;
      this.errorComputer = this.enroll.computer_id == "" ? true : false;
      this.errorNote = this.enroll.note.length > 50 ? true : false;
      if (!this.errorStudents && !this.errorEmployee && !this.errorCourse && !this.errorComputer && !this.errorNote) {
        this.saveEnrollCourse();
      } else {
        this.popup.Error = true;
        this.popup.TextError = "Please select all information.";
      }
    },
    getComputer() {
      http
        .get("/computer/")
        .then(response => {
          this.computers = response.data;
          // find roomId
          var roomId = 1;
          for (let elem in this.courses) {
            if (this.courses[elem].id == this.enroll.course_id) {
              roomId = this.courses[elem].room.id;
            }
          }
          // console.log(roomId);

          // set comForRoom
          var comForRoom = [];
          var i = 0;
          for (let elem in this.computers) {
            if (this.computers[elem].room.id == roomId) {
              comForRoom[i] = this.computers[elem];
              i = i + 1;
            }
          }

          this.computers = comForRoom;
          // console.log(this.computers);

          // set computer enrollCourse = null

          for (let elem in this.enrollCourse) {

            if (this.enrollCourse[elem].course.id == this.enroll.course_id) {
              
              for (let index in this.computers) {
                if (this.computers[index].pcNumber == this.enrollCourse[elem].computer.pcNumber) {
                  this.computers[index].pcNumber = "null";
                }
              }
            }
          }

          // Delete com computer enrollCourse = null
          comForRoom = [];
          i = 0;
          for (let index in this.computers) {
            if (this.computers[index].pcNumber != "null") {
              comForRoom[i] = this.computers[index];
              i = i + 1;
            }
          }

          this.computers = comForRoom;
          // console.log(this.computers);


        })
        .catch(e => {
          console.log(e);
        });
    },
    getEnrollCourse() {
      http
        .get("/enrollCourse/")
        .then(response => {
          this.enrollCourse = response.data;
          console.log(this.enrollCourse);
        })
        .catch(e => {
          console.log(e);
        });
    },
    setCourse() {
      this.showDetail = false;
      this.enroll.course_id = "";
      this.enroll.computer_id = "";
      this.enroll.dateTime = "";
      this.enroll.note = "";

      this.errorStudents = false;
      http
        .get("/course/")
        .then(response => {
          this.courses = response.data;
          var coursesId = [];
          var i = 0;
          for (let elem in this.enrollCourse) {
            if (this.enrollCourse[elem].student.id == this.enroll.students_id) {
              coursesId[i] = this.enrollCourse[elem].course.id - 1;
              i = i + 1;
            }
          }

          for (let index in coursesId) {
            this.courses[coursesId[index]].courseName = "null";
          }
          var courses = [];
          var j = 0;
          for (let elem in this.courses) {
            if (this.courses[elem].courseName != "null") {
              courses[j] = this.courses[elem];
              j = j + 1;
            }
          }
          this.courses = courses;
          console.log(this.courses);
        })
        .catch(e => {
          console.log(e);
        });
    },
    saveEnrollCourse() {
      http
        .post(
          "/enrollCourse/" +
            this.enroll.students_id +
            "/" +
            this.enroll.course_id +
            "/" +
            this.enroll.computer_id +
            "/" +
            this.enroll.dateTime +
            "/" +
            (this.enroll.note == "" ? "-" : this.enroll.note) +
            "/" +
            this.enroll.employee_id,
          this.enroll
        )
        .then(response => {
          console.log(response.data);
          // alert("บันทึกข้อมูล สำเร็จ!!");
          this.getResetData();

          this.popup.Success = true;
          this.popup.TextSuccess = "OK !,Data has been saved successfully.";
        })
        .catch(e => {
          console.log(e);
          // alert("บันทึกข้อมูล ไม่สำเร็จ!!");

          this.popup.Error = true;
          this.popup.TextError = "The data recording failed due to" + e + ".";
        });
    },
    getNowTime() {
      if(this.enroll.note.length < 51){
        this.errorNote = false;
      }

      var xTime = new Date();
      var FullYear = xTime.getFullYear();
      var Month =
        xTime.getMonth() + 1 > 9
          ? xTime.getMonth() + 1
          : "0" + (xTime.getMonth() + 1);
      var Day = xTime.getDate() > 9 ? xTime.getDate() : "0" + xTime.getDate();
      var Hours =
        xTime.getHours() > 9 ? xTime.getHours() : "0" + xTime.getHours();
      var Minutes =
        xTime.getMinutes() > 9 ? xTime.getMinutes() : "0" + xTime.getMinutes();
      var Seconds =
        xTime.getSeconds() > 9 ? xTime.getSeconds() : "0" + xTime.getSeconds();
      this.enroll.dateTime =
        FullYear +
        "-" +
        Month +
        "-" +
        Day +
        " " +
        Hours +
        ":" +
        Minutes +
        ":" +
        Seconds;
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
    getStudents() {
      http
        .get("/student/")
        .then(response => {
          this.students = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getResetData() {
      this.showDetail = false;
      this.enroll.students_id = "";
      this.enroll.course_id = "";
      this.enroll.computer_id = "";
      this.enroll.dateTime = "";
      this.enroll.note = "";
      this.getEmployees();
      this.getStudents();
      this.getEnrollCourse();
    },
    getCourseDetail() {
      this.errorCourse = false;
      for (let elem in this.courses) {
        if (this.courses[elem].id == this.enroll.course_id) {
          this.courseDetail.tutur = this.courses[elem].employee.fullname;
          this.courseDetail.subjects = this.courses[elem].subjects.subjectsName;
          this.courseDetail.room = this.courses[elem].room.room;
          this.courseDetail.studyTime =
            this.courses[elem].time.day +
            " " +
            this.courses[elem].time.start_time +
            " - " +
            this.courses[elem].time.end_time +
            " น.";
          this.courseDetail.price = this.courses[elem].price + " บาท";
        }
      }
      this.showDetail = true;

      this.getComputer();
    },
    setCreatedBy() {
      this.enroll.employee_id = this.$session.get("userId");
    }
  },

  created() {
    setInterval(() => this.getNowTime());
  },
  mounted() {
    this.setCreatedBy();
    this.getEmployees();
    this.getStudents();
    this.getEnrollCourse();
  }
};
</script>