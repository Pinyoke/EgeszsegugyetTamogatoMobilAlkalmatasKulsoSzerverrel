import { Component, OnInit } from '@angular/core';
import { Allergen } from '../model/allergen.model';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AllergenService } from '../service/allergen.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-allergen',
  templateUrl: './form-allergen.component.html',
  styleUrls: ['./form-allergen.component.css']
})
export class FormAllergenComponent implements OnInit {

  allergen : Allergen = new Allergen();
  allergenForm : FormGroup;

  constructor(/*public activeModal: NgbActiveModal,*/ private router:Router,private fb: FormBuilder, private allergenService: AllergenService) { }

  ngOnInit(): void {
    this.allergen = JSON.parse(localStorage.getItem('allergenToEdit')); 
    this.createForm();
  }

  createForm() {
    if(this.allergen.id != null){
      this.allergenForm = this.fb.group({
       
        name: this.allergen.name,
         });
        
    }else{
      this.allergenForm = this.fb.group({
      
        name: '',
       
      });
  }
  }

  submitForm(){
    console.log("Submit Offer Form");
    if(this.allergen.id == null){
    this.allergen = new Allergen(); 
    }  
    this.allergen.name = this.allergenForm.value.name;
    
    
    this.allergenService.saveAllergen(this.allergen).subscribe();
    this.router.navigate(['allergen'])
  
  }

closeForm(){
  this.router.navigate(['allergen']);

}
  delete()
  {
    
    this.allergenService.deleteAllergen(this.allergen.id).subscribe(data => console.log(data));
    this.router.navigate(['allergen']);
   
  } 

   

}
