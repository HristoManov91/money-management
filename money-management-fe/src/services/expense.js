import axios from "axios";

const EXPENSE_CONTROLLER_BASE_URL = 'http://localhost:8081/expenses/'

export class ExpenseService {

    async addExpense(expenseDto) {
        console.log(expenseDto)
        let result = {};

        await axios.post(EXPENSE_CONTROLLER_BASE_URL , expenseDto).then((response) => {
            result.status = 'OK';
            result.data = response.data;
        }).catch((err) => {
            console.log(err)
            result.status = 'ERROR';
            result.error = err.response.data;
        })

        return result;
    }
}