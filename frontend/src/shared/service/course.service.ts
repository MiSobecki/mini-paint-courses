import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, Subscription, tap} from "rxjs";
import {CourseShortInfo} from "../model/course-short-info";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private readonly ROOT_URL = '/api/courses';

  private _coursesShortInfo: BehaviorSubject<CourseShortInfo[]> = new BehaviorSubject<CourseShortInfo[]>([]);
  coursesShortInfo$: Observable<CourseShortInfo[]> = this._coursesShortInfo.asObservable();
  private coursesShortInfoSub: Subscription | undefined;

  totalPages = 0;

  constructor(private httpClient: HttpClient) {
  }

  findAll(): void {
    this.coursesShortInfoSub = this.httpClient.get(environment.apiUrl + this.ROOT_URL).pipe(
      tap((response: any) => {
        const courses = response.content as CourseShortInfo[];
        this._coursesShortInfo.next(courses);

        this.totalPages = response.totalPages;
      })
    ).subscribe();
  }

  unsubscribeCoursesShortInfo(): void {
    this.coursesShortInfoSub?.unsubscribe();
  }
}
