import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientsComponent } from "./components/clients/clients.component";
import { CreateClientComponent } from "./components/create-client/create-client.component";
import { UpdateClientComponent } from "./components/update-client/update-client.component";

const routes: Routes = [
  { path: 'clients', component: ClientsComponent },
  { path: 'create-client', component: CreateClientComponent },
  { path: 'update-client/:id', component: UpdateClientComponent },
  { path: '',   redirectTo: 'clients', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
