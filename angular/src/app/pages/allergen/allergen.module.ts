import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AllergenComponent } from './allergen/allergen.component';
import { FormAllergenComponent } from './form-allergen/form-allergen.component';



@NgModule({
  declarations: [AllergenComponent, FormAllergenComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class AllergenModule { }
