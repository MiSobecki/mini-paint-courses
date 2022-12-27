import {Component, Input} from '@angular/core';
import {CourseShortInfo} from "../../../../shared/model/course-short-info";

@Component({
  selector: 'app-homepage-course-short-info',
  templateUrl: './homepage-course-short-info.component.html',
  styleUrls: ['./homepage-course-short-info.component.scss']
})
export class HomepageCourseShortInfoComponent {

  @Input() course: CourseShortInfo = new CourseShortInfo();

}
