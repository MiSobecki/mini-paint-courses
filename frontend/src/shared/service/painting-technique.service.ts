import {Injectable} from '@angular/core';
import {BehaviorSubject, catchError, Observable, Subscription, tap} from "rxjs";
import {PaintingTechnique} from "../model/painting-technique";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PaintingTechniqueService {

  private readonly ROOT_URL = '/api/painting-techniques';

  private _paintingTechniques: BehaviorSubject<PaintingTechnique[]> = new BehaviorSubject<PaintingTechnique[]>([]);
  paintingTechniques$: Observable<PaintingTechnique[]> = this._paintingTechniques.asObservable();
  private paintingTechniquesSub: Subscription | undefined;

  constructor(private httpClient: HttpClient) {
  }

  findAll(): void {
    this.httpClient.get<PaintingTechnique[]>(environment.apiUrl + this.ROOT_URL).pipe(
      tap((paintingTechniques: PaintingTechnique[]) => {
        this._paintingTechniques.next(paintingTechniques);
      }),
      catchError((error) => {
        console.log(error);
        return error;
      })
    )
      .subscribe()
  }

  unsubscribe(): void {
    this.paintingTechniquesSub?.unsubscribe();
  }

}
