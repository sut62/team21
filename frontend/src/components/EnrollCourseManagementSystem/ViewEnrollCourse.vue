<template>
  <div>
    <div align="center" justify="center">
      <v-card width="1000" style="margin: 40px;">
        <v-card-title>
          <label style="margin-bottom: 30px;margin-left: 20px;">ตารางเเสดงผลการลงทะเบียนเรียน</label>
          <v-spacer></v-spacer>
          <v-text-field
            v-model="field_search"
            append-icon="fas fa-search"
            label="ค้นหาชื่อนักเรียน"
            solo
            rounded
            @keyup.enter.native="getEnrollCourseByStudentName"
          ></v-text-field>
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
                <th class="text-left">วิชา</th>
                <th class="text-left">ราคา</th>
              </tr>
            </thead>

            <tbody v-if="view.data">
              <tr v-for="item in viewEnrollCourse" :key="item.id">
                <td>{{ item.id }}</td>
                <td>{{ item.student.fullname }}</td>
                <td>{{ item.date }}</td>
                <td>{{ item.note }}</td>
                <td>{{ item.computer.pcNumber }}</td>
                <td>{{ item.course.courseName }}</td>
                <td>{{ item.course.subjects.subjectsName }}</td>
                <td>{{ item.course.price }} บาท.</td>
              </tr>
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
          >view all</v-btn>
          <Label style="margin-right: 20px;">Rows all page: {{ viewEnrollCourse.length }}</Label>
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
import http from "../../http-common";
export default {
  name: "ViewEnrollCourse",
  data() {
    return {
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
    getEnrollCourseByStudentName() {
      this.view.data = false;
      this.view.loading = true;

      http
        .get("/viewEnrollCourse/" + this.field_search.trim() + "/")
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