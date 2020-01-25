<template>
  <div>
    <div align="center" justify="center">
      <v-card width="1000" style="margin: 40px;">
        <v-card-title>
          <label style="margin-bottom: 10px;margin-left: 20px;">ตารางเเสดงข้อมูลคอร์สเรียน</label>
          <v-spacer></v-spacer>
      </v-card-title>

        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">ลำดับ</th>
                <th class="text-left">ชื่อคอร์สเรียน</th>
                <th class="text-left">ครูผู้สอน</th>
                <th class="text-left">วิชา</th>
                <th class="text-left">ห้องเรียน</th>
                <th class="text-left">เวลา</th>
                <th class="text-left">ราคา</th>
               </tr>
            </thead>

            <tbody v-if="view.data">
              <tr v-for="item in course" :key="item.id">
                <td>{{ item.id }}</td>
                <td>{{ item.courseName }}</td>
                <td>{{ item.employee.fullname }}</td>
                <td>{{ item.subjects.subjectsName }}</td>
                <td>{{ item.room.room }}</td>
                <td>{{ item.time.day+" "+item.time.start_time+"-"+item.time.end_time }}</td>
                <td>{{ item.price}}</td>
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
          <Label style="margin-right: 20px;">Rows all page: {{ course.length }}</Label>
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
import http from "../../http-common";
export default {
  name: "ViewCourse",
  data() {
    return {
      view: {
        data: false,
        loading: false
      },
      course: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    
   getCourse() {
       this.view.data = false;
      this.view.loading = true;
       http
        .get("/course/")
        .then(response => {
          this.course = response.data;
          console.log(this.employee);

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
    this.getCourse();
  }
};
</script>
