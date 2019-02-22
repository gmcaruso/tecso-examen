import { Component, OnInit, Input } from '@angular/core';
import { RestService } from '../../rest/rest.service';
import { ActivatedRoute, Router } from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-account-detail',
  templateUrl: './account-detail.component.html',
  styleUrls: ['./account-detail.component.css']
})
export class AccountDetailComponent implements OnInit {

  account: any;
  isNew: false;
  error: null;
  typeOfCurrency: ['PESOS', 'EUROS', 'DOLARES'];
  @Input()  newAccount = {accountNumber: '', currency: 'PESOS', balance: '', movements: []};

  constructor(private location: Location, public rest: RestService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.isNew = this.route.snapshot.params['type'];
    if (!this.isNew) {
      this.rest.getAccount(this.route.snapshot.params['id']).subscribe((data: {}) => {
        console.log(data);
        this.account = data;
      });
    }
  }

  saveAccount() {
    this.rest.postAccount(this.newAccount).subscribe((data: {}) => {
      console.log(data);
    });
    this.router.navigateByUrl('');
    window.location.reload();
  }

  deleteAccount() {
    this.rest.deleteAccount(this.route.snapshot.params['id'])
      .subscribe(
        data => this.redirect(),
        error => this.error = error
      );
  }

  private redirect() {
    this.router.navigateByUrl('');
    window.location.reload();
  }
}
