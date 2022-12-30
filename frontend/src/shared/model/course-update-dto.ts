import {CourseStep} from "./course-step";

export class CourseUpdateDto {

  constructor(title: string,
              shortDescription: string,
              steps: CourseStep[]) {
    this.title = title;
    this.shortDescription = shortDescription;
    this.steps = steps;
  }

  title!: string;
  shortDescription!: string;
  steps!: CourseStep[];

}
