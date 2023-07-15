import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ClientService} from "../../services/client.service";
import {Client} from "../../model/client.model";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-update-client',
  templateUrl: './update-client.component.html',
  styleUrls: ['./update-client.component.scss']
})
export class UpdateClientComponent implements OnInit{

  client: Client = {};

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private clientService: ClientService,
    private snackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {
    const identification = this.route.snapshot.paramMap.get('id');
    const identificationType = this.route.snapshot.queryParamMap.get('identificationType');
    const clientToFind: Client = {
      identification: identification == null ? undefined : parseInt(identification),
      identificationType: identificationType == null ? '' : identificationType
    }

    this.clientService.findClient(clientToFind).subscribe({
      next: (data) => {
        this.client = data;
      },
      error: (e) => {
        this.snackBar.open(e.error.errors, "Cerrar");
        this.router.navigateByUrl('clients');
      }
    });
  }

  updateClient(){
    this.clientService.updateClient(this.client).subscribe({
      next: (data) => {
        this.snackBar.open('Cliente actualizado exitosamente', "Cerrar");
      },
      error: (e) => {
        this.snackBar.open(e.error.errors, "Cerrar");
      }
    });
  }

  checkClient(): boolean{
    return this.client.identification == null || this.client.identification == undefined || this.client.identification == 0 ||
      this.client.identificationType == null || this.client.identificationType == undefined || this.client.identificationType == '' ||
      this.client.gender == null || this.client.gender == undefined ||this.client.gender == '' ||
      this.client.name == null || this.client.name == undefined ||this.client.name == '';
  }
}
