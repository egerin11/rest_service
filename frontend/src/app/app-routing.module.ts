import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GetAllCatsComponent } from './components/get-all-cats/get-all-cats.component';
import { CatDetailsComponent } from './components/cat-details/cat-details.component';

const routes: Routes = [
{path:'',component:GetAllCatsComponent},
{ path: 'cat/:id', component: CatDetailsComponent },
{ path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
