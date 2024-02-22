import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { RegisterService } from '../sercices/register.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerService = inject(RegisterService);

  applyForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
    phoneNumber: new FormControl(''),
    employeeNumber: new FormControl(''),
    role: new FormControl('')
  })

  submitRegistrationForm() {
    this.registerService.sendRegistrationData(
      this.applyForm.value.firstName ?? '',
      this.applyForm.value.lastName ?? '',
      this.applyForm.value.email ?? '',
      this.applyForm.value.phoneNumber ?? '',
      this.applyForm.value.employeeNumber ?? '',
      this.applyForm.value.role ?? ''
    );
    }
}
