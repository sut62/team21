<template>
  <v-app>
    <v-app-bar app class>
      <v-toolbar-title class="headline text-uppercase">
        <span class="display-2 font-weight-black">THE Tutor &nbsp;&nbsp;</span>
        <span class="font-weight-light body-2">Tutoring institute</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn @click="Home">
        <span class="mr-2">HOME</span>
      </v-btn>
      <v-btn @click="Employee">
        <span class="mr-2">Employee</span>
      </v-btn>
      <v-btn @click="Student">
        <span class="mr-2">Student</span>
      </v-btn>
      <v-btn @click="Course">
        <span class="mr-2">Course</span>
      </v-btn>
      <v-btn @click="CourseRegistration">
        <span class="mr-2">Course Register</span>
      </v-btn>
      <v-btn @click="Expenses">
        <span class="mr-2">Expenses</span>
      </v-btn>
      <v-btn @click="Reviews">
        <span class="mr-2">Reviews</span>
      </v-btn>
    </v-app-bar>
        <v-content class="">
          <router-view />
        </v-content>
  </v-app>
</template>
<script>
export default {
  data() {
    return {
      items: [
        { title: "Dashboard", icon: "mdi-view-dashboard" },
        { title: "Photos", icon: "mdi-image" },
        { title: "About", icon: "mdi-help-box" }
      ],
      right: null
    };
  },
  methods: {
    Home() {
      this.$router.push("/");
    },
    Employee() {
      this.$router.push("/AddEmployee");
    },
    Student() {
      this.$router.push("/AddStudent");
    },
    Course() {
      this.$router.push("/AddCourse");
    },
    CourseRegistration() {
      this.$router.push("/CourseRegister");
    },
    Expenses() {
      this.$router.push("/StudentExpenses");
    },
    Reviews() {
      this.$router.push("/AddReviews");
    }
  }
};
</script>


