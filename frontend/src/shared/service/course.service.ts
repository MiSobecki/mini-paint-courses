import {Injectable} from '@angular/core';
import {BehaviorSubject, catchError, firstValueFrom, map, Observable, Subscription, tap} from "rxjs";
import {CourseShortInfo} from "../model/course-short-info";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {AuthService} from "./auth.service";
import {Course} from "../model/course";
import {CourseStep} from "../model/course-step";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {CourseUpdateDto} from "../model/course-update-dto";

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
              private authService: AuthService,
              private snackBar: MatSnackBar,
              private router: Router) {
  }

  create(course: Course): void {
    this.convertPaintsMapsToJavaMap(course);

    const credentials = this.authService.getCredentials();

    if (credentials) {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        })
      };

      firstValueFrom(this.httpClient.post(environment.apiUrl + this.ROOT_URL, course, httpOptions))
        .then(() => {
          this.snackBar.open('Created course successfully', 'Close', {
            duration: 3000
          });

          this.router.navigate(['/user-courses']).finally();
        })
        .catch(error => {
          console.log(error);
          this.snackBar.open(error.message, 'Close', {
            duration: 3000
          });
        });
    }
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
      }),
      catchError(error => {
        console.log(error);
        this.snackBar.open(error.message, 'Close', {
          duration: 3000
        });
        return error;
      })
    ).subscribe();
  }

  find(id: string): Promise<Course> {
    return firstValueFrom(this.httpClient.get<Course>(environment.apiUrl + this.ROOT_URL + '/' + id)
      .pipe(
        map(course => {
          course.steps = course.steps.map(step => this.convertPaintsMap(step));
          return course;
        })
      ));
  }

  update(course: CourseUpdateDto,
         id: string): void {
    this.convertPaintsMapsToJavaMap(course);

    const credentials = this.authService.getCredentials();

    if (credentials) {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        })
      };

      firstValueFrom(this.httpClient.patch(environment.apiUrl + this.ROOT_URL + '/' + id, course, httpOptions))
        .then(() => {
          this.snackBar.open('Updated course successfully', 'Close', {
            duration: 3000
          });

          this.router.navigate(['/user-courses']).finally();
        })
        .catch(error => {
          console.log(error);
          this.snackBar.open(error.message, 'Close', {
            duration: 3000
          });
        });
    }
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

          this.snackBar.open('Deleted course successfully', 'Close', {
            duration: 3000
          });
        }),
        catchError((error) => {
          console.log(error);
          this.snackBar.open(error.message, 'Close', {
            duration: 3000
          });
          return error
        })
      ).subscribe();
    }
  }

  unsubscribeCoursesShortInfo(): void {
    this.coursesShortInfoSub?.unsubscribe();
  }

  private convertPaintsMap(courseStep: CourseStep): CourseStep {
    const paints = courseStep.paintTechniqueIdToPaintIdMap as unknown as { [name: string]: string };

    const newPaints: Map<string, string> = new Map<string, string>();
    Object.keys(paints).forEach(key => {
      newPaints.set(key, paints[key]);
    });

    courseStep.paintTechniqueIdToPaintIdMap = newPaints;

    return courseStep;
  }

  private convertPaintsMapsToJavaMap(course: Course | CourseUpdateDto): void {
    course.steps.forEach(x => {
      const paints = Object.create(null);
      for (const [k, v] of x.paintTechniqueIdToPaintIdMap) {
        paints[k] = v;
      }
      x.paintTechniqueIdToPaintIdMap = paints;
    });
  }

}
