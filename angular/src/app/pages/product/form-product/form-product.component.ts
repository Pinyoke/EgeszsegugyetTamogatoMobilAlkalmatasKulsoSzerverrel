import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Product } from '../model/product.model';
import { FoodIngredient } from '../model/food-ingredient.model';
import { Image } from '../model/image.model';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ProductService } from '../service/product.service';
import { ImageService } from '../service/image.service';
import { ContactPoint } from 'app/pages/seller/model/contact-point.model';
import { ReactiveFormsModule } from '@angular/forms';
import { FileUploadServiceService } from '../service/file-upload-service.service';
import { Router } from '@angular/router';
import { FoodIngredientCreateComponent } from '../food-ingredient-create/food-ingredient-create.component';

@Component({
  selector: 'app-form-product',
  templateUrl: './form-product.component.html',
  styleUrls: ['./form-product.component.css']
})
export class FormProductComponent implements OnInit {

  productForm: FormGroup;
  product: Product = new Product();
  foodIngredients: FoodIngredient[] =[];  
  
  imageUrl: string;
  imageChange: boolean = false;

  fileToUpload: File = null;

  constructor(private modalService: NgbModal,private router:Router,private fileUploadService: FileUploadServiceService,private imageService: ImageService/*, public activeModal: NgbActiveModal*/, private fb: FormBuilder, private productService : ProductService) { }

  ngOnInit(): void {
    this.product = JSON.parse(localStorage.getItem('productToEdit'));    
    this.imageUrl = this.product.imageUrl;
    this.createForm();
  }

  createForm() {
    if(this.product.id != null){
      this.foodIngredients = this.product.foodIngredients;
      this.productForm = this.fb.group({
       
        name: this.product.name,
        description:this.product.description,
        gtin13:this.product.gtin13,
        sku:this.product.sku,
        type: '',
        foodIngredientName: '', 
      });
      (<HTMLInputElement>document.getElementById("image")).style.display =
      (this.product.imageUrl != null) ? "block" : "none";
      (<HTMLInputElement>document.getElementById("image")).src = 
      (this.product.imageUrl != null) ? this.product.imageUrl : '';


    }else{
      this.productForm = this.fb.group({
        
        name: '',
        description:'',
        gtin13:'',
        sku:'',
        type: '',
        foodIngredientName: '', 
      });
      (<HTMLInputElement>document.getElementById("image")).style.display =
      (this.product.imageUrl != null) ? "block" : "none";
    }    
  }
/*
  closeModal() {
    this.activeModal.close('Modal Closed');
  
  }
  */

  submitForm()
  {
    this.uploadFileToActivity();
      
    
    
  }

  createProduct(){
    
    console.log("SUBMIT FORM")

    this.product.name = this.productForm.value.name;
    this.product.description = this.productForm.value.description;
    this.product.gtin13 = this.productForm.value.gtin13;
    this.product.sku = this.productForm.value.sku;
    this.product.foodIngredients = this.foodIngredients;
    this.product.imageUrl = this.imageUrl;
    console.log("PRODUCTBÓLVETTIMAGEURL: " + this.product.imageUrl);
    this.productService.saveProduct(this.product).subscribe();    
    this.router.navigate(['products'])
  }

  delete(){
    console.log("Delete method indul el");
    this.productService.deleteProduct(this.productForm.value.id).subscribe();
    this.router.navigate(['products'])
  }

  createFoodIngredient()
  { 
      console.log("Belépett a creatorba")
      const modalRef = this.modalService.open(FoodIngredientCreateComponent); 
      modalRef.result.then((result) => {
      console.log(result);
      this.foodIngredients.push(JSON.parse(localStorage.getItem('createdIngredient')));
      
    
      
    }).catch((error) => {
      console.log(error);
    });
   
  }

  deleteFoodIngredient(foodIngredient: FoodIngredient)
  {
    //ForEachel végig menni az egészen.
    for(let i = 0;i  < this.product.foodIngredients.length; i++){
        if(this.product.foodIngredients[i] == foodIngredient)
        {
            this.product.foodIngredients.splice(i,1);
        }
    } 
  }   

  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);

    this.imageChange = true;
    this.showImage(files.item(0));
  }

  showImage(selectedFile: File) {

    (<HTMLInputElement>document.getElementById("image")).style.display = "block" ;
      
     let src;

    var reader = new FileReader();
    reader.readAsDataURL(selectedFile);
    reader.onload = (_event) => {
      src = reader.result;
      (<HTMLInputElement>document.getElementById("image")).src = src;
    }
  }


  uploadFileToActivity() {
    if(this.imageChange){
    this.fileUploadService.postFile(this.fileToUpload).subscribe(response =>
      {this.imageUrl = response.fileDownloadUri;
       console.log(response.fileDownloadUri);
       this.createProduct();
      
      }
      );
    }else{
      this.createProduct();
    }
  }

  closeForm(){
    this.router.navigate(['products'])
  }
  

}
