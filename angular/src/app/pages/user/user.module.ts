import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FormUserComponent } from './form-user/form-user.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { UserComponent } from './user/user.component';
import { SellerSelectComponent } from './seller-select/seller-select.component';



@NgModule({
  declarations: [UserComponent, LoginComponent, RegisterComponent, FormUserComponent, SellerSelectComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class UserModule { }
