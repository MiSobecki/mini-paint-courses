import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, Subscription, tap} from "rxjs";
import {CourseShortInfo} from "../model/course-short-info";
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";

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

  constructor(private httpClient: HttpClient) {
  }

  findAll(pageNumber: number): void {
    let params = new HttpParams();
    params = params.append('page', pageNumber - 1);
    params = params.append('size', this.pageSize);

    this.coursesShortInfoSub = this.httpClient.get(environment.apiUrl + this.ROOT_URL, {params: params}).pipe(
      tap((response: any) => {
        this.totalElements = response.totalElements;
        this.currentPage = response.pageable.pageNumber + 1;

        const courses = response.content as CourseShortInfo[];
        this._coursesShortInfo.next(courses);
      })
    ).subscribe();
  }

  unsubscribeCoursesShortInfo(): void {
    this.coursesShortInfoSub?.unsubscribe();
  }
}
