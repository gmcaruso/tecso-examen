import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';

import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AccountComponent } from './account/account.component';
import { AccountDetailComponent } from './account-detail/account-detail.component';
import { MovementComponent } from './movement/movement.component';
import { MovementDetailComponent } from './movement-detail/movement-detail.component';

const appRoutes: Routes = [
  {path: '', component: AccountComponent },
  {path: 'movement/:id', component: MovementComponent},
  {path: 'movement-details/:id', component: MovementDetailComponent},
  {path: 'account', component: AccountComponent},
  {path: 'account-details/:id', component: AccountDetailComponent},
  {path: 'account-details/new/:type', component: AccountDetailComponent}
];
@NgModule({
  declarations: [
    AppComponent,
    AccountComponent,
    AccountDetailComponent,
    MovementComponent,
    MovementDetailComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    FormsModule,
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
