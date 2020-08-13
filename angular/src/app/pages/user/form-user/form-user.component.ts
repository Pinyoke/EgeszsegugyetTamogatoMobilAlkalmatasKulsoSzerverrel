import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { User } from '../model/user.model';
import { Sensitivity } from '../model/sensitivity.model';
import { Role } from '../model/role.model';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UserService } from '../service/user.service';
import { Seller } from 'app/pages/seller/model/seller.model';
import { SellerSelectComponent } from '../seller-select/seller-select.component';
import { RoleService } from '../service/role.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-user',
  templateUrl: './form-user.component.html',
  styleUrls: ['./form-user.component.css']
})
export class FormUserComponent implements OnInit {

  userForm: FormGroup;
  user: User = new User();
  sensitivitys : Sensitivity[] = [];
  
  sellers: Seller[] = [];
  roles : Role[] = [];
  oldRole : Role;
  newRole: Role;
  newSeller: Seller;
  selectedRole: String = '';
  selectedSeller: String = '';

  modifyPassword: boolean = false;

  constructor(private router:Router,private modalService: NgbModal,private fb: FormBuilder, private userService : UserService, private roleService: RoleService) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('userToEdit')); 
    this.getRoles();
    this.createForm(); 
    console.log(this.user.role.role);
    console.log(this.user.seller); 
  }
  getRoles() {
    this.roleService.getRoles().subscribe(roles => { this.roles = roles });
  }

  createForm() {
    if(this.user.id != null){    
      if(this.user.seller == null){
        this.userForm = this.fb.group({
          
          name: this.user.name,
          email: this.user.email,
          password: '',   
          actualrole: this.user.role.role,
          actualSeller: ''                
        });
      }else{      
      this.userForm = this.fb.group({
        name: this.user.name,
        email: this.user.email,
        password: '',   
        actualrole: this.user.role.role,
        actualSeller: this.user.seller.name              
      });
    }
      this.sensitivitys = this.user.sensitivitys;
    }else{
      this.userForm = this.fb.group({        
        name: '',  
        email: '',  
        password: '',  
        actualrole: '',
        actualSeller: ''
      });

    }   
    
  }

  editPassword(){
    this.modifyPassword = !this.modifyPassword;
    
  }

  submitForm(){
    console.log(this.modifyPassword)

 
    this.user.name = this.userForm.value.name;
    this.user.email = this.userForm.value.email;
    if(this.modifyPassword){
    this.user.password = this.userForm.value.password;
    }
    if(this.newRole != null){
      this.user.role = this.newRole;
    }
    if(this.newSeller != null){
      this.user.seller = this.newSeller;
    }
    if(this.user.id == null){
      console.log("Uj user");
      this.userService.userRegistration(this.user).subscribe();
    }else{
      console.log("Regi user");
     
      console.log(this.user);
      this.userService.saveUser(this.user).subscribe();
    }
   
    this.router.navigate(['users']);
    
  }

  delete()
  {
    this.userService.deleteUser(this.user.id).subscribe(data => console.log(data));
    this.router.navigate(['users']);
  }

  deleteSensitivity(sensitivity : Sensitivity){
    console.log("TODO: delete sensitivity")
  }

  createSensitivity(){
    console.log("TODO: create sensitivity")
  }

  selectRoleChangeHandler (event: any) {
    //update the ui
    this.selectedRole = event.target.value;
    console.log(this.selectedRole );
    this.roles.forEach(role => {
        if(role.role == this.selectedRole){
              this.newRole = role;
        }
    })
    console.log(this.newRole.role);
  } 

  
  selectSellerChangeHandler (event: any) {
    //update the ui
    this.selectedSeller = event.target.value;
    console.log(this.selectedSeller );
    this.sellers.forEach(seller => {
        if(seller.name == this.selectedSeller){
              this.newSeller = seller;
        }
    })
    console.log(this.newSeller.name);
  } 

  openModel(){
    const modalRef = this.modalService.open(SellerSelectComponent); 
    modalRef.result.then((result) => {
      console.log(result);
      this.user.seller = JSON.parse(localStorage.getItem('selectedSeller'));
      console.log(this.user);
      this.userForm.controls.actualSeller.setValue(this.user.seller.name);
      
    }).catch((error) => {
      console.log(error);
    });
    
  }

  closeForm(){
    this.router.navigate(['users']);
  }

  

}
