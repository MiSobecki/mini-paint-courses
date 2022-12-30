import {Injectable} from '@angular/core';
import {BehaviorSubject, catchError, Observable, Subscription, tap} from "rxjs";
import {FactionShortInfo} from "../model/faction-short-info";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class FactionService {

  private readonly ROOT_URL = '/api'

  private _factionsShortInfo: BehaviorSubject<FactionShortInfo[]> = new BehaviorSubject<FactionShortInfo[]>([]);
  factionsShortInfo$: Observable<FactionShortInfo[]> = this._factionsShortInfo.asObservable();
  private factionsShortInfoSub?: Subscription;

  constructor(private httpClient: HttpClient,
              private snackBar: MatSnackBar) {
  }

  findAllByGame(gameId: string): void {
    this.factionsShortInfoSub = this.httpClient.get<FactionShortInfo[]>(
      environment.apiUrl + this.ROOT_URL + '/games/' + gameId + '/factions')
      .pipe(
        tap((games: FactionShortInfo[]) => {
          this._factionsShortInfo.next(games);
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
    this.factionsShortInfoSub?.unsubscribe();
  }

}
