import { Component } from '@angular/core';
import {RouterModule, RouterOutlet} from '@angular/router';
import { RegisterComponent } from "./register/register.component";
import {ReactiveFormsModule} from "@angular/forms";


@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [
        RouterOutlet,
        RegisterComponent,
      ReactiveFormsModule,
      RouterModule,
    ]
})
export class AppComponent {

}
