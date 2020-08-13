import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ContactPoint } from '../model/contact-point.model';

@Component({
  selector: 'app-contact-point-create',
  templateUrl: './contact-point-create.component.html',
  styleUrls: ['./contact-point-create.component.css']
})
export class ContactPointCreateComponent implements OnInit {

  contactPointForm : FormGroup;

  constructor(public activeModal: NgbActiveModal, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.createForm();
  }
  createForm() {
    this.contactPointForm = this.fb.group({   
      areaServed: '',
      telephone: '', 
      availableLanguage: '', 
    });
    
  }

  submitForm(){
    let contactPoint = new ContactPoint;
    contactPoint.areaServed = this.contactPointForm.value.areaServed;
    contactPoint.availableLanguage = this.contactPointForm.value.availableLanguage;
    contactPoint.telephone = this.contactPointForm.value.telephone;   
    localStorage.setItem('createdContactPoint', JSON.stringify(contactPoint));
    this.activeModal.close('Modal Closed');
    console.log(contactPoint);
  }

}
