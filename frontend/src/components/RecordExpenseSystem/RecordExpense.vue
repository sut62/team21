// SELECT DISTINCT  STUDENT.STUDENT_ID  ,  STUDENT.FULLNAME  FROM STUDENT , ENROLL_COURSE WHERE STUDENT.STUDENT_ID = ENROLL_COURSE.STUDENT_ID
<template>
  <v-container fluid>
    <v-hover v-slot:default="{ hover }">
      <v-card width="600" :elevation="hover ? 12 : 5" style="margin: auto; margin-top: 50px;">
        <v-app-bar dark color="light-blue lighten-1">
          <v-btn icon>
            <v-icon large>mdi-label</v-icon>
          </v-btn>

          <v-toolbar-title>RecordExpense</v-toolbar-title>

          <v-spacer></v-spacer>

          <v-btn icon>
            <v-icon>mdi-dialpad</v-icon>
          </v-btn>
        </v-app-bar>

        <v-container style="margin-top: 50px; padding-bottom: 30px;">
          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select
                label="Expense Type"
                v-model="RecordExpense.expenseType"
                :items="ExpenseType"
                item-text="type"
                item-value="id"
                outlined
                @change="setSelete"
              ></v-select>
            </v-col>
          </v-row>

          <v-row v-if="selete.emp">
            <v-col cols="8" style="margin: auto;">
              <v-select
                label="Employee"
                v-model="RecordExpense.rec"
                :items="employee"
                item-text="fullname"
                item-value="id"
                outlined
                @change="setBudgetEmployee"
              ></v-select>
            </v-col>
          </v-row>

          <v-row v-if="selete.stu">
            <v-col cols="8" style="margin: auto;">
              <v-select
                label="Student"
                v-model="RecordExpense.enrollCourse_id"
                :items="enrollCourse"
                item-text="student.fullname"
                item-value="id"
                outlined
                @change="setBudgetStudent"
              ></v-select>

            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-text-field disabled v-model="RecordExpense.budget" label="Budget" outlined dense></v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-text-field disabled v-model="RecordExpense.date" label="Date" outlined></v-text-field>
              <!-- disabled -->
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select
                v-model="RecordExpense.createdBy"
                :items="employee"
                item-text="fullname"
                item-value="id"
                label="Created by"
                outlined
              ></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-btn
              style="margin: auto;"
              large
              color="light-blue lighten-1"
              dark
              @click="checkList"
            >SAVE FROM</v-btn>
          </v-row>
        </v-container>
      </v-card>
    </v-hover>

    <v-row>
      <v-snackbar v-model="snackbar">
        {{ text }}
        <v-btn color="pink" text @click="reloadPage">Close</v-btn>
      </v-snackbar>
    </v-row>
  </v-container>
</template>
<script>
import http from "../../http-common";
export default {
  name: "RecordExpense",
  data: () => ({
    selete: {
      stu: false,
      emp: false
    },
    RecordExpense: {
      expenseType: "",
      enrollCourse_id: "",
      budget: "",
      rec: "",
      date: "",
      createdBy: ""
    },
    snackbar: false,
    text: "OK !,Data has been saved successfully.",
    ExpenseType: [],
    employee: [],
    enrollCourse: [],
    student: [],
  }),
  methods: {
    /* eslint-disable no-console */
    checkList(){
      // type student
          if((this.RecordExpense.expenseType == 1 && this.RecordExpense.enrollCourse_id != "" && this.RecordExpense.createdBy != "") ||
          (this.RecordExpense.expenseType == 2 && this.RecordExpense.rec != "" && this.RecordExpense.createdBy != "")){
              this.saveRecordExpense();
          
          }else{
            this.text = "กรุณาเลือกข้อมูลให้ครบ";
            this.snackbar = true;
          }
    },
    reloadPage() {
      this.snackbar = false;
      location.reload();
      window.location.reload(false);
    },
    saveRecordExpense() {
      // type student
      if (this.RecordExpense.expenseType == 1) {
        console.log('save student');
        http
        .post(
          "/recordExpenseStudent/" +
            this.RecordExpense.expenseType +
            "/" +
            this.RecordExpense.enrollCourse_id +
            "/" +
            this.RecordExpense.budget +
            "/" +
            this.RecordExpense.date +
            "/" +
            this.RecordExpense.createdBy,
            this.RecordExpense
        )
        .then(response => {
          console.log(response);
          this.text = "บันทึกข้อมูลเสร็จสิ้น";
          this.snackbar = true;
        })
        .catch(e => {
          console.log(e);
          this.text = "กรุณาป้อนข้อมูลให้ครบ";
          this.snackbar = true;
        });
      // type employee 
      } else if (this.RecordExpense.expenseType == 2) {
        console.log('save employee');
        http
        .post(
          "/recordExpenseEmployee/" +
            this.RecordExpense.expenseType +
            "/" +
            this.RecordExpense.rec +
            "/" +
            this.RecordExpense.budget +
            "/" +
            this.RecordExpense.date +
            "/" +
            this.RecordExpense.createdBy,
            this.RecordExpense
        )
        .then(response => {
          console.log(response);
          this.text = "บันทึกข้อมูลเสร็จสิ้น";
          this.snackbar = true;
          location.reload();
       
        })
        .catch(e => {
          console.log(e);
          this.text = "ไม่สามารถเชื่อมต่อฐานข้อมูลได้";
          this.snackbar = true;
         
        });
      }
    },
    setSelete() {
      
      if (this.RecordExpense.expenseType == 2) {
        this.selete.emp = true;
        this.selete.stu = false;
        this.RecordExpense.budget = "";
      } else if (this.RecordExpense.expenseType == 1) {
        this.selete.stu = true;
        this.selete.emp = false;
        this.RecordExpense.budget = "";
      }
    },
    setBudgetEmployee() {
      //emp
      for (let elem in this.employee) {
        if (this.employee[elem].id == this.RecordExpense.rec) {
          this.RecordExpense.budget = this.employee[elem].position.salary;
          
        }

        console.log("setBudgetEmployee");
      }
    },
    setBudgetStudent() {
      //emp
      for (let elem in this.enrollCourse) {
        if (this.enrollCourse[elem].id == this.RecordExpense.enrollCourse_id) {
           this.RecordExpense.budget = this.enrollCourse[elem].course.price;
          console.log(this.enrollCourse[elem].course.price);
        }

        console.log("setBudgetStudent");
      }
    },
    getNowTime() {
     
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
      this.RecordExpense.date =
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
    getExpenseType() {
      http
        .get("/expenseType/")
        .then(response => {
          this.ExpenseType = response.data;
          console.log(this.ExpenseType);
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
    getEmployees() {
      http
        .get("/employee")
        .then(response => {
          this.employee = response.data;
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
    }
  },
  created() {
    setInterval(() => this.getNowTime());
  },
  mounted() {
    this.getExpenseType();
    this.getEmployees();
    this.getEnrollCourse();
  }
};
</script>
