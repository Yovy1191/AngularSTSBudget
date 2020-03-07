import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { UserComponent } from './user/user.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { NoPageFoundComponent } from './no-page-found/no-page-found.component';
import { IncomeComponent } from './income/income.component';
import { BillComponent } from './bill/bill.component';


@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    FooterComponent,
    HomeComponent,
    NoPageFoundComponent,
    IncomeComponent,
    BillComponent
   ],
  imports: [
    BrowserModule,
    AppRoutingModule


   ],
  schemas: [ NO_ERRORS_SCHEMA],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
