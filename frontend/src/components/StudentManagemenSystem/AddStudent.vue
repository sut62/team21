<template>
  <v-container fluid>
    <v-hover v-slot:default="{ hover }">
      <v-card width="600" :elevation="hover ? 12 : 5" style="margin: auto; margin-top: 50px;">
        <v-app-bar dark color="light-blue lighten-1">
          <v-btn icon>
            <v-icon large>mdi-label</v-icon>
          </v-btn>

          <v-toolbar-title>สมัครสมาชิก</v-toolbar-title>

          <v-spacer></v-spacer>

          <v-btn icon>
            <v-icon>mdi-dialpad</v-icon>
          </v-btn>
        </v-app-bar>

        <v-container style="margin-top: 50px; padding-bottom: 30px;">

          <v-row>

            <v-col cols="8" style="margin: auto;">
              <v-text-field 
              v-model="student.username"
              :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required 
              label="กรอกUsername" outlined dense></v-text-field>
            </v-col>

          </v-row>

          <v-row>

            <v-col cols="8" style="margin: auto;">
              <v-text-field 
              v-model="student.password" :type="'password'"
              :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required 
              label="กรอกPassword" outlined dense></v-text-field>
            </v-col>

          </v-row>

          <v-row>

            <v-col cols="3" style="margin: auto;">
              <v-select 
              v-model="student.nametitleId"
              :items="nametitles" 
              item-text="nametitle"
              item-value="id"
              :rules="[(v) => !!v || 'จำเป็นต้องเลือกข้อมูล']"
                required
              label="คำนำหน้าชื่อ" outlined></v-select>
            </v-col>

            <v-col cols="8" style="margin: auto;">
              <v-text-field 
              v-model="student.fullname" 
              :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required 
              label="กรอกชื่อ-นามสกุล" outlined dense></v-text-field>
            </v-col>

          </v-row>

          <v-row>

            <v-col cols="3" style="margin: auto;">
              <v-select v-model="student.genderId"
              :items="genders" 
              item-text="gender"
              item-value="id"
              :rules="[(v) => !!v || 'จำเป็นต้องเลือกข้อมูล']"
                required
              label="เพศ" outlined></v-select>
            </v-col>

            <v-col cols="8" style="margin: auto;">
              <v-text-field 
              v-model="student.tel" 
              :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required 
              label="เบอรโทรติดต่อ" outlined dense></v-text-field>
            </v-col>

          </v-row>

           <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-text-field v-model="student.old"
              :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required 
              label="กรอกอายุ" outlined dense></v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-text-field v-model="student.email"
              :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required 
              label="E-Mail" outlined dense></v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select v-model="student.provinceId" 
              :items="provinces"
               item-text="province"
               item-value="id"
               :rules="[(v) => !!v || 'จำเป็นต้องเลือกข้อมูล']"
                required
               label="จังหวัด" outlined></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-text-field v-model="student.address" 
              :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required 
              label="กรอกที่อยู่เพิ่มเติม" outlined dense></v-text-field>
            </v-col>
          </v-row>

         <v-row>
            <v-btn
              style="margin: auto;"
              large
              color="light-blue lighten-1"
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
        <v-btn color="pink" text @click="snackbar = false">Close</v-btn>
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
        provinceId: ""
        
      },
      items: [],
      valid : false,
      genders : [],
      nametitles : [],
      provinces : [],
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
            this.student.email,
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
          this.text = "กรุณาป้อนข้อมูลให้ครบ";
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
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getGenders();
    this.getNametitles();
    this.getProvinces();
  }
};

</script>

