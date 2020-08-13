import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product.model';
import { ProductService } from '../service/product.service';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormProductComponent } from '../form-product/form-product.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Product[];
  pages:Array<Number>;
  page: number =0;
  name: string = "";
  

  constructor(private router:Router,private productService: ProductService, private fb: FormBuilder,private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getAllProduct();
    
  }

  getAllProduct() {
    this.productService.getProducts(this.page, this.name).subscribe(productPage => { 
      
      this.products = productPage['content']; console.log(productPage) ;
      console.log(this.products) ;
      this.pages = new Array(productPage['totalPages']);
    });
  }

  openEditFormModal(productToEdit : Product) {
    localStorage.setItem('productToEdit', JSON.stringify(productToEdit));
    this.router.navigate(['productform'])
    
    /*
    const modalRef = this.modalService.open(FormProductComponent); 
      
    modalRef.componentInstance.product = productToEdit;
    modalRef.componentInstance.foodIngredients = productToEdit.foodIngredients;
    
    modalRef.result.then((result) => {
      console.log(result);
      this.getAllProduct();
    }).catch((error) => {
      console.log(error);
    });
    */
  }

  openCreateFormModal() {
    localStorage.setItem('productToEdit', JSON.stringify(new Product()));
    this.router.navigate(['productform'])
    /*const modalRef = this.modalService.open(FormProductComponent);   
    
    modalRef.result.then((result) => {
      console.log(result);
      this.getAllProduct();
      console.log("EZ MI A FASZOM?")
    }).catch((error) => {
      console.log(error);
      console.log("EZ MI A KURVA ANYÁD?")
    });
    */
  }

  deleteProduct(productId : number)
  {
    console.log(this.productService.deleteProduct(productId).subscribe());
    this.getAllProduct();
    this.router.navigate(['products'])
   
  }

  setPage(i,event:any){
    this.page = i;
    this.getAllProduct();
  }


  search(event:any){
    console.log(event.target.value);
    this.name = event.target.value 
    this.getAllProduct();
   
    
  }

 

}
