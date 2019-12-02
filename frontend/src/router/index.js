import Vue from 'vue'
import Router from 'vue-router';

// import Home from '../components/HomePages/Home';
import CourseRegister from '../components/CourseRegistrationManagementSystem/CourseRegister';

Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
            path: '/',
            component: CourseRegister
        },

    ]
});