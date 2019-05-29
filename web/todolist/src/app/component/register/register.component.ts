import {Component, OnInit} from '@angular/core';
import {RegisterService} from '../../service/register.service';
import {VerifiableRegisterUser} from '../../dto/VerifiableRegisterUser';
import {RegisterUser} from '../../dto/RegisterUser';
import {Router} from '@angular/router';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
    public verifiableRegisterUser: VerifiableRegisterUser = new VerifiableRegisterUser();

    constructor(private registerService: RegisterService, private router: Router) {
    }

    register() {
        if (this.verifiableRegisterUser.password === this.verifiableRegisterUser.confirmPassword &&
            this.verifiableRegisterUser.login !== null) {
            this.registerService.register(new RegisterUser(this.verifiableRegisterUser.login, this.verifiableRegisterUser.password))
                .subscribe(success => {
                    console.log('registered successfully!');
                    this.router.navigate(['auth']);
                });

        }
    }

    ngOnInit() {
    }


}
