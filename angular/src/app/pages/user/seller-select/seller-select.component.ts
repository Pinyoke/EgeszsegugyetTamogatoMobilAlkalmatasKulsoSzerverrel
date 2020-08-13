import { Component, OnInit } from '@angular/core';
import { Seller } from 'app/pages/seller/model/seller.model';
import { SellerService } from 'app/pages/seller/service/seller.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-seller-select',
  templateUrl: './seller-select.component.html',
  styleUrls: ['./seller-select.component.css']
})
export class SellerSelectComponent implements OnInit {

  sellers: Seller[];
  page: number =0;
  name: string = "";
  pages:Array<Number>;

  constructor(public activeModal: NgbActiveModal,private sellerService: SellerService) { }

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

  search(event:any){
    console.log(event.target.value);
    this.name = event.target.value 
    this.getAllSeller();
   
    
  }

  setPage(i,event:any){
    this.page = i;
    this.getAllSeller();
  }

  add(seller: Seller){
    localStorage.setItem('selectedSeller', JSON.stringify(seller));
    this.activeModal.close('Modal Closed');
  }

}
