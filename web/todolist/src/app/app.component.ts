import {Component} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';
import {AuthenticationService} from './service/authentication.service';
import {TokenProvider} from './provider/token.provider';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
    constructor(
        private router: Router,
        private authenticationService: AuthenticationService,
        private tokenProvider: TokenProvider
    ) {

        this.router.events.subscribe(
            (event: any) => {
                if (event instanceof NavigationEnd) {
                    this.autoLogin(event.url);

                }
            }
        );
    }
//TODO: refactor
    autoLogin(url: string) {
        const lsToken = localStorage.getItem('token');
        if (lsToken) {
            this.authenticationService.validate(lsToken).subscribe(u => {
                this.tokenProvider.setToken(lsToken);
                if (url === '/') {
                    this.router.navigate(['/tasks']);
                } else {
                    this.router.navigate([url]);
                }
            }, e => {
                this.router.navigate(['/auth']);
                // TODO: out token key name in localstorage as const
                localStorage.removeItem('token');
            });
        } else {
            if (url === '/register') {
                this.router.navigate(['/register']);
            } else {
                this.router.navigate(['/auth']);
            }
        }
    }
}
