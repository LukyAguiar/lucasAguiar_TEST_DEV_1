import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Company } from 'src/app/model/company.model';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })
  export class CompanyService {
    constructor(private httpClient: HttpClient) {
    }

    public listarCompanies(): Observable<Company[]> {
        return this.httpClient.get<Company[]>('http://localhost:8080/stock/list');
      }
  }



