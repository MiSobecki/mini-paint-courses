import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, catchError, Observable, Subscription, tap} from "rxjs";
import {Paint} from "../model/paint";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PaintService {

  private readonly ROOT_URL = '/api/paints';

  private _paints: BehaviorSubject<Paint[]> = new BehaviorSubject<Paint[]>([]);
  paints$: Observable<Paint[]> = this._paints.asObservable();
  private paintsSub: Subscription | undefined;

  constructor(private httpClient: HttpClient) {
  }

  findAll(): void {
    this.httpClient.get<Paint[]>(environment.apiUrl + this.ROOT_URL).pipe(
      tap((paints: Paint[]) => {
        this._paints.next(paints);
      }),
      catchError((error) => {
        console.log(error);
        return error;
      })
    )
      .subscribe()
  }

  unsubscribe(): void {
    this.paintsSub?.unsubscribe();
  }

}
