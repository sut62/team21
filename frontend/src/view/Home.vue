<template>
  <v-app>
    <v-app-bar color="#100E17" dark style="padding: 10px;">
      <img src="../assets/logo2.png" style="height: 80%;" />
      <!-- <v-toolbar-title style="color: white;" class="display-2 font-weight-black">THE TUTOR</v-toolbar-title> -->

      <v-spacer></v-spacer>

      <div class="my-2">
        <v-btn text class="btn-hover headline font-weight-light" color="white">HOME</v-btn>
      </div>
      <div class="my-2">
        <v-btn text class="btn-hover headline font-weight-light" color="white">NEWS</v-btn>
      </div>
      <div class="my-2">
        <v-btn text class="btn-hover headline font-weight-light" color="white">Course</v-btn>
      </div>

      <div class="my-2">
        <v-btn text class="btn-hover headline font-weight-light" color="white">Reviews</v-btn>
      </div>
      <div class="my-2" style="margin-right: 100px;">
        <v-btn text class="btn-hover headline font-weight-light" color="white">ABOUT US</v-btn>
      </div>

      <div class="my-2 display-2">
        <Label style="color: white">|</Label>
      </div>
      <div class="my-2">
        <v-btn

          id="showLogin"
          @click.stop="LoginStu.showDialog = true"
          text
          class="btn-hover headline font-weight-light"
          color="white"
        >LOGIN</v-btn>
      </div>
    </v-app-bar>

    <v-content>
      <template>
        <v-carousel cycle show-arrows-on-hover hide-delimiters  style>
          <v-carousel-item key="1" src="../assets/carousel/img1.jpg">
            <v-row
              class="fill-height"
              align="center"
              justify="center"
              style="margin-right: 400px;margin-top: 100px;"
            >
              <div class="display-4 font-weight-black" style="color: white;">Cheap price</div>
            </v-row>
          </v-carousel-item>
          <v-carousel-item key="2" src="../assets/carousel/img2.jpg">
            <v-row class="fill-height" align="center" justify="center" style="margin-top: 100px;">
              <div class="display-4 font-weight-black" style="color: white;">Leading institutions</div>
            </v-row>
          </v-carousel-item>
          <v-carousel-item key="3" src="../assets/carousel/img3.jpg">
            <v-row
              class="fill-height"
              justify="center"
              style="margin-top: 50px;margin-right: 290px;"
            >
              <div class="display-4 font-weight-black" style="color: white;">Positives review</div>
            </v-row>
          </v-carousel-item>
        </v-carousel>
      </template>

      <!-- login from student-->

      <template>
        <v-row justify="center">
          <v-dialog v-model="LoginStu.showDialog" max-width="500px">
            <v-card style="background-color: #F2F3F4">
              <v-card-title>
                <span class="display-1 font-weight-light">LOGIN AS STUDENT</span>
                <v-spacer></v-spacer>

                <v-btn icon>
                  <v-icon id="closeShowStudent" size="24px" @click="LoginStu.showDialog = false">fas fa-times</v-icon>
                </v-btn>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-spacer></v-spacer>
                    <v-col cols="8">
                      <v-text-field
                        id="usernameStu1"
                        :rules="[(v) => !!v || 'You didn\'t enter a username .']"
                        v-model="LoginStu.username"
                        class="headline font-weight-light"
                        label="Username"
                        required
                      ></v-text-field>
                      <span
                        v-if="LoginStu.errorUsername"
                        style="margin: auto; color: red;"
                      >You didn't enter a username .</span>
                    </v-col>
                    <v-spacer></v-spacer>
                  </v-row>
                  <v-row>
                    <v-spacer></v-spacer>
                    <v-col cols="8">
                      <v-text-field
                        id="passwordStu1"
                        :rules="[(v) => !!v || 'You didn\'t enter a password .']"
                        v-model="LoginStu.password"
                        class="headline font-weight-light"
                        label="Password"
                        type="password"
                        required
                      ></v-text-field>
                      <span
                        v-if="LoginStu.errorPassword"
                        style="margin: auto; color: red;"
                      >You didn't enter a password .</span>
                    </v-col>
                    <v-spacer></v-spacer>
                  </v-row>
                  <v-row>
                    <v-spacer></v-spacer>
                    <v-btn
                      id="bin-login-stu"
                      @click="checkLoginStudent"
                      class="headline font-weight-light"
                      color="info"
                      width="200"
                      height="40"
                    >LOGIN</v-btn>
                    <v-spacer></v-spacer>
                  </v-row>
                </v-container>
                <v-row style="margin: 200;">
                  <v-spacer></v-spacer>
                  <v-btn
                    id="showloginAsEmp"
                    class="subtitle-2 font-weight-light"
                    text
                    color="info"
                    @click.stop="showLogin('emp')"
                  >Log in as an employee</v-btn>
                </v-row>
              </v-card-text>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <!-- login from employee-->

      <template>
        <v-row justify="center">
          <v-dialog v-model="LoginEmp.showDialog" max-width="500px">
            <v-card style="background-color: #F2F3F4">
              <v-card-title>
                <span class="display-1 font-weight-light">LOGIN AS STAFF</span>
                <v-spacer></v-spacer>

                <v-btn icon>
                  <v-icon id="closeShowEmployee" size="24px" @click="LoginEmp.showDialog = false">fas fa-times</v-icon>
                </v-btn>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-spacer></v-spacer>
                    <v-col cols="8">
                      <v-text-field
                        id="usernameEmp1"
                        :rules="[(v) => !!v || 'You didn\'t enter a username .']"
                        v-model="LoginEmp.username"
                        class="headline font-weight-light"
                        label="Username"
                        required
                      ></v-text-field>
                      <span
                        v-if="LoginEmp.errorUsername"
                        style="margin: auto; color: red;"
                      >You didn't enter a username .</span>
                    </v-col>
                    <v-spacer></v-spacer>
                  </v-row>
                  <v-row>
                    <v-spacer></v-spacer>
                    <v-col cols="8">
                      <v-text-field
                        id="passwordEmp1"
                        :rules="[(v) => !!v || 'You didn\'t enter a password .']"
                        v-model="LoginEmp.password"
                        class="headline font-weight-light"
                        label="Password"
                        type="password"
                        required
                      ></v-text-field>
                      <span
                        v-if="LoginEmp.errorPassword"
                        style="color: red;"
                      >You didn't enter a password .</span>
                    </v-col>
                    <v-spacer></v-spacer>
                  </v-row>
                  <v-row>
                    <v-spacer></v-spacer>
                    <v-btn
                      id="bin-login-emp"
                      @click="checkLoginEmployee"
                      class="headline font-weight-light"
                      color="info"
                      width="200"
                      height="40"
                    >LOGIN</v-btn>
                    <v-spacer></v-spacer>
                  </v-row>
                </v-container>
                <v-row style="margin: 200;">
                  <v-spacer></v-spacer>
                  <v-btn
                    id="showloginAsStu"
                    class="subtitle-2 font-weight-light"
                    text
                    color="info"
                    @click.stop="showLogin('stu')"
                  >Log in as an student</v-btn>
                </v-row>
              </v-card-text>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <!-- Bottom sheets  Success-->
      <template>
        <div class="text-center">
          <v-bottom-sheet v-model="sheet.Success" persistent>
            <v-sheet class="text-center" height="200px">
              <v-btn
                id="SuccessEmp1"
                v-if="LoginEmp.showDialog"
                class="mt-8"
                color="info"
                width="100"
                @click="redirectEmployee"
              >OK !</v-btn>
              <v-btn
                id="SuccessStu2"
                v-if="LoginStu.showDialog"
                class="mt-8"
                color="info"
                width="100"
                @click="redirectStudent"
              >OK !</v-btn>
              <div class="py-3">{{ popup.TextSuccess }}</div>
            </v-sheet>
          </v-bottom-sheet>
        </div>
      </template>

      <!-- Bottom sheets  Error-->
      <template>
        <div class="text-center">
          <v-bottom-sheet v-model="sheet.Error" persistent>
            <v-sheet class="text-center" height="200px">
              <v-btn id="BottomError1" class="mt-8" color="error" @click="sheet.Error = false" width="100">CLOSE</v-btn>
              <div class="py-3">{{ popup.TextError }}</div>
            </v-sheet>
          </v-bottom-sheet>
        </div>
      </template>
    </v-content>

    <v-footer class="flex" flat tile height="200"  style="background-color: #100E17; ">
      <v-card
        flat
        tile
        class="lighten-1 white--text text-center"
        style="background-color: #100E17; margin: auto;"
      >
        <v-card-text style="color: white;">
          <v-btn  v-for="icon in icons" :key="icon" class="mx-4 white--text" icon>
            <v-icon size="24px">{{ icon }}</v-icon>
          </v-btn>
        </v-card-text>

        <!-- <v-divider></v-divider> -->
        <v-card-text class="white--text" style="color: white;">
          <strong>Email: se_team21@office365.sut.ac.th</strong>
        </v-card-text>
        <v-card-text class="white--text" style="color: white;">
          <strong>Copyright © 2020 | SUT#25 | CPE#22 | SE-TEAM#21 </strong>
        </v-card-text>
      </v-card>
    </v-footer>
  </v-app>
