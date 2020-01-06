import Vue from 'vue'
import Router from 'vue-router';

import Home from '../components/HomePages/Home';
import AddEmployee from '../components/EmployeeManagementSystem/AddEmployee';
import AddStudent from '../components/StudentManagemenSystem/AddStudent';
import AddCourse from '../components/CourseManagementSystem/AddCourse';
import AddEnrollCourse from '../components/EnrollCourseManagementSystem/AddEnrollCourse';  
import StudentExpenses from '../components/ExpensesManagementSystem/StudentExpenses';
import AddReviews from '../components/ReviewsManagementSystem/AddReviews';

Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
            path: '/',
            component: Home
        },
        {
            path: '/AddEmployee',
            component: AddEmployee
        },
        {
            path: '/AddStudent',
            component: AddStudent
        },
        {
            path: '/AddCourse',
            component: AddCourse
        },
        {
            path: '/AddEnrollCourse',
            component: AddEnrollCourse
        },
        {
            path: '/StudentExpenses',
            component: StudentExpenses
        },
        {
            path: '/AddReviews',
            component: AddReviews
        },

    ]
});