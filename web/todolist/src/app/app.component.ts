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
    isLoaded: boolean = false;

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

    onLoad(loaded: () => void) {
        setTimeout(() => {
            if (this.isLoaded) {
                console.debug('onload: loaded...');
                loaded();
            } else {
                this.onLoad(loaded);
                console.debug('onload: waiting...');
            }
        }, 10);
    }

    autoLogin(url: string) {
        console.log('autolog');
        const lsToken = localStorage.getItem('token');
        if (lsToken) {
            this.authenticationService.validate(lsToken).subscribe(u => {
                this.tokenProvider.setToken(lsToken);
                this.isLoaded = true;
                if (url === '/') {
                    this.router.navigate(['/tasks']);
                } else {
                    this.router.navigate([url]);
                }
            }, e => {
                localStorage.removeItem('token');
                this.isLoaded = true;
                this.router.navigate(['/auth']);
            });
        } else {
            this.isLoaded = true;
            if (url === '/register') {
                this.router.navigate(['/register']);
            } else {
                this.router.navigate(['/auth']);
            }
        }
    }
}
