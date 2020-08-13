import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ToastrModule } from "ngx-toastr";

import { SidebarModule } from './sidebar/sidebar.module';
import { FooterModule } from './shared/footer/footer.module';
import { NavbarModule} from './shared/navbar/navbar.module';
import { FixedPluginModule} from './shared/fixedplugin/fixedplugin.module';

import { AppComponent } from './app.component';
import { AppRoutes } from './app.routing';

import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { HomeModule } from './pages/home/home.module';
import { ProductModule } from './pages/product/product.module';
import { OfferModule } from './pages/offer/offer.module';
import { SellerModule } from './pages/seller/seller.module';
import { UserModule } from './pages/user/user.module';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AllergenModule } from './pages/allergen/allergen.module';


@NgModule({
  declarations: [
    AppComponent,
    AdminLayoutComponent
  ],
  imports: [
    BrowserAnimationsModule,
    RouterModule.forRoot(AppRoutes,{
      useHash: true
    }),
    SidebarModule,
    NavbarModule,
    ToastrModule.forRoot(),
    FooterModule,
    FixedPluginModule,
    HomeModule,
    ProductModule,
    OfferModule,
    SellerModule,
    UserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AllergenModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
