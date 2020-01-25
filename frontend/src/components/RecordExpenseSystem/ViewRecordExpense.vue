<template>
  <div>
    <div align="center" justify="center">
      <v-card width="1000" style="margin: 40px;">
        <v-card-title>
          <label >ตารางเเสดงประวัติการใช้จ่าย</label>
          <v-spacer></v-spacer>
          <!-- append-icon="fas fa-search" -->
        </v-card-title>

        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">เลขที่</th>
                <th class="text-left">ประเภทค่าใช้จ่าย</th>
                <th class="text-left">ชื่อ-สกุล</th>
                <th class="text-left">วันเวลาที่บันทึก</th>
                <th class="text-left">จำนวนเงิน (บาท.)</th>
                <th class="text-left">ผู้บันทึก</th>
                
              </tr>
            </thead>

            <tbody v-if="view.data">
              <tr v-for="item in viewRecordExpense" :key="item.id" >
                <td>{{ item.id }}</td>
                <td>{{ item.expenseType.type }}</td>

                <td v-if="item.expenseType.id == 1" >{{ item.enrollCourse.student.fullname }}</td>

                <td v-if="item.expenseType.id == 2" >{{ item.rec.fullname }}</td>
             
                <td>{{ item.date }}</td>
                <td>{{ item.budget }}</td>
                <td>{{ item.createdBy.fullname }}</td>
                
              </tr>
              <tr v-if="viewRecordExpense.length < 1">
                <td colspan="8" class="text-center">
                  <h5>ไม่พบข้อมูล</h5>
                </td>
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
            @click="getRecordExpense"
          >ดูข้อมูลทั้งหมด</v-btn>

          <Label v-if="viewRecordExpense.length < 1" style="margin-right: 20px;">ไม่พบข้อมูล</Label>
          <Label v-if="viewRecordExpense.length > 0" style="margin-right: 20px;">พบข้อมูล</Label>
        </v-card-actions>
      </v-card>
    </div>

  </div>
</template>

<script>
import http from "../../http-common";
export default {
  name: "ViewRecordExpense",
  data() {
    return {
      view: {
        data: false,
        loading: false
      },
      field_search: "",
      viewRecordExpense: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getRecordExpense() {
      this.view.data = false;
      this.view.loading = true;

      http
        .get("/recordExpense")
        .then(response => {
          this.viewRecordExpense = response.data;
          console.log(this.viewRecordExpense);

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
    this.getRecordExpense();
  }
};
</script>