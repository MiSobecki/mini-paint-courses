import {Injectable} from '@angular/core';
import {BehaviorSubject, catchError, Observable, Subscription, tap} from "rxjs";
import {MiniatureShortInfo} from "../model/miniature-short-info";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class MiniatureService {

  private readonly ROOT_URL = '/api'

  private _miniaturesShortInfo: BehaviorSubject<MiniatureShortInfo[]> = new BehaviorSubject<MiniatureShortInfo[]>([]);
  miniaturesShortInfo$: Observable<MiniatureShortInfo[]> = this._miniaturesShortInfo.asObservable();
  private miniaturesShortInfoSub?: Subscription;

  constructor(private httpClient: HttpClient,
              private snackBar: MatSnackBar) {
  }

  findAllByFaction(factionId: string): void {
    this.miniaturesShortInfoSub = this.httpClient.get<MiniatureShortInfo[]>(
      environment.apiUrl + this.ROOT_URL + '/factions/' + factionId + '/miniatures')
      .pipe(
        tap((games: MiniatureShortInfo[]) => {
          this._miniaturesShortInfo.next(games);
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
    this.miniaturesShortInfoSub?.unsubscribe();
  }

}
