import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable, of, Subscription} from "rxjs";
import {CourseShortInfo} from "../../../shared/model/course-short-info";
import {CourseService} from "../../../shared/service/course.service";
import {AuthService} from "../../../shared/service/auth.service";
import {CourseFilters} from "../../../shared/model/course-filters";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-courses',
  templateUrl: './user-courses.component.html',
  styleUrls: ['./user-courses.component.scss']
})
export class UserCoursesComponent implements OnInit, OnDestroy {

  courses: Observable<CourseShortInfo[]> = of([]);

  pageNumber = 1;
  totalElements = 1;
  pageSize = 1;

  coursesSub: Subscription | undefined;

  filters: CourseFilters = new CourseFilters(
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '');

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
    });

    this.filters.username = this.authService.getUsername();

    this.courseService.findAll(this.pageNumber, this.filters);
  }

  ngOnDestroy() {
    this.coursesSub?.unsubscribe();
  }

  onSearch(filters: CourseFilters): void {
    this.filters = filters;

    this.filters.username = this.authService.getUsername();

    this.courseService.findAll(this.pageNumber, this.filters);
  }

  onPageChange(): void {
    this.courseService.findAll(this.pageNumber, this.filters);
  }

  openCourseCreation(): void {
    this.router.navigate(['/course-create']).finally();
  }

}
