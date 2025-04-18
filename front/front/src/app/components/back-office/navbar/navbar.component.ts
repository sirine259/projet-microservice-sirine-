import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',

  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
 constructor(private route:Router){}
   logout(){
    localStorage.clear()
    this.route.navigateByUrl("/")
  }
  isconnect(){
    return localStorage.getItem("state") ? true :false;
  }
}
