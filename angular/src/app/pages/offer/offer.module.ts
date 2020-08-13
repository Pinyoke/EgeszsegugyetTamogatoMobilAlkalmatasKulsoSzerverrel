import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OfferComponent } from './offer/offer.component';
import { FormOfferComponent } from './form-offer/form-offer.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ProductSelectComponentComponent } from './product-select-component/product-select-component.component';



@NgModule({
  declarations: [OfferComponent, FormOfferComponent, ProductSelectComponentComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class OfferModule { }
