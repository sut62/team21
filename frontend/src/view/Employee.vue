<template>
  <v-app>
    <v-navigation-drawer
      app
      permanent
      fixed
      color="#100E17"
      dark
      height="2000"
      width="265"
    >
      <v-list dense nav>
        <v-list-item-content>
          <v-list-item class="center" style="height: 100px;background: #100E17;">
            <img src="../assets/logo2.png" style="height: 80px; margin: auto;" />
          </v-list-item>
          <v-list-item-title class="title text-center">
            <Label text class="text-center title font-weight-light" color="white">POSITION</Label>
          </v-list-item-title>
          <v-list-item-subtitle class="text-center">
            <Label
              text
              class="title font-weight-light"
              color="white"
            >{{this.$session.get('position')}}</Label>
          </v-list-item-subtitle>
        </v-list-item-content>

        <v-divider></v-divider>

        <!-- Page Add information -->

        <v-list-item-title
          class="title"
          style="margin-top: 30px; margin-boottom: 10px;margin-left:20px;"
        >
          <Label text class="title font-weight-light" color="white">Page Add information</Label>
        </v-list-item-title>

        <v-list-item v-if="show.navigationEmp" id="emp00" link @change="showEmp">
          <v-list-item-icon style="width:20px;">
            <v-icon size="24px" style="margin: auto;">fas fa-user-secret</v-icon>
          </v-list-item-icon>
          <Label class style="color: white; ">AddEmployee</Label>
        </v-list-item>

        <v-list-item v-if="show.navigationStu" id="stu00" link @change="showStu">
          <v-list-item-icon style="width:20px;">
            <v-icon size="24px" style="margin: auto;">fas fa-user-graduate</v-icon>
          </v-list-item-icon>
          <Label class style="color: white; ">AddStudent</Label>
        </v-list-item>

        <v-list-item v-if="show.navigationCourse" id="css00" link @change="showCourse">
          <v-list-item-icon style="width:20px;">
            <v-icon size="22px" style="margin: auto;">fas fa-book-open</v-icon>
          </v-list-item-icon>
          <Label class style="color: white; ">AddCourse</Label>
        </v-list-item>

        <v-list-item v-if="show.navigationEnroll" id="enr00" link @change="showEnroll">
          <v-list-item-icon style="width:20px;">
            <v-icon size="24px" style="margin: auto;">fas fa-book-medical</v-icon>
          </v-list-item-icon>
          <Label class style="color: white; ">AddEnrollCourse</Label>
        </v-list-item>

        <v-list-item v-if="show.navigationRecord" id="rec00" link @change="showRecord">
          <v-list-item-icon style="width:20px;">
            <v-icon size="24px" style="margin: auto;">fas fa-money-check-alt</v-icon>
          </v-list-item-icon>
          <Label class style="color: white; ">AddRecordExpense</Label>
        </v-list-item>

        <!-- Page View information -->

        <v-divider style="margin-top: 30px; margin-boottom: 10px;"></v-divider>

        <v-list-item-title
          class="title"
          style="margin-top: 30px; margin-boottom: 10px;margin-left:20px;"
        >
          <Label text class="title font-weight-light" color="white">Page View information</Label>
        </v-list-item-title>

        <v-list-item v-if="show.navigationEmp" id="viemp00" link @change="showVEmp">
          <v-list-item-icon style="width:20px;">
            <v-icon size="24px" style="margin: auto;">fas fa-user-secret</v-icon>
          </v-list-item-icon>
          <Label class style="color: white; ">ViewEmployee</Label>
        </v-list-item>

        <v-list-item v-if="show.navigationStu" id="vistu00" link @change="showVStu">
          <v-list-item-icon style="width:20px;">
            <v-icon size="24px" style="margin: auto;">fas fa-user-graduate</v-icon>
          </v-list-item-icon>
          <Label class style="color: white; ">ViewStudent</Label>
        </v-list-item>

        <v-list-item v-if="show.navigationCourse" id="vicss00" link @change="showVCourse">
          <v-list-item-icon style="width:20px;">
            <v-icon size="22px" style="margin: auto;">fas fa-book-open</v-icon>
          </v-list-item-icon>
          <Label class style="color: white; ">ViewCourse</Label>
        </v-list-item>

        <v-list-item v-if="show.navigationEnroll" id="vienr00" link @change="showVEnroll">
          <v-list-item-icon style="width:20px;">
            <v-icon size="24px" style="margin: auto;">fas fa-book-medical</v-icon>
          </v-list-item-icon>
          <Label class style="color: white; ">ViewEnrollCourse</Label>
        </v-list-item>

        <v-list-item v-if="show.navigationRecord" id="virec00" link @change="showVRecord">
          <v-list-item-icon style="width:20px;">
            <v-icon size="24px" style="margin: auto;">fas fa-money-check-alt</v-icon>
          </v-list-item-icon>
          <Label class style="color: white; ">ViewRecordExpense</Label>
        </v-list-item>

        <v-list-item v-if="show.navigationReviews" id="vireviews00" link @change="showVReviews">
          <v-list-item-icon style="width:20px;">
            <v-icon size="24px" style="margin: auto;">mdi-message-text</v-icon>
          </v-list-item-icon>
          <Label class style="color: white; ">ViewReviews</Label>
        </v-list-item>


      </v-list>
    </v-navigation-drawer>

    <v-app-bar app absolute color="#100E17" dark height="90px">
      <v-btn text class="headline font-weight-light" color="white">{{componentName}}</v-btn>
      <Label class="my-2 display-1" style="color: white; margin-left: 10px;">/</Label>

      <v-spacer></v-spacer>

      <v-btn
        text
        class="headline font-weight-light"
        color="white"
      >ชื่อ : {{this.$session.get('fullname')}}</v-btn>

      <div class="my-2 display-2" style="margin-right: 20px;">
        <Label style="color: white ">|</Label>
      </div>

      <v-menu transition="slide-x-transition" bottom right>
        <template v-slot:activator="{ on }">
          <v-btn class="deep-orange headline" color="primary" dark v-on="on">
            <v-icon size="24px" style="margin-right: 10px; color: white; width:30px;">fas fa-bars</v-icon>Menu
          </v-btn>
        </template>

        <v-list style="background-color: #1A76D2;">
          <v-list-item @click="logOut">
            <v-list-item-title style="color: white;">
              <v-icon
                size="24px"
                style="margin-right: 10px; color: white; width:30px;"
              >fas fa-door-open</v-icon>LOGOUT
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>

    
    <v-content style="margin: 0px;padding: 0px;padding-top: 120px;">

      <!-- Add data components -->
      <v-row v-if="show.emp">
        <v-col cols="2" style="margin: 0px;padding: 0px;"></v-col>
        <v-col
          cols="10"
          style="display: flex;align-items: center;justify-content: center;"
        >
          <AddEmployee style="margin: auto;" />
        </v-col>
      </v-row>

      <v-row v-if="show.stu">
        <v-col cols="2" style="margin: 0px;padding: 0px;"></v-col>
        <v-col
          cols="10"
          style="display: flex;align-items: center;justify-content: center;"
        >
          <AddStudent style="margin: auto;" />
        </v-col>
      </v-row>

      <v-row v-if="show.course">
        <v-col cols="2" style="margin: 0px;padding: 0px;"></v-col>
        <v-col
          cols="10"
          style="display: flex;align-items: center;justify-content: center;"
        >
          <AddCourse style="margin: auto;" />
        </v-col>
      </v-row>

      <v-row v-if="show.enroll">
        <v-col cols="2" style="margin: 0px;padding: 0px;"></v-col>
        <v-col
          cols="10"
          style="display: flex;align-items: center;justify-content: center;"
        >
          <AddEnrollCourse style="margin: auto;" />
        </v-col>
      </v-row>

      <v-row v-if="show.record">
        <v-col cols="2" style="margin: 0px;padding: 0px;"></v-col>
        <v-col
          cols="10"
          style="display: flex;align-items: center;justify-content: center;"
        >
          <AddRecordExpense style="margin: auto;" />
        </v-col>
      </v-row>

      <!-- View data components -->

      <v-row v-if="show.viemp">
        <v-col cols="2" style="margin: 0px;padding: 0px;"></v-col>
        <v-col
          cols="10"
          style="display: flex;align-items: center;justify-content: center;"
        >
          <ViewEmployee style="margin: auto;" />
        </v-col>
      </v-row>

      <v-row v-if="show.vistu">
        <v-col cols="2" style="margin: 0px;padding: 0px;"></v-col>
        <v-col
          cols="10"
          style="display: flex;align-items: center;justify-content: center;"
        >
          <ViewStudent style="margin: auto;" />
        </v-col>
      </v-row>

      <v-row v-if="show.vicourse">
        <v-col cols="2" style="margin: 0px;padding: 0px;"></v-col>
        <v-col
          cols="10"
          style="display: flex;align-items: center;justify-content: center;"
        >
          <ViewCourse style="margin: auto;" />
        </v-col>
      </v-row>

      <v-row v-if="show.vienroll">
        <v-col cols="2" style="margin: 0px;padding: 0px;"></v-col>
        <v-col
          cols="10"
          style="display: flex;align-items: center;justify-content: center;"
        >
          <ViewEnrollCourse style="margin: auto;" />
        </v-col>
      </v-row>

      <v-row v-if="show.virecord">
        <v-col cols="2" style="margin: 0px;padding: 0px;"></v-col>
        <v-col
          cols="10"
          style="display: flex;align-items: center;justify-content: center;"
        >
          <ViewRecordExpense style="margin: auto;" />
        </v-col>
      </v-row>

      <v-row v-if="show.vireviews">
        <v-col cols="2" style="margin: 0px;padding: 0px;"></v-col>
        <v-col
          cols="10"
          style="display: flex;align-items: center;justify-content: center;"
        >
          <ViewReviews style="margin: auto;" />
        </v-col>
      </v-row>



    </v-content>
  </v-app>
