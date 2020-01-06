<template>
  <v-container fluid>
    <v-hover v-slot:default="{ hover }">
      <v-card width="600" :elevation="hover ? 12 : 5" style="margin: auto; margin-top: 50px;">
        <v-app-bar dark color="light-blue lighten-1">
          <v-btn icon>
            <v-icon large>mdi-label</v-icon>
          </v-btn>

          <v-toolbar-title>Enroll Course</v-toolbar-title>

          <v-spacer></v-spacer>

          <v-btn icon>
            <v-icon>mdi-dialpad</v-icon>
          </v-btn>
        </v-app-bar>

        <v-container style="margin-top: 50px; padding-bottom: 30px;">

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select v-model="enroll.students_id" :items="students" item-text="fullname" item-value="id" label="students name" @change="setCourse" outlined></v-select>
              <span v-if="errorStudents" style="margin-left: 30px; color: red;">You have not selected combobox students.</span>
              
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
                <v-select v-model="enroll.course_id" :items="courses" item-text="courseName" item-value="id" label="course name" @change="getSetCourseDetail" outlined></v-select>
                <span v-if="errorCourse" style="margin-left: 30px; color: red;">You have not selected combobox course.</span>

                <div v-if="showDetail" style="margin-left: 30px;">
                  <h2>#Course Detail</h2>
                  <h4>Tutur: {{courseDetail.tutur}}</h4>
                  <h4>Room: {{courseDetail.room}}</h4>
                  <h4>Subjects: {{courseDetail.subjects}}</h4>
                  <h4>StudyTime: {{courseDetail.studyTime}}</h4>
                  <h4>Price: {{courseDetail.price}}</h4>
                </div>
            </v-col>

          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-text-field v-model="enroll.dateTime"  label="date time" outlined></v-text-field>
              <!-- disabled -->
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select v-model="enroll.employee_id" :items="employees" item-text="fullname" item-value="id" label="employees name" @change="errorEmployee = false" outlined></v-select>
              <span v-if="errorEmployee" style="margin-left: 30px; color: red;">You have not selected combobox employee.</span>
            </v-col>
          </v-row>
       
          <v-row>
            <v-btn
              style="margin: auto;"
              large
              color="light-blue lighten-1"
              dark
              @click="chackList"
            > Enroll </v-btn>
          </v-row>
        </v-container>
      </v-card>
    </v-hover>

    <v-row>
      <v-snackbar v-model="snackbar">
        {{ text }}
        <v-btn color="pink" text @click="snackbar = false">Close</v-btn>
        <v-btn color="blue" text @click="reloadPage">Ok</v-btn>
      </v-snackbar>
    </v-row>
  </v-container>
</template>
<script>
import http from "../../http-common";
export default {
  name: "EnrollCourse",
  data: () => ({
    snackbar: false,
    showDetail: false,
    errorStudents: false,
    errorEmployee: false,
    errorCourse: false,
    counterStaff:[],
    text: "",
    students:[],
    employees:[],
    courses:[],
    courseDetail:{
      tutur:"",
      subjects:"",
      room:"",
      studyTime:"",
      price:""
    },
    enrollCourse:[],
    enroll:{
      students_id:"",
      employee_id:"",
      course_id:"",
      dateTime:"",
    }
  }),
  methods: {
    /* eslint-disable no-console */
    reloadPage(){
      window.location.reload(false);
    },
    chackList(){
      this.errorStudents = this.enroll.students_id == "" ? true : false;
      this.errorEmployee = this.enroll.employee_id == "" ? true : false;
      this.errorCourse = this.enroll.course_id == "" ? true : false;
      if(!this.errorStudents && !this.errorEmployee && !this.errorCourse){
        this.saveEnrollCourse();
      }else{
        this.snackbar = true;
        this.text = "Please select all information.";
      }
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
      this.errorStudents = false;
      http
        .get("/course/")
        .then(response => {
          this.courses = response.data;
          var coursesId = [];
          var i = 0;
          for (let elem in this.enrollCourse) {
            
              if(this.enrollCourse[elem].student.id == this.enroll.students_id){
                coursesId[i] = this.enrollCourse[elem].course.id - 1;
                i=i+1;
              }
          }
      
          for (let index in coursesId) {
              this.courses[coursesId[index]].courseName = "null";     
          }
          var courses = [];
          var j = 0;
          for (let elem in this.courses) {
              if(this.courses[elem].courseName != "null"){
                courses[j] = this.courses[elem];
                j=j+1;
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
            this.enroll.dateTime +
            "/" +
            this.enroll.employee_id,
          this.enroll
        )
        .then(response => {
          console.log(response.data);
          // alert("บันทึกข้อมูล สำเร็จ!!");
          this.getResetData();
          this.snackbar = true;
          this.text = "OK !,Data has been saved successfully.";
        })
        .catch(e => {
          console.log(e);
          // alert("บันทึกข้อมูล ไม่สำเร็จ!!");
          this.snackbar = true;
          this.text = "The data recording failed due to" + e +".";
        });
    },
    getNowTime(){
      // const answer = x > 10 ? "greater than 10" : "less than 10";
      var xTime = new Date();
      var FullYear = xTime.getFullYear();
      var Month = (xTime.getMonth()+1) > 9 ? (xTime.getMonth()+1):("0"+(xTime.getMonth()+1));
      var Day = xTime.getDate() > 9 ? xTime.getDate():("0"+xTime.getDate());
      var Hours = xTime.getHours() > 9 ? xTime.getHours():("0"+xTime.getHours());
      var Minutes = xTime.getMinutes() > 9 ? xTime.getMinutes():("0"+xTime.getMinutes());
      var Seconds = xTime.getSeconds() > 9 ? xTime.getSeconds():("0"+xTime.getSeconds());
      this.enroll.dateTime =  FullYear +"-"+Month +"-"+Day+" "+Hours+":"+Minutes+":"+Seconds;
    },
    getEmployees() {
      http
        .get("/employee")
        .then(response => {
          this.employees = response.data;
          this.setCounterStaff();
          
        })
        .catch(e => {
          console.log(e);
        });
    },
    setCounterStaff(){
      let counterStaff = [];
      let i = 0;
      for (let elem in this.employees) {
          if(this.employees[elem].position.id == 2){
            counterStaff[i] = this.employees[elem];
            i=i+1;
          }
      }
      this.employees = counterStaff;
      console.log(this.employees);
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
      this.enroll.dateTime = "";
      this.enroll.employee_id = "";
      this.getEmployees();
      this.getStudents();
      this.getEnrollCourse();
    },
    getSetCourseDetail() {
      this.errorCourse = false;
      for (let elem in this.courses) {
          if(this.courses[elem].id == this.enroll.course_id){
            this.courseDetail.tutur = this.courses[elem].employee.fullname;
            this.courseDetail.subjects = this.courses[elem].subjects.name;
            this.courseDetail.room = this.courses[elem].room.name;
            this.courseDetail.studyTime = 
                                  this.courses[elem].time.day + " " +
                                  this.courses[elem].time.start_time+" - "+
                                  this.courses[elem].time.end_time + " น.";
            this.courseDetail.price = this.courses[elem].price + " บาท";
           
          }
      }
      this.showDetail = true;
      
    },
  },
  created () {
    setInterval(() => this.getNowTime())
  },
  mounted() {
      this.getEmployees();
      this.getStudents();
      this.getEnrollCourse();
      
  }
};
</script>