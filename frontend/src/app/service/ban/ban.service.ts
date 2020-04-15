import {Ban} from "../../models/ban";
import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BanService {

  constructor(private httpClient: HttpClient) { }

  getBans(): Observable<Ban[]> {
    return this.httpClient.get<Ban[]>("http://localhost:8080/api/bans");
  }
}





