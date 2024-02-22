import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { RegisterComponent } from './register/register.component';
import { OtpComponent } from './otp/otp.component';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginComponent,
        title: 'Login'
    },
    {
        path:'register',
        component: RegisterComponent,
        title: 'Registration'
    },
    {
        path:'otp',
        component: OtpComponent,
        title: 'OTP'
    },
    {
        path: '', 
        redirectTo:'/login',
        pathMatch: 'full'
    },
    {
        path: '**',
        component: PageNotFoundComponent,
        title: 'Page 404'
    },
];
