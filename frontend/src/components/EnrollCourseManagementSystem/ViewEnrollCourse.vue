<template>
  <div>
    <div align="center" justify="center">
      <v-card width="1000" style="margin: 40px;">
        <v-card-title>
          <label style="margin-bottom: 30px;margin-left: 20px;">ตารางเเสดงผลการลงทะเบียนเรียน</label>
          <v-spacer></v-spacer>
          <!-- append-icon="fas fa-search" -->
          <v-text-field
            id="search_enroll001"
            v-model="field_search"
            label="ค้นหาชื่อนักเรียน"
            solo
            rounded
          ></v-text-field>
          <v-btn icon style="position: relative;right: 50px;bottom:15px; margin: 0;">
            <v-icon id="search_btn_enroll001" size="24px" @click="getEnrollCourseByStudentName">fas fa-search</v-icon>
          </v-btn>
        </v-card-title>

        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">เลขที่</th>
                <th class="text-left">นักเรียนที่ลงทะเบียน</th>
                <th class="text-left">วันเวลา</th>
                <th class="text-left">หมายเหตุ</th>
                <th class="text-left">เลขที่นั่งคอมพิวเตอร์</th>
                <th class="text-left">คอร์สที่ลงทะเบียน</th>
                <th class="text-left">ผู้บันทึกข้อมูล</th>
              </tr>
            </thead>

            <tbody v-if="view.data">
              <tr v-for="item in viewEnrollCourse" :key="item.id" v-on:click="clickRowTable(item)">
                <td>{{ item.id }}</td>
                <td>{{ item.student.fullname }}</td>
                <td>{{ item.date }}</td>
                <td>{{ item.note }}</td>
                <td>{{ item.computer.pcNumber }}</td>
                <td>{{ item.course.courseName }}</td>
                <td>{{ item.employee.fullname }}</td>
              </tr>
              <!-- <tr v-if="viewEnrollCourse.length < 1">
                <td colspan="7" class="text-center">
                  <h5>ไม่พบข้อมูล</h5>
                </td>
              </tr> -->
            </tbody>

            <tbody v-if="view.loading">
              <tr v-if="view.loading">
                <td colspan="8">
                  <v-text-field color="rgb(24,103,193)" loading disabled></v-text-field>
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            text
            color="rgb(24,103,193)"
            style="margin-right: 20px;"
            @click="getEnrollCourseAll"
           
          >ดูข้อมูลทั้งหมด</v-btn>
       
          <Label id="ass001" v-if="viewEnrollCourse.length < 1" style="margin-right: 20px;">ไม่พบข้อมูล</Label>
          <Label id="ass002" v-if="viewEnrollCourse.length > 0" style="margin-right: 20px;">พบข้อมูล</Label>
        </v-card-actions>
      </v-card>
    </div>

    <!-- popup Row Deatil -->

    <template>
      <v-row justify="center">
        <v-dialog v-model="popup.RowDetail" max-width="700px">
          <v-card style="background-color: #F2F3F4">
            <v-app-bar dark color="#1A76D2">
              <v-toolbar-title>ข้อมูลการลงทะเบียนเรียน</v-toolbar-title>

              <v-spacer></v-spacer>
              <v-btn icon>
                <v-icon size="24px" @click="popup.RowDetail = false">fas fa-times</v-icon>
              </v-btn>
            </v-app-bar>

            <v-card-text>
              <v-container style="color: black;">
                <h2>
                  เลขที่:
                  <span class="font-weight-light">{{RowId.EnrollDetail.id}}</span>
                </h2>
                <br />
                <h2>
                  นักเรียนที่ลงทะเบียน:
                  <span class="font-weight-light">{{RowId.StuDetail.fullname}}</span>
                </h2>
                <br />
                <h2>
                  วันเวลา:
                  <span class="font-weight-light">{{RowId.EnrollDetail.date}}</span>
                </h2>
                <br />
                <h2>
                  หมายเหตุ:
                  <span class="font-weight-light">{{RowId.EnrollDetail.note}}</span>
                </h2>
                <br />
                <h2>
                  เลขที่นั่งคอมพิวเตอร์:
                  <span class="font-weight-light">{{RowId.ComDetail.pcNumber}}</span>
                </h2>
                <br />
                <h2>
                  คอร์สที่ลงทะเบียน:
                  <span
                    class="font-weight-light"
                  >{{RowId.CourseDetail.courseName}}</span>
                </h2>
                <br />
                <div style="margin-left: 30px;">
                  <h3>
                    ติวเตอร์:
                    <span class="font-weight-light">{{RowId.CourseDetail.tutur}}</span>
                  </h3>
                  <h3>
                    ห้องเรียน:
                    <span class="font-weight-light">{{RowId.CourseDetail.room}}</span>
                  </h3>
                  <h3>
                    วิชา:
                    <span class="font-weight-light">{{RowId.CourseDetail.subjectsName}}</span>
                  </h3>
                  <h3>
                    เวลาเรียน:
                    <span class="font-weight-light">{{RowId.CourseDetail.studyTime}}</span>
                  </h3>
                  <h3>
                    ราคาคอร์ส:
                    <span class="font-weight-light">{{RowId.CourseDetail.price}}</span>
                  </h3>
                </div>
                <br />
                <h2>
                  ผู้บันทึกข้อมูล:
                  <span class="font-weight-light">{{RowId.EmpDatail.fullname}}</span>
                </h2>
                <br />

                <v-row>
                  <v-spacer></v-spacer>
                  <v-btn
                    id="closeRow12322"
                    @click="popup.RowDetail = false"
                    class="font-weight-light"
                    color="primary"
                    width="100"
                    height="20"
                  >ปิด</v-btn>
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
  name: "ViewEnrollCourse",
  data() {
    return {
      snackbar: false,
      text: "",
      popup: {
        RowDetail: false
      },
      RowId: {
        EnrollDetail: {
          id: "",
          date: "",
          note: ""
        },
        StuDetail: {
          fullname: ""
        },
        CourseDetail: {
          tutur: "",
          room: "",
          subjects: "",
          studyTime: "",
          price: ""
        },
        ComDetail: {
          pcNumber: ""
        },
        EmpDatail: {
          fullname: ""
        }
      },
      view: {
        data: false,
        loading: false
      },
      field_search: "",
      viewEnrollCourse: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    clickRowTable(item) {
      console.log(item);
      this.popup.RowDetail = true;

      this.RowId.EnrollDetail.id = item.id;
      this.RowId.EnrollDetail.date = item.date;
      this.RowId.EnrollDetail.note = item.note;

      this.RowId.StuDetail.fullname = item.student.fullname;

      this.RowId.ComDetail.pcNumber = item.computer.pcNumber;

      this.RowId.CourseDetail.courseName = item.course.courseName;
      this.RowId.CourseDetail.tutur = item.course.employee.fullname;
      this.RowId.CourseDetail.room = item.course.room.room;
      this.RowId.CourseDetail.subjectsName = item.course.subjects.subjectsName;
      this.RowId.CourseDetail.studyTime =
        item.course.time.day +
        " " +
        item.course.time.start_time +
        " - " +
        item.course.time.end_time +
        " น.";
      this.RowId.CourseDetail.price = item.course.price;

      this.RowId.EmpDatail.fullname = item.employee.fullname;
    },
    getEnrollCourseByStudentName() {
      if (this.field_search != "") {
        this.view.data = false;
        this.view.loading = true;
        http
          .get("/viewEnrollCourse/" + this.field_search.trim() + "/")
          .then(response => {
            this.viewEnrollCourse = response.data;
            console.log(this.viewEnrollCourse);

            if (this.viewEnrollCourse.length > 0) {
              this.snackbar = true;
              this.text = "พบข้อมูล";
            } else {
              this.snackbar = true;
              this.text = "ไม่พบข้อมูล";
            }

            this.view.loading = false;
            this.view.data = true;
          })
          .catch(e => {
            console.log(e);

            this.view.loading = false;
            this.view.data = true;
          });
      }
    },
    getEnrollCourseAll() {
      this.view.data = false;
      this.view.loading = true;

      http
        .get("/viewEnrollCourse/all/")
        .then(response => {
          this.viewEnrollCourse = response.data;
          console.log(this.viewEnrollCourse);

          this.view.loading = false;
          this.view.data = true;
        })
        .catch(e => {
          console.log(e);

          this.view.loading = false;
          this.view.data = true;
        });
    }
  },
  mounted() {
    this.getEnrollCourseAll();
  }
};
</script>