import {Component, OnInit} from '@angular/core';
import {Observable, of} from "rxjs";
import {CourseShortInfo} from "../../../shared/model/course-short-info";
import {CourseService} from "../../../shared/service/course.service";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent implements OnInit {

  courses: Observable<CourseShortInfo[]> = of([]);

  constructor(private courseService: CourseService) {
  }

  ngOnInit(): void {
    this.courses = this.courseService.coursesShortInfo$;

    this.courseService.findAll();
  }

}
