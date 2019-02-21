import { Component, OnInit } from '@angular/core';
import { RestService } from '../../rest/rest.service';
import { ActivatedRoute, Router } from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-movement-detail',
  templateUrl: './movement-detail.component.html',
  styleUrls: ['./movement-detail.component.css']
})
export class MovementDetailComponent implements OnInit {

  movement: any;

  constructor(private location: Location, public rest: RestService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.rest.getMovement(this.route.snapshot.params['id']).subscribe((data: {}) => {
      console.log(data);
      this.movement = data;
    });
  }

  goBack() {
    this.location.back();
  }

}
