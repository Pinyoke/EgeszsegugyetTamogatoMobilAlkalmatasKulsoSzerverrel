import { Routes } from '@angular/router';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { TableComponent } from '../../pages/table/table.component';
import { TypographyComponent } from '../../pages/typography/typography.component';
import { IconsComponent } from '../../pages/icons/icons.component';
import { MapsComponent } from '../../pages/maps/maps.component';
import { NotificationsComponent } from '../../pages/notifications/notifications.component';
import { UpgradeComponent } from '../../pages/upgrade/upgrade.component';
import { ProductComponent } from 'app/pages/product/product/product.component';
import { SellerComponent } from 'app/pages/seller/seller/seller.component';
import { OfferComponent } from 'app/pages/offer/offer/offer.component';
import { LoginComponent } from 'app/pages/user/login/login.component';
import { RegisterComponent } from 'app/pages/user/register/register.component';
import { UserComponent } from 'app/pages/user/user/user.component';
import { AllergenComponent } from 'app/pages/allergen/allergen/allergen.component';
import { FormProductComponent } from 'app/pages/product/form-product/form-product.component';
import { FormSellerComponent } from 'app/pages/seller/form-seller/form-seller.component';
import { FormAllergenComponent } from 'app/pages/allergen/form-allergen/form-allergen.component';
import { FormOfferComponent } from 'app/pages/offer/form-offer/form-offer.component';
import { FormUserComponent } from 'app/pages/user/form-user/form-user.component';

export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard',      component: DashboardComponent },
    { path: 'table',          component: TableComponent },
    { path: 'typography',     component: TypographyComponent },
    { path: 'icons',          component: IconsComponent },
    { path: 'maps',           component: MapsComponent },
    { path: 'notifications',  component: NotificationsComponent },
    { path: 'upgrade',        component: UpgradeComponent },
    { path: 'products',        component: ProductComponent },
    { path: 'sellers',        component: SellerComponent },
    { path: 'offers',        component: OfferComponent },
    { path: 'users',        component: UserComponent },
    { path: 'login',        component: LoginComponent},
    { path: 'register',        component: RegisterComponent},
    { path: 'allergen',        component: AllergenComponent},
    { path: 'productform',        component: FormProductComponent},  
    { path: 'sellerform',        component: FormSellerComponent},   
    { path: 'allergenform',        component: FormAllergenComponent},   
    { path: 'offerform',        component: FormOfferComponent},   
    { path: 'userform',        component: FormUserComponent},   

];
