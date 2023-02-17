import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: "/",
        name: 'Home',
        component: () => import(/* webpackChunkName: "home" */ "@/components/HomeComponent"),
        children: [
            {
                path: "users/login",
                name: 'Login',
                component: () => import(/* webpackChunkName: "login" */ "@/components/UserLoginComponent")
            },
            {
                path: "users/register",
                name: 'Register',
                component: () => import(/* webpackChunkName: "register" */ "@/components/UserRegisterComponent")
            },
        ]
    },
    {
        path: "/expense/add",
        name: "AddExpense",
        component: () => import(/* webpackChunkName: "details" */ "@/components/AddExpenseComponent")
    },
    {path: "*", redirect: '/'} //ToDo error modal or redirect
]

const router = new VueRouter({
    routes,
    mode: 'history'
})

// TODO
router.beforeEach((to, from, next) => {
    const publicPages = ['/users/login', '/users/register', '/'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    if (authRequired && !loggedIn) {
        next('/login');
    } else {
        next();
    }
});

export default router;