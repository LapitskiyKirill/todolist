import {Component} from '@angular/core';
import {RegisterService} from './service/register.service';
import {NavigationEnd, Router} from '@angular/router';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
    constructor(private router: Router) {
        this.router.events.subscribe(
            (event: any) => {
                if (event instanceof NavigationEnd) {
                    if (event.url === '/') {
                        this.router.navigate(['auth']);
                    }
                }
            }
        );
    }
}
