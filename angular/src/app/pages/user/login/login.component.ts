import { Component, OnInit } from '@angular/core';
import { User } from '../model/user.model';
import { UserService } from '../service/user.service';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userForm = this.fb.group({
    email: [''],
    password: ['']
  });
  user: User;

  constructor(private router:Router,private userService: UserService, private fb: FormBuilder) { }

  ngOnInit(): void {
  }

  onSubmit(){
    this.user = new User();
    this.user.email = this.userForm.value.email;
    this.user.password = this.userForm.value.password;
    console.log(this.user.email)
    this.userService.authenticate(this.user.email,this.user.password).subscribe(data =>{
        //ITT kell majd megadni hogy hova dobjon
        console.log(localStorage.getItem('token'));
        this.userService.setUserRole().subscribe(role => {localStorage.setItem('role',role.role)});
        this.router.navigate(['products']);
    },
    err => {
      console.log(err)
      console.log("error")
    });
    console.log(this.user.email)
  }

   logintest(){
    console.log(this.userService.isUserLoggedIn());
  }


  logout(){
    this.userService.logOut();
  }
}
