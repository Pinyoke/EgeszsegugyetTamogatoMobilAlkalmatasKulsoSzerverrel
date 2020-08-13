import { Component, OnInit } from '@angular/core';
import { Role } from 'app/pages/user/model/role.model';
import { UserService } from 'app/pages/user/service/user.service';


export interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
    role: string;
}

export const ROUTES: RouteInfo[] = [
    
    { path: '/products',      title: 'Products',          icon:'nc-single-copy-04', class: '', role: 'ROLE_SELLERADMIN' },
    { path: '/sellers',      title: 'Sellers',          icon:'nc-cart-simple', class: '', role: 'ROLE_ADMIN' },
    { path: '/offers',      title: 'Offers',          icon:'nc-tag-content', class: '', role: 'ROLE_SELLERADMIN' },
    { path: '/users',      title: 'Users',          icon:'nc-badge', class: '', role: 'ROLE_ADMIN' },
    { path: '/login',      title: 'Login',          icon:'nc-single-02', class: '', role: ''},
    { path: '/register',      title: 'Register',          icon:'nc-single-02', class: '', role: ''},
    { path: '/allergen',      title: 'Allergen',          icon:'nc-ambulance', class: '', role: 'ROLE_ADMIN'},
   
];

@Component({
    moduleId: module.id,
    selector: 'sidebar-cmp',
    templateUrl: 'sidebar.component.html',
})

export class SidebarComponent implements OnInit {
    public menuItems: any[];
    

    constructor(private userService: UserService) { }

    ngOnInit() {
        var sidebar = <HTMLElement>document.querySelector('.sidebar');
        sidebar.setAttribute('data-color', "black");
        sidebar.setAttribute('data-active-color', "warning");
        this.menuItems = ROUTES.filter(menuItem => menuItem);
    }


    isUserHasPermission(role: string){
        
        if(role == ""){
            if(this.isUserLoggedIn()){
                return false;
            }else{
            return true;
            }
        }
        
        if(localStorage.getItem('role') == "ROLE_ADMIN"){
            return true;
        }

        if(role == localStorage.getItem('role')){
            return true;
        }else{
            return false;
        }
    }

    isUserLoggedIn(){

        let user = localStorage.getItem('username')
        //console.log(!(user === null))
        return !(user === null)
    
      }
    

}


