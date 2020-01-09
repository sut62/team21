<template>
  <v-app>
    <v-row>
      <v-navigation-drawer app permanent fixed color="#100E17" dark style="height:2000px">
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

          <v-list-item v-if="show.navigationEmp" link @change="showEmp">
            <v-list-item-icon style="width:40px;">
              <v-icon size="24px" style="margin: auto;">fas fa-user-secret</v-icon>
            </v-list-item-icon>
            <Label class style="color: white; ">Employee</Label>
          </v-list-item>

          <v-list-item v-if="show.navigationStu" link @change="showStu">
            <v-list-item-icon style="width:40px;">
              <v-icon size="24px" style="margin: auto;">fas fa-user-graduate</v-icon>
            </v-list-item-icon>
            <Label class style="color: white; ">Student</Label>
          </v-list-item>

          <v-list-item v-if="show.navigationCourse" link @change="showCourse">
            <v-list-item-icon style="width:40px;">
              <v-icon size="22px" style="margin: auto;">fas fa-book-open</v-icon>
            </v-list-item-icon>
            <Label class style="color: white; ">Course</Label>
          </v-list-item>

          <v-list-item v-if="show.navigationEnroll" link @change="showEnroll">
            <v-list-item-icon style="width:40px;">
              <v-icon size="24px" style="margin: auto;">fas fa-book-medical</v-icon>
            </v-list-item-icon>
            <Label class style="color: white; ">EnrollCourse</Label>
          </v-list-item>

          <v-list-item v-if="show.navigationRecord" link @change="showRecord">
            <v-list-item-icon style="width:40px;">
              <v-icon size="24px" style="margin: auto;">fas fa-money-check-alt</v-icon>
            </v-list-item-icon>
            <Label class style="color: white; ">RecordExpense</Label>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
    </v-row>

    <v-app-bar app absolute color="#100E17" dark height="90px">
      <v-btn text class="headline font-weight-light" color="white">{{componentName}}</v-btn>
      <Label class="my-2 display-1" style="color: white; margin-left: 10px;">/</Label>

      <v-spacer></v-spacer>

      <v-btn
        text
        class="headline font-weight-light"
        color="white"
      >NAME : {{this.$session.get('fullname')}}</v-btn>

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
          <v-list-item>
            <v-list-item-title style="color: white;">
              <v-icon
                size="24px"
                style="margin-right: 10px; color: white; width:30px;"
              >fas fa-user-cog</v-icon>SETTINGS PROFILE
            </v-list-item-title>
          </v-list-item>
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

      <!-- <Label class="my-2 display-2" style="color: white; margin-left: 20px;">|</Label>

      <v-btn @click="logOut" text class="headline font-weight-light" color="white">LOGOUT</v-btn>-->
    </v-app-bar>

    <!-- Sizes your content based upon application components -->
    <v-content>
      <v-row v-if="show.emp">
        <AddEmployee style="position:relative;left:7.5%" />
      </v-row>

      <v-row v-if="show.stu">
        <AddStudent style="position:relative;left:7.5%" />
      </v-row>

      <v-row v-if="show.course">
        <AddCourse style="position:relative;left:18.5%" />
      </v-row>

      <v-row v-if="show.enroll">
        <AddEnrollCourse style="position:relative;left:18.5%" />
      </v-row>

      <v-row v-if="show.record">
        <RecordExpense style="position:relative;left:18.5%" />
      </v-row>
    </v-content>
  </v-app>
</template>

<script>
import AddEmployee from "../components/EmployeeManagementSystem/AddEmployee";
import AddStudent from "../components/StudentManagemenSystem/AddStudent";
import AddEnrollCourse from "../components/EnrollCourseManagementSystem/AddEnrollCourse";
import RecordExpense from "../components/RecordExpenseSystem/RecordExpense";
import AddCourse from "../components/CourseManagementSystem/AddCourse";

export default {
  /* eslint-disable no-console */
  components: {
    AddEmployee,
    AddStudent,
    AddCourse,
    AddEnrollCourse,
    RecordExpense
  },
  data() {
    return {
      componentName: "Dashboard",
      show: {
        navigationEmp: false,
        emp: false,
        navigationStu: false,
        stu: false,
        navigationCourse: false,
        course: false,
        navigationEnroll: false,
        enroll: false,
        navigationRecord: false,
        record: false
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
    navigationTutor() {
      this.show.navigationEmp = false;
      this.show.navigationStu = false;
      this.show.navigationCourse = true;
      this.show.navigationEnroll = false;
      this.show.navigationRecord = false;
    },
    navigationManager() {
      this.show.navigationEmp = true;
      this.show.navigationStu = true;
      this.show.navigationCourse = true;
      this.show.navigationEnroll = true;
      this.show.navigationRecord = true;
    },
    showEmp() {
      this.componentName = "Employee";
      this.show.stu = false;
      this.show.course = false;
      this.show.enroll = false;
      this.show.record = false;
      this.show.emp = true;
    },
    showStu() {
      this.componentName = "Student";
      this.show.emp = false;
      this.show.course = false;
      this.show.enroll = false;
      this.show.record = false;
      this.show.stu = true;
    },
    showCourse() {
      this.componentName = "Course";
      this.show.stu = false;
      this.show.enroll = false;
      this.show.record = false;
      this.show.emp = false;
      this.show.course = true;
    },
    showEnroll() {
      this.componentName = "Enroll Course";
      this.show.emp = false;
      this.show.course = false;
      this.show.record = false;
      this.show.stu = false;
      this.show.enroll = true;
    },
    showRecord() {
      this.componentName = "Record Expense";
      this.show.emp = false;
      this.show.course = false;
      this.show.enroll = false;
      this.show.stu = false;
      this.show.record = true;
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