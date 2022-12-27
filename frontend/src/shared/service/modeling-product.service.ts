import {Injectable} from '@angular/core';
import {BehaviorSubject, catchError, Observable, Subscription, tap} from "rxjs";
import {ModelingProduct} from "../model/modeling-product";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ModelingProductService {

  private readonly ROOT_URL = '/api/modeling-products';

  private _modelingProducts: BehaviorSubject<ModelingProduct[]> = new BehaviorSubject<ModelingProduct[]>([]);
  modelingProducts$: Observable<ModelingProduct[]> = this._modelingProducts.asObservable();
  private modelingProductsSub: Subscription | undefined;

  constructor(private httpClient: HttpClient) {
  }

  findAll(): void {
    this.httpClient.get<ModelingProduct[]>(environment.apiUrl + this.ROOT_URL).pipe(
      tap((modelingProducts: ModelingProduct[]) => {
        this._modelingProducts.next(modelingProducts);
      }),
      catchError((error) => {
        console.log(error);
        return error;
      })
    )
      .subscribe()
  }

  unsubscribe(): void {
    this.modelingProductsSub?.unsubscribe();
  }

}
