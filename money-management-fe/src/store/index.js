import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios";
import {authenticate} from './auth-module'

Vue.use(Vuex)

export default new Vuex.Store ({
    modules: {
        authenticate
    },
    state: {
        expenseCategories: [],
        expenseSubCategories: [],
        //TODO products
    },
    getters: {
        getExpenseCategories(state) {
            return state.expenseCategories
        },
        getExpenseSubCategories(state) {
            return state.expenseSubCategories;
        }
    },
    mutations: {
        setExpenseCategories(state , payload){
            state.expenseCategories = payload;
        },
        setExpenseSubCategories(state, payload){
            state.expenseSubCategories = payload;
        }
    },
    actions: {
        async getExpenseCategories(context){
            //TODO
            const axiosResponse = await axios.get('http://localhost:8081/genres');
            context.commit('setExpenseCategories' , axiosResponse.data)
        },
        async getExpenseSubCategories(context){
            //TODO
            const axiosResponse = await axios.get('http://localhost:8081/platforms');
            context.commit('setExpenseSubCategories' , axiosResponse.data)
        }
    }
})