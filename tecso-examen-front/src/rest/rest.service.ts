import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  constructor(private http: HttpClient) { }

   endpoint = 'http://localhost:8080/api/';
   httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };

  private extractData(res: Response) {
    const body = res;
    return body || { };
  }
//////////////////////////////////////// CUENTAS ////////////////////////////////////////////
  postAccount(account): Observable<any> {
    console.log(account);
    return this.http.post<any>(this.endpoint + 'account/crear-cuenta', JSON.stringify(account), this.httpOptions);
  }

  getAccount(id): Observable<any> {
    return this.http.get(this.endpoint + 'account/encontrar-cuenta?accountId=' + id).pipe(
      map(this.extractData));
  }

  getAccounts(): Observable<any> {
    return this.http.get(this.endpoint + 'account/listar-cuentas').pipe(
      map(this.extractData));
  }

//////////////////////////////////////// MOVIMIENTOS ////////////////////////////////////////

  patchMovement(idAccount, movement): Observable<any> {
    console.log(movement);
    return this.http.
      patch<any>(this.endpoint + 'movement/agregar-movimiento?accountId=' + idAccount, JSON.stringify(movement), this.httpOptions);
  }
  getMovement(id): Observable<any> {
    return this.http.get(this.endpoint + 'movement/encontrar-movimiento?accountId=' + id).pipe(
      map(this.extractData));
  }

  getMovements(id): Observable<any> {
    return this.http.get(this.endpoint + 'movement/listar-movimientos?accountId=' + id).pipe(
      map(this.extractData));
  }
}
