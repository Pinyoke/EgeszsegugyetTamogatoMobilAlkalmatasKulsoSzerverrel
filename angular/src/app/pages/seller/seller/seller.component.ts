import { Component, OnInit } from '@angular/core';
import { Seller } from '../model/seller.model';
import { SellerService } from '../service/seller.service';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormSellerComponent } from '../form-seller/form-seller.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-seller',
  templateUrl: './seller.component.html',
  styleUrls: ['./seller.component.css']
})
export class SellerComponent implements OnInit {

  sellers: Seller[];
  page: number =0;
  name: string = "";
  pages:Array<Number>;

  constructor(private router:Router,private sellerService: SellerService ,private fb: FormBuilder/*,private modalService: NgbModal*/) { }

  ngOnInit(): void {
    this.getAllSeller();
  }

  getAllSeller() {
    this.sellerService.getSellers(this.page, this.name).subscribe(sellerPage => {         
      this.sellers = sellerPage['content']; console.log(sellerPage);
      console.log(this.sellers);
      this.pages = new Array(sellerPage['totalPages']);
    });
  }

  openEditFormModal(sellerToEdit : Seller) {
    localStorage.setItem('sellerToEdit', JSON.stringify(sellerToEdit));
    this.router.navigate(['sellerform']);
   /* const modalRef = this.modalService.open(FormSellerComponent);   
    modalRef.componentInstance.seller = sellerToEdit;
    modalRef.componentInstance.contactPoints = sellerToEdit.contactPoint;
    modalRef.componentInstance.contactPoints = sellerToEdit.sellerAdmin;
    //Ide majd még lehet kell valamit ádani
    //modalRef.componentInstance.foodIngredients = productToEdit.foodIngredients;

    modalRef.result.then((result) => {
      console.log(result);
      this.getAllSeller();
    }).catch((error) => {
      console.log(error);
    });
    */

  }

  openCreateFormModal() {
    localStorage.setItem('sellerToEdit', JSON.stringify(new Seller));
    this.router.navigate(['sellerform']);
    /*const modalRef = this.modalService.open(FormSellerComponent);   
    
    modalRef.result.then((result) => {
      console.log(result);
      this.getAllSeller();
    }).catch((error) => {
      console.log(error);
    });
    */
  }

  deleteSeller(sellerId : number)
  {
    this.sellerService.deleteSeller(sellerId).subscribe(message => console.log(message));
  }

  search(event:any){
    console.log(event.target.value);
    this.name = event.target.value 
    this.getAllSeller();
   
    
  }

  setPage(i,event:any){
    this.page = i;
    this.getAllSeller();
  }


 

}
