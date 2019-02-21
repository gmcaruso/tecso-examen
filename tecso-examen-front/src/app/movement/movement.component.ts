import { Component, OnInit } from '@angular/core';
import { RestService } from '../../rest/rest.service';
import { ActivatedRoute, Router } from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-movement',
  templateUrl: './movement.component.html',
  styleUrls: ['./movement.component.css']
})
export class MovementComponent implements OnInit {

  movements: any = [];

  constructor(private location: Location, public rest: RestService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.getMovements();
  }

  getMovements() {
    this.movements = [];
    this.rest.getMovements(this.route.snapshot.params['id']).subscribe((data: {}) => {
      console.log(data);
      this.movements = data;
    });
  }

  goBack() {
    this.location.back();
  }

}
