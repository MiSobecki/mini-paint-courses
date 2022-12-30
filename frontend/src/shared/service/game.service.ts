import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, catchError, Observable, Subscription, tap} from "rxjs";
import {GameShortInfo} from "../model/game-short-info";
import {environment} from "../../environments/environment";
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private readonly ROOT_URL = '/api/games'

  private _gamesShortInfo: BehaviorSubject<GameShortInfo[]> = new BehaviorSubject<GameShortInfo[]>([]);
  gamesShortInfo$: Observable<GameShortInfo[]> = this._gamesShortInfo.asObservable();
  private gamesShortInfoSub?: Subscription;

  constructor(private httpClient: HttpClient,
              private snackBar: MatSnackBar) {
  }

  findAll(): void {
    this.gamesShortInfoSub = this.httpClient.get<GameShortInfo[]>(environment.apiUrl + this.ROOT_URL)
      .pipe(
        tap((games: GameShortInfo[]) => {
          this._gamesShortInfo.next(games);
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
    this.gamesShortInfoSub?.unsubscribe();
  }

}
