<template>
  <v-app>
    <v-app-bar color="#100E17" dark height="90px">
      <!-- <v-app-bar-nav-icon> </v-app-bar-nav-icon> -->
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

      <!-- <div class="my-2">
        <v-btn text class="headline font-weight-light" color="white">SING UP</v-btn>
      </div>-->
      <div class="my-2 display-2">
        <Label style="color: white">|</Label>
      </div>
      <div class="my-2">
        <v-btn
          @click.stop="LoginStu.showDialog = true"
          text
          class="btn-hover headline font-weight-light"
          color="white"
        >LOGIN</v-btn>
      </div>
    </v-app-bar>

    <v-content>
      <template>
        <v-carousel cycle show-arrows-on-hover hide-delimiters height="400" style>
          <!-- <v-carousel-item v-for="(item,i) in items" :key="i" :src='item.src' ></v-carousel-item> -->
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
                  <v-icon size="24px" @click="LoginStu.showDialog = false">fas fa-times</v-icon>
                </v-btn>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-spacer></v-spacer>
                    <v-col cols="8">
                      <v-text-field
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
                  <v-icon size="24px" @click="LoginEmp.showDialog = false">fas fa-times</v-icon>
                </v-btn>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-spacer></v-spacer>
                    <v-col cols="8">
                      <v-text-field
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
                v-if="LoginEmp.showDialog"
                class="mt-8"
                color="info"
                @click="redirectEmployee"
              >OK !</v-btn>
              <v-btn
                v-if="LoginStu.showDialog"
                class="mt-8"
                color="info"
                @click="redirectStudent"
              >OK !</v-btn>
              <div class="py-3">เข้าสู่ระบบเสร็จสิ้น .</div>
            </v-sheet>
          </v-bottom-sheet>
        </div>
      </template>

      <!-- Bottom sheets  Error-->
      <template>
        <div class="text-center">
          <v-bottom-sheet v-model="sheet.Error" persistent>
            <v-sheet class="text-center" height="200px">
              <v-btn class="mt-8" color="error" @click="sheet.Error = false">CLOSE</v-btn>
              <div class="py-3">username หรือ password ผิด .</div>
            </v-sheet>
          </v-bottom-sheet>
        </div>
      </template>
    </v-content>

    <v-footer class="flex" flat tile style="background-color: #100E17; ">
      <v-card
        flat
        tile
        class="lighten-1 white--text text-center"
        style="background-color: #100E17; margin: auto;"
      >
        <v-card-text style="color: white;">
          <v-btn v-for="icon in icons" :key="icon" class="mx-4 white--text" icon>
            <v-icon size="24px">{{ icon }}</v-icon>
          </v-btn>
        </v-card-text>

        <!-- <v-divider></v-divider> -->

        <v-card-text class="white--text" style="color: white;">
          <strong>Copyright © 2020 | SUT-SE-TEAM21</strong>
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
      // if (this.LoginStu.username == "") {
      //   this.LoginStu.errorUsername = true;
      // }
      // if (this.LoginStu.password == "") {
      //   this.LoginStu.errorPassword = true;
      // }

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

              this.sheet.Success = true;
            } else {
              this.sheet.Error = true;
              console.log("login ไม่สำเร็จ");
            }
          })
          .catch(e => {
            console.log(e);
          });
      } else {
        console.log("login ไม่สำเร็จ");
        this.sheet.Error = true;
      }
    },
    checkLoginEmployee() {
      // if (this.LoginEmp.username == "") {
      //   this.LoginEmp.errorUsername = true;
      // }
      // if (this.LoginEmp.password == "") {
      //   this.LoginEmp.errorPassword = true;
      // }

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
              if (response.data[0].position.id == 1) {
                console.log("position นี้เข้าสู่ระบบไม่ได้");
                this.sheet.Error = true;
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

                this.sheet.Success = true;
              }
            } else {
              this.sheet.Error = true;
            }
          })
          .catch(e => {
            console.log(e);
            this.sheet.Error = true;
          });
      } else {
        console.log("login ไม่สำเร็จ");

        this.sheet.Error = true;
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
    },
    checkKeybord() {
      if (this.LoginStu.username.length > 0) {
        this.LoginStu.errorUsername = false;
      }
      if (this.LoginStu.password.length > 0) {
        this.LoginStu.errorPassword = false;
      }
      if (this.LoginEmp.username.length > 0) {
        this.LoginEmp.errorUsername = false;
      }
      if (this.LoginEmp.password.length > 0) {
        this.LoginEmp.errorPassword = false;
      }
    }
  },
  created() {
    // setInterval(() => this.checkKeybord());
  }
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