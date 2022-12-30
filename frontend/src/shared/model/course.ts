import {CourseStep} from "./course-step";
import {CourseMiniature} from "./course-miniature";
import {UserDto} from "./user-dto";

export class Course {

  constructor(id: string | null,
              title: string,
              shortDescription: string,
              steps: CourseStep[],
              miniature: CourseMiniature,
              user: UserDto | null) {
    if (id) {
      this.id = id;
    }
    this.title = title;
    this.shortDescription = shortDescription;
    this.steps = steps;
    this.miniature = miniature;
    if (user) {
      this.user = user;
    }
  }

  id!: string;
  title!: string;
  shortDescription!: string;
  steps!: CourseStep[];
  miniature!: CourseMiniature
  user!: UserDto;


}
