<template>
  <v-app>
    <v-app-bar app >
      <v-toolbar-title class="headline text-uppercase">
        <span>System</span>
        <span class="font-weight-light">SE-G21</span>
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
        <span class="mr-2">CourseRegistration</span>
      </v-btn>
      <v-btn @click="Expenses">
        <span class="mr-2">ExpensesManagement</span>
      </v-btn>
      <v-btn @click="Reviews">
        <span class="mr-2">Reviews</span>
      </v-btn>
    </v-app-bar>

    <v-content>
      <router-view />
    </v-content>

  </v-app>
</template>
<script>
export default {
  data: () => ({
    icons: [
      "fab fa-facebook",
      "fab fa-twitter",
      "fab fa-google-plus",
      "fab fa-linkedin",
      "fab fa-instagram"
    ]
  }),
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


