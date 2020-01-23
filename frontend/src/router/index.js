import Vue from "vue";
import Router from "vue-router";

import Home from "../view/Home";
import Employee from "../view/Employee";
import Student from "../view/Student";
// import AddEmployee from '../components/EmployeeManagementSystem/AddEmployee';
// import AddStudent from '../components/StudentManagemenSystem/AddStudent';
// import AddCourse from '../components/CourseManagementSystem/AddCourse';
// import AddEnrollCourse from '../components/EnrollCourseManagementSystem/AddEnrollCourse';
// import RecordExpense from '../components/RecordExpenseSystem/RecordExpense';
// import AddReviews from '../components/ReviewsManagementSystem/AddReviews';

import ViewEmployee from "../components/EmployeeManagementSystem/ViewEmployee";
import ViewStudent from "../components/StudentManagemenSystem/ViewStudent";
import ViewCourse from "../components/CourseManagementSystem/ViewCourse";
import ViewEnrollCourse from "../components/EnrollCourseManagementSystem/ViewEnrollCourse";
import ViewRecordExpense from "../components/RecordExpenseSystem/ViewRecordExpense";
import ViewReviews from "../components/ReviewsManagementSystem/ViewReviews";

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
    },
    {
      path: "/ViewEmployee",
      component: ViewEmployee
    },
    {
      path: "/ViewStudent",
      component: ViewStudent
    },
    {
      path: "/ViewCourse",
      component: ViewCourse
    },
    {
      path: "/ViewEnrollCourse",
      component: ViewEnrollCourse
    },
    {
      path: "/ViewRecordExpense",
      component: ViewRecordExpense
    },
    {
      path: "/ViewReviews",
      component: ViewReviews
    }
  ]
});


// {
//     path: '/AddEmployee',
//     component: AddEmployee
// },
// {
//     path: '/AddStudent',
//     component: AddStudent
// },
// {
//     path: '/AddCourse',
//     component: AddCourse
// },
// {
//     path: '/AddEnrollCourse',
//     component: AddEnrollCourse
// },
// {
//     path: '/RecordExpense',
//     component: RecordExpense
// },
// {
//     path: '/AddReviews',
//     component: AddReviews
// },
