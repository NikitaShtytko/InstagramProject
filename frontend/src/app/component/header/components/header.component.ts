import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

// import { NavbarModule, WavesModule, ButtonsModule } from 'angular-bootstrap-md';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {

  constructor(private router: Router) {}

  ngOnInit(): void {
  }
}
