import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatSidenavModule} from "@angular/material/sidenav";
import {HeaderComponent} from './navigation/header/header.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {HomepageComponent} from './main-content/homepage/homepage.component';
import {
  HomepageCourseShortInfoComponent
} from './main-content/homepage/homepage-course-short-info/homepage-course-short-info.component';
import {MatCardModule} from "@angular/material/card";
import {HttpClientModule} from '@angular/common/http';
import {MatGridListModule} from "@angular/material/grid-list";
import {MatPaginatorModule} from "@angular/material/paginator";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginRegisterFormComponent } from './main-content/login-register-form/login-register-form.component';
import {FormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import { UserCoursesComponent } from './main-content/user-courses/user-courses.component';
import { UserCourseShortInfoComponent } from './main-content/user-courses/user-course-short-info/user-course-short-info.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomepageComponent,
    HomepageCourseShortInfoComponent,
    LoginRegisterFormComponent,
    UserCoursesComponent,
    UserCourseShortInfoComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatSidenavModule,
        MatToolbarModule,
        MatButtonModule,
        MatCardModule,
        HttpClientModule,
        MatGridListModule,
        MatPaginatorModule,
        NgbModule,
        FormsModule,
        MatFormFieldModule,
        MatInputModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
