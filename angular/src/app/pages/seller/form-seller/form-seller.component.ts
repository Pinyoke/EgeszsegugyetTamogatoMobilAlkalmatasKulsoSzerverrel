import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Seller } from '../model/seller.model';
import { User } from 'app/pages/user/model/user.model';
import { ContactPoint } from '../model/contact-point.model';
import { Offer } from 'app/pages/offer/model/offer.model';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SellerService } from '../service/seller.service';
import { Router } from '@angular/router';
import { ContactPointCreateComponent } from '../contact-point-create/contact-point-create.component';

@Component({
  selector: 'app-form-seller',
  templateUrl: './form-seller.component.html',
  styleUrls: ['./form-seller.component.css']
})
export class FormSellerComponent implements OnInit {

  sellerForm: FormGroup;
  seller: Seller = new Seller();
  sellerAdmins: User[] =[];
  contactPoints : ContactPoint[] = [];
  offers : Offer[] = [];
  

  constructor(private modalService: NgbModal,private router:Router, private fb: FormBuilder, private sellerService : SellerService) { }

  ngOnInit(): void {
    this.seller = JSON.parse(localStorage.getItem('sellerToEdit')); 
    this.createForm();
  }
  createForm() {
    if(this.seller.id != null){
      this.sellerAdmins = this.seller.sellerAdmin;
      this.sellerForm = this.fb.group({
       
        name: this.seller.name,
        url: this.seller.url,
        sellerType: this.seller.sellerType,
        areaServed: '',
        type: '',
        contactOption:'',
        telephone:'',
        contactType:'',
        availableLanguage:'',
         });
         this.offers = this.seller.offer;
         this.contactPoints = this.seller.contactPoint;
         this.sellerAdmins = this.sellerAdmins;
    }else{
      this.sellerForm = this.fb.group({
      
        name: '',
        url: '',
        sellerType: '',
        areaServed: '',
        type: '',
        contactOption:'',
        telephone:'',
        contactType:'',
        availableLanguage:'',
      });
  }
  }
 
  addNewAdmin()
  {
      console.log("TODO: Add new admin")
  }

  deleteAdmin(adminUser : User){
    console.log("TODO: Delete admin")
  }

  submitForm(){
    console.log("Seller Submit")
      
    this.seller.name = this.sellerForm.value.name;
    this.seller.contactPoint = this.contactPoints;
    this.seller.sellerAdmin =this.sellerAdmins;
    this.seller.url = this.sellerForm.value.url;
    this.seller.sellerType = this.sellerForm.value.sellerType;  
    this.seller.offer = this.offers;  

    this.sellerService.saveSeller(this.seller).subscribe();
    this.router.navigate(['sellers'])
   /*
    this.closeModal();
    */

  }
/*
  closeModal(){
    this.activeModal.close('Modal Closed');

  }
  */

  delete(){
    this.sellerService.deleteSeller(this.seller.id).subscribe(message => console.log(message));
    this.router.navigate(['sellers'])

  }

  deleteContactPoint(contactPoint){
    //ForEachel végig menni az egészen.
    for(let i = 0;i  < this.seller.contactPoint.length; i++){
      if(this.seller.contactPoint[i] == contactPoint)
      {
          this.seller.contactPoint.splice(i,1);
      }
  }
  }

  addNewContactPoint()
  {
    console.log("Belépett a creatorba")
      const modalRef = this.modalService.open(ContactPointCreateComponent); 
      modalRef.result.then((result) => {
      console.log(result);
      this.contactPoints.push(JSON.parse(localStorage.getItem('createdContactPoint')));   
    
      
    }).catch((error) => {
      console.log(error);
    });

  }

  deleteOffer(offerId : number){
    console.log("TODO:Delete Offer")
  }

  closeForm(){
    this.router.navigate(['sellers'])
  }

}
