import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, firstValueFrom, Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Credentials} from "../model/credentials";
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly CREDENTIALS_STORAGE = 'credentials';

  private _isAuthenticated: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  isAuthenticated$: Observable<boolean> = this._isAuthenticated.asObservable();

  constructor(private httpClient: HttpClient,
              private snackBar: MatSnackBar) {
  }

  startupLogin(): void {
    const storedCredentials = sessionStorage.getItem(this.CREDENTIALS_STORAGE);

    if (storedCredentials) {
      const credentials: Credentials = JSON.parse(storedCredentials);

      this.sendLoginRequest(credentials.username, credentials.password).finally();
    }
  }

  login(username: string,
        password: string): Promise<boolean | void> {
    return this.sendLoginRequest(username, password);
  }

  logout(): void {
    sessionStorage.removeItem(this.CREDENTIALS_STORAGE);

    this._isAuthenticated.next(false);
  }

  register(username: string,
           password: string): Promise<boolean | void> {
    return firstValueFrom(this.httpClient.post(environment.apiUrl + '/api/users', new Credentials(username, password))).then(() => {
      sessionStorage.setItem(this.CREDENTIALS_STORAGE, JSON.stringify(new Credentials(username, password)));
      this._isAuthenticated.next(true);
    })
      .catch(error => {
        console.log(error);
        this.snackBar.open(error.message, 'Close', {
          duration: 3000
        });
      });
  }

  getUsername(): string {
    const storedCredentials = sessionStorage.getItem(this.CREDENTIALS_STORAGE);

    if (storedCredentials) {
      const credentials: Credentials = JSON.parse(storedCredentials);

      return credentials.username;
    }

    return '';
  }

  getCredentials(): Credentials | undefined {
    let credentials: Credentials | undefined;

    const storedCredentials = sessionStorage.getItem(this.CREDENTIALS_STORAGE);

    if (storedCredentials) {
      credentials = JSON.parse(storedCredentials);
    }

    return credentials;
  }

  private sendLoginRequest(username: string,
                           password: string): Promise<boolean | void> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa(username + ':' + password)
      })
    };

    return firstValueFrom(this.httpClient.get(environment.apiUrl + '/api/users', httpOptions)).then(() => {
      sessionStorage.setItem(this.CREDENTIALS_STORAGE, JSON.stringify(new Credentials(username, password)));
      this._isAuthenticated.next(true);
    })
      .catch(error => {
        console.log(error);
        this.snackBar.open(error.message, 'Close', {
          duration: 3000
        });
      });
  }

}
