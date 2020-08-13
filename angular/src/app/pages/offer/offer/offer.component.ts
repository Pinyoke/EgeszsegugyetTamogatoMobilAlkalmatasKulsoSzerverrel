import { Component, OnInit } from '@angular/core';
import { Offer } from '../model/offer.model';
import { OfferService } from '../service/offer.service';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { RoleService } from 'app/pages/user/service/role.service';
import { FormOfferComponent } from '../form-offer/form-offer.component';
import { Product } from 'app/pages/product/model/product.model';
import { ProductService } from 'app/pages/product/service/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-offer',
  templateUrl: './offer.component.html',
  styleUrls: ['./offer.component.css']
})
export class OfferComponent implements OnInit {

  offers : Offer[];
  products: Product[];
  page:number=0;
  pages:Array<Number>;

  name: string = "";
  


  constructor(private router:Router,private productService: ProductService ,private offerService : OfferService, private fb: FormBuilder/*,private modalService: NgbModal*/) { }

  ngOnInit(): void {
    
    this.getAllOffers();
   
    //this.getAllProducts();
  }

  getAllProducts() {
        this.productService.getProducts(this.page,"").subscribe(productFromService => { this.products = productFromService; console.log(productFromService) });
  }

  getAllOffers() {
    this.offerService.getOffers().subscribe(offerPage => { 
      this.offers = offerPage['content']; console.log(offerPage);
      console.log(this.offers) ;
      this.pages = new Array(offerPage['totalPages']);
    
    });
    
  }


  openEditFormModal(offerToEdit : Offer) {
    localStorage.setItem('offerToEdit', JSON.stringify(offerToEdit));
    this.router.navigate(['offerform'])
    /*const modalRef = this.modalService.open(FormOfferComponent);   
    modalRef.componentInstance.offer = offerToEdit;  
    modalRef.componentInstance.products = this.products;
    modalRef.componentInstance.oldProductName = offerToEdit.productName;
    modalRef.result.then((result) => {
      console.log(result);
      this.getAllOffers();
     
    }).catch((error) => {
      console.log(error);
      this.getAllOffers();
      
    });*/

   
  }

  openCreateFormModal() {
    this.router.navigate(['offerform'])
    localStorage.setItem('offerToEdit', JSON.stringify(new Offer()));
    /*const modalRef = this.modalService.open(FormOfferComponent);   
    modalRef.componentInstance.products = this.products;
    modalRef.result.then((result) => {
      console.log(result);
      this.getAllOffers();     
    }).catch((error) => {
      console.log(error);
      this.getAllOffers();    
    });
    */
  }

  deleteOffer(offerId : number)
  {
    this.offerService.deleteOffer(offerId).subscribe(data => console.log(data));
    this.getAllOffers();    
  }

  reloadPage(){
    window.location.reload();
  }

  search(event:any){
    console.log(event.target.value);
    this.name = event.target.value 
    this.getAllOffers();
   
    
  }

  setPage(i,event:any){
    this.page = i;
    this.getAllOffers();
  }

}
