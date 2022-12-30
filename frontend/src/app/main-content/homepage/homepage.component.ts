import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable, of, Subscription} from "rxjs";
import {CourseShortInfo} from "../../../shared/model/course-short-info";
import {CourseService} from "../../../shared/service/course.service";
import {AuthService} from "../../../shared/service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent implements OnInit, OnDestroy {

  courses: Observable<CourseShortInfo[]> = of([]);

  pageNumber = 1;
  totalElements = 1;
  pageSize = 1;

  isAuthenticated = false;

  coursesSub: Subscription | undefined;
  isAuthenticatedSub?: Subscription;

  constructor(private courseService: CourseService,
              private authService: AuthService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.courses = this.courseService.coursesShortInfo$;

    this.coursesSub = this.courseService.coursesShortInfo$.subscribe(() => {
      this.pageNumber = this.courseService.currentPage;
      this.totalElements = this.courseService.totalElements;
      this.pageSize = this.courseService.pageSize;
    })

    this.courseService.findAll(this.pageNumber, undefined);

    this.authService.isAuthenticated$.subscribe((value: boolean) => {
      this.isAuthenticated = value;
    })
  }

  ngOnDestroy() {
    this.coursesSub?.unsubscribe();
    this.isAuthenticatedSub?.unsubscribe();
  }

  openCourseCreation(): void {
    this.router.navigate(['/course-create']).finally();
  }

}
