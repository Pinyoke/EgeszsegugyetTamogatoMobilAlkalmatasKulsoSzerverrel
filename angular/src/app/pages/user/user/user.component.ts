import { Component, OnInit } from '@angular/core';
import { User } from '../model/user.model';
import { Role } from '../model/role.model';
import { UserService } from '../service/user.service';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { RoleService } from '../service/role.service';
import { FormUserComponent } from '../form-user/form-user.component';
import { Seller } from 'app/pages/seller/model/seller.model';
import { SellerService } from 'app/pages/seller/service/seller.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users : User[];
  roles : Role[];
  seller : Seller[];

  page: number =0;
  email: string = "";
  pages:Array<Number>;


  constructor(private router:Router,private sellerService: SellerService,private userService : UserService, private fb: FormBuilder/*,private modalService: NgbModal*/,private roleService: RoleService) { }

  ngOnInit(): void {
    this.getAllUser();
    this.getAllRole();
    this.getAllSeller();
  }

  getAllSeller()
  {
    this.sellerService.getSellers(this.page, "").subscribe(sellerPage => { 
      
      this.seller = sellerPage['content']; 
     
    });

  }

  getAllRole() {
    this.roleService.getRoles().subscribe(roles => { this.roles = roles });
  }


  getAllUser() {
    
    this.userService.getUsers(this.page, this.email).subscribe(userPage => { 
      
      this.users = userPage['content'];
      this.pages = new Array(userPage['totalPages']);
    });
    }

  openEditFormModal(userToEdit : User) {
    localStorage.setItem('userToEdit', JSON.stringify(userToEdit));
    this.router.navigate(['userform'])
    
    /*const modalRef = this.modalService.open(FormUserComponent);
    modalRef.componentInstance.user = userToEdit;
    modalRef.componentInstance.sensitivitys = userToEdit.sensitivitys;  
    modalRef.componentInstance.roles = this.roles;
    modalRef.componentInstance.sellers = this.seller;
    modalRef.result.then((result) => {
      console.log(result);
      this.getAllUser();
     
    }).catch((error) => {
      console.log(error);
      this.getAllUser();
    
    });
      */
   
  }

  openCreateFormModal() {
    localStorage.setItem('userToEdit', JSON.stringify(new User()));
    this.router.navigate(['userform'])
   /* const modalRef = this.modalService.open(FormUserComponent);   
    
    modalRef.result.then((result) => {
      console.log(result);
      this.getAllUser();
      
    }).catch((error) => {
      console.log(error);
      this.getAllUser();
     
    });
    */
  }

  deleteUser(userId : number)
  {
    this.userService.deleteUser(userId).subscribe(data => console.log(data));
    this.getAllUser();
    window.location.reload();
  }

  reloadPage(){
    window.location.reload();
  }

  search(event:any){
    console.log(event.target.value);
    this.email = event.target.value 
    this.getAllUser();
   
    
  }

  setPage(i,event:any){
    this.page = i;
    this.getAllUser();
  }


}
