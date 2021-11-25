import { CompanyService } from './../../service/company.service';
import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/model/company.model';

@Component({
  selector: 'app-companies-table',
  templateUrl: './companies-table.component.html',
  styleUrls: ['./companies-table.component.scss']
})
export class CompaniesTableComponent implements OnInit {

  companies: Company[];

  constructor(private service: CompanyService) { }

  ngOnInit() {
  }

  carregarCompanies() {
    this.service.listarCompanies().subscribe(
      (companies: Company[]) => {
        this.companies = companies;
      }, (error: any) => {
        console.log( error);
      }
    );
  }

}
