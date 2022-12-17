import {Component, Input} from '@angular/core';
import {CourseShortInfo} from "../../../../shared/model/course-short-info";

@Component({
  selector: 'app-user-course-short-info',
  templateUrl: './user-course-short-info.component.html',
  styleUrls: ['./user-course-short-info.component.scss']
})
export class UserCourseShortInfoComponent {

  @Input() course: CourseShortInfo = new CourseShortInfo();

  openCourse(): void {
    // TODO
    console.log('Open Course');
  }

  updateCourse(): void {
    // TODO
    console.log('Update Course');
  }

  deleteCourse(): void {
    // TODO
    console.log('Delete Course');
  }

}
