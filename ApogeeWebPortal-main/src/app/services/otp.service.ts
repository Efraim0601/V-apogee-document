import { Injectable } from '@angular/core';
import {catchError, Observable, throwError} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class OtpService {

  url = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  sendOtpCode(first: number,
              second: number,
              third: number,
              fourth: number,
              fifth: number,
              sixth: number): Observable<any>{
    const otpCode = {
      firstDigit: first,
      secondDigit: second,
      thirdDigit: third,
      fourthDigit: fourth,
      fifthDigit: fifth,
      sixthDigit: sixth
    }

    console.log('otp work');
    return this.http.post<any>(this.url, otpCode)
      .pipe(
        catchError(this.handleError)
      );
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

