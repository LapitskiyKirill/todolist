import {Component, OnInit} from '@angular/core';
import {RegisterUser} from '../../Entities/RegisterUser';
import {RegisterService} from '../../Services/register.service';
import {Router} from '@angular/router';
import {NewUser} from '../../Entities/NewUser';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  public registerUser: NewUser = new NewUser();

  constructor(
    private registerService: RegisterService,
    private router: Router
  ) {
  }

  register() {
    if (this.registerUser.login !== '' && this.registerUser.password === this.registerUser.passwordConfirm) {
      console.log(this.registerUser.login);
      this.registerService.register(new RegisterUser(this.registerUser.login, this.registerUser.password)).subscribe(success => {
        this.router.navigate(['auth']);
      });

    }
  }

  ngOnInit() {
  }

}
