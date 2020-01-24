import Vue from "vue";
import Router from "vue-router";

import Home from "../view/Home";
import Employee from "../view/Employee";
import Student from "../view/Student";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      component: Home
    },
    {
      path: "/Employee",
      component: Employee
    },
    {
      path: "/Student",
      component: Student
    }
  ]
});
