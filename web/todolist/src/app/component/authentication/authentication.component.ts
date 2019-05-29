import {Component, OnInit} from '@angular/core';
import {VerifiableAuthenticationUser} from '../../dto/VerifiableAuthenticationUser';
import {AuthenticationService} from '../../service/authentication.service';
import {Router} from '@angular/router';
import {AuthenticationUser} from '../../dto/AuthenticationUser';
import {AppComponent} from '../../app.component';

@Component({
    selector: 'app-authentication',
    templateUrl: './authentication.component.html',
    styleUrls: ['./authentication.component.scss']
})
export class AuthenticationComponent implements OnInit {
    public verifiableAuthenticationUser: VerifiableAuthenticationUser = new VerifiableAuthenticationUser();

    constructor(private authenticationService: AuthenticationService,
                private router: Router,
                private app: AppComponent
    ) {
    }

    ngOnInit() {
    }

    authenticate() {
        this.app.onLoad(() => {
            if (this.verifiableAuthenticationUser.password !== null &&
                this.verifiableAuthenticationUser.login !== null) {
                this.authenticationService.authenticate(
                    new AuthenticationUser(this.verifiableAuthenticationUser.login, this.verifiableAuthenticationUser.password)
                ).subscribe(token => {
                    localStorage.setItem('token', token);
                    this.router.navigate(['tasks']);
                });
            }
        });
    }
}
