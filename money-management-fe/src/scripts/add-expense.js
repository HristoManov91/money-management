import {ExpenseService} from "@/services/expense";
import {maxLength, minLength, maxValue, minValue, required} from "vuelidate/lib/validators";

export default {
    name: "AddExpenseComponent",
    data() {
        return {
            expenseService: new ExpenseService(),
            expense: {
                category: null,
                subCategory: null,
                date: null,
                products: [],
                price: null,
                discount: null,
                description: null,
                storeName: null
            },
            product: {
                productType: {
                    name: null,
                    brand: null,
                    productCategory: null
                },
                standardPrice: null,
                priceDiscount: null,
                priceAfterDiscount: null,
                quantity: null,
                finalPrice: null
            },
        }
    },
    validations: {
        expense: {
            category: {
                required,
                minLength: minLength(2),
                maxLength: maxLength(50)
            },
            subCategory: {
                minLength: minLength(2),
                maxLength: maxLength(50)
            },
            storeName: {
                minLength: minLength(2),
                maxLength: maxLength(50)
            },
            date: {
                required,
                //TODO past or present validation
            },
            price: {
                required,
                minValue: minValue (0.01),
                maxValue: maxValue (1000000)
            },
            discount: {
                minValue: minValue (0),
                maxValue: maxValue (1000000)
            },
            description: {
                maxLength: maxLength(100)
            },
            products: {
                maxLength: maxLength(50)
            }
        },
        product: {
            productType: {
                name: {
                    required,
                    minLength: minLength(2),
                    maxLength: maxLength(50)
                },
                brand: {
                    maxLength: maxLength(50)
                },
                productCategory: {
                    required,
                    minLength: minLength(2),
                    maxLength: maxLength(50)
                }
            },
            standardPrice: {
                required,
                minValue: minValue(0.01),
                maxValue: maxValue(1000000)
            },
            priceDiscount: {
                minValue: minValue (0),
                maxValue: maxValue (1000000)
            },
            priceAfterDiscount: {
                required,
                minValue: minValue(0.01),
                maxValue: maxValue(1000000)
            },
            quantity: {
                required,
                minValue: minValue(0.001),
                maxValue: maxValue(1000000)
            },
            finalPrice: {
                required,
                minValue: minValue(0.01),
                maxValue: maxValue(1000000)
            }
        }
    },
    methods: {
        addExpense() {
            this.$v.expense.$touch();
            if (this.$v.expense.$invalid) {
                // TODO not valid
                alert("Error expense")
                console.log('product',this.$v.product)
            } else {
                this.expenseService.addExpense(this.expense).then((response) => {
                    if (response.status === 'OK') {
                        this.clearExpense();
                        // TODO
                        alert("Success add expense")
                    } else {
                        // TODO
                        alert("BAD REQUEST")
                    }
                })
            }
        },
        addProductToExpense() {
            this.$v.product.$touch();
            if (this.$v.product.$invalid) {
                // TODO not valid

                alert("Error product")
            } else {
                let copy = JSON.parse(JSON.stringify(this.product))
                this.expense.products.push(copy)
                this.productClearFields();
            }
        },
        productClearFields() {
            this.product.productType.brand = null;
            this.product.productType.productCategory = null;
            this.product.productType.name = null;
            this.product.priceDiscount = null;
            this.product.priceAfterDiscount = null;
            this.product.quantity = null;
            this.product.standardPrice = null;
            this.product.finalPrice = null;
            this.$v.product.$reset();
        },
        clearExpense() {
            this.expense.category = null;
            this.expense.subCategory = null;
            this.expense.date = null;
            this.expense.products = [];
            this.expense.discount = null;
            this.expense.price = null;
            this.expense.description = null;
            this.expense.storeName = null;
            this.$v.expense.$reset();
        },
        productSetFinalPrice() {
            if (this.product.priceAfterDiscount !== null && this.product.priceAfterDiscount !== ''
                && this.product.quantity !== null && this.product.quantity !== '') {
                this.product.finalPrice = Number((this.product.priceAfterDiscount * this.product.quantity).toFixed(2));
            }
        },
        productSetDiscount() {
            if (this.product.standardPrice !== null && this.product.standardPrice !== ''
                && this.product.priceAfterDiscount !== null && this.product.priceDiscount !== '') {
                this.product.priceDiscount = Number((this.product.standardPrice - this.product.priceAfterDiscount).toFixed(2));
            }
        },
        setDecimalFormat(event) {
            if (event.target.id === 'quantity') {
                return Number(Number(event.target.value).toFixed(3));
            }
            return Number(Number(event.target.value).toFixed(2));
        },
        capitalize(event) {
            return event.target.value.toUpperCase();
        }
    },
    computed: {
        checkProductsIsEmpty() {
            return this.expense.products.length === 0;
        }
    },
    watch: {
        'product.quantity'() {
            this.productSetFinalPrice();
        },
        'product.priceAfterDiscount'() {
            this.productSetDiscount();
            this.productSetFinalPrice();
        },
        'product.standardPrice'() {
            this.productSetDiscount();
        },
        'expense.products'() {
            if (this.expense.products.length > 0) {
                let sum = 0;
                let discount = 0;
                for (const product of this.expense.products) {
                    sum += product.finalPrice;
                    discount += product.priceDiscount * product.quantity;
                }
                this.expense.price = Number(sum.toFixed(2));
                this.expense.discount = Number(discount.toFixed(2));
            } else {
                return null;
            }
        }
    }
}