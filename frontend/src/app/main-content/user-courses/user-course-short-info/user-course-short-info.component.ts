import {Component, Input} from '@angular/core';
import {CourseShortInfo} from "../../../../shared/model/course-short-info";
import {CourseService} from "../../../../shared/service/course.service";

@Component({
  selector: 'app-user-course-short-info',
  templateUrl: './user-course-short-info.component.html',
  styleUrls: ['./user-course-short-info.component.scss']
})
export class UserCourseShortInfoComponent {

  @Input() course: CourseShortInfo = new CourseShortInfo();

  constructor(private courseService: CourseService) {
  }

  openCourse(): void {
    // TODO
    console.log('Open Course');
  }

  updateCourse(): void {
    // TODO
    console.log('Update Course');
  }

  deleteCourse(): void {
    this.courseService.delete(this.course.id);
  }

}
