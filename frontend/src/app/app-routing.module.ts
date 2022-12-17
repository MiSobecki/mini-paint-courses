import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomepageComponent} from "./main-content/homepage/homepage.component";
import {LoginRegisterFormComponent} from "./main-content/login-register-form/login-register-form.component";
import {UserCoursesComponent} from "./main-content/user-courses/user-courses.component";
import {AuthGuard} from "../shared/guard/auth.guard";

const routes: Routes = [
  {path: '', redirectTo: '/homepage', pathMatch: 'full'},
  {path: 'homepage', title: 'Homepage', component: HomepageComponent},
  {path: 'user-courses', title: 'User Courses', component: UserCoursesComponent, canActivate: [AuthGuard]},
  {path: 'login', title: 'Login', component: LoginRegisterFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
