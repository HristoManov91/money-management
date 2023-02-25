<template>
  <div class="container">
    <div class="grid">
      <form @submit.prevent="addExpense">
        <label for="category">Category:</label>
        <input id="category" type="text"
               :class="{invalidFiled: $v.expense.category.$error}"
               v-model.trim="$v.expense.category.$model"
               @input="expense.category = capitalize($event)"/>
        <span v-if="$v.expense.category.$dirty && !$v.expense.category.required" class="errorMessage">
            Полето е задължително!
          </span>
        <span v-else-if="!$v.expense.category.minLength || !$v.expense.category.maxLength" class="errorMessage">
            Дължината на полето трябва да е между 2 и 50 символа!
          </span>
        <label for="subCategory">subCategory:</label>
        <input id="subCategory" type="text"
               :class="{invalidFiled: $v.expense.subCategory.$error}"
               v-model.trim="$v.expense.subCategory.$model"
               @input="expense.subCategory = capitalize($event)"/>
        <span v-if="!$v.expense.subCategory.minLength || !$v.expense.subCategory.maxLength" class="errorMessage">
            Дължината на полето трябва да е между 2 и 50 символа!
          </span>
        <label for="date">Date:</label>
        <input id="date"
               :class="{invalidFiled: $v.expense.date.$error}"
               v-model.trim="$v.expense.date.$model" type="date"/>
        <span v-if="$v.expense.date.$dirty && !$v.expense.date.required" class="errorMessage">
            Полето е задължително!
          </span>
        <!-- TODO span for past or present validation -->
        <label for="date">StoreName:</label>
        <input id="storeName" type="text"
               :class="{invalidFiled: $v.expense.storeName.$error}"
               v-model.trim="$v.expense.storeName.$model"
               @input="expense.storeName = capitalize($event)"/>
        <span v-if="!$v.expense.storeName.minLength || !$v.expense.storeName.maxLength" class="errorMessage">
            Дължината на полето трябва да е между 2 и 50 символа!
          </span>
        <label for="price">Price:</label>
        <input id="price" type="number" step="0.01" :disabled="!checkProductsIsEmpty"
               :class="{invalidFiled: $v.expense.price.$error}"
               v-model.number="$v.expense.price.$model"
               @input="expense.price = setDecimalFormat($event)"/>
        <span v-if="$v.expense.price.$dirty && !$v.expense.price.required" class="errorMessage">
             Полето е задължително!
          </span>
        <span v-else-if="!$v.expense.price.minValue || !$v.expense.price.maxValue" class="errorMessage">
            Размерът на сумата може да бъде между 0.01 и 1000000!
          </span>
        <label for="discount">Discount:</label>
        <input id="discount" type="number" step="0.01" :disabled="!checkProductsIsEmpty"
               :class="{invalidFiled: $v.expense.discount.$error}"
               v-model.number="$v.expense.discount.$model"
               @input="expense.discount = setDecimalFormat($event)"/>
        <span v-if="!$v.expense.discount.minValue || !$v.expense.discount.maxValue" class="errorMessage">
            Отстъпката може да бъде между 0 и 1000000!
          </span>
        <label>Description:</label>
        <textarea v-model.trim="$v.expense.description.$model"
                  :class="{invalidFiled: $v.expense.description.$error}"
                  @input="expense.description = capitalize($event)"/>
        <span v-if="!$v.expense.description.maxLength" class="errorMessage">
            Дължината на полето може да бъде до 100 символа!
          </span>
        <button>Add expense</button>
      </form>
      <form @submit.prevent="addProductToExpense">
        <label for="productTypeName">ProductTypeName:</label>
        <input id="productTypeName" type="text"
               v-model.trim="$v.product.productType.name.$model"
               :class="{invalidFiled: $v.product.productType.name.$error}"
               @input="product.productType.name = capitalize($event)"/>
        <span v-if="$v.product.productType.name.$dirty && !$v.product.productType.name.required" class="errorMessage">
            Полето е задължително!
          </span>
        <span v-else-if="!$v.product.productType.name.minLength || !$v.product.productType.name.maxLength" class="errorMessage">
            Дължината на полето трябва да е между 2 и 50 символа!
          </span>
        <label for="productTypeBrand">productTypeBrand:</label>
        <input id="productTypeBrand" type="text"
               :class="{invalidFiled: $v.product.productType.brand.$error}"
               v-model.trim="$v.product.productType.brand.$model"
               @input="product.productType.brand = capitalize($event)"/>
        <span v-if="!$v.expense.subCategory.maxLength" class="errorMessage">
            Дължината на полето може да бъде до 50 символа!
          </span>
        <label for="productTypeProductCategory">productTypeProductCategory:</label>
        <input id="productTypeProductCategory" type="text"
               :class="{invalidFiled: $v.product.productType.productCategory.$error}"
               v-model.trim="$v.product.productType.productCategory.$model"
               @input="product.productType.productCategory = capitalize($event)"/>
        <span v-if="$v.product.productType.productCategory.$dirty && !$v.product.productType.productCategory.required" class="errorMessage">
            Полето е задължително!
          </span>
        <span v-else-if="!$v.product.productType.productCategory.minLength || !$v.product.productType.productCategory.maxLength" class="errorMessage">
            Дължината на полето трябва да е между 2 и 50 символа!
          </span>
        <label for="standardPrice">standardPrice:</label>
        <input id="standardPrice" type="number" step="0.01"
               :class="{invalidFiled: $v.product.standardPrice.$error}"
               v-model.number="$v.product.standardPrice.$model"
               @input="product.standardPrice = setDecimalFormat($event)"/>
        <span v-if="$v.product.standardPrice.$dirty && !$v.product.standardPrice.required" class="errorMessage">
             Полето е задължително!
          </span>
        <span v-else-if="!$v.product.standardPrice.minValue || !$v.product.standardPrice.maxValue" class="errorMessage">
           Цената може да бъде между 0.01 и 1000000!
          </span>
        <label for="priceDiscount">priceDiscount:</label>
        <input id="priceDiscount" type="number" step="0.01" disabled
               :class="{invalidFiled: $v.product.priceDiscount.$error}"
               v-model.number="$v.product.priceDiscount.$model"
               @input="product.priceDiscount = setDecimalFormat($event)"/>
        <span v-if="!$v.product.priceDiscount.minValue || !$v.product.priceDiscount.maxValue" class="errorMessage">
           Отстъпката може да бъде между 0 и 1000000!
          </span>
        <label for="priceAfterDiscount">priceAfterDiscount:</label>
        <input id="priceAfterDiscount" type="number" step="0.01"
               :class="{invalidFiled: $v.product.priceAfterDiscount.$error}"
               v-model.number="$v.product.priceAfterDiscount.$model"
               @input="product.priceAfterDiscount = setDecimalFormat($event)"/>
        <span v-if="$v.product.priceAfterDiscount.$dirty && !$v.product.priceAfterDiscount.required" class="errorMessage">
             Полето е задължително!
          </span>
        <span v-else-if="!$v.product.priceAfterDiscount.minValue || !$v.product.priceAfterDiscount.maxValue" class="errorMessage">
           Цената може да бъде между 0.01 и 1000000!
          </span>
        <label for="quantity">quantity:</label>
        <input id="quantity" type="number" step="0.001"
               :class="{invalidFiled: $v.product.quantity.$error}"
               v-model.number="$v.product.quantity.$model"
               @input="product.quantity = setDecimalFormat($event)"/>
        <span v-if="$v.product.quantity.$dirty && !$v.product.quantity.required" class="errorMessage">
             Полето е задължително!
          </span>
        <span v-else-if="!$v.product.quantity.minValue || !$v.product.quantity.maxValue" class="errorMessage">
           Количеството може да бъде между 0.001 и 1000000!
          </span>
        <label for="finalPrice">finalPrice:</label>
        <input id="finalPrice" type="number" step="0.01" disabled
               :class="{invalidFiled: $v.product.finalPrice.$error}"
               v-model.number="$v.product.finalPrice.$model"/>
        <span v-if="$v.product.finalPrice.$dirty && !$v.product.finalPrice.required" class="errorMessage">
             Полето е задължително!
          </span>
        <span v-else-if="!$v.product.finalPrice.minValue || !$v.product.finalPrice.maxValue" class="errorMessage">
           Сумата може да бъде между 0.001 и 1000000!
          </span>
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

<script src="../scripts/add-expense.js"></script>

<style scoped>
  @import url("../assets/css/add-expense.css");

</style>