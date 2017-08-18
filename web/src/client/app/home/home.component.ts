import {Component, OnInit} from '@angular/core';

import {User} from '../_models/index';
import {UserService} from '../_services/index';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../_services/authentication.service";
import {AlertService} from "../_services/alert.service";
import {AdresService} from "../_services/adres.service";
import {Adres} from "../_models/adres";

@Component({
  moduleId: module.id,
  templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {
  currentUser: User;
  users: User[] = [];
  returnUrl: string;
  adresler :Adres[]=[];

  constructor(private userService: UserService,
              private adresService:AdresService,
              private route: ActivatedRoute,
              private router: Router,
              private authenticationService: AuthenticationService,
              private alertService: AlertService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.loadAllUsers();
  }

  deleteUser(id: number) {
    this.userService.delete(id).subscribe(() => {
      this.loadAllUsers()
    });
  }

  private loadAllUsers() {
    this.adresService.getAll().subscribe(adresler => {
      this.adresler = adresler;
      console.log(adresler);
    });
  }
}
