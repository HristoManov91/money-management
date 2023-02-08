import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: "/expense/add",
        name: "AddExpense",
        component: () => import(/* webpackChunkName: "details" */ "@/components/AddExpenseComponent")
    }
]

const router = new VueRouter({
    routes,
    mode: 'history'
})

// TODO
// router.beforeEach((to, from, next) => {
//
// });

export default router;