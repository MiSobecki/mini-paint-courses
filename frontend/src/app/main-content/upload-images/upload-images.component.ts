import {Component, EventEmitter, Input, Output} from '@angular/core';
import {CourseStepImagesInfo} from "../../../shared/model/course-step-images-info";

@Component({
  selector: 'app-upload-images',
  templateUrl: './upload-images.component.html',
  styleUrls: ['./upload-images.component.scss']
})
export class UploadImagesComponent {

  @Input() courseStepImagesInfo?: CourseStepImagesInfo;
  @Output() courseStepImagesInfoChange = new EventEmitter<CourseStepImagesInfo>();

  selectFiles(event: any): void {
    if (this.courseStepImagesInfo) {
      this.courseStepImagesInfo.selectedFileNames = [];
      this.courseStepImagesInfo.selectedFiles = event.target.files;
      this.courseStepImagesInfo.previews = [];

      if (this.courseStepImagesInfo.selectedFiles && this.courseStepImagesInfo.selectedFiles[0]) {
        const numberOfFiles = this.courseStepImagesInfo.selectedFiles.length;
        for (let i = 0; i < numberOfFiles; i++) {
          const reader = new FileReader();

          reader.onload = (e: any) => {
            this.courseStepImagesInfo?.previews.push(e.target.result);
          };

          reader.readAsDataURL(this.courseStepImagesInfo.selectedFiles[i]);

          this.courseStepImagesInfo.selectedFileNames.push(this.courseStepImagesInfo.selectedFiles[i].name);
        }
      }

      this.courseStepImagesInfoChange.emit(this.courseStepImagesInfo);
    }
  }


}
