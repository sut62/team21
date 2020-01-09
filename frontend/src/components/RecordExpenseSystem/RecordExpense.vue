<template>
  <v-container fluid>
    <v-hover v-slot:default="{ hover }">
      <v-card width="600" :elevation="hover ? 12 : 5">
        <v-app-bar dark color="blue darken-2">
          <v-btn icon>
            <v-icon large>mdi-label</v-icon>
          </v-btn>

          <v-toolbar-title>ระบบบันทึกค่าใช้จ่าย</v-toolbar-title>

          <v-spacer></v-spacer>
        </v-app-bar>

        <v-container style="margin-top: 50px; padding-bottom: 30px;">
          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select
                label="เลือกประเภทการบันทึก"
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
                label="เลือกชื่อพนักงาน"
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
                label="เลือกชื่อนักเรียน"
                v-model="RecordExpense.enrollCourse_id"
                :items="enrollCourse"
                item-text="student.fullname"
                item-value="id"
                outlined
                @change="setBudgetStudent"
              ></v-select>
              <div v-if="enrollCourseDatail.show" style="margin-left: 30px;">
                <h2>#Enroll Course Datail</h2>
                <h4>student: {{enrollCourseDatail.student}}</h4>
                <h4>course: {{enrollCourseDatail.course}}</h4>
                <h4>computer: {{enrollCourseDatail.computer}}</h4>
                <h4>employee: {{enrollCourseDatail.employee}}</h4>
                <h4>date: {{enrollCourseDatail.date}}</h4>
              </div>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-text-field disabled v-model="RecordExpense.budget" label="จำนวนเงิน" outlined dense></v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-text-field disabled v-model="RecordExpense.date" label="วันที่บันทึก" outlined></v-text-field>
              <!-- disabled -->
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select
                disabled
                v-model="RecordExpense.createdBy"
                :items="employee"
                item-text="fullname"
                item-value="id"
                label="ผู้บันทึก"
                outlined
              ></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-btn
              style="margin: auto;"
              large
              color="blue darken-2"
              dark
              @click="checkList"
            >SAVE FROM</v-btn>
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
    </template>
  </v-container>
