<template>
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
              <v-text-field id="ad001" v-model="course.course_name" label="กรอกชื่อคอร์ส" outlined></v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select
                id="ad002"
                v-model="course.teacher"
                :items="teacher"
                label="เลือกครูผู้สอน"
                outlined
                @change="getTeacher"
              ></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select
                id="ad003"
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
                id="ad004"
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
                id="ad005"
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
              <v-text-field  id="ad006" v-model="course.price" label="กรอกราคา" outlined></v-text-field>
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
                  <Label>{{popup.TextSuccess}}</Label>
                </v-row>
                <v-row>
                  <v-spacer></v-spacer>
                  <v-btn
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
                  <Label>{{popup.TextError}}</Label>
                </v-row>
                <v-row>
                  <v-spacer></v-spacer>
                  <v-btn
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



        </v-container>
      </v-card>
  </v-hover>
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
      price: "",
      course_name: "",
      teacher:"",
      employee_id:""
    },
    subjects: [],
    teacher:[],
    employees: [],
    rooms: [],
    times: [],
    timesTmp: [],
    courses: [],
    checkroomtime: "",
    popup: {
      TextSuccess: "TextSuccess",
      Success: false,
      TextError: "TextError",
      Error: false
    },
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
          this.popup.Error = true;
          this.popup.TextError = "มีคอร์สที่ใช้ห้องเรียนและเวลาเรียนนี้แล้วกรุณาเลือกใหม่";
          this.checkroomtime = 1;
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
          this.resetData();
          this.popup.Success = true;
          this.popup.TextSuccess = "บันทึกสำเร็จ";
        })
        .catch(e => {
          console.log(e);
          this.popup.Error = true;
          this.popup.TextError = "บันทึกไม่สำเร็จ กรุณากรอกข้อมูลให้ครบถ้วนและถูกต้อง";
        });
    },
    resetData() {
        
        this.getSubjects();
        this.getRooms();
        this.getTimes();
        this.getCourses();
      this.course.course_name= ""
      this.course.subjects_id= "",
      this.course.room_id= "",
      this.course.dateTime= "",
      this.course.price= "",
      this.course.teacher= ""
      
    },

    getEmployees() {
      http
        .get("/employee")
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
          
          let teacher = [];
          var z=0;
          for (let elem in this.employees) {
          if(this.employees[elem].position.id == 3 ){
            teacher[z] = this.employees[elem].fullname
            z++;
          }
          this.teacher = teacher;
          }
          
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
    getTeacher(){
        for (let elem in this.employees) {
        if (this.course.teacher == this.employees[elem].fullname){
            this.course.employee_id = this.employees[elem].id;
        }
      }
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
    
  },
  mounted() {
    this.getEmployees();
    this.getSubjects();
    this.getRooms();
    this.getTimes();
    this.getCourses();
  }
};
</script>
