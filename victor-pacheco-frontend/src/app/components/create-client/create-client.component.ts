import { Component } from '@angular/core';
import {Client} from "../../model/client.model";
import {ClientService} from "../../services/client.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-create-client',
  templateUrl: './create-client.component.html',
  styleUrls: ['./create-client.component.scss']
})
export class CreateClientComponent {

  client: Client = {};


  constructor(
    private clientService: ClientService,
    private snackBar: MatSnackBar) {
  }

  checkClient(): boolean{
    return this.client.identification == null || this.client.identification == undefined || this.client.identification == 0 ||
      this.client.identificationType == null || this.client.identificationType == undefined || this.client.identificationType == '' ||
      this.client.gender == null || this.client.gender == undefined ||this.client.gender == '' ||
      this.client.name == null || this.client.name == undefined ||this.client.name == '';
  }

  createClient(){
    this.clientService.createClient(this.client).subscribe({
      next: (data) => {
        this.client = {};
        this.snackBar.open('Cliente creado exitosamente', "Cerrar");
      },
      error: (e) => {
        this.snackBar.open(e.error.errors, "Cerrar");
      }
    });
  }
}
