import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConfirmDialogComponent } from './components/confirm-dialog/confirm-dialog.component';
import { CatDetailsComponent } from './components/cat-details/cat-details.component';
import { GetAllCatsComponent } from './components/get-all-cats/get-all-cats.component';
import { HeaderComponent } from './components/ui/header/header.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 
import {  HttpClientModule } from '@angular/common/http';
import {MatCardModule} from '@angular/material/card'; 
import {MatDialogModule} from '@angular/material/dialog';
import { DialogBoxComponent } from './components/dialog-box/dialog-box.component'; 
import {MatInputModule} from '@angular/material/input'; 
import {MatButtonModule} from '@angular/material/button'; 
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatListModule} from '@angular/material/list'; 
import {MatIconModule} from '@angular/material/icon'; 
import {MatExpansionModule} from '@angular/material/expansion'; 
import {MatSnackBarModule} from '@angular/material/snack-bar'; 
@NgModule({
  declarations: [
    AppComponent,
    ConfirmDialogComponent,
    CatDetailsComponent,
    GetAllCatsComponent,
    HeaderComponent,
    DialogBoxComponent  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    HttpClientModule,
    MatCardModule,
    MatDialogModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatListModule,
    MatIconModule,
    MatExpansionModule,
    MatSnackBarModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
