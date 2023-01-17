import {Observable} from "rxjs";

export class CourseStepImagesInfo {

  courseStepId!: string;
  selectedFiles!: FileList;
  selectedFileNames: string[] = [];

  previews: string[] = [];
  imageInfos!: Observable<any>;

}
