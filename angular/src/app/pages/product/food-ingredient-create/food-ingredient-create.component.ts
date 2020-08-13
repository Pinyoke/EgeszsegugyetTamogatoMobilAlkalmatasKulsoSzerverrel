import { Component, OnInit, createPlatformFactory } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Seller } from 'app/pages/seller/model/seller.model';
import { FoodIngredient } from '../model/food-ingredient.model';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-food-ingredient-create',
  templateUrl: './food-ingredient-create.component.html',
  styleUrls: ['./food-ingredient-create.component.css']
})
export class FoodIngredientCreateComponent implements OnInit {

  foodIngredientForm : FormGroup;

  constructor(public activeModal: NgbActiveModal, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.createForm();
  }
  createForm() {   
      
      this.foodIngredientForm = this.fb.group({   
        type: '',
        foodIngredientName: '', 
      });
    }

    submitForm(){
        console.log("Minden jo");
        let foodIngredient = new FoodIngredient;
        foodIngredient.name = this.foodIngredientForm.value.foodIngredientName ;
        foodIngredient.type = this.foodIngredientForm.value.type;
        localStorage.setItem('createdIngredient', JSON.stringify(foodIngredient));
        this.activeModal.close('Modal Closed');
        console.log(foodIngredient);
      }

  
}
