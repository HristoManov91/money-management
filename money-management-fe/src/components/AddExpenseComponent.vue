<template>
  <div class="container">
    <div class="grid">
      <form @submit.prevent="addExpense">
        <label for="category">Category:</label>
        <input id="category" type="text"
               v-model.trim="$v.expense.category.$model"
               @input="expense.category = capitalize($event)"/>
        <label for="subCategory">subCategory:</label>
        <input id="subCategory" type="text"
               v-model.trim="$v.expense.subCategory.$model"
               @input="expense.subCategory = capitalize($event)"/>
        <label for="date">Date:</label>
        <input id="date" v-model.trim="$v.expense.date.$model" type="date"/>
        <label for="date">StoreName:</label>
        <input id="storeName" type="text"
               v-model.trim="$v.expense.storeName.$model"
               @input="expense.storeName = capitalize($event)"/>
        <label for="price">Price:</label>
        <input id="price" type="number" step="0.01" :disabled="!checkProductsIsEmpty"
               v-model.number="$v.expense.price.$model"
               @input="expense.price = setDecimalFormat($event)"/>
        <label for="discount">Discount:</label>
        <input id="discount" type="number" step="0.01" :disabled="!checkProductsIsEmpty"
               v-model.number="$v.expense.discount.$model"
               @input="expense.discount = setDecimalFormat($event)"/>
        <label>Description:</label>
        <textarea v-model.trim="$v.expense.description.$model"
                  @input="expense.description = capitalize($event)"/>
        <button>Add expense</button>
      </form>
      <form @submit.prevent="addProductToExpense">
        <label for="productTypeName">ProductTypeName:</label>
        <input id="productTypeName" type="text"
               v-model.trim="$v.product.productType.name.$model"
               @input="product.productType.name = capitalize($event)"/>
        <label for="productTypeBrand">productTypeBrand:</label>
        <input id="productTypeBrand" type="text"
               v-model.trim="$v.product.productType.brand.$model"
               @input="product.productType.brand = capitalize($event)"/>
        <label for="productTypeProductCategory">productTypeProductCategory:</label>
        <input id="productTypeProductCategory" type="text"
               v-model.trim="$v.product.productType.productCategory.$model"
               @input="product.productType.productCategory = capitalize($event)"/>
        <label for="standardPrice">standardPrice:</label>
        <input id="standardPrice" type="number" step="0.01"
               v-model.number="$v.product.standardPrice.$model"
               @input="product.standardPrice = setDecimalFormat($event)"/>
        <label for="priceDiscount">priceDiscount:</label>
        <input id="priceDiscount" type="number" step="0.01" disabled
               v-model.number="$v.product.priceDiscount.$model"
               @input="product.priceDiscount = setDecimalFormat($event)"/>
        <label for="priceAfterDiscount">priceAfterDiscount:</label>
        <input id="priceAfterDiscount" type="number" step="0.01"
               v-model.number="$v.product.priceAfterDiscount.$model"
               @input="product.priceAfterDiscount = setDecimalFormat($event)"/>
        <label for="quantity">quantity:</label>
        <input id="quantity" type="number" step="0.001"
               v-model.number="$v.product.quantity.$model"
               @input="product.quantity = setDecimalFormat($event)"/>
        <label for="finalPrice">finalPrice:</label>
        <input id="finalPrice" type="number" step="0.01" disabled
               v-model.number="$v.product.finalPrice.$model"/>
        <button>Add product</button>
      </form>
      <div>
        <!-- TODO table -->
        <p>Products in expense</p>
        <li v-for="(prod,index) in expense.products" :key="index">
          {{ prod.productType.name }} {{ prod.productType.brand }} - {{ prod.priceAfterDiscount }} - {{ prod.quantity }}
          - {{ prod.finalPrice }};
        </li>
      </div>
    </div>
  </div>
</template>

<script src="../scripts/add-expense-component.js"></script>

<style scoped>
@import url("../assets/css/add-expense-component.css");

</style>