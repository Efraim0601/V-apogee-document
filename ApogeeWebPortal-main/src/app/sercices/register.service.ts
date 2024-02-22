import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  url = 'http://localhost:8080/api';

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
  }
}
