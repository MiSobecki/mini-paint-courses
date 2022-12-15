import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable, of, Subscription} from "rxjs";
import {CourseShortInfo} from "../../../shared/model/course-short-info";
import {CourseService} from "../../../shared/service/course.service";

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

  coursesSub: Subscription | undefined;

  constructor(private courseService: CourseService) {
  }

  ngOnInit(): void {
    this.courses = this.courseService.coursesShortInfo$;

    this.coursesSub = this.courseService.coursesShortInfo$.subscribe(() => {
      this.pageNumber = this.courseService.currentPage;
      this.totalElements = this.courseService.totalElements;
      this.pageSize = this.courseService.pageSize;
    })

    this.courseService.findAll(this.pageNumber);
  }

  ngOnDestroy() {
    this.coursesSub?.unsubscribe();
  }

}
