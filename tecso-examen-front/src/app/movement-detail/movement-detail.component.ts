import { Component, OnInit, Input } from '@angular/core';
import { RestService } from '../../rest/rest.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-movement-detail',
  templateUrl: './movement-detail.component.html',
  styleUrls: ['./movement-detail.component.css']
})
export class MovementDetailComponent implements OnInit {

  error: null;
  movement: any;
  isNew: false;
  typeOfMovement: ['CREDIT', 'DEBIT'];
  @Input()  newMovement = {description: '', typeOfMovement: 'CREDIT', date: '', amount: []};

  constructor(private location: Location, public rest: RestService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.isNew = this.route.snapshot.params['type'];
    if (!this.isNew) {
      this.rest.getMovement(this.route.snapshot.params['id']).subscribe((data: {}) => {
        console.log(data);
        this.movement = data;
      });
    }
  }

  goBack() {
    this.location.back();
  }

  saveMovement() {
    this.rest.patchMovement(this.route.snapshot.params['type'], this.newMovement)
    .subscribe(
      data => this.goBack(),
      error => this.error = error
    );
  }

}
