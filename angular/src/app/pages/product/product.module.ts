import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductComponent } from './product/product.component';
import { FormProductComponent } from './form-product/form-product.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { FoodIngredientCreateComponent } from './food-ingredient-create/food-ingredient-create.component';




@NgModule({
  declarations: [ProductComponent, FormProductComponent, FoodIngredientCreateComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class ProductModule { }
