<template>
  <div>
    <div align="center" justify="center">
      <v-card width="1000" style="margin: 40px;">
        <v-card-title>
          <label style="margin-bottom: 30px;margin-left: 20px;">ตารางเเสดงผลการสมัครสมาชิก</label>
          <v-spacer></v-spacer>
          <v-text-field
            v-model="field_search"
            append-icon="fas fa-search"
            label="ค้นหาชื่อนักเรียน"
            solo
            rounded
            @keyup.enter.native="getStudentByStudentFullname"
          ></v-text-field>
        </v-card-title>

        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">เลขที่</th>
                <th class="text-left">คำนำหน้าชื่อ</th>
                <th class="text-left">ชื่อ-นามสกุล</th>
                <th class="text-left"> เพศ </th>
                <th class="text-left">อายุ</th>
                <th class="text-left">จังหวัด</th>
                <th class="text-left">   เบอร์โทร   </th>
                <th class="text-left">อีเมลล์</th>
                <th class="text-left">ที่อยู่</th>
              </tr>
            </thead>

            <tbody v-if="view.data">
              <tr v-for="item in student" :key="item.id">
                <td>{{ item.id }}</td>
                <td>{{ item.nametitle.nametitle }}</td>
                <td>{{ item.fullname }}</td>
                <td>{{ item.gender.gender }}</td>
                <td>{{ item.old }}</td>
                <td>{{ item.province.province}}</td>
                <td>{{ item.tel }}</td>
                <td>{{ item.email }}</td>
                <td>{{ item.address}}</td>
              </tr>
            </tbody>

            <tbody v-if="view.loading">
              <tr v-if="view.loading">
                <td colspan="11">
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
            @click="getStudentAll"
          >view all</v-btn>
          <Label style="margin-right: 20px;">Rows all page: {{ student.length }}</Label>
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
import http from "../../http-common";
export default {
  name: "ViewStudent",
  data() {
    return {
      view: {
        data: false,
        loading: false
      },
      field_search: "",
      student: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getStudentByStudentFullname() {
      this.view.data = false;
      this.view.loading = true;

      http
        .get("/student/" + this.field_search.trim() + "/")
        .then(response => {
          this.student = response.data;
          console.log(this.student);

          this.view.loading = false;
          this.view.data = true;
        })
        .catch(e => {
          console.log(e);

          this.view.loading = false;
          this.view.data = true;
        });
    },
    getStudentAll() {
      this.view.data = false;
      this.view.loading = true;

      http
        .get("/student")
        .then(response => {
          this.student = response.data;
          console.log(this.student);

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
    this.getStudentAll();
  }
};
</script>
