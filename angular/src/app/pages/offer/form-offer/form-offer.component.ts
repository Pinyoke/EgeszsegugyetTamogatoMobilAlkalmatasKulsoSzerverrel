import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { OfferService } from '../service/offer.service';
import { Offer } from '../model/offer.model';
import { Product } from 'app/pages/product/model/product.model';
import { ProductSelectComponentComponent } from '../product-select-component/product-select-component.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-offer',
  templateUrl: './form-offer.component.html',
  styleUrls: ['./form-offer.component.css']
})
export class FormOfferComponent implements OnInit {

  offerForm: FormGroup;
  offer: Offer = new Offer();

  products: Product[] = [];
  selectedProduc: String = '';
  newProduct: Product;
  oldProductName: string;

  constructor(private router:Router,private modalService: NgbModal ,private fb: FormBuilder, private offerService : OfferService) { }

  ngOnInit(): void {
    this.offer = JSON.parse(localStorage.getItem('offerToEdit')); 
    this.createForm();
    console.log(this.products.length);
  }

  
  createForm() {
    if(this.offer.id != null){      
      this.offerForm = this.fb.group({
        url: this.offer.url,
        price: this.offer.price,
        actualProduct: this.offer.productName 
      });     
    }else{
      this.offerForm = this.fb.group({
        url: '',
        price: '',
        actualProduct: ''
      });
    }       
  }

  submitForm(){
      this.offer.price = this.offerForm.value.price;
      this.offer.url = this.offerForm.value.url;
      if(this.offer.product == null){
        this.offer.productName = this.offerForm.value.productName;
      }else{
      this.offer.productName = this.offer.product.name;
      }
      console.log(this.offer);
      this.offerService.saveOffer(this.offer).subscribe();
      this.router.navigate(['offers']);
  }

  closeForm(){
    this.router.navigate(['offers']);
  }

  delete()
  {
    this.offerService.deleteOffer(this.offer.id).subscribe(data => console.log(data));
    this.router.navigate(['offers']);
  } 

  selectProductChangeHandler (event: any) {
      //update the ui
      this.selectedProduc = event.target.value;
      console.log(this.selectedProduc );
      this.products.forEach(product => {
          if(product.name == this.selectedProduc){
                this.newProduct = product;
          }
      })
      console.log(this.newProduct.name);
    
  }  

  openModel(){
    const modalRef = this.modalService.open(ProductSelectComponentComponent); 
    modalRef.result.then((result) => {
      console.log(result);
      this.offer.product = JSON.parse(localStorage.getItem('selectedProduct'));
      
      this.offerForm.controls.actualProduct.setValue(this.offer.product.name);
      
    }).catch((error) => {
      console.log(error);
    });

  }
  

}
