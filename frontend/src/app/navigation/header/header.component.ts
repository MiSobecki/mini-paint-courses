import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Subscription} from "rxjs";
import {AuthService} from "../../../shared/service/auth.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit, OnDestroy {

  private selectedPage: string;

  private isAuthenticatedSub: Subscription | undefined;

  isAuthenticated = false;
  username = '';

  constructor(private router: Router,
              private authService: AuthService) {
    this.selectedPage = this.router.url;
  }

  ngOnInit(): void {
    this.setSelectedPage();

    this.isAuthenticatedSub = this.authService.isAuthenticated$.subscribe((value: boolean) => {
      this.username = this.authService.getUsername();
      this.isAuthenticated = value;
    });

    this.router.events.subscribe(() => {
      if (this.selectedPage !== this.router.url) {
        this.setSelectedPage();
      }
    })
  }

  ngOnDestroy(): void {
    this.isAuthenticatedSub?.unsubscribe();
  }

  isPageSelected(title: string): boolean {
    return this.selectedPage === title;
  }

  openHomepage(): void {
    this.router.navigate(['/homepage']).finally();
  }

  openUserCourses(): void {
    this.router.navigate(['/user-courses']).finally();
  }

  openLogin(): void {
    this.router.navigate(['/login']).finally();
  }

  logout(): void {
    this.authService.logout();
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
