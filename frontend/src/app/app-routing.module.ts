import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomepageComponent} from "./main-content/homepage/homepage.component";
import {LoginRegisterFormComponent} from "./main-content/login-register-form/login-register-form.component";
import {UserCoursesComponent} from "./main-content/user-courses/user-courses.component";
import {AuthGuard} from "../shared/guard/auth.guard";
import {CourseDisplayPageComponent} from "./main-content/course-display-page/course-display-page.component";
import {CourseCreationPageComponent} from "./main-content/course-creation-page/course-creation-page.component";
import {CourseUpdatePageComponent} from "./main-content/course-update-page/course-update-page.component";

const routes: Routes = [
  {path: '', redirectTo: '/homepage', pathMatch: 'full'},
  {path: 'homepage', title: 'Homepage', component: HomepageComponent},
  {path: 'user-courses', title: 'UserDto Courses', component: UserCoursesComponent, canActivate: [AuthGuard]},
  {path: 'login', title: 'Login', component: LoginRegisterFormComponent},
  {path: 'courses/:courseId', title: 'Course', component: CourseDisplayPageComponent},
  {path: 'course-create', title: 'Course creation', component: CourseCreationPageComponent, canActivate: [AuthGuard]},
  {
    path: 'course-update/:courseId',
    title: 'Course update',
    component: CourseUpdatePageComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
