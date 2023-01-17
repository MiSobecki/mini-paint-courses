import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpRequest} from "@angular/common/http";
import {firstValueFrom} from "rxjs";
import {CourseStepImage} from "../model/course-step-image";
import {environment} from "../../environments/environment";
import {AuthService} from "./auth.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private readonly ROOT_URL = "/api/course-step-images"

  constructor(private httpClient: HttpClient,
              private authService: AuthService,
              private snackBar: MatSnackBar) {
  }

  upload(image: File,
         courseStepId: string): void {
    const credentials = this.authService.getCredentials();

    if (credentials) {
      const formData = new FormData();

      formData.append('image', image);

      const headers = new HttpHeaders({
        'Accept': 'application/json',
        'Authorization': 'Basic ' + btoa(credentials.username + ':' + credentials.password)
      })

      const req = new HttpRequest('POST', `${environment.apiUrl}${this.ROOT_URL}/${courseStepId}`, formData, {
        headers: headers,
        reportProgress: true,
        responseType: 'json'
      });

      this.httpClient.request(req).subscribe(null, ((error) => {
        console.log(error);
        this.snackBar.open(error.message, 'Close', {
          duration: 3000
        });
      }));
    }
  }

  download(courseStepId: string): Promise<CourseStepImage[]> {
    return firstValueFrom(this.httpClient.get<CourseStepImage[]>(environment.apiUrl + this.ROOT_URL + '/' + courseStepId));
  }

  delete(id: string): void {
    this.httpClient.delete(environment.apiUrl + this.ROOT_URL + '/' + id);
  }

}
