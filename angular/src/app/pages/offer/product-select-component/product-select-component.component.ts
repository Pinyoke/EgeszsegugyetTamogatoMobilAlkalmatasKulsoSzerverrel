import { Component, OnInit } from '@angular/core';
import { Product } from 'app/pages/product/model/product.model';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ProductService } from 'app/pages/product/service/product.service';

@Component({
  selector: 'app-product-select-component',
  templateUrl: './product-select-component.component.html',
  styleUrls: ['./product-select-component.component.css']
})
export class ProductSelectComponentComponent implements OnInit {

  products: Product[];
  page: number =0;
  name: string = "";
  pages:Array<Number>;

  constructor(public activeModal: NgbActiveModal,private productService: ProductService) { }

  ngOnInit(): void {
    this.getAllProducts();
  }
  getAllProducts() {
    this.productService.getProducts(this.page, this.name).subscribe(productPage => { 
      
      this.products = productPage['content']; console.log(productPage) ;
      console.log(this.products) ;
      this.pages = new Array(productPage['totalPages']);
    });
  }

  search(event:any){
    console.log(event.target.value);
    this.name = event.target.value 
    this.getAllProducts();
   
    
  }

  setPage(i,event:any){
    this.page = i;
    this.getAllProducts();
  }

  add(product: Product){
    localStorage.setItem('selectedProduct', JSON.stringify(product));
    this.activeModal.close('Modal Closed');
  }


}
