export class CourseStepImage {

  constructor(courseStepId: string,
              imageData: any) {
    this.courseStepId = courseStepId;
    this.imageData = imageData;
  }

  id!: string;
  courseStepId!: string;
  imageData!: any;

}
