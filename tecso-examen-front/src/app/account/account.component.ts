import { Component, OnInit } from '@angular/core';
import { RestService } from '../../rest/rest.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  accounts: any = [];

  constructor(public rest: RestService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.getProducts();
  }

  getProducts() {
    this.accounts = [];
    this.rest.getAccounts().subscribe((data: {}) => {
      console.log(data);
      this.accounts = data;
    });
  }
}
