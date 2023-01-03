import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, catchError, Observable, Subscription, tap} from "rxjs";
import {Producer} from "../model/producer";
import {environment} from "../../environments/environment";
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class ProducerService {

  private readonly ROOT_URL = '/api/producers'

  private _producers: BehaviorSubject<Producer[]> = new BehaviorSubject<Producer[]>([]);
  producers$: Observable<Producer[]> = this._producers.asObservable();
  producersSub: Subscription | undefined;

  constructor(private httpClient: HttpClient,
              private snackBar: MatSnackBar) {
  }

  findAll(): void {
    this.httpClient.get<Producer[]>(environment.apiUrl + this.ROOT_URL).pipe(
      tap((producers: Producer[]) => {
        this._producers.next(producers);
      }),
      catchError((error) => {
        console.log(error);
        this.snackBar.open(error.message, 'Close', {
          duration: 3000
        });
        return error;
      })
    ).subscribe();
  }

  unsubscribe(): void {
    this.producersSub?.unsubscribe();
  }

}