</template>
<script>
import http from "../../http-common";
export default {
  name: "RecordExpense",
  data: () => ({
    popup: {
      TextSuccess: "TextSuccess",
      Success: false,
      TextError: "TextError",
      Error: false
    },
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
    enrollCourseDatail: {
      show: false,
      student: "",
      course: "",
      computer: "",
      employee: "",
      date: ""
    },
    snackbar: false,
    text: "OK !,Data has been saved successfully.",
    ExpenseType: [],
    employee: [],
    enrollCourse: [],
    recordExpense: [],
    student: []
  }),
  methods: {
    /* eslint-disable no-console */
    resetData() {
      this.selete.stu = false;
      this.selete.emp = false;

      // reset data get from database

      this.getEnrollCourse();
      this.setCreatedBy();
      this.getExpenseType();
      this.getEmployees();

      // reset data combobox

      this.enrollCourseDatail.show = false;
      this.RecordExpense.expenseType = "";
      this.RecordExpense.enrollCourse_id = "";
      this.RecordExpense.budget = "";
      this.RecordExpense.rec = "";

      // reset data enrollCourseDatail

      this.enrollCourseDatail.student = "";
      this.enrollCourseDatail.course = "";
      this.enrollCourseDatail.computer = "";
      this.enrollCourseDatail.employee = "";
      this.enrollCourseDatail.date = "";
    },
    checkList() {
      // type student
      if (
        (this.RecordExpense.expenseType == 1 &&
          this.RecordExpense.enrollCourse_id != "" &&
          this.RecordExpense.createdBy != "") ||
        (this.RecordExpense.expenseType == 2 &&
          this.RecordExpense.rec != "" &&
          this.RecordExpense.createdBy != "")
      ) {
        this.saveRecordExpense();
      } else {
        this.popup.TextError = "กรุณาเลือกข้อมูลให้ครบ";
        this.popup.Error = true;
      }
    },
    reloadPage() {
      location.reload();
      window.location.reload(false);
    },
    setEnrollCourseDatail() {
      for (let elem in this.enrollCourse) {
        if (this.enrollCourse[elem].id == this.RecordExpense.enrollCourse_id) {
          this.enrollCourseDatail.student = this.enrollCourse[
            elem
          ].student.fullname;
          this.enrollCourseDatail.course = this.enrollCourse[
            elem
          ].course.courseName;
          this.enrollCourseDatail.computer = this.enrollCourse[
            elem
          ].computer.pcNumber;
          this.enrollCourseDatail.employee = this.enrollCourse[
            elem
          ].employee.fullname;
          this.enrollCourseDatail.date = this.enrollCourse[elem].date;
        }
      }
    },
    saveRecordExpense() {
      // type student
      if (this.RecordExpense.expenseType == 1) {
        console.log("save student");
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

            this.resetData();
            this.popup.Success = true;
            this.popup.TextSuccess = "บันทึกข้อมูลเสร็จสิ้น";
          })
          .catch(e => {
            console.log(e);

            this.popup.TextError = "ไม่สามารถเชื่อมต่อฐานข้อมูลได้ " + e;
            this.popup.Error = true;
          });
        // type employee
      } else if (this.RecordExpense.expenseType == 2) {
        /* eslint-disable no-console */
        console.log("save employee");
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
            console.log(response.data);
            this.resetData();
            this.popup.Success = true;
            this.popup.TextSuccess = "บันทึกข้อมูลเสร็จสิ้น";
          })
          .catch(e => {
            console.log(e);

            this.popup.TextError = "ไม่สามารถเชื่อมต่อฐานข้อมูลได้" + e;
            this.popup.Error = true;
          });
      }
    },
    setSelete() {
      if (this.RecordExpense.expenseType == 2) {
        this.enrollCourseDatail.show = false;
        this.selete.emp = true;
        this.selete.stu = false;
        this.RecordExpense.budget = "";
      } else if (this.RecordExpense.expenseType == 1) {
        this.enrollCourseDatail.show = false;
        this.selete.stu = true;
        this.selete.emp = false;
        this.RecordExpense.budget = "";
      }
    },
    setBudgetEmployee() {
      //emp
      this.enrollCourseDatail.show = false;
      for (let elem in this.employee) {
        if (this.employee[elem].id == this.RecordExpense.rec) {
          this.RecordExpense.budget = this.employee[elem].position.salary;
        }
      }
    },
    setBudgetStudent() {
      //emp

      this.setEnrollCourseDatail();
      this.enrollCourseDatail.show = true;

      for (let elem in this.enrollCourse) {
        if (this.enrollCourse[elem].id == this.RecordExpense.enrollCourse_id) {
          this.RecordExpense.budget = this.enrollCourse[elem].course.price;
          console.log(this.enrollCourse[elem].course.price);
        }
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
          this.getRecordExpense();
        })
        .catch(e => {
          console.log(e);
        });
    },
    getRecordExpense() {
      http
        .get("/recordExpense")
        .then(response => {
          this.recordExpense = response.data;

          for (var elem in this.recordExpense) {
            for (var index in this.enrollCourse) {
              if (
                this.enrollCourse[index].id ==
                this.recordExpense[elem].enrollCourse.id
              ) {
                this.enrollCourse[index].student.fullname = "null";
              }
            }
          }

          var temp = [];
          var i = 0;
          for (var k in this.enrollCourse) {
            if (this.enrollCourse[k].student.fullname != "null") {
              temp[i] = this.enrollCourse[k];
              i = i + 1;
            }
          }

          this.enrollCourse = temp;
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
    },
    setCreatedBy() {
      this.RecordExpense.createdBy = this.$session.get("userId");
    }
  },
  created() {
    setInterval(() => this.getNowTime());
  },
  mounted() {
    this.resetData();
  }
};
</script>
