<template>
  <v-container>
    <v-hover v-slot:default="{ hover }">
      <v-card width="800" :elevation="hover ? 12 : 5">
        <v-app-bar dark color="blue darken-2">
          <v-btn icon>
            <v-icon large>mdi-label</v-icon>
          </v-btn>

          <v-toolbar-title>สมัครสมาชิก</v-toolbar-title>

          <v-spacer></v-spacer>
        </v-app-bar>

        <v-container style>
          <v-row style="margin-left: 50px; margin-right: 50px">
            <v-col cols="4" style>
              <v-select
                item-text="nametitle"
                item-value="id"
                v-model="student.nametitleId"
                :items="nametitles"
                :rules="[(v) => !!v || 'จำเป็นต้องเลือกข้อมูล']"
                required
                label="เลือกคำนำหน้าชื่อ"
                outlined
              ></v-select>
            </v-col>
            <v-col cols="8" style>
              <v-text-field
                v-model="student.fullname"
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
                item-text="gender"
                item-value="id"
                v-model="student.genderId"
                :items="genders"
                :rules="[(v) => !!v || 'จำเป็นต้องเลือกข้อมูล']"
                required
                label="เลือกเพศ"
                outlined
              ></v-select>
            </v-col>

            <v-col cols="8">
              <v-text-field
                v-model="student.old"
                :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required
                label="กรอกอายุ"
                outlined
                dense
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row style="margin-left: 50px; margin-right: 50px">
            <v-col>
              <v-text-field
                v-model="student.username"
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
                v-model="student.password"
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
                item-text="province"
                item-value="id"
                :rules="[(v) => !!v || 'จำเป็นต้องเลือกข้อมูล']"
                required
                v-model="student.provinceId"
                :items="provinces"
                label="เลือกจังหวัด"
                outlined
              ></v-select>
            </v-col>
          </v-row>

          

          <v-row style="margin-left: 50px; margin-right: 50px">
            <v-col>
              <v-text-field
                v-model="student.tel"
                :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required
                label="กรอกเบอร์โทร"
                outlined
                dense
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row style="margin-left: 50px; margin-right: 50px">
            <v-col>
              <v-text-field
                v-model="student.email"
                :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required
                label="กรอกอีเมล"
                outlined
                dense
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row md="8" style="margin-left: 50px; margin-right: 50px">
            <v-col>
              <v-textarea
                v-model="student.address"
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
              <v-select
                disabled
                v-model="student.employee_id"
                :items="employees"
                item-text="fullname"
                item-value="id"
                label="CreatedBy"
                outlined
              ></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-btn
              style="margin: auto;"
              large
              color="blue darken-2"
              width="300"
              dark
              @click="saveStudent"
            >บันทึก</v-btn>
          </v-row>
        </v-container>
      </v-card>
    </v-hover>

    <v-row>
      <v-snackbar v-model="snackbar">
        {{ text }}
        <v-btn color="pink" text @click="snackbar = false"></v-btn>
      </v-snackbar>
    </v-row>
  </v-container>
</template>

<script>
import http from "../../http-common";

export default {
  name: "student",
  data() {
    return {
      student: {
        fullname: "",
        old: "",
        tel: "",
        email: "",
        address: "",
        genderId: "",
        nametitleId: "",
        username: "",
        password: "",
        provinceId: "",
        employee_id: ""
      },
      items: [],
      valid: false,
      employees: [],
      genders: [],
      nametitles: [],
      provinces: [],
      snackbar: false,
      text: ""
    };
  },
  methods: {
    /* eslint-disable no-console */
    getGenders() {
      http
        .get("/gender")
        .then(response => {
          this.genders = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getNametitles() {
      http
        .get("/nametitle")
        .then(response => {
          this.nametitles = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getProvinces() {
      http
        .get("/province")
        .then(response => {
          this.provinces = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    saveStudent() {
      http
        .post(
          "/student/" +
            this.student.nametitleId +
            "/" +
            this.student.genderId +
            "/" +
            this.student.fullname +
            "/" +
            this.student.provinceId +
            "/" +
            this.student.address +
            "/" +
            this.student.username +
            "/" +
            this.student.password +
            "/" +
            this.student.old +
            "/" +
            this.student.tel +
            "/" +
            this.student.email +
            "/" +
            this.student.employee_id,
          this.student
        )
        .then(response => {
          console.log(response);
          this.text = "บันทึกข้อมูลเสร็จสิ้น";
          this.snackbar = true;
          location.reload();
        })
        .catch(e => {
          console.log(e);
          this.text = "กรุณาป้อนข้อมูลให้ครบถ้วน";
          this.snackbar = true;
        });
      this.submitted = true;
    },

    clear() {
      this.$refs.form.reset();
    },

    refreshList() {
      this.getGenders();
      this.getNametitles();
      this.getProvinces();
    },
    getEmployees() {
      http
        .get("/employee")
        .then(response => {
          this.employees = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    setCreatedBy() {
      this.student.employee_id = this.$session.get("userId");
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.setCreatedBy();
    this.getEmployees();
    this.getGenders();
    this.getNametitles();
    this.getProvinces();
  }
};
</script>
