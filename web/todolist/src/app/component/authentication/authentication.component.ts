import {Component, OnInit} from '@angular/core';
import {VerifiableAuthenticationUser} from '../../dto/VerifiableAuthenticationUser';
import {RegisterUser} from '../../dto/RegisterUser';
import {AuthenticationService} from '../../service/authentication.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-authentication',
    templateUrl: './authentication.component.html',
    styleUrls: ['./authentication.component.scss']
})
export class AuthenticationComponent implements OnInit {
    private verifiableAuthenticationUser: VerifiableAuthenticationUser = new VerifiableAuthenticationUser();

    constructor(private authenticationService: AuthenticationService, private router: Router) {
    }

    ngOnInit() {
    }

    authenticate() {
        if (this.verifiableAuthenticationUser.password !== null &&
            this.verifiableAuthenticationUser.login !== null) {
            this.authenticationService.authenticate(
                new RegisterUser(this.verifiableAuthenticationUser.login, this.verifiableAuthenticationUser.password)
            ).subscribe(token => {
                console.log(token);
                localStorage.setItem('token', token);
                this.router.navigate(['tasks']);
            });

        }
    }

}
