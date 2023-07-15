import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ClientService} from "../../services/client.service";
import {Client} from "../../model/client.model";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.scss']
})
export class ClientsComponent implements AfterViewInit{

  clients: Client[] = [];

  displayedColumns: string[] = ['identificationType', 'identification', 'name', 'gender', 'options'];
  dataSource = new MatTableDataSource<Client>(this.clients);

  @ViewChild(MatPaginator) paginator :any = MatPaginator;

  constructor(
    private clientService: ClientService,
    private snackBar: MatSnackBar) {
  }

  ngAfterViewInit(): void {
    this.init();
  }

  deleteClient(client: Client){
    this.clientService.deleteClient(client).subscribe({
      next: (data) => {
        this.init();
        this.snackBar.open('Cliente borrado exitosamente', "Cerrar");
      },
      error: (e) => {
        this.snackBar.open(e.error.errors, "Cerrar");
      }
    });
  }

  init(){
    this.clientService.findAllClients().subscribe(data => {
      this.clients = data;
      this.dataSource = new MatTableDataSource<Client>(this.clients);
      this.dataSource.paginator = this.paginator;
    });
  }
}
