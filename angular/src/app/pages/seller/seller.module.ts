import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SellerComponent } from './seller/seller.component';
import { FormSellerComponent } from './form-seller/form-seller.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ContactPointCreateComponent } from './contact-point-create/contact-point-create.component';



@NgModule({
  declarations: [SellerComponent, FormSellerComponent, ContactPointCreateComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class SellerModule { }
