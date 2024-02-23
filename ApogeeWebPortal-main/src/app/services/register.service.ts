import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";


const BASE_URL = ["http://localhost:8080/"]

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  register(signRequest: any): Observable<any> {
    return this.http.post(BASE_URL + 'signup', signRequest)
  }


 /* url = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  sendRegistrationData(firstName: string,
    lastName: string,
    email: string,
    phoneNumber: string,
    employeeNumber: string,
    role: string) {

      const registrationData = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        phoneNumber: phoneNumber,
        employeeNumber: employeeNumber,
        role: role
      }

      return this.http.post<any>(this.url, registrationData);
  }*/
}
