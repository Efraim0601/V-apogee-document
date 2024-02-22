import {Component, signal} from '@angular/core';
import { RouterModule } from '@angular/router';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {RegisterService} from "../sercices/register.service";

@Component({
  selector: 'app-login',
  standalone: true,
    imports: [
        RouterModule,
        FormsModule,
        ReactiveFormsModule
    ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(
    private service: RegisterService,
    private fb: FormBuilder
  ) {
    this.loginForm = this.fb.group({
      email : ['', [Validators.required, Validators.email]],
      password : ['', [Validators.required]],
      confirmPassword : ['', [Validators.required]],
    }, {validators: this.passwordMathValidator})
  }

  ngOnInit(): void {
  }




  passwordMathValidator(formGroup: FormGroup) {
    const password = formGroup.get('password')?.value;
    const confirmPassword = formGroup.get('confirmPassword')?.value;
    if (password != confirmPassword) {
      formGroup.get('confirmPassword')?.setErrors({ passwordMismatch: true });
    } else {
      formGroup.get('confirmPassword')?.setErrors(null);
    }
  }

  submitloginForm() {
    if (this.loginForm) {
      console.log(this.loginForm.value);
      this.service.register(this.loginForm.value).subscribe(
        (response) => {
          if (response.id != null) {
            alert("Hello " + response.name);
          }
        }
      );
    }
  }

}
