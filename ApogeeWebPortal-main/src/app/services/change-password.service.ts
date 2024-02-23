import { Injectable } from '@angular/core';
import {catchError, Observable, throwError} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ChangePasswordService {

  url = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  sendNewPassword(
    newPassword: string
  ): Observable<any>{

    const password = {
      value: newPassword
    }

    console.log('change password service works');
    return this.http.post<any>(this.url, newPassword)
      .pipe(
        catchError(this.handleError)
      )
  }

  private handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }
}

