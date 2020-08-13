import { Component, OnInit } from '@angular/core';
import { Allergen } from '../model/allergen.model';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AllergenService } from '../service/allergen.service';
import { FormAllergenComponent } from '../form-allergen/form-allergen.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-allergen',
  templateUrl: './allergen.component.html',
  styleUrls: ['./allergen.component.css']
})
export class AllergenComponent implements OnInit {

  allergens: Allergen[];
  pages:Array<Number>;
  page: number =0;
  name: string = "";

  constructor(private router:Router,private allergenService: AllergenService,private fb: FormBuilder/*,private modalService: NgbModal*/) { }

  ngOnInit(): void {
    this.getAllAllergens();
  }

  getAllAllergens() {    
    this.allergenService.getAllergens(this.page, this.name).subscribe(allergenPage => { 
      
      this.allergens = allergenPage['content'];      
      this.pages = new Array(allergenPage['totalPages']);
    });
  }

  openEditFormModal(allergenToEdit : Allergen) {
    localStorage.setItem('allergenToEdit', JSON.stringify(allergenToEdit));
    this.router.navigate(['allergenform'])
    /*const modalRef = this.modalService.open(FormAllergenComponent);   
    modalRef.componentInstance.allergen = allergenToEdit;
   

    modalRef.result.then((result) => {
      console.log(result);
      this.getAllAllergens();
    }).catch((error) => {
      console.log(error);
    });
      */
  }

  openCreateFormModal() {
    localStorage.setItem('allergenToEdit', JSON.stringify(new Allergen()));
    this.router.navigate(['allergenform'])
   /* const modalRef = this.modalService.open(FormAllergenComponent);   
    
    modalRef.result.then((result) => {
      console.log(result);
      this.getAllAllergens();
    }).catch((error) => {
      console.log(error);
    });
    */
  }

  deleteAllergen(allergenId : number)
  {
   
    this.allergenService.deleteAllergen(allergenId).subscribe(message => console.log(message));
  }

  setPage(i,event:any){
    this.page = i;
    this.getAllAllergens();
  }


  search(event:any){
    console.log(event.target.value);
    this.name = event.target.value 
    this.getAllAllergens();
   
    
  }

}