</template>

<script>
/* eslint-disable no-console */
import AddEmployee from "../components/EmployeeManagementSystem/AddEmployee";
import AddStudent from "../components/StudentManagemenSystem/AddStudent";
import AddEnrollCourse from "../components/EnrollCourseManagementSystem/AddEnrollCourse";
import AddRecordExpense from "../components/RecordExpenseSystem/RecordExpense";
import AddCourse from "../components/CourseManagementSystem/AddCourse";

import ViewEmployee from "../components/EmployeeManagementSystem/ViewEmployee";
import ViewStudent from "../components/StudentManagemenSystem/ViewStudent";
import ViewCourse from "../components/CourseManagementSystem/ViewCourse";
import ViewEnrollCourse from "../components/EnrollCourseManagementSystem/ViewEnrollCourse";
import ViewRecordExpense from "../components/RecordExpenseSystem/ViewRecordExpense";
import ViewReviews from "../components/ReviewsManagementSystem/ViewReviews";

export default {
  /* eslint-disable no-console */
  components: {
    AddEmployee,
    AddStudent,
    AddCourse,
    AddEnrollCourse,
    AddRecordExpense,
    ViewEmployee,
    ViewStudent,
    ViewCourse,
    ViewEnrollCourse,
    ViewRecordExpense,
    ViewReviews,
  },
  data() {
    return {
      componentName: "Dashboard",
      show: {
        navigationEmp: false,
        emp: false,
        viemp: false,
        navigationStu: false,
        stu: false,
        vistu: false,
        navigationCourse: false,
        course: false,
        vicourse: false,
        navigationEnroll: false,
        enroll: false,
        vienroll: false,
        navigationRecord: false,
        virecord: false,
        record: false,
        navigationReviews: false,
        vireviews: false
      }
    };
  },
  methods: {
    /* eslint-disable no-console */
    redirectHome() {
      this.$router.push("/");
    },
    showNavigation(x) {
      if (x == 2) {
        this.navigationCounterStaff();
      } else if (x == 3) {
        this.navigationTutor();
      } else if (x == 4) {
        this.navigationManager();
      }
    },
    navigationCounterStaff() {
      this.show.navigationEmp = false;
      this.show.navigationStu = true;
      this.show.navigationCourse = false;
      this.show.navigationEnroll = true;
      this.show.navigationRecord = false;
    },
    navigationManager() {
      this.show.navigationEmp = true;
      this.show.navigationStu = false;
      this.show.navigationCourse = true;
      this.show.navigationEnroll = false;
      this.show.navigationRecord = true;
    },
    showEmp() {
      this.componentName = "Add Employee";
      this.show.stu = false;
      this.show.course = false;
      this.show.enroll = false;
      this.show.record = false;
      this.show.emp = true;

      this.show.viemp = false;
      this.show.vicourse = false;
      this.show.vienroll = false;
      this.show.vistu = false;
      this.show.virecord = false;
      this.show.vireviews = false;
    },
    showVEmp() {
      this.componentName = "View Employee";
      this.show.stu = false;
      this.show.course = false;
      this.show.enroll = false;
      this.show.record = false;
      this.show.emp = false;

      this.show.vistu = false;
      this.show.vicourse = false;
      this.show.vienroll = false;
      this.show.virecord = false;
      this.show.viemp = true;
      this.show.vireviews = false;
    },
    showStu() {
      this.componentName = "Add Student";
      this.show.emp = false;
      this.show.course = false;
      this.show.enroll = false;
      this.show.record = false;
      this.show.stu = true;

      this.show.viemp = false;
      this.show.vicourse = false;
      this.show.vienroll = false;
      this.show.vistu = false;
      this.show.virecord = false;
      this.show.vireviews = false;
    },
    showVStu() {
      this.componentName = "View Student";
      this.show.emp = false;
      this.show.course = false;
      this.show.enroll = false;
      this.show.record = false;
      this.show.stu = false;

      this.show.viemp = false;
      this.show.vicourse = false;
      this.show.vienroll = false;
      this.show.virecord = false;
      this.show.vistu = true;
      this.show.vireviews = false;
    },
    showCourse() {
      this.componentName = "Add Course";
      this.show.stu = false;
      this.show.enroll = false;
      this.show.record = false;
      this.show.emp = false;
      this.show.course = true;

      this.show.viemp = false;
      this.show.vicourse = false;
      this.show.vienroll = false;
      this.show.vistu = false;
      this.show.virecord = false;
      this.show.vireviews = false;
    },
    showVCourse() {
      this.componentName = "View Course";
      this.show.stu = false;
      this.show.enroll = false;
      this.show.record = false;
      this.show.emp = false;
      this.show.course = false;

      this.show.vistu = false;
      this.show.vienroll = false;
      this.show.virecord = false;
      this.show.viemp = false;
      this.show.vicourse = true;
    },
    showEnroll() {
      this.componentName = "Add Enroll Course";
      this.show.emp = false;
      this.show.course = false;
      this.show.record = false;
      this.show.stu = false;
      this.show.enroll = true;

      this.show.viemp = false;
      this.show.vicourse = false;
      this.show.vienroll = false;
      this.show.vistu = false;
      this.show.virecord = false;
      this.show.vireviews = false;
    },
    showVEnroll() {
      this.componentName = "View Enroll Course";
      this.show.emp = false;
      this.show.course = false;
      this.show.record = false;
      this.show.stu = false;
      this.show.enroll = false;

      this.show.viemp = false;
      this.show.vicourse = false;
      this.show.vienroll = true;
      this.show.vistu = false;
      this.show.virecord = false;
      this.show.vireviews = false;
    },
    showRecord() {
      this.componentName = "Add Record Expense";
      this.show.emp = false;
      this.show.course = false;
      this.show.enroll = false;
      this.show.stu = false;
      this.show.record = true;
      this.show.vireviews = false;

      this.show.viemp = false;
      this.show.vicourse = false;
      this.show.vienroll = false;
      this.show.vistu = false;
      this.show.virecord = false;
      this.show.vireviews = false;
    },
    showVRecord() {
      this.componentName = "View Record Expense";
      this.show.emp = false;
      this.show.course = false;
      this.show.enroll = false;
      this.show.stu = false;
      this.show.record = false;

      this.show.viemp = false;
      this.show.vicourse = false;
      this.show.vienroll = false;
      this.show.vistu = false;
      this.show.virecord = true;
      this.show.vireviews = false;
    },
    showVReviews(){
      this.componentName = "View Reviews";
      this.show.emp = false;
      this.show.course = false;
      this.show.enroll = false;
      this.show.stu = false;
      this.show.record = false;

      this.show.viemp = false;
      this.show.vicourse = false;
      this.show.vienroll = false;
      this.show.vistu = false;
      this.show.virecord = false;
      this.show.vireviews = true;
      
    },
    logOut() {
      this.$session.destroy();
      this.$router.push("/");
    }
  },
  beforeCreate() {
    if (!this.$session.exists()) {
      this.$router.push("/");
    }
  },
  mounted() {
    this.showNavigation(this.$session.get("positionId"));
    this.show.emp = true;
  }
};
</script>

<style >
.v-list-item {
  margin: 5px;
  border-radius: 4px;
}
.v-list-item:hover {
  background: #1a76d2;
}
.v-list-item:active {
  background: #1a76d2;
}
</style>