<template>
  <div>
    <v-hover v-slot:default="{ hover }">
      <v-card width="800" :elevation="hover ? 12 : 5">
        <v-app-bar dark color="#1A76D2">
          <v-btn icon>
            <v-icon large>mdi-label</v-icon>
          </v-btn>

          <v-toolbar-title>ระบบจัดเก็บข้อมูลพนักงาน</v-toolbar-title>

          <v-spacer></v-spacer>
        </v-app-bar>

        <v-container style>
          <v-row style="margin-left: 50px; margin-right: 50px">
            <v-col cols="4" style>
              <v-select
                id="emp001"
                item-text="nametitle"
                item-value="id"
                v-model="employee.nametitle"
                :items="nametitle"
                :rules="[(v) => !!v || 'จำเป็นต้องเลือกข้อมูล']"
                required
                label="เลือกคำนำหน้าชื่อ"
                outlined
                @change="setGender"
              ></v-select>
            </v-col>
            <v-col cols="8" style>
              <v-text-field
                id="emp002"
                v-model="employee.fullname"
                :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required
                label="ชื่อ-นามสกุล"
                outlined
                dense
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row style="margin-left: 50px; margin-right: 50px">
            <v-col cols="4">
              <v-select
                id="emp003"
                item-text="gender"
                item-value="id"
                v-model="employee.gender"
                :items="gender"
                :rules="[(v) => !!v || 'จำเป็นต้องเลือกข้อมูล']"
                required
                label="เลือกเพศ"
                outlined
                disabled
              ></v-select>
            </v-col>
            <v-col cols="8">
              <v-select
                id="emp004"
                item-text="position"
                item-value="id"
                v-model="employee.position"
                :items="position"
                :rules="[(v) => !!v || 'จำเป็นต้องเลือกข้อมูล']"
                required
                label="เลือกตำแหน่งงาน"
                outlined
              ></v-select>
            </v-col>
          </v-row>
          <v-row style="margin-left: 50px; margin-right: 50px">
            <v-col>
              <v-text-field
                id="emp005"
                v-model="employee.username"
                :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required
                label="๊กรอก Username"
                outlined
                dense
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row style="margin-left: 50px; margin-right: 50px">
            <v-col>
              <v-text-field
                id="emp006"
                v-model="employee.password"
                :type="'password'"
                :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required
                label="๊กรอก Password"
                outlined
                dense
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row style="margin-left: 50px; margin-right: 50px">
            <v-col>
              <v-select
                id="emp007"
                item-text="province"
                item-value="id"
                :rules="[(v) => !!v || 'จำเป็นต้องเลือกข้อมูล']"
                required
                v-model="employee.province"
                :items="province"
                label="เลือกจังหวัด"
                outlined
              ></v-select>
            </v-col>
          </v-row>

          <v-row style="margin-left: 50px; margin-right: 50px">
            <v-col>
              <v-text-field
                id="emp011"
                v-model="employee.email"
                :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required
                label="๊กรอก Email"
                outlined
                dense
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row md="8" style="margin-left: 50px; margin-right: 50px">
            <v-col>
              <v-textarea
                id="emp008"
                v-model="employee.address"
                :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required
                label="กรอกที่อยู่"
                outlined
                dense
              ></v-textarea>
            </v-col>
          </v-row>
          <v-row style="margin-left: 50px; margin-right: 50px">
            <v-col>
              <v-text-field
                id="emp009"
                v-model="employee.dateTime"
                :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required
                label="๊เวลาที่บันทึก"
                outlined
                dense
                disabled
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-btn
              id="emp010"
              style="margin: auto;"
              large
              color="#1A76D2"
              width="300"
              dark
              @click="saveEmployee"
            >บันทึก</v-btn>
          </v-row>
        </v-container>
      </v-card>
    </v-hover>

    <v-row>
      <v-snackbar v-model="snackbar">
        {{ text }}
        <v-btn id="emp011" text-align="center" color="pink" text @click="ResetData"></v-btn>
      </v-snackbar>
    </v-row>
  </div>
</template>
<script>
import http from "../../http-common";
export default {
  name: "AddEmployee",
  data: () => ({
    employee: {
      nametitle: "",
      fullname: "",
      gender: "",
      position: "",
      province: "",
      address: "",
      username: "",
      password: "",
      dateTime: "",
      email: ""
    },
    nametitle: [],
    gender: [],
    position: [],
    province: [],
    snackbar: false,
    text: ""
  }),
  /* eslint-disable no-console */
  methods: {
    getNametitle() {
      http
        .get("/nametitle")
        .then(response => {
          this.nametitle = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getGender() {
      http
        .get("/gender")
        .then(response => {
          this.gender = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getProvince() {
      http
        .get("/province")
        .then(response => {
          this.province = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getPosition() {
      http
        .get("/position")
        .then(response => {
          this.position = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    saveEmployee() {
      // @PostMapping("/employee/{nametitle_id}/{fullname}/{gender_id}/{position_id}/{province_id}/{address}/{username}/{password}/{dateTime}")
      http
        .post(
          "/employee/" +
            this.employee.nametitle +
            "/" +
            this.employee.fullname +
            "/" +
            this.employee.gender +
            "/" +
            this.employee.position +
            "/" +
            this.employee.province +
            "/" +
            this.employee.address +
            "/" +
            this.employee.username +
            "/" +
            this.employee.password +
            "/" +
            this.employee.dateTime +
            "/" +
            this.employee.email,
          this.employee
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
    },
    setGender() {
      if (this.employee.nametitle == 1 || this.employee.nametitle == 4) {
        this.employee.gender = 1;
      } else {
        this.employee.gender = 2;
      }
    },
    getNowTime() {
      // const answer = x > 10 ? "greater than 10" : "less than 10";
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
      this.employee.dateTime =
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
    ResetData() {
      window.location.reload(false);
      this.snackbar = false;
    }
  },
  created() {
    setInterval(() => this.getNowTime());
  },
  mounted() {
    this.getNametitle();
    this.getGender();
    this.getProvince();
    this.getPosition();
  }
};
</script>

