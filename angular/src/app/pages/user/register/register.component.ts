import { Component, OnInit } from '@angular/core';
import { User } from '../model/user.model';
import { UserService } from '../service/user.service';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  userForm = this.fb.group({
    email: [''],
    password: [''],
    name: ['']
  });

  user: User;  
  
  constructor(private router:Router,private userService: UserService, private fb: FormBuilder) { }

  ngOnInit(): void {
  }

  onSubmit(){
    this.user = new User();
    this.user.email = this.userForm.value.email;
    this.user.password = this.userForm.value.password;
    this.user.name = this.userForm.value.name;
    this.userService.userRegistration(this.user).subscribe(user => {

      if(user.id != null){
        this.router.navigate(['login']);
      }
    })

  }

}
