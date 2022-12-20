import {Injectable} from '@angular/core';
import {BehaviorSubject, catchError, Observable, Subscription, tap} from "rxjs";
import {CourseShortInfo} from "../model/course-short-info";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private readonly ROOT_URL = '/api/courses';

  private _coursesShortInfo: BehaviorSubject<CourseShortInfo[]> = new BehaviorSubject<CourseShortInfo[]>([]);
  coursesShortInfo$: Observable<CourseShortInfo[]> = this._coursesShortInfo.asObservable();
  private coursesShortInfoSub: Subscription | undefined;

  totalElements = 0;
  currentPage = 1;
  pageSize = 10;

  constructor(private httpClient: HttpClient,
              private authService: AuthService) {
  }

  findAll(pageNumber: number,
          username: string | undefined): void {
    let params = new HttpParams();
    params = params.append('page', pageNumber - 1);
    params = params.append('size', this.pageSize);

    if (username) {
      params = params.append('username', username);
    }

    this.coursesShortInfoSub = this.httpClient.get(environment.apiUrl + this.ROOT_URL, {params: params}).pipe(
      tap((response: any) => {
        this.totalElements = response.totalElements;
        this.currentPage = response.pageable.pageNumber + 1;

        const courses = response.content as CourseShortInfo[];
        this._coursesShortInfo.next(courses);
      })
    ).subscribe();
  }

  delete(id: string): void {
    const credentials = this.authService.getCredentials();

    if (credentials) {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        })
      };

      this.httpClient.delete(environment.apiUrl + this.ROOT_URL + '/' + id, httpOptions).pipe(
        tap(() => {
          let courses = this._coursesShortInfo.getValue();

          courses = courses.filter(x => x.id !== id);

          this._coursesShortInfo.next(courses);
        }),
        catchError((error) => {
          console.log(error);

          return error;
        })
      ).subscribe();
    }
  }

  unsubscribeCoursesShortInfo(): void {
    this.coursesShortInfoSub?.unsubscribe();
  }
}
