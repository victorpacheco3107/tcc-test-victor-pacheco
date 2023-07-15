import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Client} from "../model/client.model";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private url: string = "http://localhost:5000/api/v1/client";

  constructor(private httpClient: HttpClient) { }

  findClient(client: Client){
    return this.httpClient.get<Client>(`${this.url}/${client.identification}?identificationType=${client.identificationType}` );
  }

  findAllClients(){
    return this.httpClient.get<Client[]>(this.url);
  }

  createClient(client: Client){
    return this.httpClient.post<Client>(this.url, client);
  }

  deleteClient(client: Client){
    return this.httpClient.delete<Client>(`${this.url}/${client.identification}?identificationType=${client.identificationType}` );
  }

  updateClient(client: Client){
    return this.httpClient.put<Client>(`${this.url}/${client.identification}?identificationType=${client.identificationType}`, client);
  }

}
