import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  private selectedPage: string;

  constructor(private router: Router) {
    this.selectedPage = '/homepage';

    this.setSelectedPage();
  }

  isPageSelected(title: string): boolean {
    return this.selectedPage === title;
  }

  private setSelectedPage(): void {
    const routeUrl = this.router.url;

    if (routeUrl === '/') {
      this.selectedPage = '/homepage'
    } else {
      this.selectedPage = routeUrl;
    }
  }

}
