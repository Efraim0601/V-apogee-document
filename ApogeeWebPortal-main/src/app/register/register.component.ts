import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router, RouterModule} from '@angular/router';
import {RegisterService} from "../services/register.service";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterModule,
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit{

  public registerForm: FormGroup  ;

  constructor(
    private service: RegisterService,
    private fb: FormBuilder,
    private route : Router
  ) {

    this.registerForm = this.fb.group({
      firstName : ['', [Validators.required]],
      lastName : ['', [Validators.required]],
      numeroempl : ['', [Validators.required]],
      phoneNumber : ['', [Validators.required]],
      email : ['', [Validators.required, Validators.email]],
      password : ['', [Validators.required]],
      confirmPassword : ['', [Validators.required]],
    }, {validators: this.passwordMathValidator})

    /*this.registerForm = this.fb.group({
      firstName : ['', [Validators.required]],
      lastName : ['', [Validators.required]],
      employeeNumber : ['', [Validators.required, Validators.pattern('[0-9]{5}')]],
      phoneNumber : ['', [Validators.required, Validators.pattern('[0-9]{10}')]],
      email : ['', [Validators.required, Validators.email]],
      password : ['', [Validators.required]],
      confirmPassword : ['', [Validators.required]],
    }, {validators: this.passwordMathValidator})*/
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

  submitForm() {
    if (this.registerForm) {
      console.log(this.registerForm.value);
      this.service.register(this.registerForm.value).subscribe(
        (response) => {
          if (response.id != null) {
            this.route.navigate(['/otp']);
            alert("Hello " + response.name);
          }
        }
      );
    }
  }
}
