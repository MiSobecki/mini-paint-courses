import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, Subscription, tap} from "rxjs";
import {CourseShortInfo} from "../model/course-short-info";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CourseServiceService {

  private readonly ROOT_URL = '/api/courses';

  private _coursesShortInfo: BehaviorSubject<CourseShortInfo[]> = new BehaviorSubject<CourseShortInfo[]>([]);
  coursesShortInfo$: Observable<CourseShortInfo[]> = this._coursesShortInfo.asObservable();
  private coursesShortInfoSub: Subscription | undefined;

  constructor(private httpClient: HttpClient) {
  }

  findAll(): void {
    this.coursesShortInfoSub = this.httpClient.get<CourseShortInfo[]>(environment.apiUrl + this.ROOT_URL).pipe(
      tap((courses: CourseShortInfo[]) => {
        this._coursesShortInfo.next(courses);
      })
    ).subscribe();
  }

  unsubscribeCoursesShortInfo(): void {
    this.coursesShortInfoSub?.unsubscribe();
  }
}