</template>



<script>
import http from "../http-common";
export default {
  data() {
    return {
      popup: {
        TextSuccess: "TextSuccess",
        Success: false,
        TextError: "TextError",
        Error: false
      },
      sheet: {
        Success: false,
        Error: false
      },
      icons: [
        "fab fa-facebook",
        "fab fa-twitter",
        "fab fa-google-plus",
        "fab fa-linkedin",
        "fab fa-instagram"
      ],
      LoginStu: {
        showDialog: false,
        errorUsername: false,
        errorpassword: false,
        username: "",
        password: ""
      },
      LoginEmp: {
        showDialog: false,
        errorUsername: false,
        errorpassword: false,
        username: "",
        password: ""
      }
    };
  },
  methods: {
    /* eslint-disable no-console */
    resetData() {
      this.LoginStu.errorUsername = false;
      this.LoginStu.errorpassword = false;
      this.LoginEmp.errorUsername = false;
      this.LoginEmp.errorpassword = false;
    },
    checkLoginStudent() {

      if (this.LoginStu.username != "" && this.LoginStu.password != "") {
        http
          .get(
            "/LoginStudent/" +
              this.LoginStu.username +
              "/" +
              this.LoginStu.password
          )
          .then(response => {
            if (response.data[0] != null) {
              this.$session.start();
              this.$session.set("userId", response.data[0].id);
              this.$session.set("username", response.data[0].username);
              this.$session.set("fullname", response.data[0].fullname);
              this.$session.set("position", "นักเรียน");

              this.popup.TextSuccess = "เข้าสู่ระบบเสร็จสิ้น .";
              this.sheet.Success = true;
              
              console.log("เข้าสู่ระบบเสร็จสิ้น .");
            } else {

              this.popup.TextError = "username หรือ password ผิด .";
              this.sheet.Error = true;
              
              console.log("username หรือ password ผิด .");
            }
          })
          .catch(e => {

            this.popup.TextError = "ไม่สามารถเชื่อมต่อฐานข้อมูลได้ เนื่องจาก "+e;
            this.sheet.Error = true;
            console.log("ไม่สามารถเชื่อมต่อฐานข้อมูลได้ เนื่องจาก "+e);
          });
      } else {

        this.popup.TextError = "กรุณากรอก username เเละ password .";
        this.sheet.Error = true;
        console.log("กรุณากรอก username เเละ password .");


      }
    },
    checkLoginEmployee() {

      if (this.LoginEmp.username != "" && this.LoginEmp.password != "") {
        http
          .get(
            "/LoginEmployee/" +
              this.LoginEmp.username +
              "/" +
              this.LoginEmp.password
          )
          .then(response => {
            if (response.data[0] != null) {
              if (response.data[0].position.id == 1 || response.data[0].position.id == 3) {

                this.popup.TextError = "ตำเเหน่งของคุณไม่สามารถเข้าสู่ระบบได้ .";
                this.sheet.Error = true;
                console.log("ตำเเหน่งของคุณไม่สามารถเข้าสู่ระบบได้ .");
              } else {
                this.$session.start();
                this.$session.set("userId", response.data[0].id);
                this.$session.set("username", response.data[0].username);
                this.$session.set("fullname", response.data[0].fullname);
                this.$session.set("positionId", response.data[0].position.id);
                this.$session.set(
                  "position",
                  response.data[0].position.position
                );

                this.popup.TextSuccess = "เข้าสู่ระบบเสร็จสิ้น .";
                this.sheet.Success = true;
              }
            } else {
              this.popup.TextError = "username หรือ password ผิด .";
              this.sheet.Error = true;
              
              console.log("username หรือ password ผิด .");
            }
          })
          .catch(e => {
            this.popup.TextError = "ไม่สามารถเชื่อมต่อฐานข้อมูลได้ เนื่องจาก "+e;
            this.sheet.Error = true;
            console.log("ไม่สามารถเชื่อมต่อฐานข้อมูลได้ เนื่องจาก "+e);
          });
      } else {

        this.popup.TextError = "กรุณากรอก username เเละ password .";
        this.sheet.Error = true;
        console.log("กรุณากรอก username เเละ password .");
      }
    },
    showLogin(x) {
      if (x == "stu") {
        this.LoginEmp.showDialog = false;
        this.LoginStu.showDialog = true;
      } else {
        this.LoginStu.showDialog = false;
        this.LoginEmp.showDialog = true;
      }
    },
    redirectEmployee() {
      this.sheet = false;
      this.$router.push("/Employee");
    },
    redirectStudent() {
      this.sheet = false;
      this.$router.push("/Student");
    }
  },
};
</script>

<style >
.btn-hover {
  background-color: #100e17;
}
.btn-hover:hover {
  background-color: #1a76d2;
}
</style>